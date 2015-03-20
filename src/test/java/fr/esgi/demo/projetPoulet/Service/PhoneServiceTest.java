package fr.esgi.demo.projetPoulet.Service;

import fr.esgi.demo.DemoApplication;
import fr.esgi.demo.ProjetPoulet.DTO.PhoneDTO;
import fr.esgi.demo.ProjetPoulet.Entity.Phone;
import fr.esgi.demo.ProjetPoulet.Service.PhoneService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by Guillaume on 20/03/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DemoApplication.class )
public class PhoneServiceTest {
    @Autowired
    PhoneService phoneService;

    PhoneDTO phoneDTO;

    @Before
    public void setUp(){
        phoneDTO = new PhoneDTO();
    }

    @Test
    public void should_ReturnFalse_withSerialNumberAndNumberNull(){
        //given
        phoneDTO.setNumber(null);
        phoneDTO.setSerialNumber(null);

        assertThat( phoneService.validateBeforeCreation( phoneDTO ), is( false ) );
    }

    @Test
    public void should_ReturnFalse_withSerialNumber(){
        //given
        phoneDTO.setNumber("0321468975");
        phoneDTO.setSerialNumber(null);

        assertThat( phoneService.validateBeforeCreation( phoneDTO ), is( false ) );
    }

    @Test
    public void should_ReturnFalse_withNumberNull(){
        //given
        phoneDTO.setNumber(null);
        phoneDTO.setSerialNumber("3215697");

        assertThat( phoneService.validateBeforeCreation( phoneDTO ), is( false ) );
    }

    @Test
    public void should_ReturnTrue_withSerialNumberAndNumberNotNull(){
        //given
        phoneDTO.setNumber("oihdsfoihfs");
        phoneDTO.setSerialNumber("3215697");

        assertThat( phoneService.validateBeforeCreation( phoneDTO ), is( true ) );
    }

    @Test
    public void should_setAnonymousMode(){
        //given
        Phone phoneTest = new Phone();
        phoneTest.setLastName("Lenain");
        phoneTest.setFirstName("Popo");
        phoneTest = phoneService.setAnonymMode(phoneTest);
        assertThat(phoneTest.getFirstName(),is("*******"));
        assertThat(phoneTest.getLastName(),is("*******"));
    }
}
