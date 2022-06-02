package com.prueba.titamedia.web.response;

import com.prueba.titamedia.domain.enumeration.TypeDebt;
import com.prueba.titamedia.service.dto.DebtDTO;
import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DebtResponse {

    private UUID id;

    private String number;

    private TypeDebt type;

    public DebtResponse(DebtDTO debtDTO){
        this.id = debtDTO.getId();
        this.number = debtDTO.getNumber();
        this.type = debtDTO.getType();
    }
}
