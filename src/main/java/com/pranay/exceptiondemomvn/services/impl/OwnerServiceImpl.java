package com.pranay.exceptiondemomvn.services.impl;

import com.pranay.exceptiondemomvn.exception.core.EntityNotFoundException;
import com.pranay.exceptiondemomvn.models.Owner;
import com.pranay.exceptiondemomvn.repositories.OwnerRepository;
import com.pranay.exceptiondemomvn.services.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OwnerServiceImpl implements OwnerService {

	@Autowired
	OwnerRepository ownerRepository;

	@Override
	public Owner save(Owner owner) {
		return ownerRepository.save(owner);
	}

	@Override
	public Owner findById(Long ownerId) {
		Optional<Owner> owner = ownerRepository.findById(ownerId);
		return owner.orElseThrow(() -> new EntityNotFoundException(new Object[] {Owner.class.toString(), ownerId}));
	}

	@Override
	public Owner update(Owner newOwner) {
		Owner owner = findById(newOwner.getId());
		owner.setName(newOwner.getName());
		return ownerRepository.save(owner);
	}
}
