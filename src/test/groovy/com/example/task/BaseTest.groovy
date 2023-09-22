package com.example.task

import com.example.task.controller.UserController
import com.example.task.repository.UserRepository
import com.github.tomakehurst.wiremock.WireMockServer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import spock.lang.Shared
import spock.lang.Specification

@SpringBootTest(properties = "githubUrl=http://localhost:8080")
@ActiveProfiles("test")
@AutoConfigureMockMvc
class BaseTest extends Specification {

    @Autowired
    UserController userController

    @Autowired
    UserRepository userRepository

    @Shared
    WireMockServer wm

    def setupSpec() {
        wm = new WireMockServer()
        wm.start()
    }

    def cleanup() {
        wm.resetAll()
    }

    def readFile(String fileName) {
        new File("src/test/resources/${fileName}").text
    }
}
