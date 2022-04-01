package hello.world.controller;

import javax.inject.Inject;

import hello.world.service.GreetingService;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;

@Controller("/greet")
public class GreetController {

    @Inject
    private GreetingService greetingService;

    @Get("/{name}")
    public String greet(String name) {
        return greetingService.getGreeting() + name;
    }

    @Post(value = "/{name}", consumes = MediaType.TEXT_PLAIN)
    public String setGreeting(@Body String name) {
        return greetingService.getGreeting() + name;
    }
}