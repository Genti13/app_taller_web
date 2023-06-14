package ar.edu.unlam.tallerweb1.domain.persona;

import ar.edu.unlam.tallerweb1.domain.dieta.Dieta;

import java.util.List;

public class ServicioPersonaImp implements ServicioPersona{

    @Override
    public int getTMB(Persona persona) {
        Dieta dieta = persona.getDietas().get(0);
        int cantRutinas = dieta.getRutinas().size();
        int edad = persona.getEdad();
        int peso = persona.getPeso();
        int altura = (int) persona.getAltura()*100;
        char genero = persona.getGenero();

        int s;
        switch (genero){
            case 'M': s = 5; break;
            case 'F': s = -161; break;
            default: s = -100;break;
        }

        return (int) (((10 * peso) + (6.25 * altura) - (5*edad) + s) * (cantRutinas < 7 ? (1.2 + cantRutinas * 0.875) : 1.9));
    }
}
