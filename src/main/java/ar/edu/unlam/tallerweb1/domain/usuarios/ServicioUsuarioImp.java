package ar.edu.unlam.tallerweb1.domain.usuarios;

import ar.edu.unlam.tallerweb1.delivery.DatosRegistro;
import ar.edu.unlam.tallerweb1.domain.conditionScore.ConditionScore;
import ar.edu.unlam.tallerweb1.domain.dieta.Dieta;
import ar.edu.unlam.tallerweb1.domain.ejercicio.Ejercicio;
import ar.edu.unlam.tallerweb1.domain.estados.Cardiaco;
import ar.edu.unlam.tallerweb1.domain.estados.Estado;
import ar.edu.unlam.tallerweb1.domain.menu.Ingrediente;
import ar.edu.unlam.tallerweb1.domain.menu.Menu;
import ar.edu.unlam.tallerweb1.domain.menu.Plato;
import ar.edu.unlam.tallerweb1.domain.rutina.Rutina;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServicioUsuarioImp implements ServicioUsuario {

    @Override
    public Usuario crearUsuario(DatosRegistro datosRegistro) {
        Usuario usuario = new Usuario();
        usuario.setEmail(datosRegistro.getEmail());
        usuario.setPassword(datosRegistro.getPassword());
        usuario.setNombre(datosRegistro.getNombre());
        usuario.setApellido(datosRegistro.getApellido());
        usuario.setEdad(datosRegistro.getEdad());
        usuario.setAltura(datosRegistro.getAltura());
        usuario.setPeso(datosRegistro.getPeso());
        usuario.setGenero(datosRegistro.getGenero());

        usuario.setEstado(new Cardiaco());

        usuario.setObjetivo(datosRegistro.getObjetivo());
        usuario.setConditionScore(new ConditionScore());

        List<Dieta> dietas = makeDieta();



        usuario.setDieta(dietas);



        return usuario;
    }

    @Override
    public int getTMB(Usuario persona) {
        Dieta dieta = persona.getDieta().get(0);
        int cantRutinas = dieta.getRutinas().size();
        int edad = persona.getEdad();
        int peso = persona.getPeso();
        int altura = (int) persona.getAltura() * 100;
        String genero = persona.getGenero();

        int s;
        switch (genero) {
            case "Male":
                s = 5;
                break;
            case "Female":
                s = -161;
                break;
            default:
                s = -100;
                break;
        }

        return (int) (((10 * peso) + (6.25 * altura) - (5 * edad) + s) * (cantRutinas < 7 ? (1.2 + cantRutinas * 0.875) : 1.9));
    }

    @Override
    public void updateCS(Usuario persona, int newCS) {
        persona.addNewWeekCS(newCS);
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
