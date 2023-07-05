package ar.edu.unlam.tallerweb1.domain.usuarios;

import ar.edu.unlam.tallerweb1.delivery.DatosRegistro;
import ar.edu.unlam.tallerweb1.domain.estados.Cardiaco;
import ar.edu.unlam.tallerweb1.domain.estados.Estado;
import ar.edu.unlam.tallerweb1.domain.estados.RepositorioEstado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("servicioRegistro")
@Transactional
public class ServicioRegistroImpl implements ServicioRegistro {

    private RepositorioUsuario repositorioUsuario;
    private RepositorioEstado repositorioEstado;

    @Autowired
    public ServicioRegistroImpl(RepositorioUsuario repositorioUsuario) {
        this.repositorioUsuario = repositorioUsuario;
        this.repositorioEstado = repositorioEstado;
    }

    @Override
    public void registrarUsuario(DatosRegistro datosRegistro) {
        Usuario usuarioNuevo = new Usuario();

        //hardcode para que funcione. En teoria con la base levantada y llena,
        // el repositorioEstado deberia funcionar
        //Estado estadoUsuarioNuevo = new Cardiaco();

        //if (repositorioEstado.buscarEstado(datosRegistro.getEstado()) == null) {
        //    throw new IllegalArgumentException("El estado seleccionado No existe");
        //}

        if (repositorioUsuario.buscar(datosRegistro.getEmail()) != null) {
            throw new IllegalArgumentException("El correo electrónico ya está registrado");
        }

        //Estado estadoUsuarioNuevo = repositorioEstado.buscarEstado(datosRegistro.getEstado());

        usuarioNuevo.setEmail(datosRegistro.getEmail());
        usuarioNuevo.setPassword(datosRegistro.getPassword());
        usuarioNuevo.setNombre(datosRegistro.getNombre());
        //usuarioNuevo.setEstado(estadoUsuarioNuevo);
        usuarioNuevo.setEdad(datosRegistro.getEdad());
        usuarioNuevo.setAltura(datosRegistro.getAltura());
        usuarioNuevo.setPeso(datosRegistro.getPeso());
        usuarioNuevo.setGenero(datosRegistro.getGenero());
        usuarioNuevo.setObjetivo(datosRegistro.getObjetivo());

        // Guardar al usuario en el repositorio
        repositorioUsuario.guardar(usuarioNuevo);
    }
}

