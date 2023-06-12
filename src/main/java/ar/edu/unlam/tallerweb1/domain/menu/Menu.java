package ar.edu.unlam.tallerweb1.domain.menu;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @OneToMany
    private List<Plato> platos;

    public Menu(List<Plato> platos) {
        this.platos = platos;
    }

    public Menu(Plato plato) {
        this.platos = new ArrayList<Plato>();
        this.platos.add(plato);
    }

    public void agregarPlato(Plato plato) {
        this.platos.add(plato);
    }

    public Object getPlatos() {
        return this.platos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Menu menu = (Menu) o;
        return Objects.equals(platos, menu.platos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(platos);
    }

    public int calcularValor() {
        int valor = 0;
        for (Plato plato : platos) {
            valor += plato.calcularValorEnBaseACalorias();
        }
        return valor;
    }
}
