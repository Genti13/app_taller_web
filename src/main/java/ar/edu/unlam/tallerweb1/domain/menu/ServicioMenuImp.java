package ar.edu.unlam.tallerweb1.domain.menu;
import ar.edu.unlam.tallerweb1.domain.ejercicio.EjercicioRepetidoException;
import ar.edu.unlam.tallerweb1.domain.estados.Estado;
import ar.edu.unlam.tallerweb1.domain.menu.*;
import ar.edu.unlam.tallerweb1.domain.rutina.RutinaRestringidaException;

import java.util.List;
public class ServicioMenuImp implements ServicioMenu{

    @Override
    public void agregarPlato(Plato plato, Menu menu, Estado estadoPersona) throws MenuRestringidoException, PlatoExistenteException {
        List<String> restricciones = estadoPersona.getRestricciones();
        List<Plato> platos = (List<Plato>) menu.getPlatos();
        List<Ingrediente> ingredientes = (List<Ingrediente>) plato.getIngredientes();

        if (platos.contains(plato) && !platos.isEmpty()){
            throw new PlatoExistenteException("El plato ya existe en el menú.");
        }
            for (String restriccion : restricciones) {
                for(Ingrediente ingrediente : ingredientes){
                    if (restriccion.equals(ingrediente.getNombre())) {
                        throw new MenuRestringidoException("El ingrediente no está permitido para este estado");
                }

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
