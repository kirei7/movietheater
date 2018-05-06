package com.epam.rd.movietheater.dao.impl.jpa;

import com.epam.rd.movietheater.dao.UserAccountDao;
import com.epam.rd.movietheater.dao.impl.jpa.repository.UserAccountRepository;
import com.epam.rd.movietheater.model.entity.UserAccount;

import java.util.List;
import java.util.Optional;

public class UserAccountDaoImpl implements UserAccountDao {

    private UserAccountRepository repository;

    @Override
    public UserAccount save(UserAccount userAccount) {
        return repository.save(userAccount);
    }

    @Override
    public boolean remove(UserAccount userAccount) {
        repository.delete(userAccount);
        return !repository.findById(userAccount.getId()).isPresent();
    }

    @Override
    public Optional<UserAccount> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<UserAccount> findAll() {
        return repository.findAll();
    }
}
