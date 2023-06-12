package ar.edu.unlam.tallerweb1.domain.rutina;

import ar.edu.unlam.tallerweb1.domain.estados.Estado;
import ar.edu.unlam.tallerweb1.domain.ejercicio.Ejercicio;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Rutina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @OneToMany
    private List<Ejercicio> ejercicios;

    public Rutina(List<Ejercicio> ejercicios) {
        this.ejercicios = ejercicios;
    }

    public Rutina() {

    }

    public List<Ejercicio> getEjercicios() {
        return ejercicios;
    }

    public void setEjercicios(List<Ejercicio> ejercicios) {
        this.ejercicios = ejercicios;
    }

    public void agregarEjercicio(Ejercicio ejercicio, Estado estadoPersona) throws RutinaRestringidaException {
        List<String> restricciones = estadoPersona.getRestricciones();
        for (String restriccion : restricciones) {
            if (restriccion.equalsIgnoreCase(ejercicio.getNombre())) {
                throw new RutinaRestringidaException("El ejercicio no está permitido para este estado.");
            }
        }
        ejercicios.add(ejercicio);
    }

    public void modificarEjercicio(Ejercicio ejercicioAnterior, Ejercicio ejercicioNuevo) {
        int index = ejercicios.indexOf(ejercicioAnterior);
        if (index != -1) {
            ejercicios.set(index, ejercicioNuevo);
        }
    }

    public void eliminarEjercicio(Ejercicio ejercicio) {
        ejercicios.remove(ejercicio);
    }

    public int calcularValor() {
        List<Ejercicio> ejercicios = this.ejercicios;
        int valor = 0;

       for(Ejercicio ejercicio: ejercicios){
           valor += ejercicio.getValorEnBaseACaloriasQuemadas();
       }
        return valor;
    }
}
