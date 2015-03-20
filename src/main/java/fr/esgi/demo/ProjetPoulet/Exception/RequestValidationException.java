package fr.esgi.demo.ProjetPoulet.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * Created by Guillaume on 20/03/2015.
 */
@ResponseStatus(BAD_REQUEST)
public class RequestValidationException extends RuntimeException {
    public RequestValidationException(String s) {
        super(s);
    }
}
