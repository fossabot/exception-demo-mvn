package com.pranay.exceptiondemomvn.models.dtos;

import com.pranay.exceptiondemomvn.models.Owner;

public class OwnerDto implements DtoTransformer<Owner> {
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
	public Owner convertToEntity() {
		Owner owner = new Owner();
		owner.setId(this.getId());
		owner.setName(this.getName());
		return owner;
	}
}
