package fr.esgi.demo.ProjetPoulet.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NO_CONTENT;

/**
 * Created by Guillaume on 20/03/2015.
 */
@ResponseStatus(NO_CONTENT)
public class EmptyContentException extends RuntimeException {
    public EmptyContentException(String s) {
        super(s);
    }
}
