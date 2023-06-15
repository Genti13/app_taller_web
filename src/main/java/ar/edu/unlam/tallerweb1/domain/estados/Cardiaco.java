package ar.edu.unlam.tallerweb1.domain.estados;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.Arrays;

public class Cardiaco extends Estado {

    public Cardiaco() {
        this.nombre = "Cardiaco";
        this.restricciones = new ArrayList<>(
                Arrays.asList("Carne Procesada", "Azucar", "Sal", "Carbohidratos Refinados", "Pesas")); // fisico_pesas, alimento_sal
    }


}
