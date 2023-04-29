package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.dto.AnimalRequestDto;
import com.demo.dto.AnimalResponseDto;
import com.demo.dto.PagedResponseDto;
import com.demo.service.AnimalService;

@RestController
@RequestMapping("/animals")
public class AnimalController {

	@Autowired
	private AnimalService animalService;

	@GetMapping("/list")
	public ResponseEntity<List<AnimalResponseDto>> getAll() {
		return ResponseEntity.ok(animalService.getAll());
	}
    @PostMapping	
	public ResponseEntity<Void>saveAnimal(@RequestBody AnimalRequestDto animalRequestDto){
		animalService.saveAnimal(animalRequestDto);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
    @GetMapping("/{animalId}")
    public ResponseEntity<AnimalResponseDto>getById(@PathVariable Long animalId){
		return ResponseEntity.ok(animalService.getById(animalId));
    }
    
    @PutMapping("/{animalId}")
    public ResponseEntity<Void> updateAnimal(@PathVariable Long animalId,@RequestBody AnimalRequestDto animalRequestDto){
    	animalService.updateAnimal(animalId, animalRequestDto);
    	return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
    @DeleteMapping("/{animalId}")
    public ResponseEntity<Void> deleteAnimal(@PathVariable Long animalId){
    	animalService.deleteAnimal(animalId);
    	return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    	
    }
    @GetMapping
    public ResponseEntity<PagedResponseDto<AnimalResponseDto>>getAllAnimal(Pageable pageable){
    	return ResponseEntity.ok(animalService.getAllAnimal(pageable));
    }
    
}
