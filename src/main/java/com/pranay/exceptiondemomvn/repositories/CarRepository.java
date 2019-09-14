package com.pranay.exceptiondemomvn.repositories;

import com.pranay.exceptiondemomvn.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long> {
	List<Car> findByOwnerId(Long ownerId);
	Optional<Car> findByIdAndOwnerId(Long ownerId, Long carId);
}
