package ar.edu.unlam.tallerweb1.domain.dieta;
import ar.edu.unlam.tallerweb1.domain.menu.Ingrediente;
import ar.edu.unlam.tallerweb1.domain.menu.Menu;
import ar.edu.unlam.tallerweb1.domain.menu.MenuRestringidoException;
import ar.edu.unlam.tallerweb1.domain.menu.Plato;
import ar.edu.unlam.tallerweb1.domain.rutina.Rutina;
import ar.edu.unlam.tallerweb1.domain.ejercicio.Ejercicio;
import ar.edu.unlam.tallerweb1.domain.rutina.RutinaRestringidaException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ServicioDietaImp implements ServicioDieta {

    @Override
    public void agregarMenu(Dieta dieta, Menu menu, List<String> restricciones) throws MenuRestringidoException {
        ArrayList<Plato> platos = (ArrayList<Plato>) menu.getPlatos();

        for (Plato plato : platos) {
            for (Ingrediente ingrediente : plato.getIngredientes()) {
                if (restricciones.contains(ingrediente.getNombre())) {
                    throw new MenuRestringidoException("El menú contiene ingredientes restringidos.");
                }
            }
        }
        dieta.getMenus().add(menu);
    }

    @Override
    public void quitarMenu(Dieta dieta, Menu menu) {
        dieta.getMenus().remove(menu);
    }

    @Override
    public void modificarMenu(Dieta dieta, Menu oldMenu, Menu newMenu) {
        int index = dieta.getMenus().indexOf(oldMenu);

        if (index != -1) {
            dieta.getMenus().set(index, newMenu);
        }
    }

    @Override
    public void agregarRutina(Dieta dieta, Rutina rutina, List<String> restricciones) throws RutinaRestringidaException {
        List<Ejercicio> ejercicios = rutina.getEjercicios();

        for (Ejercicio ejercicio : ejercicios) {
            System.out.print(restricciones);
            if (restricciones.contains(ejercicio.getNombre())) {
                throw new RutinaRestringidaException("La rutina contiene ejercicios restringidos.");
            }
        }

        dieta.getRutinas().add(rutina);
    }

    @Override
    public void quitarRutina(Dieta dieta, Rutina rutina) {
        dieta.getRutinas().remove(rutina);
    }

    @Override
    public void modificarRutina(Dieta dieta, Rutina rutinaVieja, Rutina rutinaNueva) {
        int index = dieta.getRutinas().indexOf(rutinaVieja);

        if (index != -1) {
            dieta.getRutinas().set(index, rutinaNueva);
        }
    }

    @Override
    public int calcularPuntaje(Dieta dieta) {
        List<Rutina> rutinas = dieta.getRutinas();
        List<Menu> menus = dieta.getMenus();

        int puntajeMenu = 0;

        for(Menu menu : menus){
            puntajeMenu += menu.calcularValor();
        }

        return puntajeMenu;
    }

}
