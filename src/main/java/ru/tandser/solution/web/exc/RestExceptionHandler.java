package ru.tandser.solution.web.exc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.annotation.*;
import ru.tandser.solution.service.exc.NotFoundException;
import ru.tandser.solution.web.Principal;

import javax.servlet.http.HttpServletRequest;

import static org.slf4j.event.Level.ERROR;
import static org.slf4j.event.Level.WARN;
import static org.springframework.core.Ordered.HIGHEST_PRECEDENCE;
import static org.springframework.core.Ordered.LOWEST_PRECEDENCE;
import static org.springframework.http.HttpStatus.*;

@ControllerAdvice(annotations = RestController.class)
public class RestExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private static class ErrorInfo {
        public final String url;
        public final String cause;
        public final String details;

        public ErrorInfo(String url, Exception exc) {
            this.url     = url;
            this.cause   = exc.getClass().getSimpleName();
            this.details = exc.getLocalizedMessage();
        }
    }

    @ExceptionHandler(NotFoundException.class)
    @Order(HIGHEST_PRECEDENCE)
    @ResponseStatus(NOT_FOUND)
    @ResponseBody
    public ErrorInfo catchException(HttpServletRequest req, NotFoundException exc) {
        return logAndGetErrorInfo(req, exc, WARN);
    }

    @ExceptionHandler(BadRequestException.class)
    @Order(HIGHEST_PRECEDENCE)
    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    public ErrorInfo catchException(HttpServletRequest req, BadRequestException exc) {
        return logAndGetErrorInfo(req, exc, WARN);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @Order(HIGHEST_PRECEDENCE)
    @ResponseStatus(CONFLICT)
    @ResponseBody
    public ErrorInfo catchException(HttpServletRequest req, DataIntegrityViolationException exc) {
        return logAndGetErrorInfo(req, exc, WARN);
    }

    @ExceptionHandler(ObjectOptimisticLockingFailureException.class)
    @Order(HIGHEST_PRECEDENCE)
    @ResponseStatus(CONFLICT)
    @ResponseBody
    public ErrorInfo catchException(HttpServletRequest req, ObjectOptimisticLockingFailureException exc) {
        return logAndGetErrorInfo(req, exc, WARN);
    }

    @ExceptionHandler(Exception.class)
    @Order(LOWEST_PRECEDENCE)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorInfo catchException(HttpServletRequest req, Exception exc) {
        return logAndGetErrorInfo(req, exc, ERROR);
    }

    private ErrorInfo logAndGetErrorInfo(HttpServletRequest req, Exception exc, Level level) {
        Principal principal = Principal.get();

        String username = principal != null
                ? principal.getUsername()
                : "anonymous";

        switch (level) {
            default: log.debug("{}: {}, {}", username, req.getRequestURL(), exc.toString());
        }

        return new ErrorInfo(req.getRequestURL().toString(), exc);
    }
}