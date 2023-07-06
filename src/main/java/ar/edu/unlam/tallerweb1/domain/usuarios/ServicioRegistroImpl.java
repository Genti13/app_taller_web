package ar.edu.unlam.tallerweb1.domain.usuarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("servicioRegistro")
@Transactional
public class ServicioRegistroImpl implements ServicioRegistro {

    private RepositorioUsuario repositorioUsuario;

    @Autowired
    public ServicioRegistroImpl(RepositorioUsuario repositorioUsuario) {
        this.repositorioUsuario = repositorioUsuario;
    }

    @Override
    public void registrarUsuario(Usuario usuario) {

        // Verificar si el correo electrónico ya está registrado
        if (repositorioUsuario.buscar(usuario.getEmail()) != null) {
            throw new IllegalArgumentException("Los dtos ingresados no son validos");
        }

        // Guardar al usuario en el repositorio
        repositorioUsuario.guardar(usuario);
    }
}

