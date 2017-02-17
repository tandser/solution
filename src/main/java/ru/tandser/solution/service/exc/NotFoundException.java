package ru.tandser.solution.service.exc;

public class NotFoundException extends RuntimeException {

    public NotFoundException() {}

    public NotFoundException(String message) {
        super(message);
    }
}