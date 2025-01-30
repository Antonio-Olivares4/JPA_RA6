package com.hlc.usuario_uno_a_uno.servicio;



import java.util.Optional;

import com.hlc.usuario_uno_a_uno.entidad.Usuario;
import com.hlc.usuario_uno_a_uno.repositorio.UsuarioRepositorio;

public class UsuarioServicioImpl implements UsuarioServicio {

	
	private final UsuarioRepositorio usuarioRepositorio;
    
    public UsuarioServicioImpl(UsuarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }

    @Override
    public Usuario guardarOActualizarUsuario(Usuario usuario) {
    	if (usuario.getId() != null && usuarioRepositorio.existsById(usuario.getId())) {
            Optional<Usuario> usuarioExistente = usuarioRepositorio.findById(usuario.getId());
            if (usuarioExistente.isPresent()) {
                Usuario actualizado = usuarioExistente.get();
                actualizado.setUsername(usuario.getUsername());
                actualizado.setPassword(usuario.getPassword());
                actualizado.setInformacionUsuario(usuario.getInformacionUsuario());
                return usuarioRepositorio.save(actualizado);
            }
        }
        return usuarioRepositorio.save(usuario);

    }

    @Override
    public Usuario obtenerUsuarioPorId(Long id) {
        Optional<Usuario> usuario = usuarioRepositorio.findById(id);
        return  usuario.orElseThrow(() -> new RuntimeException("No se encuentra el usuario"));
    }

    @Override
    public void eliminarUsuario(Long id) {
        usuarioRepositorio.deleteById(id);
    }


}
