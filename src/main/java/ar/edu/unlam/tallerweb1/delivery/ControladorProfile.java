package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.dieta.Dieta;
import ar.edu.unlam.tallerweb1.domain.usuarios.ServicioLogin;
import ar.edu.unlam.tallerweb1.domain.usuarios.ServicioProfile;
import ar.edu.unlam.tallerweb1.domain.usuarios.ServicioUsuario;
import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;
import java.util.ArrayList;

@Controller
public class ControladorProfile {
    private ServicioLogin servicioLogin;
    private ServicioProfile servicioProfile;
    private ServicioUsuario servicioUsuario;

    @Autowired
    public ControladorProfile(ServicioLogin servicioLogin, ServicioProfile servicioProfile,ServicioUsuario servicioUsuario){
        this.servicioLogin = servicioLogin;
        this.servicioProfile = servicioProfile;
        this.servicioUsuario = servicioUsuario;
    }


    @RequestMapping(path = "/profile", method = RequestMethod.POST)
    public ModelAndView irAPerfil(@ModelAttribute("datosRegistro") DatosLogin datosLogin, HttpServletRequest request) {
        ModelMap model = new ModelMap();
        model.put("datosRegistro", new DatosRegistro());

        Usuario usuario = servicioLogin.consultarUsuario(datosLogin.getEmail(),datosLogin.getPassword());

        model.put("mail", usuario.getEmail());
        model.put("nombre", usuario.getNombre());
        model.put("apellido", usuario.getApellido());

        model.put("dietas", usuario.getDieta());
        model.put("conditionScore", usuario.getConditionScore().getHistorico());
        model.put("lastCS", usuario.getConditionScore().getLastCS());

        request.setAttribute("user", usuario.getEmail());

        return new ModelAndView("profile", model);
    }
/*
<<<<<<< HEAD
    @RequestMapping(path = "/editprofile", method = RequestMethod.GET)
    public ModelAndView irAEditarPerfil() {
        ModelMap model = new ModelMap();
        model.addAttribute("datosRegistro", new DatosRegistro());
        return new ModelAndView("editprofile", model);
    }
=======
 */   @RequestMapping(path = "/editar-perfil")
    public ModelAndView irAEditarPerfil() {
        ModelMap model = new ModelMap();
        model.put("datosCondiciones", new DatosCondiciones());
        return new ModelAndView("editar-perfil", model);
    }

//>>>>>>> b99638a71b37fb9318c5406a369bfec6a20fbc4f

    @RequestMapping(path = "/editar-perfil", method = RequestMethod.POST)
    public ModelAndView actualizarCondiciones(@ModelAttribute("datosCondiciones") DatosCondiciones datosCondiciones,HttpServletRequest request) {

     Usuario usuario = servicioUsuario.actualizarCondiciones(datosCondiciones);

        return new ModelAndView("act-condiciones");
    }

    @RequestMapping(path = "/act-condiciones", method = RequestMethod.GET)
    public ModelAndView actualizacionExitosa() {
     return new ModelAndView("act-condiciones");
    }
}
