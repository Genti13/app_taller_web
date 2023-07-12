package ar.edu.unlam.tallerweb1.domain.usuarios;

import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;
import org.springframework.stereotype.Repository;

// Interface que define los metodos del Repositorio de Usuarios.
@Repository
public interface RepositorioUsuario {

	Usuario buscarUsuario(String email, String password);
	void guardar(Usuario usuario);
    Usuario buscar(String email);
	void modificar(Usuario usuario);

}
