package com.devtiro.quickstart.controller;

import com.devtiro.quickstart.entity.Personal;
import com.devtiro.quickstart.services.PersonalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personals")
public class PersonalController {

    @Autowired
    private PersonalService personalService;

    @PostMapping
    public Personal createPersonal(@RequestBody Personal personal) {
        return personalService.savePersonal(personal);
    }

    @GetMapping
    public List<Personal> getAllPersonals() {
        return personalService.getAllPersonal();
    }

    @GetMapping("/{id}")
    public Personal getPersonalById(@PathVariable Long id)
    {
        return personalService.getPersonalById(id);
    }

    @PutMapping("/{id}")
    public Personal updatePersonal(@PathVariable Long id, @RequestBody Personal personal) {
        return personalService.updatePersonal(id, personal);
    }

    @DeleteMapping("/{id}")
    public void deletePersonalById(@PathVariable Long id) {
        personalService.deletePersonal(id);
    }

}
