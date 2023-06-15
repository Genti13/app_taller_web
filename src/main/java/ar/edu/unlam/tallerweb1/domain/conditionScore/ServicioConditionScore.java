package ar.edu.unlam.tallerweb1.domain.conditionScore;

import ar.edu.unlam.tallerweb1.domain.persona.Persona;

public interface ServicioConditionScore {

    public Integer getActual(Persona persona);
    public int calculateEffectivity(Persona persona);

    void updateWeeklyCS(Persona persona, int newCs);
}
