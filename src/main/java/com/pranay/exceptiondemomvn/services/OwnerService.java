package com.pranay.exceptiondemomvn.services;

import com.pranay.exceptiondemomvn.models.Owner;

import java.util.List;

public interface OwnerService {
	List<Owner> findAll();
	Owner save(Owner owner);
	Owner findById(Long ownerId);
	Owner update(Long ownerId, Owner newOwner);
}
