package fr.futuretech.esportclash.core.domain.exceptions;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String entity, String key) {
        super(
            String.format("%s with key %s not found", entity, key)
        );
    }
}
