package hello.world.service;

import javax.inject.Singleton;

import io.micronaut.context.annotation.Primary;

@Primary
@Singleton
public class EnglishGreetingService implements GreetingService {
  @Override
  public String getGreeting() {
    return "Hello ";
  }
}
