package com.heroes.controller;

import com.heroes.dto.Name;
import com.heroes.model.Hero;
import com.heroes.service.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/")
public class HeroController {
    @Autowired
    private HeroService heroService;

    @GetMapping
    public ResponseEntity<Flux<Hero>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(heroService.findAll());
    }

    @GetMapping("name/{name}")
    public ResponseEntity<Mono<Hero>> findByName(@PathVariable String name) {
        return ResponseEntity.status(HttpStatus.OK).body(heroService.findByName(name));
    }

    @GetMapping("power/{power}")
    public ResponseEntity<Flux<Hero>> findByPower(@PathVariable String power) {
        return ResponseEntity.status(HttpStatus.OK).body(heroService.findByPower(power));
    }

    @PostMapping
    public ResponseEntity<Mono<Hero>> create(@RequestBody Hero hero) {
        return ResponseEntity.status(HttpStatus.CREATED).body(heroService.save(hero));
    }

    @PutMapping
    public ResponseEntity<Mono<Hero>> update(@RequestBody Hero hero) {
        return ResponseEntity.status(HttpStatus.OK).body(heroService.update(hero));
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestBody Name name) {
        heroService.delete(name);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
