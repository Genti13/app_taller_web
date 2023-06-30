package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.conditionScore.ServicioConditionScore;
import ar.edu.unlam.tallerweb1.domain.usuarios.RepositorioUsuario;
import ar.edu.unlam.tallerweb1.domain.usuarios.ServicioLogin;
import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ControladorReporteDeEstadoTest {

    private HttpSession session;
    private HttpServletRequest request;
    private ControladorReporteDeEstado controladorEstado;
    private ServicioConditionScore servicioConditionScore;
    private ServicioLogin servicioLogin;
    private RepositorioUsuario repositorioPersona;
    @Before
    public void init(){
        session = mock(HttpSession.class);
        request = mock(HttpServletRequest.class);

        servicioLogin = mock(ServicioLogin.class);
        servicioConditionScore = mock(ServicioConditionScore.class);

        repositorioPersona = mock(RepositorioUsuario.class);

        controladorEstado = new ControladorReporteDeEstado(this.servicioConditionScore, this.servicioLogin, this.repositorioPersona);
    }

    @Test
    public void conditionScoreDebajoDe50EnviaAPantallaEspecial(){
        Usuario usuario = new Usuario();

        when(servicioLogin.consultarUsuario(any(), any())).thenReturn(usuario);
        when(request.getSession()).thenReturn(session);
        when(servicioConditionScore.getActual(any())).thenReturn(40);

        ModelAndView vista = controladorEstado.analizarCS(request);

        assertThat(vista.getViewName()).isEqualTo("underCS");
    }
}
