package ar.edu.unlam.tallerweb1.domain.estados;

public interface RepositorioEstado {
    Estado buscarEstado(String estado);
    void guardar(Estado estado);
}
