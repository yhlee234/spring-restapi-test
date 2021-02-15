package com.example.restapi.service;

import com.example.restapi.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public List<User> getUsers();

    public List<User> getUsersList(int page, int size, String sortDir, String sort);

    public Optional<User> getUserById(Long userId);

    public User addUser(User user);

    public User updateUser(User user);

    public void deleteUser(Long userId);
}
