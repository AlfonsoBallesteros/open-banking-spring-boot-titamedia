package com.prueba.titamedia.service.mapper;

import com.prueba.titamedia.domain.Bank;
import com.prueba.titamedia.service.dto.BankDTO;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(componentModel = "spring", uses = {})
public interface BankMapper extends EntityMapper<BankDTO, Bank> {

    BankDTO toDto(Bank bank);

    Bank toEntity(BankDTO bankDTO);

    default Bank fromId(UUID id) {
        if (id == null) {
            return null;
        }
        Bank bank = new Bank();
        bank.setId(id);
        return bank;
    }
}
