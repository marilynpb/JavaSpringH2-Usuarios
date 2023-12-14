package com.apirest.apirest;

import com.apirest.apirest.model.User;
import com.apirest.apirest.repository.UserRepository;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(User user){
        return userRepository.save(user);
    }

    public User getUserById(Long id){
        Optional<User> optionalUser = userRepository.findAllById(id);
        return optionalUser.get();
    }

    public User updateUser(Long id, User user) throws UserNotFoundException {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            User userExistente = optionalUser.get();
            userExistente.setFirstName(user.getFirstName());
            userExistente.setLastName(user.getLastName());
            userExistente.setEmail(user.getEmail());

            return userRepository.save(userExistente);
        } else {
            throw new UserNotFoundException("No se encontró el usuario con ID: " + id);
        }
    }

    public List<User> getAllUsers(){
        return userRepository.getAllUsers();
    }

    public void deleteUser(Long id){
        userRepository.deleteUser(id);
    }
}
