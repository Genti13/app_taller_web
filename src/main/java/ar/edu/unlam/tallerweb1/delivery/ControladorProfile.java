package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.usuarios.ServicioLogin;
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

    @Autowired
    public ControladorProfile(ServicioLogin servicioLogin){
        this.servicioLogin = servicioLogin;
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

//        ArrayList<String> dietas = new ArrayList<>();
//        dietas.add("Uno");
//        dietas.add("Dos");
//        dietas.add("Tres");
//
//
//        ArrayList<Integer> puntos = new ArrayList<>();
//
//        puntos.add(50);
//        puntos.add(60);
//        puntos.add(75);
//        puntos.add(85);

        return new ModelAndView("profile", model);
    }

    @RequestMapping(path = "/editprofile", method = RequestMethod.GET)
    public ModelAndView irAEditarPerfil() {
        ModelMap model = new ModelMap();
        model.addAttribute("datosRegistro", new DatosRegistro());
        return new ModelAndView("editprofile", model);
    }

    @RequestMapping(path = "/act-condiciones", method = RequestMethod.POST)
    public ModelAndView actualizarCondiciones() {
        return new ModelAndView("act-condiciones");
    }
}
