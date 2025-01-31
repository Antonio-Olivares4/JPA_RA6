package com.hlc.usuario_uno_a_uno.entidad;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToOne;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;


@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true, length = 50)
    @NotBlank(message = "El nombre de usuario no puede estar vacío")
    private String username;
    
    @Column(nullable = false)
    @NotBlank(message = "La contraseña no puede ser nula")
    private String password;
    
    @Valid
    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private InformacionUsuario informacionUsuario;
    
    // Constructor vacío
    public Usuario() {}
    
    // Constructor con parámetros
    public Usuario(String username, String password, InformacionUsuario informacionUsuario) {
        this.username = username;
        this.password = password;
        this.informacionUsuario = informacionUsuario;
    }
    
    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public InformacionUsuario getInformacionUsuario() {
        return informacionUsuario;
    }

    public void setInformacionUsuario(InformacionUsuario informacionUsuario) {
        this.informacionUsuario = informacionUsuario;
    }
}
