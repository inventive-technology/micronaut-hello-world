package hello.world.controller;

import javax.inject.Inject;

import hello.world.service.GreetingService;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.reactivex.Single;

@Controller("/async/greet")
public class AsyncGreetController {

    @Inject
    private GreetingService greetingService;

    @Get("/{name}")
    public Single<String> greet(String name) {
        return Single.just(greetingService.getGreeting() + name);
    }
}