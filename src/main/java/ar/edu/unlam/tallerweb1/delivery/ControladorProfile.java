package ar.edu.unlam.tallerweb1.delivery;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Controller
public class ControladorProfile {
    @RequestMapping(path = "/profile", method = RequestMethod.POST)
    public ModelAndView irAPerfil() {
        ModelMap model = new ModelMap();
        model.put("datosRegistro", new DatosRegistro());
        model.put("nombre", "DARTH");
        model.put("apellido"," VADER");
        model.put("lastCS", 80);
        model.put("birthday", "13/11/1997");
        model.put("description", "Lord Sith");

        ArrayList<String> dietas = new ArrayList<>();
        dietas.add("Uno");
        dietas.add("Dos");
        dietas.add("Tres");


        ArrayList<Integer> puntos = new ArrayList<>();

        puntos.add(50);
        puntos.add(60);
        puntos.add(75);
        puntos.add(85);

        model.put("dietas", dietas);
        model.put("puntos", puntos);


        return new ModelAndView("profile", model);
    }


}
