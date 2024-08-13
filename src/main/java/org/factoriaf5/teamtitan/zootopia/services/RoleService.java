package org.factoriaf5.teamtitan.zootopia.services;

import org.factoriaf5.teamtitan.zootopia.exceptions.RoleNotFoundException;
import org.factoriaf5.teamtitan.zootopia.models.Role;
import org.factoriaf5.teamtitan.zootopia.repositories.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    
    RoleRepository repository;

    public RoleService(RoleRepository repository) {
        this.repository = repository;
    }

    public Role getById(Long id) {
        Role role = repository.findById(id).orElseThrow(() -> new RoleNotFoundException("Role not found"));
        return role;
    }

}
