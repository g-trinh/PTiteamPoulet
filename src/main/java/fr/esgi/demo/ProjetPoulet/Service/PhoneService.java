package fr.esgi.demo.ProjetPoulet.Service;

import fr.esgi.demo.ProjetPoulet.DTO.PhoneDTO;
import fr.esgi.demo.ProjetPoulet.Entity.Phone;
import fr.esgi.demo.ProjetPoulet.Repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ListIterator;

/**
 * Created by Guillaume on 20/03/2015.
 */
@Service
public class PhoneService {
    @Autowired
    CommonService commonService;

    @Autowired
    PhoneRepository phoneRepository;

    public boolean validateBeforeCreation( PhoneDTO phoneDTO){
        return commonService.validateNotNull( phoneDTO.getSerialNumber(), phoneDTO.getNumber() ) && !phoneDTO.getSerialNumber().equals("") && !phoneDTO.getNumber().equals("");
    }

    public Phone buildPhoneFromDTO( PhoneDTO phoneDTO ){
        Phone newPhone = fr.esgi.demo.ProjetPoulet.Entity.Phone.newUser()
                .withFirstName(phoneDTO.getFirstName())
                .withId(phoneDTO.getId())
                .withLastName(phoneDTO.getLastName())
                .withNumber(phoneDTO.getNumber())
                .withSerialNumber(phoneDTO.getSerialNumber())
                .withStolen(phoneDTO.isStolen())
                .build()
                ;

        return newPhone;
    }

    public Phone save( PhoneDTO phone ){
        return phoneRepository.save( buildPhoneFromDTO( phone ) );
    }

    public List<Phone> getAllStolen( boolean anonymousMode ){
        List<Phone> phones = phoneRepository.findByStolen(true);

        if( anonymousMode ){
            setAnonymModeToAll( phones );
        }

        return phones;
    }

    public Phone getOnePhone( Long phoneId, boolean anonymousMode ){
        Phone phone = phoneRepository.findOne(phoneId);
        if(anonymousMode && phone != null) phone = setAnonymMode(phone) ;
        return phone;
    }

    public Phone changeStolenStatus(Phone phone) {
        phone.setStolen( phone.isStolen() ? false : true );
        return phoneRepository.save( phone );
    }

    public void deletePhone(Phone phone) {
        phoneRepository.delete(phone);
    }

    public Phone setAnonymMode(Phone phone){
        phone.setFirstName("*******");
        phone.setLastName("*******");
        return phone;
    }

    public List<Phone> setAnonymModeToAll( List<Phone> phones ){
        ListIterator<Phone> phoneIterator = phones.listIterator();

        while(phoneIterator.hasNext()){
            Phone thisPhone = phoneIterator.next();
            thisPhone = setAnonymMode(thisPhone);

            phoneIterator.set(thisPhone);
        }

        return phones;
    }
}
