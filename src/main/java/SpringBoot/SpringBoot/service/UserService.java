package SpringBoot.SpringBoot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import SpringBoot.SpringBoot.repository.UserRepository;
import SpringBoot.SpringBoot.model.User;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private  UserRepository userRepository;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public void add(User user) {
        userRepository.save(user);
    }

    public void delete(User user) {
        boolean existContact = userRepository.existsById(user.getId());
        if (existContact) {
            User contact = userRepository.getOne(user.getId());
            userRepository.delete(contact);
        }
    }
    public void edit(User user) {
        userRepository.save(user);
    }
    public User getById(Long id) {
        return userRepository.findById(id).get();
    }
}
