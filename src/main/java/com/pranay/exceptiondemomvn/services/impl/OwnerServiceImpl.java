package com.pranay.exceptiondemomvn.services.impl;

import com.pranay.exceptiondemomvn.exception.core.EntityNotFoundException;
import com.pranay.exceptiondemomvn.exception.core.IllegalEntityAccessException;
import com.pranay.exceptiondemomvn.models.Owner;
import com.pranay.exceptiondemomvn.repositories.OwnerRepository;
import com.pranay.exceptiondemomvn.services.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OwnerServiceImpl implements OwnerService {

	@Autowired
	OwnerRepository ownerRepository;

	@Override
	public List<Owner> findAll() {
		return ownerRepository.findAll();
	}

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
	public Owner update(Long ownerId, Owner newOwner) {
		if (!ownerId.equals(newOwner.getId())) {
			throw new IllegalEntityAccessException(new Object[] {ownerId, "Request.PathVariable", newOwner.getId(), "Request.Body"});
		}
		Owner owner = findById(ownerId);
		owner.setName(newOwner.getName());
		return ownerRepository.save(owner);
	}
}
