package ar.edu.unlam.tallerweb1.domain.menu;

import org.hibernate.annotations.CollectionType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Plato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long Id;
    @OneToMany
    private List<Ingrediente> ingredientes;

    public Plato(List<Ingrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public Plato(Ingrediente ingredientes) {
        this.ingredientes = new ArrayList<>();
        this.ingredientes.add(ingredientes);
    }
    public Plato() {
    }

    public List<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<Ingrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public void agregarIngrediente(Ingrediente ingrediente) {
        this.ingredientes.add(ingrediente);
    }

    public void eliminarIngrediente(Ingrediente ingrediente) {
        this.ingredientes.remove(ingrediente);
    }

    public int calcularValorEnBaseACalorias() {
        int valor = 0;

        for (Ingrediente ingrediente : ingredientes) {
            valor += ingrediente.getCalorias();
        }

        return valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plato plato = (Plato) o;
        return Objects.equals(ingredientes, plato.ingredientes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ingredientes);
    }
}