package fr.esgi.demo.ProjetPoulet.Service;

import fr.esgi.demo.ProjetPoulet.Entity.Phone;
import fr.esgi.demo.ProjetPoulet.Repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by Guillaume on 20/03/2015.
 */
@Component
public class InitRepository {

    @Autowired
    private PhoneRepository phoneRepository;

    @Scheduled(fixedDelayString = "1000")
    public void init() {
        if(phoneRepository.count() == 0) {
            for( int i = 1; i < 10; i++ ){
                Phone.Builder builder = Phone.newUser().withSerialNumber("0321468").withFirstName("Guillaume").withLastName("Trinh").withNumber("0321454");

                if( i % 2 == 0 ) builder.withStolen(true);
                else builder.withStolen(false);

                Phone phone = builder.build();

                phoneRepository.save(phone);
            }
        }
    }
}
