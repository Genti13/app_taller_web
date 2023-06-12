package ar.edu.unlam.tallerweb1.domain.ejercicio;

import javax.persistence.*;

@Entity
public class Ejercicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column
    private String nombre;
    @Column
    private int duracion;

    public Ejercicio(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }


    public int getValorEnBaseACaloriasQuemadas() {
        return this.duracion * 1;
    }
}
