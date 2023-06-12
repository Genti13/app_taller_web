package ar.edu.unlam.tallerweb1.domain.menu;

public class Ingrediente {
    private String nombre;
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
