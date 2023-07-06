package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.usuarios.ServicioUsuario;
import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;
import ar.edu.unlam.tallerweb1.domain.conditionScore.ServicioConditionScore;
import ar.edu.unlam.tallerweb1.domain.usuarios.ServicioRegistro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ControladorRegistro {

    private ServicioRegistro servicioRegistro;
    private ServicioUsuario servicioUsuario;
    private ServicioConditionScore servicioCS;

    @Autowired
    public ControladorRegistro(ServicioRegistro servicioRegistro, ServicioUsuario servicioUsuario, ServicioConditionScore servicioCS) {
        this.servicioRegistro = servicioRegistro;
        this.servicioUsuario = servicioUsuario;
        this.servicioCS = servicioCS;
    }

    @RequestMapping(path = "/registro-usuario")
    public ModelAndView irARegistro() {
        ModelMap model = new ModelMap();
        model.put("datosRegistro",new DatosRegistro());
        return new ModelAndView("registro-usuario", model);
    }

    @RequestMapping(path = "/registro-usuario", method = RequestMethod.POST)
    public ModelAndView registrarUsuario(@ModelAttribute("datosRegistro") DatosRegistro datosRegistro, HttpServletRequest request) {
        ModelMap model = new ModelMap();

        Usuario usuario = servicioUsuario.crearUsuario(datosRegistro);
        servicioCS.saveCS(usuario.getConditionScore());
        servicioRegistro.registrarUsuario(usuario);
        model.put("mensaje", "Registro exitoso");

        return new ModelAndView("login", model);
    }

    @RequestMapping(path = "/registro-exitoso", method = RequestMethod.GET)
    public ModelAndView registroExitoso() {
        return new ModelAndView("registro-exitoso");
    }

}
