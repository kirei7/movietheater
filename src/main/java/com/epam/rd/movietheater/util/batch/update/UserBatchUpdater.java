package com.epam.rd.movietheater.util.batch.update;

import com.epam.rd.movietheater.model.dto.UserDto;
import com.epam.rd.movietheater.model.entity.User;
import com.epam.rd.movietheater.service.facade.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserBatchUpdater implements BatchUpdater<User, UserDto> {

    private UserFacade userFacade;

    @Autowired
    public UserBatchUpdater(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @Override
    public List<User> updateWithData(List<UserDto> data) {
        List<User> result = new ArrayList<>();
        data.forEach(userDto -> result.add(
                userFacade.registerUser(userDto)
        ));
        return result;
    }
}
