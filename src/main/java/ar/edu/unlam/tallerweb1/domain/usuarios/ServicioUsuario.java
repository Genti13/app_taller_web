package ar.edu.unlam.tallerweb1.domain.usuarios;

public interface ServicioUsuario {
    int getTMB(Usuario persona);

    void updateCS(Usuario persona, int newCS);
}
