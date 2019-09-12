package com.pranay.exceptiondemomvn.models;

import com.pranay.exceptiondemomvn.dtos.CarDto;

import javax.persistence.*;

@Entity
public class Car extends BaseEntity implements EntityTransformer<CarDto> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String licenseNo;

	@JoinColumn( name = "owner_id" )
	@ManyToOne( optional = false )
	private Owner owner;

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

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	@Override
	public CarDto convertToDto() {
		CarDto carDto = new CarDto();
		carDto.setId(this.getId());
		carDto.setLicenseNo(this.getLicenseNo());
		carDto.setOwnerDto(this.getOwner().convertToDto());
		return carDto;
	}
}
