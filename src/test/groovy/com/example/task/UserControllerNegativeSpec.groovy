package com.example.task

import static com.github.tomakehurst.wiremock.client.WireMock.get as wireMockGet
import static com.github.tomakehurst.wiremock.client.WireMock.jsonResponse
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class UserControllerNegativeSpec extends BaseSpec {

    def 'Should return not found when user not found'() {
        given:
          def login = "bob123455"

          def stubbedResponse = readFile("not_found_response_github.json")
          def expectedResponse = readFile("not_found_response_internal.json")

          wm.stubFor(wireMockGet("$stubbedEndpoint$login").willReturn(jsonResponse(stubbedResponse, 404)))

        when:
          def result = mockMvc.perform(get("$internalEndpoint$login"))

        then:
          result.andExpectAll(status().is(404), content().json(expectedResponse, true))

        and:
          def user = userRepository.findByLogin(login)
          user == null
    }

    def 'Should return external service not available when github is down'() {
        given:
          wm.stop()
          def login = "bob123455"

          def expectedResponse = readFile("service_not_available_response_internal.json")
        when:
          def result = mockMvc.perform(get("$internalEndpoint$login"))

        then:
          result.andExpectAll(status().is(500), content().json(expectedResponse, true))

        and:
          def user = userRepository.findByLogin(login)
          user == null

        cleanup:
          wm.start()
    }
}
