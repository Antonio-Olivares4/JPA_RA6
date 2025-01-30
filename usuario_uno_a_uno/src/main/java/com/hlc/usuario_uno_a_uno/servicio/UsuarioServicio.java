package com.hlc.usuario_uno_a_uno.servicio;

import com.hlc.usuario_uno_a_uno.entidad.Usuario;

public interface UsuarioServicio {
    
    /**
     * Guarda o actualiza un usuario en el sistema.
     * @param usuario El objeto Usuario a registrar o actualizar.
     * @return El usuario registrado o actualizado con ID asignado.
     */
    Usuario guardarOActualizarUsuario(Usuario usuario);
    
    /**
     * Obtiene un usuario por su ID.
     * @param id Identificador del usuario.
     * @return El usuario si existe, de lo contrario, null.
     */
    Usuario obtenerUsuarioPorId(Long id);
    
    /**
     * Elimina un usuario del sistema.
     * @param id Identificador del usuario a eliminar.
     */
    void eliminarUsuario(Long id);
}
