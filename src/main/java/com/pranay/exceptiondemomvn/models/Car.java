package com.pranay.exceptiondemomvn.models;

import com.pranay.exceptiondemomvn.models.dtos.CarDto;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Car extends BaseEntity implements EntityTransformer<CarDto> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String licenseNo;

	@Version
	private Integer version;

	@JoinColumn( name = "owner_id" )
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
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
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Car)) return false;
		Car car = (Car) o;
		return getId().equals(car.getId()) &&
				getLicenseNo().equals(car.getLicenseNo());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getLicenseNo());
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
