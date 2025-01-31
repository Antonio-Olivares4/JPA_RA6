package com.hlc.usuario_uno_a_uno.controlador;
import com.hlc.usuario_uno_a_uno.controlador.UsuarioControlador;
import com.hlc.usuario_uno_a_uno.entidad.Usuario;
import com.hlc.usuario_uno_a_uno.servicio.UsuarioServicio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UsuarioControladorTest {

    @Mock
    private UsuarioServicio usuarioServicio;

    @Mock
    private Model model;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private UsuarioControlador usuarioControlador;

    private Usuario usuario;

    @BeforeEach
    void setUp() {
        usuario = new Usuario();
        usuario.setId(1L);
        usuario.setUsername("testuser");
        usuario.setPassword("password");
    }

    @Test
    void testListarUsuarios() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Usuario> usuarios = new PageImpl<>(Arrays.asList(usuario));
        when(usuarioServicio.listarUsuariosPaginados(pageable)).thenReturn(usuarios);

        String view = usuarioControlador.listarUsuarios(0, 10, model);
        
        assertEquals("usuarios/listar", view);
        verify(model).addAttribute("usuarios", usuarios);
    }

    @Test
    void testMostrarFormularioNuevoUsuario() {
        String view = usuarioControlador.mostrarFormularioNuevoUsuario(model);
        
        assertEquals("usuarios/formulario", view);
        verify(model).addAttribute(eq("usuario"), any(Usuario.class));
    }

    @Test
    void testGuardarUsuarioConErrores() {
        when(bindingResult.hasErrors()).thenReturn(true);

        String view = usuarioControlador.guardarUsuario(usuario, bindingResult, model);
        
        assertEquals("usuarios/formulario", view);
        verify(model).addAttribute("usuario", usuario);
    }

    @Test
    void testGuardarUsuarioSinErrores() {
        when(bindingResult.hasErrors()).thenReturn(false);

        String view = usuarioControlador.guardarUsuario(usuario, bindingResult, model);
        
        assertEquals("redirect:/usuarios", view);
        verify(usuarioServicio).guardarOActualizarUsuario(usuario);
    }

    @Test
    void testMostrarFormularioEditarUsuario() {
        when(usuarioServicio.obtenerUsuarioPorId(1L)).thenReturn(usuario);
        
        String view = usuarioControlador.mostrarFormularioEditarUsuario(1L, model);
        
        assertEquals("usuarios/formulario", view);
        verify(model).addAttribute("usuario", usuario);
    }

    @Test
    void testEliminarUsuario() {
        String view = usuarioControlador.eliminarUsuario(1L);
        
        assertEquals("redirect:/usuarios", view);
        verify(usuarioServicio).eliminarUsuario(1L);
    }
}
