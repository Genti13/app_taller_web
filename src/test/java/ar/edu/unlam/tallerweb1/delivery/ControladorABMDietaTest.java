package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.dieta.ServicioABMDieta;
import ar.edu.unlam.tallerweb1.domain.usuarios.ServicioLogin;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.mock;

public class ControladorABMDietaTest {

    private ServicioABMDieta servicioABMDieta;
    private ControladorABMDieta controladorABMDieta;
    private HttpServletRequest request;
    private HttpSession sesion;

    @Before
    public void init(){
        request = mock(HttpServletRequest.class);
        sesion = mock(HttpSession.class);
        controladorABMDieta = new ControladorABMDieta(this.servicioABMDieta);
    }

    @Test
    public void siHayUsuarioLogueadoVaAABM(){

        when(request.getSession()).thenReturn(sesion);
        when(sesion.getAttribute("logedUser")).thenReturn(true);

        ModelAndView vista = controladorABMDieta.checkLogedUser(request);

        assertThat(vista.getViewName()).isEqualTo("abm-dieta");
    }

    @Test
    public void siNoHayUsuarioLogueadoDevuelveAlLoby(){
        when(request.getSession()).thenReturn(sesion);
        when(sesion.getAttribute("logedUser")).thenReturn(null);

        ModelAndView vista = controladorABMDieta.checkLogedUser(request);

        assertThat(vista.getViewName()).isEqualTo("login");
    }
}
