package com.pranay.exceptiondemomvn.services.impl;

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
	public List<Car> getAllCars() {
		return carRepository.findAll();
	}

	@Override
	public Car save(Car car) {
		Owner owner = ownerService.save(car.getOwner());
		car.setOwner(owner);
		return carRepository.save(car);
	}

	@Override
	public Car findById(Long carId) {
		Optional<Car> car = carRepository.findById(carId);
		return car.orElse(null);    // TODO: throw exception
	}

	@Override
	public Car update(Car newCar) {
		Car car = findById(newCar.getId());
		car.setLicenseNo(newCar.getLicenseNo());
		if (!car.getOwner().getId().equals(newCar.getOwner().getId())) {
			return null;    // TODO: think what exception to throw owner does not belong to car
		}
		car.setOwner(ownerService.update(newCar.getOwner()));
		return carRepository.save(car);
	}
}
