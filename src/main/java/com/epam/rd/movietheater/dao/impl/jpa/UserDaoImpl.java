package com.epam.rd.movietheater.dao.impl.jpa;

import com.epam.rd.movietheater.dao.UserDao;
import com.epam.rd.movietheater.dao.impl.jpa.repository.UserRepository;
import com.epam.rd.movietheater.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao {

    private UserRepository userRepository;

    @Autowired
    public UserDaoImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public boolean remove(User user) {
        userRepository.delete(user);
        return userRepository.findById(user.getId()).isPresent();
    }

    @Override
    public Optional<User> getById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getByNickName(String nickName) {
        return userRepository.findByNickName(nickName);
    }
}
