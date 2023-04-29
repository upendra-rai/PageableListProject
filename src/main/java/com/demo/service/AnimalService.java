package com.demo.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.demo.dto.AnimalRequestDto;
import com.demo.dto.AnimalResponseDto;
import com.demo.dto.PagedResponseDto;

public interface AnimalService {
	
	public List<AnimalResponseDto>getAll();
	public void saveAnimal(AnimalRequestDto animalRequestDto);
	public AnimalResponseDto getById(Long animalId);
	public void updateAnimal(Long animalId, AnimalRequestDto animalRequestDto);
	public void deleteAnimal(Long animalId);
	public PagedResponseDto<AnimalResponseDto>getAllAnimal(Pageable pageable);

}
