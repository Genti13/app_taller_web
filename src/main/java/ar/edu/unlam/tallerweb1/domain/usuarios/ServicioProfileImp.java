package ar.edu.unlam.tallerweb1.domain.usuarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("serviceProfile")
public class ServicioProfileImp implements ServicioProfile{

    private RepositorioUsuario repositorioUsuario;

    @Autowired
    public ServicioProfileImp(RepositorioUsuario repositorioUsuario){
        this.repositorioUsuario = repositorioUsuario;
    }

    @Override
    public Usuario getUserData(String mail) {
        return repositorioUsuario.buscar("mail");
    }
}
