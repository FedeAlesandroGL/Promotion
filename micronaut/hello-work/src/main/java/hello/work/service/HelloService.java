package hello.work.service;

import jakarta.inject.Singleton;

@Singleton
public class HelloService {

  public String greetingsV1(String name){
    return "Hello sir " + name;
  }

  public String greetingsV2(String name){
    return "Hola señor " + name;
  }
}
