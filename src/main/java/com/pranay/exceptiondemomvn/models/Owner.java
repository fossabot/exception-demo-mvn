package com.pranay.exceptiondemomvn.models;

import com.pranay.exceptiondemomvn.models.dtos.OwnerDto;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Owner extends BaseEntity implements EntityTransformer<OwnerDto> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;

	@OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
	private Set<Car> cars;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Car> getCars() {
		return cars;
	}

	public void setCars(Set<Car> cars) {
		this.cars = cars;
	}

	@Override
	public OwnerDto convertToDto() {
		OwnerDto ownerDto = new OwnerDto();
		ownerDto.setId(this.getId());
		ownerDto.setName(this.getName());
		if (this.getCars() != null) ownerDto.setCars(this.getCars().stream().map(Car::convertToDto).collect(Collectors.toSet()));
		return ownerDto;
	}
}
