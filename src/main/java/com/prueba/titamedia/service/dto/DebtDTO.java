package com.prueba.titamedia.service.dto;

import com.prueba.titamedia.domain.enumeration.TypeDebt;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@ToString
public class DebtDTO extends AbstractAuditingDTO implements Serializable {
    private UUID id;

    private String number;

    private TypeDebt type;

    private BigDecimal amount;

    private BigDecimal total;

    private Integer dues;

    private Float revenue;

    private BigDecimal paidOut;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DebtDTO)) {
            return false;
        }

        DebtDTO searchDTO = (DebtDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, searchDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }
}
