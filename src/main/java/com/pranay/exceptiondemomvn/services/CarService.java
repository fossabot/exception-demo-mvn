package com.pranay.exceptiondemomvn.services;

import com.pranay.exceptiondemomvn.models.Car;

import java.util.List;

public interface CarService {
	List<Car> getAllCarsByOwnerId(Long ownerId);
	Car save(Long ownerId, Car car);
	Car findById(Long carId);
	Car findByCarIdAndOwnerId(Long carId, Long ownerId);
	Car update(Long carId, Long ownerId ,Car newCar);
}
