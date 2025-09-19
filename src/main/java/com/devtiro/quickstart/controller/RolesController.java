package com.devtiro.quickstart.controller;

import com.devtiro.quickstart.entity.Roles;
import com.devtiro.quickstart.services.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RolesController {

    @Autowired
    private RolesService rolesService;

    @GetMapping
    public List<Roles> getAllRoles() {
        return rolesService.getAllRoles();
    }

    @GetMapping("/{id}")
    public Roles getRoleById(@RequestParam Long id) {
        return rolesService.getRoleById(id);
    }

    @PostMapping
    public Roles createRole(@RequestBody Roles role) {
        return rolesService.createRole(role);
    }


}

