package ar.edu.unlam.tallerweb1.domain.menu;

import javax.persistence.*;

@Entity
public class Ingrediente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @Column
    private String nombre;
    @Column
    private Integer calorias;

    public Ingrediente(String nombre, Integer calorias) {
        this.nombre = nombre;
        this.calorias = calorias;
    }

    public String getNombre() {
        return nombre;
    }

    public Integer getCalorias() {
        return calorias;
    }
}
