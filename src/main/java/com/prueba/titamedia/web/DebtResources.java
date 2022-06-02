package com.prueba.titamedia.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.prueba.titamedia.service.BankService;
import com.prueba.titamedia.service.DebtService;
import com.prueba.titamedia.service.dto.BankDTO;
import com.prueba.titamedia.service.dto.DebtDTO;
import com.prueba.titamedia.web.input.DebtInput;
import com.prueba.titamedia.web.response.DebtResponse;
import com.prueba.titamedia.web.utils.HeaderUtils;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * REST controller for managing {@link com.prueba.titamedia.domain.Debt}.
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DebtResources {

    private final Logger log = LoggerFactory.getLogger(DebtResources.class);

    private static final String ENTITY_NAME = "debt";

    private static final String applicationName = "pruebaTitaMedia";

    private final DebtService debtService;

    private final BankService bankService;


    @PostMapping("/pay-debt")
    public ResponseEntity<Object> createPost(@RequestHeader(value = "user-id", required = false) final UUID id,
                                                 @RequestBody DebtInput debtInput) throws URISyntaxException, JsonProcessingException {
        log.debug("REST request to save Post : {}", debtInput);

        return debtService.findById(id, debtInput.getNumberDebt())
                .map(d -> {
                    if(d.getAmount().compareTo(debtInput.getAmount()) > 0){
                        return ResponseEntity.status(HttpStatus.CONFLICT).body("no puede ser menor a la cuota minima");
                    }
                    BigDecimal discount = d.getAmount().multiply(BigDecimal.valueOf(d.getRevenue())).divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP);
                    d.setTotal(d.getTotal().subtract(debtInput.getAmount().subtract(discount)));
                    d.setAmount(d.getTotal().divide(BigDecimal.valueOf(d.getDues()), RoundingMode.HALF_UP));
                    d.setPaidOut(d.getPaidOut().add(debtInput.getAmount().subtract(discount)));
                    d.setDues(d.getDues()-1);
                    return debtService.save(d);
                })
                .map(r -> {
                    return ResponseEntity.created(URI.create("/api/pay-debt/" + ""))
                            .headers(HeaderUtils.createEntityCreationAlert(applicationName, true, ENTITY_NAME, ""))
                            .body(r);
                })
                .orElseGet(() -> ResponseEntity.noContent().build());
    }
    @GetMapping("/bank")
    public ResponseEntity<List<BankDTO>> getAllBanks(@RequestHeader(value = "user-id", required = false) final UUID id) {
        List<BankDTO> banks = bankService.findAll(debtService.findAll(id));
        return ResponseEntity.ok().body(banks);
    }

    @GetMapping("/bank/{bank-id}")
    public ResponseEntity<List<DebtResponse>> getAllDebt(
            @RequestHeader(value = "user-id", required = false)final UUID userId,
            @PathVariable(value = "bank-id", required = false) final UUID bankId,
            @PageableDefault(size = 10) Pageable pageable) {
        Page<DebtResponse> debt = debtService.findOne(userId, bankId, pageable).map(DebtResponse::new);
        return ResponseEntity.ok().body(debt.getContent());
    }

    @GetMapping("/debt/{debt-id}")
    public ResponseEntity<DebtDTO> getAllOneDebt(
            @RequestHeader(value = "user-id", required = false) final UUID userId,
            @PathVariable(value = "debt-id", required = false) final UUID debtId) {
       return debtService.findById(userId, debtId).map(ResponseEntity::ok)
               .orElseGet(() -> ResponseEntity.noContent().build());
    }
}
