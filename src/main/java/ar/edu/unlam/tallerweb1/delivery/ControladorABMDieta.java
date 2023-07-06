package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.dieta.ServicioABMDieta;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ControladorABMDieta {
    private ServicioABMDieta servicioABMDieta;
    @Autowired
    public ControladorABMDieta(ServicioABMDieta servicioABMDieta) {
        this.servicioABMDieta = servicioABMDieta;
    }

    @RequestMapping(path = "/abm-dieta")
    public ModelAndView checkLogedUser(HttpServletRequest request) {

        if(request.getSession().getAttribute("logedUser") == null){
            return new ModelAndView("login");
        }else {
            return new ModelAndView("abm-dieta");
        }
    }
}
