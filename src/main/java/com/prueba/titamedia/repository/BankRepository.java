package com.prueba.titamedia.repository;

import com.prueba.titamedia.domain.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BankRepository extends JpaRepository<Bank, UUID> {

}
