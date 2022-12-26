package com.heroes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class HeroNotFound extends RuntimeException {
    public HeroNotFound(String name) {
        super(String.format("The hero %s was not found.", name));
    }
}
