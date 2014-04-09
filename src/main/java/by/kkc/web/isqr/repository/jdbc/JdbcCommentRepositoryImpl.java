/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package by.kkc.web.isqr.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import by.kkc.web.isqr.model.Comment;
import by.kkc.web.isqr.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import by.kkc.web.isqr.util.EntityUtils;
import org.springframework.stereotype.Repository;

/**
 * A simple JDBC-based implementation of the {@link by.kkc.web.isqr.repository.CommentRepository} interface. Uses @Cacheable to cache the result of the
 * {@link findAll} method
 *
 * @author Ken Krebs
 * @author Juergen Hoeller
 * @author Rob Harrop
 * @author Sam Brannen
 * @author Thomas Risberg
 * @author Mark Fisher
 * @author Michael Isvy
 */
@Repository
public class JdbcCommentRepositoryImpl implements CommentRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcCommentRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Refresh the cache of Vets that the ISQRService is holding.
     *
     * @see by.kkc.web.isqr.model.service.ClinicService#findVets()
     */
    @Override
    public Collection<Comment> findAll() throws DataAccessException {
        List<Comment> comments = new ArrayList<Comment>();
        // Retrieve the list of all comments.
        comments.addAll(this.jdbcTemplate.query(
                "SELECT id, first_name, last_name FROM vets ORDER BY last_name,first_name",
                ParameterizedBeanPropertyRowMapper.newInstance(Comment.class)));

        // Retrieve the list of all possible specialties.
        final List<Specialty> specialties = this.jdbcTemplate.query(
                "SELECT id, name FROM specialties",
                ParameterizedBeanPropertyRowMapper.newInstance(Specialty.class));

        // Build each comment's list of specialties.
        for (Comment comment : comments) {
            final List<Integer> vetSpecialtiesIds = this.jdbcTemplate.query(
                    "SELECT specialty_id FROM vet_specialties WHERE vet_id=?",
                    new ParameterizedRowMapper<Integer>() {
                        @Override
                        public Integer mapRow(ResultSet rs, int row) throws SQLException {
                            return Integer.valueOf(rs.getInt(1));
                        }
                    },
                    comment.getId().intValue());
            for (int specialtyId : vetSpecialtiesIds) {
                Specialty specialty = EntityUtils.getById(specialties, Specialty.class, specialtyId);
                comment.addSpecialty(specialty);
            }
        }
        return comments;
    }
}
