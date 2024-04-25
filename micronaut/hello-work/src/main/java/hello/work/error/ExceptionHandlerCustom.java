package hello.work.error;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import jakarta.inject.Singleton;

@Singleton
public class ExceptionHandlerCustom implements ExceptionHandler<RuntimeException, HttpResponse> {

  // si está activado el global, no viene acá
  // es para una sola excepcion, no se vio la forma de manejar muchas excepciones en un solo handler. Las excepciones que
  // extiendan de RuntimeException van a ser capturadas también acá a menos que tenga un exceptionHandler específico

  @Override
  public HttpResponse handle(HttpRequest request, RuntimeException exception) {
    return HttpResponse.badRequest("Bad request");
  }
}
