package com.heroes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class HeroAlreadyExists extends RuntimeException {
    public HeroAlreadyExists(String name) {
        super(String.format("The hero %s already exists.", name));
    }
}
