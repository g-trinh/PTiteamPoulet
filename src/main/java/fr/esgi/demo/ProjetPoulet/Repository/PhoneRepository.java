package fr.esgi.demo.ProjetPoulet.Repository;

import fr.esgi.demo.ProjetPoulet.Entity.Phone;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Guillaume on 20/03/2015.
 */
@Repository
public interface PhoneRepository extends CrudRepository<Phone, Long> {

    public List<Phone> findByStolen(boolean stolen);

}
