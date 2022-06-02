package com.prueba.titamedia.domain;

import com.prueba.titamedia.domain.enumeration.TypeDebt;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * A Debt.
 */

@Entity
@Table( name = "debt")
@Getter
@Setter
@ToString
public class Debt extends  AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @Column(name = "number")
    private String number;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private TypeDebt type;

    @Column(name = "amount") //pago minimo
    private BigDecimal amount;

    @Column(name = "total")
    private BigDecimal total;

    @Column(name = "dues") //cuota
    private Integer dues;

    @Column(name = "revenue") //interes
    private Float revenue;

    @Column(name = "paid_out") //cupo - pagado
    private BigDecimal paidOut;


    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "bank_id", insertable = false, updatable = false)
    private Bank bank;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Debt)) {
            return false;
        }
        return id != null && id.equals(((Debt) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }
}
