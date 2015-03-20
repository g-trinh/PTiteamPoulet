package fr.esgi.demo.ProjetPoulet.Controller;

import fr.esgi.demo.ProjetPoulet.DTO.PhoneDTO;
import fr.esgi.demo.ProjetPoulet.Entity.Phone;
import fr.esgi.demo.ProjetPoulet.Exception.EmptyContentException;
import fr.esgi.demo.ProjetPoulet.Exception.RequestValidationException;
import fr.esgi.demo.ProjetPoulet.Service.CommonService;
import fr.esgi.demo.ProjetPoulet.Service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;

/**
 * Created by Guillaume on 20/03/2015.
 */
@RestController
@RequestMapping("/phone")
public class PhoneController {
    //code controller
    @Autowired
    private CommonService commonService;

    @Autowired
    private PhoneService phoneService;

    @RequestMapping(method = POST)
    @ResponseStatus(CREATED)
    public Phone createPhone( @RequestBody PhoneDTO newPhone ){
        if( !phoneService.validateBeforeCreation(newPhone) ) {
            throw new RequestValidationException( "Serial number or number missing." );
        }

        return phoneService.save( newPhone );
    }

    @RequestMapping(method = GET)
    public List<Phone> getAllStolenPhones(){
        List<Phone> phoneList = phoneService.getAllStolen(true);
        if( phoneList.isEmpty() ){
            throw new EmptyContentException( "No stolen phone found." );
        }

        return phoneList;
    }

    @RequestMapping(method = GET, value = "/{phoneId}")
    public Phone getOnePhoneByID(@PathVariable Long phoneId){

        Phone phone = phoneService.getOnePhone(phoneId, true);

        if (phone != null){
            return phone;
        } else {
            throw new EmptyContentException("Ton phone existe pas. Stop smoking plz.");
        }
    }

    @RequestMapping(method = PUT, value = "/{serialNumber}")
    public Phone changePhoneStolenStatus(@PathVariable String serialNumber){
        Phone phone = phoneService.getOnePhoneBySerialNumber(serialNumber, true);

        if (phone != null){
            phone = phoneService.changeStolenStatus( phone );
            return phone;
        } else {
            throw new EmptyContentException("Ton phone existe pas. Stop smoking plz.");
        }
    }

    @RequestMapping(method = DELETE, value = "/{phoneId}")
    public void deletePhone(@PathVariable Long phoneId){
        Phone phone = phoneService.getOnePhone(phoneId, true);

        if (phone != null){
            phoneService.deletePhone( phone );
        } else {
            throw new EmptyContentException("Ton phone existe pas. Stop smoking plz.");
        }
    }
}
