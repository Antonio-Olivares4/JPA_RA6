package com.hlc.usuario_uno_a_uno.error;
import jakarta.validation.ConstraintViolationException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    @RequestMapping("/error/validacion")
    public String handleValidationException(ConstraintViolationException e, Model model) {
        model.addAttribute("mensajeError", "Error de validación: " + e.getMessage());
        return "error/validacion";
    }
}