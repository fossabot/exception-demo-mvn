package com.pranay.exceptiondemomvn.controllers;

import com.pranay.exceptiondemomvn.services.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OwnerController {
	@Autowired
	private OwnerService ownerService;

	@GetMapping("/owner/{id}", )
}
