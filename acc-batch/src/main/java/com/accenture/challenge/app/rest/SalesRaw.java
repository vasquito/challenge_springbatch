package com.accenture.challenge.app.rest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesRaw {
    private Long puntoVenta;
    private Long importe;
    private Long cantidad;
    private Long temperatura;
}
