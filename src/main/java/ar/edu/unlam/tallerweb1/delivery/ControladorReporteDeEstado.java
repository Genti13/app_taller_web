package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.conditionScore.ServicioConditionScore;
import ar.edu.unlam.tallerweb1.domain.usuarios.RepositorioUsuario;
import ar.edu.unlam.tallerweb1.domain.usuarios.ServicioLogin;
import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

public class ControladorReporteDeEstado {
    private ServicioConditionScore servicioConditionScore;
    private ServicioLogin servicioLogin;
    private RepositorioUsuario repositorioUsuario;
    public ControladorReporteDeEstado(ServicioConditionScore servicioConditionScore, ServicioLogin servicioLogin, RepositorioUsuario repositorioPersona) {
        this.servicioConditionScore = servicioConditionScore;
        this.servicioLogin = servicioLogin;
        this.repositorioUsuario = repositorioPersona;
    }

@RequestMapping(path = "/ver-estado", method = RequestMethod.POST)
    public ModelAndView analizarCS(HttpServletRequest request) {
        Usuario persona = repositorioUsuario.getUsuario((String) request.getSession().getAttribute("UserName"));
        ModelMap model = new ModelMap();

        if(servicioConditionScore.getActual(persona) < 50){
            return new ModelAndView("underCS", model);
        }

        return new ModelAndView("vistaReporte", model);
    }
}
