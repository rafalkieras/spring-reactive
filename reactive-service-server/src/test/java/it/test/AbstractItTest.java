package it.test;

import com.codewise.hackday.application.App;
import it.mock.DbMock;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = App.class)
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class})
@ExtendWith({SpringExtension.class, DbMock.class})
abstract class AbstractItTest {

    @LocalServerPort
    int port;

    @Autowired
    protected WebTestClient webTestClient;
}
