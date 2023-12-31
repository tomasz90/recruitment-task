package com.example.task

import com.example.task.repository.UserRepository
import com.github.tomakehurst.wiremock.WireMockServer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Shared
import spock.lang.Specification

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options

@SpringBootTest(properties = "githubUrl=http://localhost:8090")
@ActiveProfiles("test")
@AutoConfigureMockMvc
class BaseSpec extends Specification {

    @Autowired
    UserRepository userRepository

    @Shared
    WireMockServer wm

    @Autowired
    MockMvc mockMvc

    final stubbedEndpoint = "/users/"
    final internalEndpoint = "/users/"

    def setupSpec() {
        wm = new WireMockServer(options().port(8090)) //port must match to override value in annotation
        wm.start()
    }

    def cleanupSpec() {
        wm.stop()
    }

    def cleanup() {
        userRepository.clean()
        wm.resetAll()
    }

    def readFile(String fileName) {
        new File("src/test/resources/${fileName}").text
    }
}
