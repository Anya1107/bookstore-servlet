package by.bookstore.service;

import by.bookstore.entity.User;
import by.bookstore.repository.UserRepository;

public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void add(User user) {
        userRepository.add(user);
    }

    @Override
    public void delete(int id) {
        userRepository.delete(id);
    }

    @Override
    public User findByEmail(String email) {
         return userRepository.findByEmail(email);
    }

    @Override
    public User findById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public User[] findAll() {
        return userRepository.findAll();
    }

    @Override
    public void updateFirstName(String firstName, int id) {
        userRepository.updateFirstName(firstName,id);
    }

    @Override
    public void updateLastName(String lastName, int id) {
        userRepository.updateLastName(lastName,id);
    }

    @Override
    public void updatePassword(String password, int id) {
        userRepository.updatePassword(password,id);
    }

    @Override
    public void deleteByEmail(String email) {
        userRepository.deleteByEmail(email);
    }
}
