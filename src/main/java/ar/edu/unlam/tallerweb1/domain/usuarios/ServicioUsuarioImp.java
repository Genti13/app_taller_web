package ar.edu.unlam.tallerweb1.domain.usuarios;

import ar.edu.unlam.tallerweb1.delivery.DatosRegistro;
import ar.edu.unlam.tallerweb1.delivery.DatosCondiciones;
import ar.edu.unlam.tallerweb1.domain.conditionScore.ConditionScore;
import ar.edu.unlam.tallerweb1.domain.dieta.Dieta;
import ar.edu.unlam.tallerweb1.domain.ejercicio.Ejercicio;
import ar.edu.unlam.tallerweb1.domain.estados.Cardiaco;
import ar.edu.unlam.tallerweb1.domain.estados.Estado;
import ar.edu.unlam.tallerweb1.domain.menu.Ingrediente;
import ar.edu.unlam.tallerweb1.domain.menu.Menu;
import ar.edu.unlam.tallerweb1.domain.menu.Plato;
import ar.edu.unlam.tallerweb1.domain.rutina.Rutina;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServicioUsuarioImp implements ServicioUsuario {

    @Override
    public Usuario crearUsuario(DatosRegistro datosRegistro) {
        Usuario usuario = new Usuario();
        usuario.setEmail(datosRegistro.getEmail());
        usuario.setPassword(datosRegistro.getPassword());
        usuario.setNombre(datosRegistro.getNombre());
        usuario.setApellido(datosRegistro.getApellido());
        usuario.setEdad(datosRegistro.getEdad());
        usuario.setAltura(datosRegistro.getAltura());
        usuario.setPeso(datosRegistro.getPeso());
        usuario.setGenero(datosRegistro.getGenero());
        usuario.setConditionScore(new ConditionScore());
        return usuario;
    }

    @Override
    public Usuario actualizarCondiciones(DatosCondiciones datosCondiciones) {
        Usuario usuario = new Usuario();
        usuario.setEstado(new Cardiaco());
        usuario.setObjetivo(datosCondiciones.getObjetivo());
        return usuario;
    }

    @Override
    public int getTMB(Usuario persona) {
        Dieta dieta = persona.getDieta().get(0);
        int cantRutinas = dieta.getRutinas().size();
        int edad = persona.getEdad();
        int peso = persona.getPeso();
        int altura = (int) persona.getAltura() * 100;
        String genero = persona.getGenero();

        int s;
        switch (genero) {
            case "Male":
                s = 5;
                break;
            case "Female":
                s = -161;
                break;
            default:
                s = -100;
                break;
        }

        return (int) (((10 * peso) + (6.25 * altura) - (5 * edad) + s) * (cantRutinas < 7 ? (1.2 + cantRutinas * 0.875) : 1.9));
    }

    @Override
    public void updateCS(Usuario persona, int newCS) {
        persona.addNewWeekCS(newCS);
    }

}
