package com.prueba.titamedia.repository;

import com.prueba.titamedia.domain.Debt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DebtRepository extends JpaRepository<Debt, UUID> {

    @Query("SELECT DISTINCT debt.bank.id AS bank_id FROM Debt debt WHERE debt.user.id=:id")
    List<UUID> findAllBankByUserId(@Param("id") UUID id);

    Page<Debt> findAllByUserIdAndBankId(UUID user_id, UUID bank_id, Pageable pageable);

    Optional<Debt> findByIdAndUserId(UUID id, UUID user_id);
}
