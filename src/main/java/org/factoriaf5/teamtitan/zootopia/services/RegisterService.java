package org.factoriaf5.teamtitan.zootopia.services;

import java.util.HashSet;
import java.util.Set;
import org.factoriaf5.teamtitan.zootopia.dtos.UserDto;
import org.factoriaf5.teamtitan.zootopia.implementations.IEncryptFacade;
import org.factoriaf5.teamtitan.zootopia.models.Role;
import org.factoriaf5.teamtitan.zootopia.models.User;
import org.factoriaf5.teamtitan.zootopia.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    UserRepository repository;
    RoleService roleService;
    IEncryptFacade encoderFacade;

    public RegisterService(UserRepository repository, RoleService roleService, IEncryptFacade encoderFacade) {
        this.repository = repository;
        this.roleService = roleService;
        this.encoderFacade = encoderFacade;
    }

    public String save(UserDto newUserDto) {

        String passwordDecoded = encoderFacade.decode("base64", newUserDto.getPassword());
        String passwordEncoded = encoderFacade.encode("bcrypt",  passwordDecoded);

        User user = new User(newUserDto.getUsername(), passwordEncoded);
        user.setRoles(assignDefaultRole());

        repository.save(user);

        return "Success";
    }

    public Set<Role> assignDefaultRole() {
        Role defaultRole = roleService.getById(1L);

        Set<Role> roles = new HashSet<>();
        roles.add(defaultRole);

        return roles;
    }

}
