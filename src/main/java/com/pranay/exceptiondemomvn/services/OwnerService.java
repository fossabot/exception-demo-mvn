package com.pranay.exceptiondemomvn.services;

import com.pranay.exceptiondemomvn.models.Owner;

public interface OwnerService {
	Owner save(Owner owner);
	Owner findById(Long ownerId);
	Owner update(Long ownerId, Owner newOwner);
}
