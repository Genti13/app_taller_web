package ar.edu.unlam.tallerweb1.domain;


import ar.edu.unlam.tallerweb1.domain.dieta.Dieta;
import ar.edu.unlam.tallerweb1.domain.dieta.ServicioDietaImp;
import ar.edu.unlam.tallerweb1.domain.ejercicio.Ejercicio;
import ar.edu.unlam.tallerweb1.domain.ejercicio.EjercicioRepetidoException;
import ar.edu.unlam.tallerweb1.domain.estados.Cardiaco;
import ar.edu.unlam.tallerweb1.domain.estados.Estado;
import ar.edu.unlam.tallerweb1.domain.menu.*;
import ar.edu.unlam.tallerweb1.domain.rutina.Rutina;
import ar.edu.unlam.tallerweb1.domain.rutina.RutinaRestringidaException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServicioMenuTest {
        private ServicioMenuImp servicioMenu;
        private Menu menu;
        private Plato plato;
        private Estado estadoMock;
        private Ingrediente ingrediente;
        private ServicioDietaImp servicioDieta;

        @Before
        public void init() {
            List<Ingrediente> ingredientes = new ArrayList<>();
            plato = new Plato(ingredientes);
            servicioMenu = new ServicioMenuImp();
            servicioDieta = new ServicioDietaImp();

            estadoMock = mock(Estado.class);
            menu = mock(Menu.class);
            when(estadoMock.getRestricciones()).thenReturn(new ArrayList<>());

        }

    @Test
    public void queSePuedaAgregarUnPlatoPermitido() throws MenuRestringidoException, PlatoExistenteException {
        Ingrediente ingrediente1 = new Ingrediente("lentejas", 20);
        Ingrediente ingrediente2 = new Ingrediente("caldo", 30);
        Ingrediente ingrediente3 = new Ingrediente("papas", 10);
        Ingrediente ingrediente4 = new Ingrediente("chorizo colorado", 15);
        Ingrediente ingrediente5 = new Ingrediente("panceta", 25);
        Ingrediente ingrediente6 = new Ingrediente("verduras", 5);

        List<Ingrediente> ingredientes= plato.getIngredientes();
        Plato plato = new Plato(ingredientes);
        List<Plato> platos = new  ArrayList<>();
        menu = new Menu();
        servicioMenu.agregarPlato(plato, menu, estadoMock);
        List<Plato> platosTest= menu.getPlatos();

        assertTrue(platosTest.contains(plato));

    }

    @Test(expected = PlatoExistenteException.class)
    public void queNoSePuedaAgregarUnMismoPlatoDosVeces() throws MenuRestringidoException, PlatoExistenteException {
        Ingrediente ingrediente1 = new Ingrediente("lentejas", 20);
        Ingrediente ingrediente2 = new Ingrediente("caldo", 30);
        Ingrediente ingrediente3 = new Ingrediente("papas", 10);
        Ingrediente ingrediente4 = new Ingrediente("chorizo colorado", 15);
        Ingrediente ingrediente5 = new Ingrediente("panceta", 25);
        Ingrediente ingrediente6 = new Ingrediente("verduras", 5);

        List<Ingrediente> ingredientes= new ArrayList<Ingrediente>();
        ingredientes.add(ingrediente1);
        ingredientes.add(ingrediente2);
        ingredientes.add(ingrediente3);
        ingredientes.add(ingrediente4);
        ingredientes.add(ingrediente5);
        ingredientes.add(ingrediente6);

        Plato plato1 = new Plato(ingredientes);
        Plato plato2 = new Plato(ingredientes);
        List<Plato> platos = new  ArrayList<>();
        menu = new Menu();
        servicioMenu.agregarPlato(plato1, menu, estadoMock);
        servicioMenu.agregarPlato(plato2, menu, estadoMock);
        List<Plato> platosTest= menu.getPlatos();

    }

    @Test(expected = MenuRestringidoException.class)
    public void queSiSeIntentaAgregarUnMenuRestringidoLanceUnaExcepcion() throws MenuRestringidoException, PlatoExistenteException {
        Ingrediente ingrediente0 = new Ingrediente("sal", 5);
        Ingrediente ingrediente1 = new Ingrediente("lentejas", 20);
        Ingrediente ingrediente2 = new Ingrediente("caldo", 30);
        Ingrediente ingrediente3 = new Ingrediente("papas", 10);
        Ingrediente ingrediente4 = new Ingrediente("chorizo colorado", 15);
        Ingrediente ingrediente5 = new Ingrediente("panceta", 25);
        Ingrediente ingrediente6 = new Ingrediente("verduras", 5);


        List<Ingrediente> ingredientes = new ArrayList<Ingrediente>();
        ingredientes.add(ingrediente0);
        ingredientes.add(ingrediente1);
        ingredientes.add(ingrediente2);
        ingredientes.add(ingrediente3);
        ingredientes.add(ingrediente4);
        ingredientes.add(ingrediente5);
        ingredientes.add(ingrediente6);

        Plato plato = new Plato(ingredientes);
        List<Plato> platos = new ArrayList<>();
        platos.add(plato);
        menu = new Menu(platos);
        List<Menu> menus = new ArrayList<>();
        menus.add(menu);
        Dieta dieta = new Dieta();
        dieta.setMenus(menus);
        List<String> restricciones = new Cardiaco().getRestricciones();
        servicioDieta.agregarMenu(dieta, menu, restricciones);

    }

        @Test(expected = PlatoNoExistenteException.class)
        public void queSiSeIntentaModificarUnMenuQueNoExisteLanceUnaExcepcion() throws PlatoNoExistenteException {
            Ingrediente ingrediente0 = new Ingrediente("sal", 5);
            Ingrediente ingrediente1 = new Ingrediente("lentejas", 20);
            Ingrediente ingrediente2 = new Ingrediente("caldo", 30);
            Ingrediente ingrediente3 = new Ingrediente("papas", 10);
            Ingrediente ingrediente4 = new Ingrediente("chorizo colorado", 15);
            Ingrediente ingrediente5 = new Ingrediente("panceta", 25);
            Ingrediente ingrediente6 = new Ingrediente("verduras", 5);


            List<Ingrediente> ingredientes = new ArrayList<Ingrediente>();
            ingredientes.add(ingrediente0);
            ingredientes.add(ingrediente1);
            ingredientes.add(ingrediente2);
            ingredientes.add(ingrediente3);
            ingredientes.add(ingrediente4);
            ingredientes.add(ingrediente5);
            ingredientes.add(ingrediente6);

            Plato plato1 = new Plato(ingredientes);
            Plato plato2 = new Plato();
            List<Plato> platos = new ArrayList<>();
            platos.add(plato1);
            menu = new Menu(platos);
            List<Menu> menus = new ArrayList<>();
            menus.add(menu);
            Dieta dieta = new Dieta();
            dieta.setMenus(menus);
            servicioMenu.modificarPlato(plato2, menu);

    }
    @Test
    public void queSePuedaModificarUnMenuExistente() throws PlatoNoExistenteException {
        Ingrediente ingrediente0 = new Ingrediente("sal", 5);
        Ingrediente ingrediente1 = new Ingrediente("lentejas", 20);
        Ingrediente ingrediente2 = new Ingrediente("caldo", 30);
        Ingrediente ingrediente3 = new Ingrediente("papas", 10);
        Ingrediente ingrediente4 = new Ingrediente("chorizo colorado", 15);
        Ingrediente ingrediente5 = new Ingrediente("panceta", 25);
        Ingrediente ingrediente6 = new Ingrediente("verduras", 5);

        List<Ingrediente> ingredientesP1 = new ArrayList<Ingrediente>();
        ingredientesP1.add(ingrediente0);
        ingredientesP1.add(ingrediente1);
        ingredientesP1.add(ingrediente2);
        ingredientesP1.add(ingrediente3);
        ingredientesP1.add(ingrediente4);
        ingredientesP1.add(ingrediente5);
        ingredientesP1.add(ingrediente6);

        List<Ingrediente> ingredientesP2 = new ArrayList<Ingrediente>();
        ingredientesP2.add(ingrediente1);
        ingredientesP2.add(ingrediente2);
        ingredientesP2.add(ingrediente3);
        ingredientesP2.add(ingrediente4);
        ingredientesP2.add(ingrediente5);
        ingredientesP2.add(ingrediente6);

        Plato plato1 = new Plato(ingredientesP1);
        Plato plato2 = new Plato(ingredientesP2);
        List<Plato> platos = new ArrayList<>();
        platos.add(plato1);
        menu = new Menu(platos);
        List<Menu> menus = new ArrayList<>();
        menus.add(menu);
        Dieta dieta = new Dieta();
        dieta.setMenus(menus);
        servicioMenu.modificarPlato(plato2, menu);

        assertTrue(menu.getPlatos().contains(plato2));

    }

    @Test
    public void queSePuedaEliminarUnPlatoDelMenu() throws PlatoNoExistenteException {
        Ingrediente ingrediente0 = new Ingrediente("sal", 5);
        Ingrediente ingrediente1 = new Ingrediente("lentejas", 20);
        Ingrediente ingrediente2 = new Ingrediente("caldo", 30);
        Ingrediente ingrediente3 = new Ingrediente("papas", 10);
        Ingrediente ingrediente4 = new Ingrediente("chorizo colorado", 15);
        Ingrediente ingrediente5 = new Ingrediente("panceta", 25);
        Ingrediente ingrediente6 = new Ingrediente("verduras", 5);

        List<Ingrediente> ingredientesP1 = new ArrayList<Ingrediente>();
        ingredientesP1.add(ingrediente0);
        ingredientesP1.add(ingrediente1);
        ingredientesP1.add(ingrediente2);
        ingredientesP1.add(ingrediente3);
        ingredientesP1.add(ingrediente4);
        ingredientesP1.add(ingrediente5);
        ingredientesP1.add(ingrediente6);

        List<Ingrediente> ingredientesP2 = new ArrayList<Ingrediente>();
        ingredientesP2.add(ingrediente1);
        ingredientesP2.add(ingrediente2);
        ingredientesP2.add(ingrediente3);
        ingredientesP2.add(ingrediente4);
        ingredientesP2.add(ingrediente5);
        ingredientesP2.add(ingrediente6);

        Plato plato1 = new Plato(ingredientesP1);
        Plato plato2 = new Plato(ingredientesP2);
        List<Plato> platos = new ArrayList<>();
        platos.add(plato1);
        platos.add(plato2);
        menu = new Menu(platos);
        List<Menu> menus = new ArrayList<>();
        menus.add(menu);
        Dieta dieta = new Dieta();
        dieta.setMenus(menus);
        servicioMenu.eliminarPlato(plato1, menu);

        assertFalse(menu.getPlatos().contains(plato1));

    }

    @Test(expected = PlatoNoExistenteException.class)
    public void queNoSePuedaEliminarUnPlatoInexistente() throws PlatoNoExistenteException {
        Ingrediente ingrediente0 = new Ingrediente("sal", 5);
        Ingrediente ingrediente1 = new Ingrediente("lentejas", 20);
        Ingrediente ingrediente2 = new Ingrediente("caldo", 30);
        Ingrediente ingrediente3 = new Ingrediente("papas", 10);
        Ingrediente ingrediente4 = new Ingrediente("chorizo colorado", 15);
        Ingrediente ingrediente5 = new Ingrediente("panceta", 25);
        Ingrediente ingrediente6 = new Ingrediente("verduras", 5);

        List<Ingrediente> ingredientesP1 = new ArrayList<Ingrediente>();
        ingredientesP1.add(ingrediente0);
        ingredientesP1.add(ingrediente1);
        ingredientesP1.add(ingrediente2);
        ingredientesP1.add(ingrediente3);
        ingredientesP1.add(ingrediente4);
        ingredientesP1.add(ingrediente5);
        ingredientesP1.add(ingrediente6);

        List<Ingrediente> ingredientesP2 = new ArrayList<Ingrediente>();
        ingredientesP2.add(ingrediente1);
        ingredientesP2.add(ingrediente2);
        ingredientesP2.add(ingrediente3);
        ingredientesP2.add(ingrediente4);
        ingredientesP2.add(ingrediente5);
        ingredientesP2.add(ingrediente6);

        Plato plato1 = new Plato(ingredientesP1);
        Plato plato2 = new Plato(ingredientesP2);
        List<Plato> platos = new ArrayList<>();
        platos.add(plato1);
        menu = new Menu(platos);
        List<Menu> menus = new ArrayList<>();
        menus.add(menu);
        Dieta dieta = new Dieta();
        dieta.setMenus(menus);
        servicioMenu.eliminarPlato(plato2, menu);
    }

}
