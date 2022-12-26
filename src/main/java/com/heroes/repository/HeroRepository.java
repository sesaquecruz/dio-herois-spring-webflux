package com.heroes.repository;

import com.heroes.model.Hero;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableScan
public interface HeroRepository extends CrudRepository<Hero, String> {
    List<Hero> findByPower(String power);
}
