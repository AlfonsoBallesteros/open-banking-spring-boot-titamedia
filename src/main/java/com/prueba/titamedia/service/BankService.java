package com.prueba.titamedia.service;

import com.prueba.titamedia.repository.BankRepository;
import com.prueba.titamedia.service.dto.BankDTO;
import com.prueba.titamedia.service.mapper.BankMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class BankService {

    private final Logger log = LoggerFactory.getLogger(BankService.class);

    private final BankRepository bankRepository;

    private final BankMapper bankMapper;

    /**
     * Get all the products.
     *
     * @param
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<BankDTO> findAll(List<UUID> bankIds) {
        log.debug("Request to get all banks {}", bankIds);
        return bankRepository.findAllById(bankIds).stream()
                .map(bankMapper::toDto)
                .collect(Collectors.toList());
    }

}
