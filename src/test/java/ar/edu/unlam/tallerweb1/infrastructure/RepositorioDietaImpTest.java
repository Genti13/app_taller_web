package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.domain.conditionScore.ConditionScore;
import ar.edu.unlam.tallerweb1.domain.dieta.Dieta;
import ar.edu.unlam.tallerweb1.domain.usuarios.RepositorioUsuario;
import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class RepositorioDietaImpTest extends SpringTest {

    @Autowired
    private RepositorioDietaImp repositorioDietaImp;

    @Autowired
    private RepositorioUsuario repositorioUsuario;

    @Test
    @Transactional @Rollback
    public void retornarUnaDietaCuandoSeBuscaLaDietaDelUsuario(){
        Usuario usuario = new Usuario();
        usuario.setEmail("asd@asd");

        List<Dieta> dietas = new ArrayList<>();
        Dieta dieta = new Dieta();
        dieta.setUsuario(usuario);
        dietas.add(dieta);


        ConditionScore conditionScore = new ConditionScore();

        usuario.setConditionScore(conditionScore);
        usuario.setDieta(dietas);

        session().save(conditionScore);
        session().save(usuario);

        List<Dieta> dietasBuscadas = repositorioDietaImp.buscarDietaConMail("asd@asd");

        assertThat(session().isConnected()).isTrue();
        assertThat(dietasBuscadas).isNotNull();
        assertThat(dietasBuscadas).isEqualTo(dietas);
    }

}
