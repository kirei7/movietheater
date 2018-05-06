package com.epam.rd.movietheater.dao.impl.jpa.repository;

import com.epam.rd.movietheater.model.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
}
