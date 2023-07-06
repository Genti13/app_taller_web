package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.domain.conditionScore.ConditionScore;
import ar.edu.unlam.tallerweb1.domain.dieta.Dieta;
import ar.edu.unlam.tallerweb1.domain.ejercicio.Ejercicio;
import ar.edu.unlam.tallerweb1.domain.estados.Cardiaco;
import ar.edu.unlam.tallerweb1.domain.estados.Estado;
import ar.edu.unlam.tallerweb1.domain.menu.Ingrediente;
import ar.edu.unlam.tallerweb1.domain.menu.Menu;
import ar.edu.unlam.tallerweb1.domain.menu.Plato;
import ar.edu.unlam.tallerweb1.domain.rutina.Rutina;
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

    @Test
    @Transactional @Rollback
    public void retornaLaUltimaDietaRegistradaPorElUsuario(){
        Usuario usuario = makePersona();

        List<Dieta> dietas = makeDietas();
        usuario.setDieta(dietas);

        repositorioUsuario.guardar(usuario);

        for(Dieta dieta : dietas){
            session().save(dieta);
        }

        Dieta dietaBuscada = usuario.getDieta().get(usuario.getDieta().size()-1);
        Dieta dieta = repositorioDietaImp.getUltimaDieta(usuario.getEmail());

        assertThat(dieta).isNotNull();
        assertThat(dieta).isEqualTo(dietaBuscada);
    }

    @Test
    @Transactional @Rollback
    public void retornaTodasLasDietasDeUnUsuario(){
        Usuario usuario = makePersona();

        List<Dieta> dietas = makeDietas();
        usuario.setDieta(dietas);

        repositorioUsuario.guardar(usuario);

        for(Dieta dieta : dietas){
            session().save(dieta);
        }

        List<Dieta> dietasBuscadas = repositorioDietaImp.getAllDietas(usuario.getEmail());

        assertThat(dietasBuscadas).isNotNull();
        assertThat(dietasBuscadas).isEqualTo(dietas);

    }

    private List<Dieta> makeDietas(){
        List<Dieta> dietas = new ArrayList<Dieta>();

        dietas.add(makeDieta());
        dietas.add(makeDieta());

        return dietas;
    }
    private Menu makeMenu() {
        Ingrediente in1 = new Ingrediente("Pepino",10);
        Ingrediente ing2 = new Ingrediente("Berenjena",10);

        List<Ingrediente> ingredientes = new ArrayList<>();

        ingredientes.add(in1);
        ingredientes.add(ing2);

        Plato plato = new Plato(ingredientes);

        return new Menu(plato);
    }

    private Rutina makeRutina(){
        Ejercicio ej1 = new Ejercicio("Pesas");
        Ejercicio ej2 = new Ejercicio("Flexiones");

        ej1.setDuracion(5);
        ej2.setDuracion(5);

        List<Ejercicio> ejercicios = new ArrayList<>();

        ejercicios.add(ej1);
        ejercicios.add(ej2);

        return  new Rutina(ejercicios);
    }

    private Usuario makePersona(){
        Usuario persona = new Usuario();
        persona.setEmail("Genti@programar.com");

        return persona;
    }

    private Dieta makeDieta(){
        Dieta dieta = new Dieta();

        ArrayList<Rutina> rutinas = new ArrayList<Rutina>();
        rutinas.add(makeRutina());

        List<Menu> menus = new ArrayList<Menu>();
        menus.add(makeMenu());

        dieta.setMenus(menus);
        dieta.setRutinas(rutinas);

        return dieta;
    }
}
