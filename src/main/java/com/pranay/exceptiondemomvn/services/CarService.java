package com.pranay.exceptiondemomvn.services;

import com.pranay.exceptiondemomvn.models.Car;

import java.util.List;
import java.util.Optional;

public interface CarService {
	List<Car> getAllCars();
	Car save(Car car);
	Car findById(Long carId);
	Car update(Car newCar);
}
