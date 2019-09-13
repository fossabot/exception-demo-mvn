package com.pranay.exceptiondemomvn.models;

import com.pranay.exceptiondemomvn.models.dtos.OwnerDto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Owner extends BaseEntity implements EntityTransformer<OwnerDto> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;

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

	@Override
	public OwnerDto convertToDto() {
		OwnerDto ownerDto = new OwnerDto();
		ownerDto.setId(this.getId());
		ownerDto.setName(this.getName());
		return ownerDto;
	}
}
