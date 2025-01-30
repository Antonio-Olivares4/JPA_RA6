package com.hlc.usuario_uno_a_uno.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hlc.usuario_uno_a_uno.entidad.Usuario;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {

}
