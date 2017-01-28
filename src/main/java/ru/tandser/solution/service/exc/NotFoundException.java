package ru.tandser.solution.service.exc;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }
}