package ar.edu.unlam.tallerweb1.domain.conditionScore;

import ar.edu.unlam.tallerweb1.domain.dieta.ServicioDieta;
import ar.edu.unlam.tallerweb1.domain.dieta.ServicioDietaImp;
import ar.edu.unlam.tallerweb1.domain.usuarios.ServicioUsuario;
import ar.edu.unlam.tallerweb1.domain.usuarios.ServicioUsuarioImp;
import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("servicioConditionScore")
@Transactional
public class ServicioConditionScoreImp implements  ServicioConditionScore{
    private static final int VALOR_MIN_PERDIDA = 500;
    private static final int VALOR_MAX_PERDIDA = 1000;
    private static final int VALOR_MIN_GANANCIA = 250;
    private static final int VALOR_MAX_GANANCIA = 500;
    private static  final  int VALOR_MANTENER_PESO = 100;

    private ServicioUsuario servicioUsuario;
    private ServicioDieta servicioDieta;
    private RepositorioConditionScore repositorioCS;

    @Autowired
    public ServicioConditionScoreImp(ServicioUsuario servicioPersona, ServicioDieta servicioDieta, RepositorioConditionScore repositorioCS) {
        this.servicioDieta = servicioDieta;
        this.servicioUsuario = servicioPersona;
        this.repositorioCS = repositorioCS;
    }
    public Integer getActual(Usuario persona) {
        return persona.getConditionScore().getLastCS();
    }

    @Override
    public int calculateEffectivity(Usuario persona) {
        int tmb = servicioUsuario.getTMB(persona); //necesita 1500
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

        return  puntajeCS > 10 ? 10 : puntajeCS < -10 ? -10 : puntajeCS;
    }

    @Override
    public void updateWeeklyCS(Usuario persona, int newCS) {
        servicioUsuario.updateCS(persona, this.getActual(persona) + newCS);
    }

    @Override
    public void saveCS(ConditionScore conditionScore) {
    repositorioCS.guardarCS(conditionScore);
    }
}
