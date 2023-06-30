package ar.edu.unlam.tallerweb1.domain;

import ar.edu.unlam.tallerweb1.domain.conditionScore.ConditionScore;
import ar.edu.unlam.tallerweb1.domain.conditionScore.ServicioConditionScoreImp;
import ar.edu.unlam.tallerweb1.domain.dieta.Dieta;
import ar.edu.unlam.tallerweb1.domain.dieta.RepositorioDieta;
import ar.edu.unlam.tallerweb1.domain.dieta.ServicioDietaImp;
import ar.edu.unlam.tallerweb1.domain.ejercicio.Ejercicio;
import ar.edu.unlam.tallerweb1.domain.estados.Cardiaco;
import ar.edu.unlam.tallerweb1.domain.estados.Estado;
import ar.edu.unlam.tallerweb1.domain.menu.Ingrediente;
import ar.edu.unlam.tallerweb1.domain.menu.Menu;
import ar.edu.unlam.tallerweb1.domain.menu.Plato;
import ar.edu.unlam.tallerweb1.domain.rutina.Rutina;
import ar.edu.unlam.tallerweb1.domain.usuarios.RepositorioUsuario;
import ar.edu.unlam.tallerweb1.domain.usuarios.ServicioUsuarioImp;
import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ConditionScoreTest {
    private ServicioConditionScoreImp servicioConditionScore;
    private RepositorioUsuario repositorioUsuario;
    private ServicioUsuarioImp servicioUsuario;
    private ServicioDietaImp servicioDieta;
    private RepositorioDieta repositorioDieta;

    @Before
    public void init() {
        repositorioDieta = mock(RepositorioDieta.class);
        servicioUsuario = new ServicioUsuarioImp();
        servicioDieta = new ServicioDietaImp(this.repositorioDieta);
        servicioConditionScore = new ServicioConditionScoreImp(this.servicioUsuario, this.servicioDieta);
        repositorioUsuario = mock(RepositorioUsuario.class);
        when(repositorioUsuario.getUsuario(any())).thenReturn(makePersona());
    }

    @Test
    public void valorInicialDeCSdeUserEs50() {
        Usuario persona = makePersona();
        persona.setConditionScore(new ConditionScore());
        assertThat(servicioConditionScore.getActual(persona)).isEqualTo(50);
    }

    @Test
    public void calcularTMBPersonaSinEjercicio() {
        final int VALOR_ESPERADO = 1782;

        Usuario persona = repositorioUsuario.getUsuario("userName");
        int valorObtenido = servicioUsuario.getTMB(persona);

        assertThat(valorObtenido).isEqualTo(VALOR_ESPERADO);
    }

    @Test
    public void calcularCSGanadoDePersonaQuierePerderPesoTieneDieta() {
        Usuario persona = repositorioUsuario.getUsuario("userName");
        int csGanado = servicioConditionScore.calculateEffectivity(persona);

        assertThat(csGanado).isGreaterThanOrEqualTo(-10);
        assertThat(csGanado).isLessThanOrEqualTo(10);
        assertThat(csGanado).isEqualTo(10);
    }

    @Test
    public void conditionScoreSemana2IncrementaPuntaje() {
        final  int VALOR_ESPERADO = 60;
        Usuario persona = repositorioUsuario.getUsuario("UserName");
        int csGanado = servicioConditionScore.calculateEffectivity(persona);

        servicioConditionScore.updateWeeklyCS(persona, csGanado);
        int currentCS = servicioConditionScore.getActual(persona);

        assertThat(currentCS).isEqualTo(VALOR_ESPERADO);
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

}
