package com.example.task

import static com.github.tomakehurst.wiremock.client.WireMock.get as wireMockGet
import static com.github.tomakehurst.wiremock.client.WireMock.okJson
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class UserControllerPositiveSpec extends BaseSpec {

    def 'Should save new user and return proper response'() {
        given:
          def login = "tomasz90"

          def stubbedResponse = readFile("ok_response_github.json")
          def expectedResponse = readFile("ok_response_internal.json")

          wm.stubFor(wireMockGet("$stubbedEndpoint$login").willReturn(okJson(stubbedResponse)))

        when:
          def result = mockMvc.perform(get("$internalEndpoint$login"))

        then:
          result.andExpectAll(status().isOk(), content().json(expectedResponse, true))

        and:
          def user = userRepository.findByLogin(login)
          user != null
          user.login == login
          user.requestCount == 0
    }

    def 'Should increment request_count and return proper response'() {
        given:
          def login = "tomasz90"

          def stubbedResponse = readFile("ok_response_github.json")
          def expectedResponse = readFile("ok_response_internal.json")

          wm.stubFor(wireMockGet("$stubbedEndpoint$login").willReturn(okJson(stubbedResponse)))

          def request = { mockMvc.perform(get("$internalEndpoint$login")) }

        when:
          request()
          def result = request()

        then:
          result.andExpectAll(status().isOk(), content().json(expectedResponse, true))

        and:
          def user = userRepository.findByLogin(login)
          user != null
          user.login == login
          user.requestCount == 1
    }
}
