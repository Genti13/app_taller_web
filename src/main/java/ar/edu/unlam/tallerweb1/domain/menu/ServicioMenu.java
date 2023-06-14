package ar.edu.unlam.tallerweb1.domain.menu;

import ar.edu.unlam.tallerweb1.domain.estados.Estado;

import java.util.List;

public interface ServicioMenu {

    public void agregarPlato(Plato plato, Menu menu, Estado estadoMock) throws MenuRestringidoException, PlatoExistenteException;

    public void modificarPlato(Plato plato, Menu menu) throws PlatoNoExistenteException;

    public void eliminarPlato(Plato plato, Menu menu) throws PlatoNoExistenteException;


}
