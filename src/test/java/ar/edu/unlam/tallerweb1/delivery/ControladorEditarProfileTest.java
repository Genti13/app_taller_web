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
import ar.edu.unlam.tallerweb1.domain.usuarios.ServicioLogin;
import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;
import ar.edu.unlam.tallerweb1.domain.usuarios.ServicioProfile;
import static org.assertj.core.api.Assertions.assertThat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ControladorEditarProfileTest {

    ServicioProfile servicioProfile;
    ServicioLogin servicioLogin;
    ControladorProfile controladorEditarProfile;
    HttpServletRequest request;
    HttpSession session;

    @Before
    public void init() {
        request = mock(HttpServletRequest.class);
        session = mock(HttpSession.class);
        servicioProfile = mock(ServicioProfile.class);
        servicioLogin = mock(ServicioLogin.class);
        controladorEditarProfile = new ControladorProfile(servicioLogin, servicioProfile);
    }

    @Test
    public void alCargarLaVistaVienenLosDatosEnElModel() {
        final String MAIL = "AAA@AAA";
        final String PASS = "123`";

        Usuario usuario = makePersona();
        usuario.setEmail(MAIL);
        usuario.setNombre("Alan");
        usuario.setApellido("Gentile");

        when(request.getAttribute(any())).thenReturn("Genti");
        when(servicioProfile.getUserData("Genti")).thenReturn(usuario);

        ModelAndView vista = controladorEditarProfile.irAEditarPerfil(request);

        Usuario usuarioRetornado = (Usuario)  vista.getModel().get("user");

        assertThat(usuarioRetornado).isNotNull();
        assertThat(usuarioRetornado.getNombre()).isEqualTo("Alan");
        assertThat(usuarioRetornado.getEmail()).isEqualTo(MAIL);
        assertThat(usuarioRetornado).isEqualTo(usuario);
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
