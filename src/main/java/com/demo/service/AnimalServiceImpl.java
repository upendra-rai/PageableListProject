package com.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.demo.dao.AnimalDAO;
import com.demo.dto.AnimalRequestDto;
import com.demo.dto.AnimalResponseDto;
import com.demo.dto.PagedResponseDto;
import com.demo.entity.Animal;

@Service
public class AnimalServiceImpl implements AnimalService {
	@Autowired
	private AnimalDAO animalDAO;

	@Override
	public List<AnimalResponseDto> getAll() {
		return animalDAO.findAll().stream().map(this::convertEntityToDto).collect(Collectors.toList());
	}

	private AnimalResponseDto convertEntityToDto(Animal animal) {
		AnimalResponseDto animalResponseDto = new AnimalResponseDto();
		animalResponseDto.setId(animal.getId());
		animalResponseDto.setName(animal.getName());
		animalResponseDto.setType(animal.getType());
		return animalResponseDto;

	}

	@Override
	public void saveAnimal(AnimalRequestDto animalRequestDto) {
		animalDAO.save(maptoEntity(animalRequestDto));

	}

	private Animal maptoEntity(AnimalRequestDto animalRequestDto) {
		Animal animal = new Animal();
		animal.setName(animalRequestDto.getName());
		animal.setType(animalRequestDto.getType());
		return animal;
	}

	@Override
	public AnimalResponseDto getById(Long animalId) {
		Animal animal=animalDAO.findById(animalId).orElseThrow(()->new RuntimeException("Animal Id Not Found"));
		return convertEntityToDto(animal);
	}

	@Override
	public void updateAnimal(Long animalId, AnimalRequestDto animalRequestDto) {
		Animal animal=animalDAO.findById(animalId).orElseThrow(()->new RuntimeException("Animal Id Not Found"));
		animal.setName(animalRequestDto.getName());
		animal.setType(animalRequestDto.getType());
		animalDAO.save(animal);
		
	}

	@Override
	public void deleteAnimal(Long animalId) {
		Animal animal= animalDAO.findById(animalId).orElseThrow(()->new RuntimeException("Animal Id Not Found"));
		animalDAO.delete(animal);
		
	}

	@Override
	public PagedResponseDto<AnimalResponseDto> getAllAnimal(Pageable pageable) {
	 Page<Animal>animalPage=animalDAO.findAll(pageable);
	 var pageDto=new PagedResponseDto<AnimalResponseDto>();
	 pageDto.setPage(animalPage.getNumber());
	 pageDto.setPageSize(animalPage.getSize());
	 pageDto.setTotalCount(animalPage.getTotalElements());
	 pageDto.setList(animalPage.getContent().stream().map(this::convertEntityToDto).collect(Collectors.toList()));
		return pageDto;
	}

}
