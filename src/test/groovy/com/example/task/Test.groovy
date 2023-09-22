package com.example.task

import com.example.task.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

@SpringBootTest
@ActiveProfiles("test")
class Test extends Specification {

    @Autowired
    UserRepository userRepository

    def 'Should pass'() {
        expect:
          true
          userRepository != null
    }
}
