package hello.work;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
class HelloControllerTest {

  @Inject
  @Client("/") HttpClient client;

  @Test
  void testHelloWorkResponseV1() {
    String response = client.toBlocking().retrieve(HttpRequest.GET("/hello/federico"));
    assertEquals("Hello sir federico", response);
  }

  @Test
  void testHelloWorkResponseV2() {
    String response = client.toBlocking().retrieve(HttpRequest.GET("/hello/federico").header("Accept-Version", "2"));
    assertEquals("Hola señor federico", response);
  }

}
