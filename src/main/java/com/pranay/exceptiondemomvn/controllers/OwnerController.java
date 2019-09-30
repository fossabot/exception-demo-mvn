package com.pranay.exceptiondemomvn.controllers;

import com.pranay.exceptiondemomvn.models.Owner;
import com.pranay.exceptiondemomvn.models.dtos.OwnerDto;
import com.pranay.exceptiondemomvn.services.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class OwnerController {
	@Autowired
	private OwnerService ownerService;

	@GetMapping(value = "/owners", produces = "application/vnd.demo.api.v1+json")
	public ResponseEntity<List<OwnerDto>> findAll() {
		return ResponseEntity.ok(ownerService.findAll().stream().map(Owner::convertToDto).collect(Collectors.toList()));
	}

	@GetMapping(value = "/owner/{ownerId}", produces = "application/vnd.demo.api.v1+json")
	public ResponseEntity<OwnerDto> findById(@PathVariable("ownerId") Long ownerId) {
		return ResponseEntity.ok(ownerService.findById(ownerId).convertToDto());
	}

	@PostMapping(value = "/owner", produces = "application/vnd.demo.api.v1+json")
	public ResponseEntity<OwnerDto> save(@RequestBody OwnerDto ownerDto) throws URISyntaxException {
		Owner owner = ownerService.save(ownerDto.convertToEntity());
		return ResponseEntity
				.created(new URI("/owner/" + owner.getId().toString()))
				.body(owner.convertToDto());
	}

	@PutMapping(value = "/owner/{ownerId}", produces = "application/vnd.demo.api.v1+json")
	@PatchMapping(value = "/owner/{ownerId}", produces = "application/vnd.demo.api.v1+json")
	public ResponseEntity<OwnerDto> update(@PathVariable("ownerId") Long ownerId, @RequestBody OwnerDto ownerDto) {
		return ResponseEntity.ok(ownerService.update(ownerId, ownerDto.convertToEntity()).convertToDto());
	}
}
