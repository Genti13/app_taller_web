package ar.edu.unlam.tallerweb1.domain.usuarios;

import ar.edu.unlam.tallerweb1.delivery.DatosCondiciones;
import ar.edu.unlam.tallerweb1.delivery.DatosRegistro;
import ar.edu.unlam.tallerweb1.domain.estados.Estado;

public interface ServicioUsuario {
    Usuario crearUsuario(DatosRegistro datosRegistro);

    Usuario actualizarCondiciones(DatosCondiciones datosCondiciones);

    int getTMB(Usuario persona);

    void updateCS(Usuario persona, int newCS);
}
