package ar.edu.unlam.tallerweb1.domain.usuarios;

import org.springframework.transaction.annotation.Transactional;
@Transactional
public interface ServicioProfile {

    Usuario getUserData(String mail);
}
