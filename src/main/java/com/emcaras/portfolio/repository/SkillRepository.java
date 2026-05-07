package com.emcaras.portfolio.repository;

import com.emcaras.portfolio.model.PersonalInfo;
import com.emcaras.portfolio.model.Skill;
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
public class SkillRepository implements ISkillRepository{

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final RowMapper<Skill> skillRowMapper = (rs, rowNum) -> {
        Skill skill = new Skill();
        skill.setId(rs.getLong("id"));
        skill.setName(rs.getString("name"));
        skill.setLevelPercentage(rs.getInt("level_percentage"));
        skill.setIconClass(rs.getString("icon_class"));
        skill.setPersonalInfoId(rs.getLong("personal_info_id"));

        return skill;
    };

    @Override
    public Skill save(Skill skill) {
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(skill);
        String sql;

        if(skill.getId() == null){
            //Insertamos

            KeyHolder keyHolder = new GeneratedKeyHolder();

            sql = "INSERT INTO skills (name, level_percentage, icon_class, personal_info_id) VALUES (:name, :levelPercentage, :iconClass, :personalInfoId)";
            namedParameterJdbcTemplate.update(sql, parameterSource, keyHolder, new String[]{"id"});

            if(keyHolder.getKey() != null){
                skill.setId(keyHolder.getKey().longValue());
            }
        }
        else{
            sql = "UPDATE skills SET name = :name, level_percentage = :levelPercentage, icon_class = :iconClass, personal_info_id = :personalInfoId WHERE id = :id";
            namedParameterJdbcTemplate.update(sql, parameterSource);
        }
        return skill;
    }

    @Override
    public List<Skill> findAll() {
        String sql = "SELECT * FROM skills";
        return this.namedParameterJdbcTemplate.query(sql, Collections.emptyMap(), skillRowMapper);
    }

    @Override
    public Optional<Skill> findById(Long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        String sql = "SELECT * FROM skills WHERE id = :id";
        return this.namedParameterJdbcTemplate.query(sql, params, skillRowMapper).stream().findFirst();
    }

    @Override
    public List<Skill> findByPersonalInfoId(Long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        String sql = "SELECT * FROM skills WHERE personal_info_id = :id";
        return this.namedParameterJdbcTemplate.query(sql, params, this.skillRowMapper);
    }

    @Override
    public void delete(Long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        String sql = "DELETE FROM skills WHERE id = :id";
        this.namedParameterJdbcTemplate.update(sql, params);
    }
}
