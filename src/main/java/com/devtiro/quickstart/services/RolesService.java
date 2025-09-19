package com.devtiro.quickstart.services;

import com.devtiro.quickstart.entity.Roles;
import com.devtiro.quickstart.repository.RolesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolesService {

    @Autowired
    private RolesRepository rolesRepository;
    private final Logger logger = LoggerFactory.getLogger(RolesService.class);

    public RolesService(RolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }

    public List<Roles> getAllRoles() {
        return rolesRepository.findAll();
    }

    public String getRoleByName(String name, Long id)
    {
        Roles theRole = rolesRepository.findById(id).orElse(null);
        return theRole.getName();
    }

    public Roles getRoleById(Long id) {
        return rolesRepository.findById(id).orElse(null);
    }
    public Roles createRole(Roles role) {
        return rolesRepository.save(role);
    }
    public Roles updateRole(Long id, Roles role) {
        Roles existingRole = rolesRepository.findById(id).orElse(null);
        if (existingRole != null) {
            existingRole.setName(role.getName());
            existingRole.setDescription(role.getDescription());
            return rolesRepository.save(existingRole);
        }
        else
        {
            logger.warn("Role with id {} not found", id);
            return null;
        }
    }
    public void deleteRole(Long id) {
        rolesRepository.deleteById(id);
    }

}
