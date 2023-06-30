package ar.edu.unlam.tallerweb1.delivery;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Array;

@Controller
public class ControladorProfile {
    @RequestMapping(path = "/profile")
    public ModelAndView irAPerfil() {
        ModelMap model = new ModelMap();
        model.put("datosRegistro", new DatosRegistro());
        model.put("nombre", "DARTH");
        model.put("apellido"," VADER");
        model.put("lastCS", 80);
        model.put("birthday", "13/11/1997");
        model.put("description", "Lord Sith");

        return new ModelAndView("profile", model);
    }


}
