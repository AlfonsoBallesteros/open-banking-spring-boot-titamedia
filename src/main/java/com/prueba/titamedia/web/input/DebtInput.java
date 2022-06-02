package com.prueba.titamedia.web.input;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class DebtInput {

    private BigDecimal amount;

    private UUID numberDebt;
}
