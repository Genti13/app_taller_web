package ar.edu.unlam.tallerweb1.domain.estados;

import java.util.ArrayList;
import java.util.Arrays;

public class Diabetico extends Estado{
    public Diabetico() {
        this.nombre = "Diabetico";
        this.restricciones = new ArrayList<>(
                Arrays.asList("Carne Procesada", "Azucar", "Carbohidratos Refinados", "Pesas")); // fisico_pesas, alimento_sal
    }
}
