package com.pranay.exceptiondemomvn.dtos;

import com.pranay.exceptiondemomvn.models.Car;

public class CarDto implements DtoTransformer<Car> {
	private Long id;
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
