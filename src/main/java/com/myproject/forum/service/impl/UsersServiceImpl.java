package com.myproject.forum.service.impl;

import com.myproject.forum.models.User;
import com.myproject.forum.repository.UsersRepository;
import com.myproject.forum.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User createNewUser() {
        return new User();
    }

    @Override
    public boolean saveUser(User newUser) {
        boolean isSaved = false;
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        if(userRepository.save(newUser)!=null){
            isSaved = true;
        }
        newUser.setPassword("");
        return isSaved;
    }

    @Override
    public User getUserByEmail(String userEmail) {
        return userRepository.findByEmail(userEmail);
    }

    @Override
    public User getUserById(long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public void deleteUser(long userId, String userEmailLogged) {
        User userLogged = userRepository.findByEmail(userEmailLogged);
        if(userId == userLogged.getId()){
            userRepository.delete(userLogged);
        }
    }

    @Override
    public void updateUser(User newUser) {
        User userWithDB = userRepository.findById(newUser.getId());
        userWithDB.setFirstName(newUser.getFirstName());
        userWithDB.setLastName(newUser.getLastName());
        userRepository.save(userWithDB);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void exchangeUserForAdmin(long userId, String adminEmail) {
        User admin = userRepository.findByEmail(adminEmail);
        User user = userRepository.findById(userId);
        if(admin.getRoles().stream().filter(role -> role.getName().equals("ROLE_ADMIN")).findAny()!=null){
            user.setRoles(admin.getRoles().stream().collect(Collectors.toList()));
        }
    }


}
