package ar.edu.unlam.tallerweb1.domain.estados;

import java.util.ArrayList;
import java.util.Arrays;

public class Hipertenso extends Estado{
    public Hipertenso() {
        this.nombre = "Hipertenso";
        this.restricciones = new ArrayList<>(
                Arrays.asList( "Sal", "Carne Procesada", "Queso", "Alcohol")); // fisico_pesas, alimento_sal
    }
}
