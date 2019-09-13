package com.pranay.exceptiondemomvn.controllers;

import com.pranay.exceptiondemomvn.models.dtos.CarDto;
import com.pranay.exceptiondemomvn.models.Car;
import com.pranay.exceptiondemomvn.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
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

	@GetMapping(value = "/car/{id}")
	public ResponseEntity<CarDto> findById(@PathVariable("id") Long id) {
		return ResponseEntity.ok(carService.findById(id).convertToDto());
	}

	@PostMapping(value = "/car")
	public ResponseEntity<CarDto> save(@RequestBody CarDto carDto) throws URISyntaxException {
		Car car = carService.save(carDto.convertToEntity());
		return ResponseEntity
				.created(new URI("/v1/car/" + car.getId().toString()))
				.body(car.convertToDto());
	}

	@PutMapping(value = "/car")
	public ResponseEntity<CarDto> update(@Valid @RequestBody CarDto carDto) {
		return ResponseEntity.ok(carService.update(carDto.convertToEntity()).convertToDto());
	}
}
