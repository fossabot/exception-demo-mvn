package com.pranay.exceptiondemomvn.models;

import com.pranay.exceptiondemomvn.models.dtos.CarDto;

import javax.persistence.*;

@Entity
public class Car extends BaseEntity implements EntityTransformer<CarDto> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String licenseNo;

	@Version
	private Integer version;

	@JoinColumn( name = "owner_id" )
	@ManyToOne
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

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Override
	public CarDto convertToDto() {
		CarDto carDto = new CarDto();
		carDto.setId(this.getId());
		carDto.setLicenseNo(this.getLicenseNo());
		carDto.setVersion(this.getVersion());
		return carDto;
	}
}
