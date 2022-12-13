package br.com.compass.cais.exceptions;

public class EntityInUseException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public EntityInUseException(String mensagem) {
        super(mensagem);
    }
}
