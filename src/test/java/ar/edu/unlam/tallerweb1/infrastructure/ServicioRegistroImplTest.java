package ar.edu.unlam.tallerweb1.infrastructure;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.delivery.ControladorRegistro;
import ar.edu.unlam.tallerweb1.domain.estados.Cardiaco;
import ar.edu.unlam.tallerweb1.domain.estados.Estado;
import ar.edu.unlam.tallerweb1.domain.usuarios.RepositorioUsuario;
import ar.edu.unlam.tallerweb1.domain.usuarios.ServicioRegistroImpl;
import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ServicioRegistroImplTest extends SpringTest {

    private ServicioRegistroImpl servicioRegistro;

    private RepositorioUsuario repositorioUsuario;

    private ControladorRegistro controladorRegistro;

    @BeforeEach
    public void init() {

        repositorioUsuario = mock(RepositorioUsuario.class);
        servicioRegistro = new ServicioRegistroImpl(repositorioUsuario);
    }

    @Test
    public void testRegistrarUsuarioExitoso() {
        // Datos de prueba
        Usuario usuario = new Usuario();
        usuario.setEmail("usuario@example.com");
        // ... Completa con más datos si es necesario

        // Configurar el comportamiento del repositorioUsuario simulado
        when(repositorioUsuario.buscar(usuario.getEmail())).thenReturn(null); // El correo no está registrado

        // Ejecutar el método a probar
       // servicioRegistro.registrarUsuario(usuario);

        // Verificar que se llamó al método guardar del repositorioUsuario con el usuario adecuado
        verify(repositorioUsuario).guardar(usuario);
        }

    @Test
    public void queSePuedaGuardarUsuarioEnBaseDeDatos() {

        Usuario usuario = new Usuario();
        Estado cardiaco = new Cardiaco();

        usuario.setEmail("usuario@example.com");
        usuario.setPassword("123456");
       /* usuario.setNombre("Matias");
        usuario.setApellido("Rojas");
        usuario.setEdad(26);
        usuario.setAltura(170.0);
        usuario.setPeso(70);
        usuario.setGenero("Masculino");
        usuario.setEstado(cardiaco);
        usuario.setObjetivo(0); */

        //servicioRegistro.registrarUsuario(usuario);
       // RepositorioUsuario repo = new RepositorioUsuario();

        //servicioRegistro.registrarUsuario(usuario);

        Usuario usuarioGuardado = repositorioUsuario.buscar(usuario.getEmail());
        assertNotNull(usuarioGuardado);
        assertEquals(usuario.getEmail(), usuarioGuardado.getEmail());

    }

}
