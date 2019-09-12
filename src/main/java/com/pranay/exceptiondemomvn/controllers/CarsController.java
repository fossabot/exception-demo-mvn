package com.pranay.exceptiondemomvn.controllers;

import com.pranay.exceptiondemomvn.dtos.CarDto;
import com.pranay.exceptiondemomvn.models.Car;
import com.pranay.exceptiondemomvn.models.Owner;
import com.pranay.exceptiondemomvn.services.CarService;
import com.pranay.exceptiondemomvn.services.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1")
public class CarsController {

	@Autowired
	private CarService carService;

	@GetMapping(value = "/cars")
	public List<CarDto> getAllCars() {
		return carService.getAllCars().stream()
				.map(Car::convertToDto)
				.collect(Collectors.toList());
	}

	@PostMapping(value = "/car")
	public ResponseEntity<CarDto> save(@RequestBody CarDto carDto) {
		return ResponseEntity.ok(carService.save(carDto.convertToEntity()).convertToDto());
	}

	@PutMapping(value = "/car")
	public ResponseEntity<CarDto> update(@RequestBody CarDto carDto) {
		return ResponseEntity.ok(carService.update(carDto.convertToEntity()).convertToDto());
	}
}
