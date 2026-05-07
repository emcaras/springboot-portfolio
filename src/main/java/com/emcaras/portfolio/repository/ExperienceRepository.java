package com.emcaras.portfolio.repository;

import com.emcaras.portfolio.model.Experience;
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
public class ExperienceRepository implements IExperienceRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final RowMapper<Experience> experienceRowMapper = (rs, rowNum) -> {
        Experience experience = new Experience();
        experience.setId(rs.getLong("id"));
        experience.setJobTitle(rs.getString("job_title"));
        experience.setCompanyName(rs.getString("company_name"));
        experience.setStartDate(rs.getObject("start_date", LocalDate.class));
        experience.setEndDate(rs.getObject("end_date", LocalDate.class));
        experience.setDescription(rs.getString("description"));
        experience.setPersonalInfoId(rs.getLong("personal_info_id"));
        return experience;
    };

    @Override
    public Experience save(Experience experience) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(experience);
        String sql;
        if (experience.getId() == null) {
            //Insert
            KeyHolder keyHolder = new GeneratedKeyHolder();
            sql = "INSERT INTO experiences (job_title, company_name, start_date, end_date, description, personal_info_id) " +
                    "VALUES (:jobTitle, :companyName, :startDate, :endDate, :description, :personalInfoId)";
            this.namedParameterJdbcTemplate.update(sql, params, keyHolder, new String[]{"id"});
            if (keyHolder.getKey() != null) {
                experience.setId(keyHolder.getKey().longValue());
            }
        } else {
            //Update
            sql = "UPDATE experiences SET job_title = :jobTitle, company_name = :companyName, start_date = :startDate, " +
                    "end_date = :endDate, description = :description, personal_info_id = :personalInfoId " +
                    "WHERE id = :id";
            this.namedParameterJdbcTemplate.update(sql, params);
        }
        return experience;
    }

    @Override
    public List<Experience> findAll() {
        String sql = "SELECT * FROM experiences";
        return this.namedParameterJdbcTemplate.query(sql, Collections.emptyMap(), experienceRowMapper);
    }

    @Override
    public Optional<Experience> findById(Long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        String sql = "SELECT * FROM experiences WHERE id = :id";
        return this.namedParameterJdbcTemplate.query(sql, params, experienceRowMapper).stream().findFirst();
    }

    @Override
    public List<Experience> findByPersonalInfoId(Long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        String sql = "SELECT * FROM experiences WHERE personal_info_id = :id";
        return this.namedParameterJdbcTemplate.query(sql, params, experienceRowMapper);
    }

    @Override
    public void delete(Long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        String sql = "DELETE FROM experiences WHERE id = :id";
        this.namedParameterJdbcTemplate.update(sql, params);
    }
}
