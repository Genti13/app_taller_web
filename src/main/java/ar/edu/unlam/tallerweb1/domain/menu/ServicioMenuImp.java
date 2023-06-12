package ar.edu.unlam.tallerweb1.domain.menu;
import ar.edu.unlam.tallerweb1.domain.menu.*;

import java.util.List;
public class ServicioMenuImp implements ServicioMenu{

    @Override
    public void agregarPlato(Plato plato, Menu menu) throws MenuRestringidoException, PlatoExistenteException {

        List<Plato> platos = (List<Plato>) menu.getPlatos();

        for (Plato existingPlato : platos) {
            if (existingPlato.equals(plato)) {
                throw new PlatoExistenteException("El plato ya existe en el menú.");
            }
        }
        platos.add(plato);
    }


    @Override
    public void modificarPlato(Plato plato, Menu menu) throws PlatoNoExistenteException {
        List<Plato> platos = (List<Plato>) menu.getPlatos();
        int index = platos.indexOf(plato);
        if (index != -1) {
            platos.set(index, plato);
        } else {
            throw new PlatoNoExistenteException("El plato no existe en el menú.");
        }
    }

    @Override
    public void eliminarPlato(Plato plato, Menu menu) throws PlatoNoExistenteException{
        List<Plato> platos = (List<Plato>) menu.getPlatos();

        if (platos.remove(plato)) {
            // Plato eliminado correctamente
        } else {
            throw new PlatoNoExistenteException("El plato no existe en el menú.");
        }
    }
}
