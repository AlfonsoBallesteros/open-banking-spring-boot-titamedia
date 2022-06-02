package com.prueba.titamedia.service.dto;


import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BankDTO extends AbstractAuditingDTO implements Serializable {

    private UUID id;

    private String name;

    private String code;

    private String address;

    private String country;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BankDTO)) {
            return false;
        }

        BankDTO productDTO = (BankDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, productDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

}
