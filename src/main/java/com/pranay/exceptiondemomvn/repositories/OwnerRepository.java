package com.pranay.exceptiondemomvn.repositories;

import com.pranay.exceptiondemomvn.models.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
}
