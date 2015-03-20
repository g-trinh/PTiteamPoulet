package fr.esgi.demo.projetPoulet.Service;

import fr.esgi.demo.DemoApplication;
import fr.esgi.demo.ProjetPoulet.Service.CommonService;
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
public class CommonServiceTest {
    @Autowired
    CommonService commonService;

    @Test
    public void should_ReturnFalse_with2NullObject(){
        //given
        Object object1 = null, object2 = null;

        //then
        assertThat( commonService.validateNotNull( object1, object2 ), is( false ) );
    }

    @Test
    public void should_ReturnFalse_with1NullObject(){
        //given
        Object object1 = null, object2 = new Object();

        //then
        assertThat( commonService.validateNotNull( object1, object2 ), is( false ) );
    }

    @Test
    public void should_ReturnTrue_with2Objects(){
        //given
        Object object1 = new Object(), object2 = new Object();

        //then
        assertThat( commonService.validateNotNull( object1, object2 ), is( true ) );
    }
}
