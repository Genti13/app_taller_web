package ar.edu.unlam.tallerweb1.domain.conditionScore;

import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;

public interface ServicioConditionScore {

    public Integer getActual(Usuario persona);
    public int calculateEffectivity(Usuario persona);

    void updateWeeklyCS(Usuario persona, int newCs);
}
