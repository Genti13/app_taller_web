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
import ar.edu.unlam.tallerweb1.domain.usuarios.ServicioProfile;
import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.web.servlet.ModelAndView;
import static org.assertj.core.api.Assertions.assertThat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ControladorProfileTest {

    private HttpSession session;
    private HttpServletRequest request;
    private ServicioLogin servicioLogin;
    private ControladorProfile controladorProfile;
    private ServicioProfile servicioProfile;

    @Before
    public void init(){
        session = mock(HttpSession.class);
        request = mock(HttpServletRequest.class);
        servicioLogin = mock(ServicioLogin.class);
        servicioProfile = mock(ServicioProfile.class);
        controladorProfile = new ControladorProfile(servicioLogin, servicioProfile);
    }

    @Test
    public void dadoUnUsuarioSeCaptureDeLaBDSusDatosYQuedenEnLaSesion(){
        final String MAIL  = "AAA@AAA";
        final String PASS  = "123`";

        Usuario usuario = makePersona();
        usuario.setEmail(MAIL);
        usuario.setNombre("Alan");
        usuario.setApellido("Gentile");

        DatosLogin datosLogin = new DatosLogin();
        datosLogin.setEmail(MAIL);
        datosLogin.setPassword(PASS);

        when(servicioLogin.consultarUsuario(any(), any())).thenReturn(usuario);
        ModelAndView vista = controladorProfile.irAPerfil(datosLogin, request);

        assertThat(vista.getModel().get("mail")).isNotNull();
        assertThat(vista.getModel().get("nombre")).isNotNull();
        assertThat(vista.getModel().get("apellido")).isNotNull();

        assertThat(vista.getModel().get("mail")).isEqualTo(MAIL);
        assertThat(vista.getModel().get("nombre")).isEqualTo("Alan");
        assertThat(vista.getModel().get("apellido")).isEqualTo("Gentile");

    }

    @Test
    public void dadoUnUsuarioTomarDeLaBaseDeDatosSusDietas(){
        final String MAIL  = "AAA@AAA";
        final String PASS  = "123`";

        Usuario usuario = new Usuario();
        ConditionScore conditionScore = new ConditionScore();
        usuario.setConditionScore(conditionScore);
        List<Dieta> dietasEsperadas = makeDieta();
        usuario.setDieta(dietasEsperadas);
        usuario.setEmail(MAIL);
        usuario.setNombre("Alan");
        usuario.setApellido("Gentile");

        DatosLogin datosLogin = new DatosLogin();
        datosLogin.setEmail(MAIL);
        datosLogin.setPassword(PASS);

        when(servicioLogin.consultarUsuario(any(), any())).thenReturn(usuario);
        ModelAndView vista = controladorProfile.irAPerfil(datosLogin, request);

        List<Dieta> dietasResultantes = (List<Dieta>) vista.getModel().get("dietas");

        assertThat(dietasResultantes).isNotNull();
        assertThat(dietasResultantes.size()).isEqualTo(1);
        assertThat(dietasResultantes).isEqualTo(dietasEsperadas);
    }

    @Test
    public void dadoUnUsuarioTomarDeLaBaseDeDatosSuCS(){
        final String MAIL  = "AAA@AAA";
        final String PASS  = "123`";

        Usuario usuario = makePersona();
        usuario.setEmail(MAIL);
        usuario.setNombre("Alan");
        usuario.setApellido("Gentile");

        DatosLogin datosLogin = new DatosLogin();
        datosLogin.setEmail(MAIL);
        datosLogin.setPassword(PASS);

        when(servicioLogin.consultarUsuario(any(), any())).thenReturn(usuario);
        ModelAndView vista = controladorProfile.irAPerfil(datosLogin, request);

        List<Integer> conditionScore = (ArrayList) vista.getModel().get("conditionScore");


        assertThat(conditionScore).isNotNull();
        assertThat(conditionScore.size()).isEqualTo(6);
        assertThat(conditionScore.get(conditionScore.size()-1)).isEqualTo(77);

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
