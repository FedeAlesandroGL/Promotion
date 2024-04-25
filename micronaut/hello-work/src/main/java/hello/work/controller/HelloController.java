package hello.work.controller;

import com.fasterxml.jackson.core.JsonParseException;
import hello.work.service.HelloService;
import io.micronaut.core.version.annotation.Version;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Error;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.hateoas.JsonError;
import io.micronaut.http.hateoas.Link;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import javax.validation.constraints.NotBlank;

@Controller("/")
public class HelloController {

  private final HelloService helloService;

  public HelloController(final HelloService helloService){
    this.helloService = helloService;
  }

  @Version("1")
  @Get(value = "/hello/{name}", produces = MediaType.TEXT_PLAIN)
  @Operation(summary = "Greets a person",
            description = "A friendly greeting is returned"
  )
  @ApiResponse(
      content = @Content(mediaType = "text/plain", schema = @Schema(type = "string"))
  )
  @ApiResponse(responseCode = "200", description = "Greeting")
  @Tag(name = "greetings")
  public String greetingsV1(@Parameter(description = "The name of the person") @NotBlank String name) {
    return this.helloService.greetingsV1(name);
  }

  @Version("2")
  @Get(value = "/hello/{name}", produces = MediaType.TEXT_PLAIN)
  @Operation(summary = "Greets a person",
      description = "A friendly greeting is returned"
  )
  @ApiResponse(
      content = @Content(mediaType = "text/plain", schema = @Schema(type = "string"))
  )
  @ApiResponse(responseCode = "200", description = "Greeting")
  @Tag(name = "greetings")
  public String greetingsV2(@Parameter(description = "The name of the person") @NotBlank String name) {
    return this.helloService.greetingsV2(name);
  }

  @Version("1")
  @Get(value = "error")
  public String jsonError() throws JsonParseException {
    throw new JsonParseException(null, "Error Json");
  }

  @Version("2")
  @Get(value = "error")
  public HttpResponse serverError() {
    return HttpResponse.serverError();
  }

  @Version("3")
  @Get(value = "error")
  public String runtimeError() {
    throw new RuntimeException("Error");
  }

  @Error // local, maneja una exception
  public HttpResponse<JsonError> jsonError(HttpRequest request, JsonParseException jsonParseException) {
    JsonError error = new JsonError("Invalid JSON: " + jsonParseException.getMessage()).link(Link.SELF, Link.of(request.getUri()));

    return HttpResponse.status(HttpStatus.BAD_REQUEST, "Fix your json").body(error);
  }

  @Error(status = HttpStatus.INTERNAL_SERVER_ERROR)  // local, maneja un httpstatus
  public HttpResponse<JsonError> notFound(HttpRequest request) {
    JsonError error = new JsonError("Person Not Found").link(Link.SELF, Link.of(request.getUri()));

    return HttpResponse.notFound().body(error);
  }

  @Error(global = true) // global, captura todos los errores que no sean los que están arriba específicos
  public HttpResponse<JsonError> error(HttpRequest request) {
    JsonError error = new JsonError("Bad Things Happened: ").link(Link.SELF, Link.of(request.getUri()));

    return HttpResponse.notFound().body(error);
  }
}

