package ar.edu.unlam.tallerweb1.domain.usuarios;

import ar.edu.unlam.tallerweb1.delivery.DatosRegistro;
import ar.edu.unlam.tallerweb1.domain.estados.Estado;

public interface ServicioUsuario {
    Usuario crearUsuario(DatosRegistro datosRegistro);

    int getTMB(Usuario persona);

    void updateCS(Usuario persona, int newCS);
}
