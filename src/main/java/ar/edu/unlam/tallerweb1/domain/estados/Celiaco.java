package ar.edu.unlam.tallerweb1.domain.estados;

import java.util.ArrayList;
import java.util.Arrays;

public class Celiaco extends Estado{
    public Celiaco() {
        this.nombre = "Celiaco";
        this.restricciones = new ArrayList<>(
                Arrays.asList("Harina")); // fisico_pesas, alimento_sal
    }
}
