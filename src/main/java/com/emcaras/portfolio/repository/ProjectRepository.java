package com.emcaras.portfolio.repository;

import com.emcaras.portfolio.model.Project;
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
public class ProjectRepository implements IProjectRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final RowMapper<Project> projectRowMapper = (rs, rowNum) -> {
        Project project = new Project();

        project.setId(rs.getLong("id"));
        project.setTitle(rs.getString("title"));
        project.setDescription(rs.getString("description"));
        project.setImageUrl(rs.getString("image_url"));
        project.setProjectUrl(rs.getString("project_url"));
        project.setPersonalInfoId(rs.getLong("personal_info_id"));

        return project;
    };

    @Override
    public Project save(Project project) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(project);
        String sql;
        if (project.getId() == null) {
            //Insert
            KeyHolder keyHolder = new GeneratedKeyHolder();
            sql = "INSERT INTO projects (title, description, image_url, project_url, personal_info_id) " +
                    "VALUES (:title, :description, :imageUrl, :projectUrl, :personalInfoId)";
            jdbcTemplate.update(sql, params, keyHolder, new String[]{"id"});
            if (keyHolder.getKey() != null) {
                project.setId(keyHolder.getKey().longValue());
            }
        } else {
            sql = "UPDATE projects SET title = :title, description = :description, image_url = :imageUrl, project_url = :projectUrl, personal_info_id = :personalInfoId " +
                    "WHERE id = :id";
            //Update
            jdbcTemplate.update(sql, params);
        }

        return project;
    }

    @Override
    public List<Project> findAll() {
        String sql = "SELECT * FROM projects";
        return jdbcTemplate.query(sql, Collections.emptyMap(), projectRowMapper);
    }

    @Override
    public Optional<Project> findById(Long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        String sql = "SELECT * FROM projects WHERE id = :id";
        return jdbcTemplate.query(sql, params, projectRowMapper).stream().findFirst();
    }

    @Override
    public List<Project> findByPersonalInfoId(Long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        String sql = "SELECT * FROM projects WHERE personal_info_id = :id";
        return jdbcTemplate.query(sql, params, projectRowMapper);
    }

    @Override
    public void delete(Long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        String sql = "DELETE FROM projects WHERE id = :id";
        jdbcTemplate.update(sql, params);
    }
}
