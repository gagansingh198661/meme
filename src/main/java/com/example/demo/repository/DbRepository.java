package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.dtos.Meme;

@Repository
public interface DbRepository extends JpaRepository<Meme, String>{

}
