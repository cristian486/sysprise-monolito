package br.com.sysprise.infra.exception;

import org.springframework.validation.FieldError;

record DadosErroValidacao(String campo, String mensagem) {

    public DadosErroValidacao(FieldError fieldError) {
        this(fieldError.getField(), fieldError.getDefaultMessage());
    }
}
