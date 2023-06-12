package ar.edu.unlam.tallerweb1.domain;

import ar.edu.unlam.tallerweb1.domain.dieta.Dieta;
import ar.edu.unlam.tallerweb1.domain.dieta.RepositorioDieta;
import ar.edu.unlam.tallerweb1.domain.dieta.ServicioDietaImp;
import ar.edu.unlam.tallerweb1.domain.ejercicio.Ejercicio;
import ar.edu.unlam.tallerweb1.domain.estados.*;
import ar.edu.unlam.tallerweb1.domain.menu.Ingrediente;
import ar.edu.unlam.tallerweb1.domain.menu.Menu;
import ar.edu.unlam.tallerweb1.domain.menu.MenuRestringidoException;
import ar.edu.unlam.tallerweb1.domain.menu.Plato;
import ar.edu.unlam.tallerweb1.domain.persona.Persona;
import ar.edu.unlam.tallerweb1.domain.rutina.Rutina;
import ar.edu.unlam.tallerweb1.domain.rutina.RutinaRestringidaException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ServicioDietaTest {
    private ServicioDietaImp servicioDieta;
    private Dieta dieta;
    private RepositorioDieta repositorioDieta;

    @Before
    public void init() {
        this.repositorioDieta = mock(RepositorioDieta.class);
        servicioDieta = new ServicioDietaImp();
        dieta = new Dieta();
    }

    @Test(expected = MenuRestringidoException.class)
    public void unaPersonaConProblemasCardiacosNoPuedeAgregarUnMenuConSal() throws MenuRestringidoException {
        Ingrediente sal = new Ingrediente("sal", 10);
        Plato plato = new Plato(sal);
        Menu menu = new Menu(plato);

        Estado enfermedad = new Cardiaco();
        Persona persona = new Persona();

        persona.setEstado(enfermedad);

        servicioDieta.agregarMenu(dieta, menu, persona.getEstado().getRestricciones());
    }

    @Test(expected = RutinaRestringidaException.class)
    public void unaPersonaConProblemasCardiacosNoPuedeAgregarUnaRutinaConPesas() throws RutinaRestringidaException {
        Rutina rutina = new Rutina();

        ArrayList<Ejercicio> ejercicios = new ArrayList<>();
        ejercicios.add(new Ejercicio("Pesas"));
        rutina.setEjercicios(ejercicios);
        Persona persona = makePersona();

        servicioDieta.agregarRutina(dieta, rutina, persona.getEstado().getRestricciones());
    }

    @Test
    public void sePuedeEditarUnMenuDentroDeUnaDieta() throws MenuRestringidoException {

        Ingrediente pepino = new Ingrediente("pepino", 25);
        Plato plato = new Plato(pepino);
        Menu menu = new Menu(plato);

        Persona persona = new Persona();
        persona.setEstado(new Cardiaco());

        servicioDieta.agregarMenu(dieta, menu, persona.getEstado().getRestricciones());

        Plato newPlato = new Plato(pepino);
        Menu newMenu = new Menu(newPlato);

        servicioDieta.modificarMenu(dieta, menu, newMenu);

        assertThat(dieta.getMenus()).isNotNull();
        assertThat(dieta.getMenus().size()).isGreaterThan(0);
        assertThat(dieta.getMenus().get(0).equals(newMenu));
    }

    @Test
    public void alPedirListaDeDietasObtengo2Registros(){
        List<Rutina> dietasMock = new ArrayList<>();
        dietasMock.add(makeRutina());
        dietasMock.add(makeRutina());

        when(this.repositorioDieta.getRutinas()).thenReturn(dietasMock);

        List<Rutina> dieta = repositorioDieta.getRutinas();

        assertThat(dieta).isNotNull();
        assertThat(dieta.size()).isEqualTo(2);
    }

    @Test
    public void unaDietaTieneUnPuntaje(){
        final int VALOR_ESPERADO = 30;
        when(repositorioDieta.getAllDietas()).thenReturn(this.makeDieta());
        List<Dieta> dietas = repositorioDieta.getAllDietas();

        Dieta dietaACalcular = dietas.get(0);
        int puntajeObtenido = servicioDieta.calcularPuntaje(dietaACalcular);

        assertThat(VALOR_ESPERADO).isEqualTo(puntajeObtenido);
    }

    private List<Dieta> makeDieta(){
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
        Ingrediente in1 = new Ingrediente("Pepino",10);
        Ingrediente ing2 = new Ingrediente("Berenjena",10);

        List<Ingrediente> ingredientes = new ArrayList<>();

        ingredientes.add(in1);
        ingredientes.add(ing2);

        Plato plato = new Plato(ingredientes);

        return new Menu(plato);
    }

    private Rutina makeRutina(){
        Ejercicio ej1 = new Ejercicio("Pesas");
        Ejercicio ej2 = new Ejercicio("Flexiones");

        ej1.setDuracion(5);
        ej2.setDuracion(5);

        List<Ejercicio> ejercicios = new ArrayList<>();

        ejercicios.add(ej1);
        ejercicios.add(ej2);

        return  new Rutina(ejercicios);
    }

    private Persona makePersona(){
        Estado enfermedad = new Cardiaco();
        Persona persona = new Persona();
        persona.setEstado(enfermedad);

        return persona;
    }
}
