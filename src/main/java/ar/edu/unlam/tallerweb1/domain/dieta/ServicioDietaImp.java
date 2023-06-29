package ar.edu.unlam.tallerweb1.domain.dieta;

import ar.edu.unlam.tallerweb1.domain.menu.Ingrediente;
import ar.edu.unlam.tallerweb1.domain.menu.Menu;
import ar.edu.unlam.tallerweb1.domain.menu.MenuRestringidoException;
import ar.edu.unlam.tallerweb1.domain.menu.Plato;
import ar.edu.unlam.tallerweb1.domain.rutina.Rutina;
import ar.edu.unlam.tallerweb1.domain.ejercicio.Ejercicio;
import ar.edu.unlam.tallerweb1.domain.rutina.RutinaRestringidaException;
import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ServicioDietaImp implements ServicioDieta {
    private RepositorioDieta repositorioDieta;

    public ServicioDietaImp(RepositorioDieta repositorioDieta) {
        this.repositorioDieta = repositorioDieta;
    }

    public ServicioDietaImp() {

    }

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

        for (Menu menu : menus) {
            puntajeMenu += menu.calcularValor();
        }

        return puntajeMenu;
    }


    @Override
    public List<Dieta> dameRecomendadas(Usuario persona) {
        List<Dieta> todasLasDietas = repositorioDieta.getAllDietas();
        List<Dieta> recomendadasParaLaPersona = new ArrayList<>();

        for (Dieta dieta : todasLasDietas) {
            boolean esDietaPermitida = true;

            // Filtrar por ingredientes restringidos en el menú
            for (Menu menu : dieta.getMenus()) {
                for (Plato plato : menu.getPlatos()) {
                    for (Ingrediente ingrediente : plato.getIngredientes()) {
                        if (persona.getEstado().getRestricciones().contains(ingrediente.getNombre())) {
                            esDietaPermitida = false;
                        }
                    }
                }
            }
            // Filtrar por ejercicios restringidos en las rutinas
            for (Rutina rutina : dieta.getRutinas()) {
                for (Ejercicio ejercicio : rutina.getEjercicios()) {
                    if (persona.getEstado().getRestricciones().contains(ejercicio.getNombre())) {
                        esDietaPermitida = false;
                    }
                }
            }
            // Si la dieta es permitida, agregarla a las recomendadas para la persona
            if (esDietaPermitida) {
                recomendadasParaLaPersona.add(dieta);
            }
        }
        return recomendadasParaLaPersona;
    }
}