package com.pranay.exceptiondemomvn.models.dtos;

import com.pranay.exceptiondemomvn.models.Car;

import javax.validation.constraints.NotEmpty;

public class CarDto implements DtoTransformer<Car> {
	private Long id;
	@NotEmpty(message = "Car.licenseNo must not be null")
	private String licenseNo;
	private OwnerDto ownerDto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLicenseNo() {
		return licenseNo;
	}

	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}

	public OwnerDto getOwnerDto() {
		return ownerDto;
	}

	public void setOwnerDto(OwnerDto ownerDto) {
		this.ownerDto = ownerDto;
	}

	@Override
	public Car convertToEntity() {
		Car car = new Car();
		car.setId(this.getId());
		car.setLicenseNo(this.getLicenseNo());
		car.setOwner(this.getOwnerDto().convertToEntity());
		return car;
	}
}
