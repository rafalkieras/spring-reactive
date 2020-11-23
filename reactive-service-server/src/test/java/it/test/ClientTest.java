package it.test;

import com.codewise.hackday.client.AddressResource;
import com.codewise.hackday.client.ClientResource;
import com.codewise.hackday.client.ClientUpdateResource;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import reactor.core.publisher.Mono;

import static org.assertj.core.api.Assertions.assertThat;

public class ClientTest extends AbstractItTest {

    @Test
    public void shaveSaveAndGetClient() {
        // given
        ClientUpdateResource updateResource = ClientUpdateResource.builder()
                .withName("test client")
                .withAddress(
                        AddressResource.builder()
                                .withZipCode("30-733")
                                .withCountry("Poland")
                                .withCity("Kraków")
                                .build()
                )
                .build();

        // when
        ClientResource response = webTestClient
                .post().uri("/client")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(updateResource), ClientUpdateResource.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(ClientResource.class)
                .returnResult()
                .getResponseBody();

        assertThat(response.id()).isNotNull();
        assertThat(response.name()).isEqualTo(updateResource.name());
        assertThat(response.address()).isEqualTo(updateResource.address());
    }
}
