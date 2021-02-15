package com.example.restapi.service;

import com.example.restapi.repository.UserRepository;
import com.example.restapi.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;


    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }


    @Override
    public List<User> getUsersList(int page, int size, String sortDir, String sort) {
        PageRequest pageReq
                = PageRequest.of(page, size, Sort.Direction.fromString(sortDir), sort);

        Page<User> users = userRepository.findAll(pageReq);
        return users.getContent();
    }

    @Override
    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    @Transactional
    @Override
    public User addUser(User user) {
        userRepository.save(user);
        return user;
    }

    @Transactional
    @Override
    public User updateUser(User user) {

        return user;
    }


    @Override
    public void deleteUser(Long userId) {

    }
}
