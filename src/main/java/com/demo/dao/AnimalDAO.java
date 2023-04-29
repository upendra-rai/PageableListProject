package com.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.entity.Animal;
@Repository
public interface AnimalDAO  extends JpaRepository<Animal, Long>{

}
