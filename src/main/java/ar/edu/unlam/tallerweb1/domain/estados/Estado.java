package ar.edu.unlam.tallerweb1.domain.estados;
import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public abstract class Estado {
    public Estado() {
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nombre;

    @Column @ElementCollection
    private List<String> restricciones;



    public List<String> getRestricciones(){
        return  this.restricciones;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setRestricciones(ArrayList<String> restricciones) {
        this.restricciones = restricciones;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Estado estado = (Estado) o;
        return Objects.equals(nombre, estado.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }
}

