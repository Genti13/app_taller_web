package ar.edu.unlam.tallerweb1.domain.menu;

public interface ServicioMenu {

    public void agregarPlato(Plato plato, Menu menu) throws MenuRestringidoException, PlatoExistenteException;

    public void modificarPlato(Plato plato, Menu menu) throws PlatoNoExistenteException;

    public void eliminarPlato(Plato plato, Menu menu) throws PlatoNoExistenteException;


}
