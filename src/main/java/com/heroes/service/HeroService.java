package com.heroes.service;

import com.heroes.dto.Name;
import com.heroes.exception.HeroAlreadyExists;
import com.heroes.exception.HeroNotFound;
import com.heroes.model.Hero;
import com.heroes.repository.HeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class HeroService {
    @Autowired
    private HeroRepository heroRepository;

    private Hero verifyIfExists(String name) throws HeroNotFound {
        return heroRepository.findById(name).orElseThrow(() -> new HeroNotFound(name));
    }

    private void verifyIfNotExists(String name) throws HeroAlreadyExists {
        if (heroRepository.findById(name).isPresent())
            throw new HeroAlreadyExists(name);
    }

    public Flux<Hero> findAll() {
        return Flux.fromIterable(heroRepository.findAll());
    }

    public Mono<Hero> findByName(String name) {
        Hero hero = verifyIfExists(name);
        return Mono.justOrEmpty(hero);
    }

    public Flux<Hero> findByPower(String power) {
        return Flux.fromIterable(heroRepository.findByPower(power));
    }

    public Mono<Hero> save(Hero hero) {
        verifyIfNotExists(hero.getName());
        return Mono.justOrEmpty(heroRepository.save(hero));
    }

    public Mono<Hero> update(Hero hero) {
        verifyIfExists(hero.getName());
        return Mono.justOrEmpty(heroRepository.save(hero));
    }

    public void delete(Name name) {
        verifyIfExists(name.getName());
        heroRepository.deleteById(name.getName());
    }
}
