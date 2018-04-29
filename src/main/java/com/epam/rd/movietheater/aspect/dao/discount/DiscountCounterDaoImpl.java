package com.epam.rd.movietheater.aspect.dao.discount;

import com.epam.rd.movietheater.aspect.service.DiscountCounter;
import com.epam.rd.movietheater.model.entity.User;
import com.epam.rd.movietheater.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class DiscountCounterDaoImpl implements DiscountCounterDao {

    private final String tableName = "discount_count";
    private JdbcTemplate jdbcTemplate;
    private UserService userService;

    @Autowired
    public DiscountCounterDaoImpl(JdbcTemplate jdbcTemplate, UserService userService) {
        this.jdbcTemplate = jdbcTemplate;
        this.userService = userService;
    }

    @Override
    public Optional<DiscountCounter> save(DiscountCounter discount) {
        String query = "REPLACE INTO " + tableName + " (userId, discountType, timesGiven) VALUES(?, ?, ?)";
        jdbcTemplate.update(
                query,
                discount.getUser().getId(),
                discount.getDiscount().getType(),
                discount.getTimesGiven()
        );
        return Optional.empty();
    }

    @Override
    public Optional<DiscountCounter> getDiscount(String type, User user) {
        String query = "SELECT * FROM " + tableName + " WHERE (userId = ?) AND (discountType LIKE ?)";
        Optional<DiscountCounter> result = jdbcTemplate.query(
                query,
                new Object[]{user.getId(), type}
                ,
                (rs, rowNum) -> new DiscountCounter(
                        rs.getString("discountType"),
                        userService.getById(rs.getLong("userId")).orElse(new User()),
                        rs.getLong("timesGiven")
                )
        ).stream().findFirst();
return result;
    }

    @Override
    public List<DiscountCounter> findByType(String type) {
        String query = "SELECT * FROM " + tableName + " WHERE (discountType LIKE ?)";
        return jdbcTemplate.query(
                query,
                new Object[]{ type }
                ,
                (rs, rowNum) -> new DiscountCounter(
                        rs.getString("discountType"),
                        userService.getById(rs.getLong("userId")).orElse(new User()),
                        rs.getLong("timesGiven")
                )
        ).stream().collect(Collectors.toList());
    }

    @Override
    public List<DiscountCounter> findByUser(User user) {
        String query = "SELECT * FROM " + tableName + " WHERE (userId = ?)";
        return jdbcTemplate.query(
                query,
                new Object[]{ user.getId() }
                ,
                (rs, rowNum) -> new DiscountCounter(
                        rs.getString("discountType"),
                        userService.getById(rs.getLong("userId")).orElse(new User()),
                        rs.getLong("timesGiven")
                )
        ).stream().collect(Collectors.toList());
    }
}
