package com.uabc.edu.mx.forms.Modelo;

import lombok.Data;

import java.util.Date;

@Data
public class Citas {
    private String asunto;
    private String hora;
    private String fecha;
    private String doctor;
}
