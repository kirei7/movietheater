package com.epam.rd.movietheater.service.user;

import com.epam.rd.movietheater.model.entity.User;
import com.epam.rd.movietheater.service.IdentifiableEntityService;

public interface UserService extends IdentifiableEntityService<User> {
    User getUserByEmail(String email);

    User getUserByNickName(String nickName);
}
