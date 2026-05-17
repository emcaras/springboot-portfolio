package com.emcaras.portfolio.repository;

import com.emcaras.portfolio.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepository implements IUserRepository{

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final RowMapper<User> userRowMapper = (rs, rowNum) -> {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setEnabled(rs.getBoolean("enabled"));
        return user;
    };

    @Override
    public List<User> findAll() {
        String sql = "SELECT * from users";
        return jdbcTemplate.query(sql, Collections.emptyMap(), userRowMapper);
    }

    @Override
    public Optional<User> findById(Long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        String sql = "SELECT * FROM users WHERE id = :id";
        return jdbcTemplate.query(sql, params, userRowMapper).stream().findFirst();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        Map<String, Object> params = Collections.singletonMap("username", username);
        String sql = "SELECT * FROM users WHERE username = :username";
        return this.jdbcTemplate.query(sql, params, userRowMapper).stream().findFirst();
    }

    @Override
    public User save(User user) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(user);
        String sql;
        if(user.getId() == null) {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            sql = "INSERT INTO users (username, password, enabled) VALUES (:username, :password, :enabled)";
            jdbcTemplate.update(sql, params, keyHolder, new String[]{"id"});
            if(keyHolder.getKey() != null){
                user.setId(keyHolder.getKey().longValue());
            }
        }
        else{
            sql = "UPDATE users SET username = :username, password = :password, enabled = :enabled WHERE id = :id";
            jdbcTemplate.update(sql, params);
        }
        return user;

    }


    @Override
    public void delete(Long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        String sql = "DELETE FROM users WHERE id = :id";
        jdbcTemplate.update(sql, params);
    }
}
