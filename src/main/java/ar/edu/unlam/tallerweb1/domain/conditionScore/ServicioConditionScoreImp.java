package ar.edu.unlam.tallerweb1.domain.conditionScore;

import ar.edu.unlam.tallerweb1.domain.dieta.ServicioDietaImp;
import ar.edu.unlam.tallerweb1.domain.persona.Persona;
import ar.edu.unlam.tallerweb1.domain.persona.ServicioPersonaImp;

public class ServicioConditionScoreImp {
    private static final int VALOR_MIN_PERDIDA = 500;
    private static final int VALOR_MAX_PERDIDA = 1000;
    private static final int VALOR_MIN_GANANCIA = 250;
    private static final int VALOR_MAX_GANANCIA = 500;
    private static  final  int VALOR_MANTENER_PESO = 100;

    public Integer getActual(Persona persona) {
        return persona.getConditionScore().getLastCS();
    }

    public int calculateEffectivity(Persona persona) {
        ServicioPersonaImp servicioPersona = new ServicioPersonaImp();
        ServicioDietaImp servicioDieta = new ServicioDietaImp();

        int tmb = servicioPersona.getTMB(persona); //necesita 1500
        int caloriasDieta = servicioDieta.calcularPuntaje(persona.getDieta().get(0));  //dieta es perder peso
        int objetivo = persona.getObjetivo(); //1
        int valMin = tmb;
        int valMax = tmb;

        switch (objetivo){
            case 1: valMin -= VALOR_MAX_PERDIDA; valMax -= VALOR_MIN_PERDIDA; break;
            case 2: valMin += VALOR_MIN_GANANCIA; valMax += VALOR_MAX_GANANCIA;break;
            default: valMin -= VALOR_MANTENER_PESO; valMax += VALOR_MANTENER_PESO; break;
        }

        int puntajeCS = 10;

        if(caloriasDieta > valMax){
            int excedente = ((caloriasDieta * 100) / valMax) - 100;
            puntajeCS = (int) (10 - (excedente / 2.5));
        }else{
            if(caloriasDieta < valMin){
                int excedente = 100 - ((caloriasDieta * 100) / valMin);
                puntajeCS = (int) (10 - (excedente / 2.5));
            }
        }

        return  puntajeCS;
    }
}
