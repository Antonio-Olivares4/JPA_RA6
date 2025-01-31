package com.hlc.usuario_uno_a_uno.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.hlc.usuario_uno_a_uno.entidad.InformacionUsuario;
import com.hlc.usuario_uno_a_uno.entidad.Usuario;
import com.hlc.usuario_uno_a_uno.entidad.enumerado.Rol;
import com.hlc.usuario_uno_a_uno.repositorio.UsuarioRepositorio;

@Component
public class InicializarDatos implements CommandLineRunner{
	
	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	
	
	@Override
	public void run(String... args) throws Exception {
		 if (usuarioRepositorio.count() == 0) { // Verificar si la BD está vacía
	           
			 
			 	InformacionUsuario info1 = new InformacionUsuario("user1@email.com", "12345678");
	            Usuario usuario1 = new Usuario("user1", "password123", info1, Rol.ADMIN);
	            info1.setUsuario(usuario1);
	            usuarioRepositorio.save(usuario1);
	            
	            InformacionUsuario info2 = new InformacionUsuario("user2@email.com", "87654321");
	            Usuario usuario2 = new Usuario("user2", "password456", info2, Rol.USER);
	            info2.setUsuario(usuario2);

	            usuarioRepositorio.save(usuario2);
	            
	            System.out.println("Usuarios insertados en la base de datos.");
	        }
	}

}
