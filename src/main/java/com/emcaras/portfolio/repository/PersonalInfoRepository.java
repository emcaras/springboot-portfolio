package com.emcaras.portfolio.repository;

import com.emcaras.portfolio.model.PersonalInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@RequiredArgsConstructor
public class PersonalInfoRepository implements IPersonalInfoRepository {

    private final NamedParameterJdbcTemplate namedParameterjdbcTemplate;
    private final RowMapper<PersonalInfo> personalInfoMapper = (rs, rowNum) -> {
        PersonalInfo personalInfo = new PersonalInfo();
        personalInfo.setId(rs.getLong("id"));
        personalInfo.setFirstName(rs.getString("first_name"));
        personalInfo.setLastName(rs.getString("last_name"));
        personalInfo.setTitle(rs.getString("title"));
        personalInfo.setProfileDescription(rs.getString("profile_description"));
        personalInfo.setProfileImageUrl(rs.getString("profile_image_url"));
        personalInfo.setYearsOfExperience(rs.getInt("years_of_experience"));
        personalInfo.setEmail(rs.getString("email"));
        personalInfo.setPhone(rs.getString("phone"));
        personalInfo.setLinkedinUrl(rs.getString("linkedin_url"));
        personalInfo.setGithubUrl(rs.getString("github_url"));
        return personalInfo;
    };

    @Override
    public PersonalInfo save(PersonalInfo personalInfo) {
        SqlParameterSource paramSource = new BeanPropertySqlParameterSource(personalInfo);

        //Insertamos
        if (personalInfo.getId() == null) {
            KeyHolder keyHolder = new GeneratedKeyHolder();

            String sql = "INSERT INTO personal_info (first_name, last_name, title, profile_description, profile_image_url, years_of_experience, email, phone, linkedin_url, github_url) VALUES (" +
                    ":firstName, :lastName, :title, :profileDescription, :profileImageUrl, :yearsOfExperience, :email, :phone, :linkedinUrl, :githubUrl)";
            namedParameterjdbcTemplate.update(sql, paramSource, keyHolder, new String[]{"id"});

            if(keyHolder.getKey() != null){
                personalInfo.setId(keyHolder.getKey().longValue());
            }
        }
        //Actualizamos
        else {
            String sql = "UPDATE personal_info SET first_name = :firstName, last_name = :lastName, title = :title, profile_description = :profileDescription, " +
                    "profile_image_url = :profileImageUrl, years_of_experience = :yearsOfExperience, email = :email, phone = :phone, linkedin_url = :linkedinUrl, github_url = :githubUrl " +
                    "WHERE id = :id";
            namedParameterjdbcTemplate.update(sql, paramSource);

        }
        return personalInfo;
    }

//    @Override
//    public Optional<PersonalInfo> findById(Long id) {
//        String sql = "SELECT * FROM personal_info WHERE id = :id";
//
//        Map<String, Object> params = Collections.singletonMap("id", id);
//
//        List<PersonalInfo> personalInfoList = namedParameterjdbcTemplate.query(sql, params, personalInfoMapper);
//        return personalInfoList.stream().findFirst();
//    }

    @Override
    public List<PersonalInfo> findAll() {
        String sql = "SELECT * FROM personal_info";
        return namedParameterjdbcTemplate.query(sql, Collections.emptyMap(), personalInfoMapper);
    }

    @Override
    public Optional<PersonalInfo> findById(Long id) {

        Map<String, Object> params = Collections.singletonMap("id", id);
        String sql = "SELECT * from personal_info WHERE id = :id";
        try{
            return  Optional.ofNullable(namedParameterjdbcTemplate.queryForObject(sql, params, personalInfoMapper));
        }
        catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM personal_info WHERE id = :id";
        Map<String, Object> params =  Collections.singletonMap("id", id);
        namedParameterjdbcTemplate.update(sql, params);
    }
}
