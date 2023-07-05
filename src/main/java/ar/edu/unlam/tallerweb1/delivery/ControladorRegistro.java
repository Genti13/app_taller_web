package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;
import ar.edu.unlam.tallerweb1.domain.usuarios.ServicioRegistro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ControladorRegistro {

    @Inject
    private ServicioRegistro servicioRegistro;

    @Autowired
    public ControladorRegistro(ServicioRegistro servicioRegistro) {
        this.servicioRegistro = servicioRegistro;
    }

    @RequestMapping(path = "/registro-usuario")
    public ModelAndView irARegistro() {
        ModelMap model = new ModelMap();
        model.put("datosRegistro", new DatosRegistro());
        return new ModelAndView("registro-usuario", model);
    }

    @RequestMapping(path = "/registrar-usuario", method = RequestMethod.POST)
    public ModelAndView registrarUsuario(@ModelAttribute("datosRegistro") DatosRegistro datosRegistro) {
        ModelMap model = new ModelMap();

        try {
            servicioRegistro.registrarUsuario(datosRegistro);

        }catch (IllegalArgumentException e){
            model.put("error", e.getMessage());
            return new ModelAndView("registro-usuario", model);
        }
        return new ModelAndView("redirect:/registro-exitoso",model);
    }

    @RequestMapping(path = "/registro-exitoso", method = RequestMethod.GET)
    public ModelAndView registroExitoso() {
        return new ModelAndView("registro-exitoso");
    }
}
