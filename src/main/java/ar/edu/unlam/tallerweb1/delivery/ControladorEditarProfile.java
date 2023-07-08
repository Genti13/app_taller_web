package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.usuarios.ServicioProfile;
import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

public class ControladorEditarProfile {
    ServicioProfile servicioProfile;

    @Autowired
    public ControladorEditarProfile(ServicioProfile servicioProfile) {
        this.servicioProfile = servicioProfile;
    }


}
