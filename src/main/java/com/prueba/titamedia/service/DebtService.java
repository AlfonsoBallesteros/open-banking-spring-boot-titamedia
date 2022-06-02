package com.prueba.titamedia.service;

import com.prueba.titamedia.domain.Debt;
import com.prueba.titamedia.repository.DebtRepository;
import com.prueba.titamedia.service.dto.DebtDTO;
import com.prueba.titamedia.service.mapper.DebtMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class DebtService {

    private final Logger log = LoggerFactory.getLogger(DebtService.class);

    private final DebtRepository debtRepository;

    private final DebtMapper debtMapper;

    /**
     * Get all the products.
     *
     * @param
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<UUID> findAll(UUID userId) {
        log.debug("Request to get all debt");
        return debtRepository.findAllBankByUserId(userId);
    }

    @Transactional(readOnly = true)
    public Page<DebtDTO> findOne(UUID userId, UUID bank, Pageable pageable) {
        log.debug("Request to get all debt");
        return debtRepository.findAllByUserIdAndBankId(userId, bank, pageable).map(debtMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Optional<DebtDTO> findById(UUID userId, UUID debtId) {
        log.debug("Request to get all debt");
        return debtRepository.findByIdAndUserId(debtId, userId).map(debtMapper::toDto);
    }

    /**
     * Save a debt.
     *
     * @param debtDTO the entity to save.
     * @return the persisted entity.
     */
    public DebtDTO save(DebtDTO debtDTO) {
        log.debug("Request to save debt : {}", debtDTO);
        Debt debt = debtMapper.toEntity(debtDTO);
        debt = debtRepository.save(debt);
        return debtMapper.toDto(debt);
    }

}
