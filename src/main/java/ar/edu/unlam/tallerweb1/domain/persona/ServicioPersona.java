package ar.edu.unlam.tallerweb1.domain.persona;

public interface ServicioPersona {
    int getTMB(Persona persona);

    void updateCS(Persona persona, int newCS);
}
