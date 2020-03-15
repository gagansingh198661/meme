package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.Meme;
import com.example.demo.repository.DbRepository;

@Service
public class DBStorageService {
	
	@Autowired
	private DbRepository repository;
	
	public Meme getMeme() {
		return repository.findAll().get(0);
	}
	
	public void delete() {
		repository.deleteAll();
	}
}
