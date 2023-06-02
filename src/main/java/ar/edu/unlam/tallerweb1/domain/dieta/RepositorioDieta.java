package ar.edu.unlam.tallerweb1.domain.dieta;

import ar.edu.unlam.tallerweb1.domain.rutina.Rutina;
import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;

import java.util.List;

public interface RepositorioDieta {

    public List<Rutina> get();

    List<Dieta> buscarDietaConIDUsuario(Long idUsuario);

    List<Dieta> buscarDietaConMail(String s);
}
