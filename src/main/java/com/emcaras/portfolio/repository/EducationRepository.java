package com.emcaras.portfolio.repository;

import com.emcaras.portfolio.model.Education;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class EducationRepository implements IEducationRepository{

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final RowMapper<Education> educationRowMapper = (rs, rowNum) -> {
        Education education = new Education();
        education.setId(rs.getLong("id"));
        education.setDegree(rs.getString("degree"));
        education.setInstitution(rs.getString("institution"));
        education.setStartDate(rs.getObject("start_date", LocalDate.class));
        education.setEndDate(rs.getObject("end_date", LocalDate.class));
        education.setDescription(rs.getString("description"));
        education.setPersonalInfoId(rs.getLong("personal_info_id"));
        return education;
    };

    @Override
    public Education save(Education education) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(education);
        String sql;
        if(education.getId() == null){
            //Insert
            KeyHolder keyHolder = new GeneratedKeyHolder();
            sql = "INSERT INTO educations (degree, institution, start_date, end_date, description, personal_info_id) " +
                    "VALUES (:degree, :institution, :startDate, :endDate, :description, :personalInfoId)";
            namedParameterJdbcTemplate.update(sql, params, keyHolder, new String[]{"id"});

            if(keyHolder.getKey() != null){
                education.setId(keyHolder.getKey().longValue());
            }
        }
        else{
            //Update
            sql = "UPDATE educations SET degree = :degree, institution = :institution, start_date = :startDate, " +
                    "end_date = :endDate, description = :description, personal_info_id = :personalInfoId " +
                    "WHERE id = :id";
            namedParameterJdbcTemplate.update(sql, params);
        }

        return education;
    }

    @Override
    public List<Education> findAll() {
        String sql = "SELECT * FROM educations";
        return namedParameterJdbcTemplate.query(sql, Collections.emptyMap(), educationRowMapper);
    }

    @Override
    public List<Education> findByPersonalInfoId(Long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        String sql = "SELECT * FROM educations WHERE personal_info_id = :id";
        return namedParameterJdbcTemplate.query(sql, params, educationRowMapper);
    }

    @Override
    public Optional<Education> findById(Long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        String sql = "SELECT * from educations WHERE id = :id";
        return namedParameterJdbcTemplate.query(sql, params, educationRowMapper).stream().findFirst();
    }

    @Override
    public void delete(Long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        String sql = "DELETE FROM educations WHERE id = :id";
        this.namedParameterJdbcTemplate.update(sql, params);
    }
}
