package ar.edu.unlam.tallerweb1.delivery;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControladorAPI {

    @RequestMapping(path = "/pedir_CS", method = RequestMethod.GET)
    public ResponseEntity getCS(){
        int numero = 8;
        int[] cs = {50,45,40,30,40,50,60,70,80};
        return ResponseEntity.ok(cs);
    }
}
