package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.conditionScore.ConditionScore;
import ar.edu.unlam.tallerweb1.domain.dieta.Dieta;
import ar.edu.unlam.tallerweb1.domain.ejercicio.Ejercicio;
import ar.edu.unlam.tallerweb1.domain.estados.Cardiaco;
import ar.edu.unlam.tallerweb1.domain.estados.Estado;
import ar.edu.unlam.tallerweb1.domain.menu.Ingrediente;
import ar.edu.unlam.tallerweb1.domain.menu.Menu;
import ar.edu.unlam.tallerweb1.domain.menu.Plato;
import ar.edu.unlam.tallerweb1.domain.rutina.Rutina;
import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import java.util.ArrayList;
import java.util.List;

@RestController
public class ControladorAPI {

    @RequestMapping(path = "/pedir_CS", method = RequestMethod.GET)
    public ResponseEntity getCS(){
        int numero = 8;
        int[] cs = {50,45,40,30,40,50,60,70,80};
        List<Usuario> users = new ArrayList<>();
        users.add(makePersona());

        return ResponseEntity.ok(users);
    }


    @RequestMapping(path = "/enviarWP", method = RequestMethod.GET)
    public void sendWP(){



    }

    private Usuario makePersona() {
        Estado enfermedad = new Cardiaco();
        Usuario persona = new Usuario();
        persona.setDieta(makeDieta());
        persona.setEstado(enfermedad);
        persona.setEdad(25);
        persona.setAltura(1.75);
        persona.setPeso(52);
        persona.setGenero("Female");
        persona.setObjetivo(1); //0 => Gestion; 1=> Perdida de Peso; 2=> Ganancia de peso;
        persona.setConditionScore(new ConditionScore());

        return persona;
    }

    private List<Dieta> makeDieta() {
        Dieta dieta = new Dieta();

        ArrayList<Rutina> rutinas = new ArrayList<Rutina>();
        rutinas.add(makeRutina());

        List<Menu> menus = new ArrayList<Menu>();
        menus.add(makeMenu());

        dieta.setMenus(menus);
        dieta.setRutinas(rutinas);

        List<Dieta> dietas = new ArrayList<Dieta>();

        dietas.add(dieta);

        return dietas;
    }

    private Menu makeMenu() {
        List<Ingrediente> ingredientes = new ArrayList<Ingrediente>();

        ingredientes.add(new Ingrediente("Pepino", 500));
        ingredientes.add(new Ingrediente("Berenjena", 282));

        Plato plato = new Plato(ingredientes);

        return new Menu(plato);
    }

    private Rutina makeRutina() {
        Ejercicio ej1 = new Ejercicio("Pesas");
        Ejercicio ej2 = new Ejercicio("Flexiones");

        ej1.setDuracion(5);
        ej2.setDuracion(5);

        List<Ejercicio> ejercicios = new ArrayList<>();

        ejercicios.add(ej1);
        ejercicios.add(ej2);

        return new Rutina(ejercicios);
    }

}
