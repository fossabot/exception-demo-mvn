package com.pranay.exceptiondemomvn.services.impl;

import com.pranay.exceptiondemomvn.exception.core.EntityNotFoundException;
import com.pranay.exceptiondemomvn.exception.core.IllegalEntityAccessException;
import com.pranay.exceptiondemomvn.models.Car;
import com.pranay.exceptiondemomvn.models.Owner;
import com.pranay.exceptiondemomvn.repositories.CarRepository;
import com.pranay.exceptiondemomvn.services.CarService;
import com.pranay.exceptiondemomvn.services.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {

	@Autowired
	private CarRepository carRepository;

	@Autowired
	private OwnerService ownerService;

	@Override
	public List<Car> getAllCarsByOwnerId(Long ownerId) {
		return carRepository.findByOwnerId(ownerId);
	}

	@Override
	public Car save(Long ownerId, Car car) {
		Owner owner = ownerService.findById(ownerId);
		car.setOwner(owner);
		return carRepository.save(car);
	}

	@Override
	public Car findById(Long carId) {
		Optional<Car> car = carRepository.findById(carId);
		return car.orElseThrow(() -> new EntityNotFoundException(new Object[] {Car.class.toString(), carId}));
	}

	@Override
	public Car findByCarIdAndOwnerId(Long carId, Long ownerId) {
		Optional<Car> car = carRepository.findByIdAndOwnerId(carId, ownerId);
		return car.orElseThrow(() -> new EntityNotFoundException(new Object[] {Car.class.toString(), carId}));
	}

	@Override
	public Car update(Long carId, Long ownerId, Car newCar) {
		if (!carId.equals(newCar.getId())) {
			throw new IllegalEntityAccessException(new Object[] {carId, "Request.PathVariable", newCar.getId(), "Request.Body"});
		}
		Car car = findByCarIdAndOwnerId(carId, ownerId);
		car.setLicenseNo(newCar.getLicenseNo());
		return carRepository.save(car);
	}
}
