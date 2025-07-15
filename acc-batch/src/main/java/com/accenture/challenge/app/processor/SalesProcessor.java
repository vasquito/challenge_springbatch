package com.accenture.challenge.app.processor;

import com.accenture.challenge.app.rest.SalesRaw;
import com.accenture.challenge.sales.dao.domains.Sales;
import com.accenture.challenge.sales.service.constants.PointOfSaleEnum;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class SalesProcessor implements ItemProcessor<SalesRaw, Sales> {

    @Override
    public Sales process(SalesRaw item) {
        Sales sales = Sales.builder()
                .pointSaleId(item.getPuntoVenta())
                .amount(BigDecimal.valueOf(item.getImporte()))
                .quantity(item.getCantidad())
                .taxes(calculateTax(item.getPuntoVenta(), item.getImporte()))
                .build();
        return sales;
    }

    /**
     * Function that make calculate tax
     * @param pointSale
     * @param amount
     * @return
     */
    private BigDecimal calculateTax(Long pointSale, Long amount) {
        return BigDecimal.valueOf(PointOfSaleEnum.findTax(pointSale) * amount);
    }
}
