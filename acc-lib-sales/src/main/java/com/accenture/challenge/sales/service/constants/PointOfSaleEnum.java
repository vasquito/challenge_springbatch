package com.accenture.challenge.sales.service.constants;

import lombok.Getter;

import java.util.EnumSet;

public enum PointOfSaleEnum {
    SF(1l, "Santa Fe", 0.21f),
    TF(2l, "Tierra del Fuego", 0.105f),
    BA(3l, "Buenos Aires", 0.21f),
    CABA(4l, "Ciudad de Bs As", 0.21f),
    CC(5l, "Chaco", 0.18f),
    CH(6l, "Chubut", 0.105f),
    CBA(7l, "Cordoba", 0.21f),
    CR(8l, "Corrientes", 0.21f),
    ER(9l, "Entre Rios", 0.21f),
    FO(10l, "Formosa", 0.15f),
    JY(11l, "Jujuy", 0.21f),
    LP(12l, "La Pampa", 0.18f),
    LR(13l, "La Rioja", 0.18f),
    CT(14l, "Catamarca", 0.18f),
    MZ(15l, "Mendoza", 0.21f),
    MN(16l, "Misiones", 0.21f),
    NQ(17l, "Neuquen", 0.21f),
    RN(18l, "Rio Negro", 0.21f),
    SA(19l, "Salta", 0.21f),
    SJ(20l, "San Juan", 0.18f),
    SL(21l, "San Luis", 0.18f),
    SC(22l, "Santa Cruz", 0.105f),
    TM(23l, "Tucuman", 0.18f);

    @Getter
    private Long pointOfSale;
    @Getter
    private String name;
    @Getter
    private Float tax;

    private PointOfSaleEnum(Long pointOfSale, String name, Float tax) {
        this.pointOfSale = pointOfSale;
        this.name = name;
        this.tax = tax;
    }

    /*
    TODO Delete this method
    public static Float findTax(Long pointOfSaleId) {
        for (PointOfSaleEnum pos : values()) {
            if (pos.pointOfSale.equals(pointOfSaleId)) {
                return pos.tax;
            }
        }
        return null;
    }
    */

    public static Float findTax(Long pointOfSaleId) {
        return EnumSet.allOf(PointOfSaleEnum.class)
                .stream()
                .filter(pos -> pos.getPointOfSale().equals(pointOfSaleId))
                .map(PointOfSaleEnum::getTax)
                .findFirst()
                .orElse(null);
    }
}