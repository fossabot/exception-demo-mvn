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
public class CarsController {

	@Autowired
	private CarService carService;

	@GetMapping(value = "/owner/{ownerId}/cars")
	public List<CarDto> getAllCars(@PathVariable("ownerId") Long ownerId) {
		return carService.getAllCarsByOwnerId(ownerId).stream()
				.map(Car::convertToDto)
				.collect(Collectors.toList());
	}

	@GetMapping(value = "/owner/{ownerId}/car/{carId}", produces = "application/vnd.demo.api.v1+json")
	public ResponseEntity<CarDto> findById(@PathVariable("ownerId") Long ownerId, @PathVariable("carId") Long carId) {
		return ResponseEntity.ok(carService.findByCarIdAndOwnerId(carId, ownerId).convertToDto());
	}

	@PostMapping(value = "/owner/{ownerId}/car", produces = "application/vnd.demo.api.v1+json")
	public ResponseEntity<CarDto> save(@PathVariable("ownerId") Long ownerId, @RequestBody CarDto carDto) throws URISyntaxException {
		Car car = carService.save(ownerId, carDto.convertToEntity());
		return ResponseEntity
				.created(new URI("/car/" + car.getId().toString()))
				.body(car.convertToDto());
	}

	@PutMapping(value = "/owner/{ownerId}/car/{carId}", produces = "application/vnd.demo.api.v1+json")
	@PatchMapping(value = "/owner/{ownerId}/car/{carId}", produces = "application/vnd.demo.api.v1+json")
	public ResponseEntity<CarDto> update(@PathVariable("ownerId") Long ownerId, @PathVariable("carId") Long carId, @Valid @RequestBody CarDto carDto) {
		Car car = carService.update(carId, ownerId, carDto.convertToEntity());
		return ResponseEntity.ok(car.convertToDto());
	}
}
