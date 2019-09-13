package com.pranay.exceptiondemomvn.controllers;

import com.pranay.exceptiondemomvn.models.dtos.CarDto;
import com.pranay.exceptiondemomvn.models.Car;
import com.pranay.exceptiondemomvn.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("v1")
public class CarsController {

	@Autowired
	private CarService carService;

	@GetMapping(value = "/cars")
	public List<CarDto> getAllCars() {
		return carService.getAllCars().stream()
				.map(Car::convertToDto)
				.collect(Collectors.toList());
	}

	@GetMapping(value= "/car/{id}")
	public ResponseEntity<CarDto> findById(@PathVariable("id") Long id) {
		return ResponseEntity.ok(carService.findById(id).convertToDto());
	}

	@PostMapping(value = "/car")
	public ResponseEntity<CarDto> save(@RequestBody CarDto carDto) {
		return ResponseEntity.ok(carService.save(carDto.convertToEntity()).convertToDto());
	}

	@PutMapping(value = "/car")
	public ResponseEntity<CarDto> update(@Valid @RequestBody CarDto carDto) {
		return ResponseEntity.ok(carService.update(carDto.convertToEntity()).convertToDto());
	}
}
