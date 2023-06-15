package ar.edu.unlam.tallerweb1.domain.dieta;

import ar.edu.unlam.tallerweb1.domain.rutina.Rutina;

import java.util.List;

public interface RepositorioDieta {

    public List<Rutina> getRutinas();
    public List<Dieta> getAllDietas();

    List<Dieta> buscarDietaConIDUsuario(Long idUsuario);

    List<Dieta> buscarDietaConMail(String s);

    List<Dieta> getDietasRecomendadas();
}
