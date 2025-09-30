package com.devtiro.quickstart.services;

import com.devtiro.quickstart.entity.Personal;
import com.devtiro.quickstart.repository.PersonalRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonalService {

    @Autowired
    private PersonalRepository personalRepository;
    private final Logger logger = LoggerFactory.getLogger(PersonalService.class);

    public PersonalService(PersonalRepository personalRepository)
    {
        this.personalRepository=personalRepository;
    }

    public Personal savePersonal(Personal personal)
    {
        return personalRepository.save(personal);
    }

    public List<Personal> getAllPersonal()
    {
        return personalRepository.findAll();
    }

    public Personal getPersonalById(Long id)
    {
        return personalRepository.findById(id).orElse(null);
    }

    public Personal updatePersonal(Long id, Personal personal)
    {
        Personal updatedPersonal = personalRepository.findById(id).orElse(null);

        if(updatedPersonal != null)
        {
            updatedPersonal.setFirstName(personal.getFirstName());
            updatedPersonal.setLastName(personal.getLastName());
            updatedPersonal.setEmail(personal.getEmail());
            updatedPersonal.setPhone(personal.getPhone());
            if(personal.getRole() != null)
            {
                 updatedPersonal.setRole(personal.getRole());
            }
           Personal finalPersonal = personalRepository.save(updatedPersonal);
           return finalPersonal;
        }
        else
        {
            logger.warn("Student with id {} not found", id);
            return null;
        }
    }

    public void deletePersonal(Long id)
    {
        personalRepository.deleteById(id);
    }



}
