package com.accenture.challenge.sales.dao.domains;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sales")
@Entity
public class Sales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "point_sale_id")
    private Long pointSaleId;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "taxes")
    private BigDecimal taxes;

    @UpdateTimestamp
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Override
    public String toString() {
        return "Sales.id:" + getId();
    }
}