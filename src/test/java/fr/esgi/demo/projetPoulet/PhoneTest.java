package fr.esgi.demo.projetPoulet;

import fr.esgi.demo.DemoApplication;
import fr.esgi.demo.ProjetPoulet.Entity.Phone;
import fr.esgi.demo.ProjetPoulet.Repository.PhoneRepository;
import fr.esgi.demo.ProjetPoulet.Service.PhoneService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by Guillaume on 20/03/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DemoApplication.class )
public class PhoneTest {
    @Autowired
    private PhoneService phoneService;

    @Test
    public void phoneTest(){
        List<Phone> phones = phoneService.getAllStolen();

        for( Phone phone : phones ){
            assertThat( phone.getFirstName(), is("*******") );
        }
    }
}
