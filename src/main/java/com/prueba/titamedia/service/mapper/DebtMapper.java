package com.prueba.titamedia.service.mapper;

import com.prueba.titamedia.domain.Debt;
import com.prueba.titamedia.service.dto.DebtDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring", uses = {BankMapper.class, UserMapper.class})
public interface DebtMapper extends EntityMapper<DebtDTO, Debt> {

    DebtDTO toDto(Debt debt);

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "bank", ignore = true)
    Debt toEntity(DebtDTO debtDTO);

    default Debt fromId(UUID id) {
        if (id == null) {
            return null;
        }
        Debt debt = new Debt();
        debt.setId(id);
        return debt;
    }
}
