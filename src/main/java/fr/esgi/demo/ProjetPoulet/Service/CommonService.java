package fr.esgi.demo.ProjetPoulet.Service;

import org.springframework.stereotype.Service;

/**
 * Created by Guillaume on 20/03/2015.
 */
@Service
public class CommonService {
    public boolean validateNotNull( Object... objects ){
        for( Object object : objects ){
            if( null == object ) return false;
        }

        return true;
    }
}
