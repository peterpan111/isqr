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
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import by.kkc.web.isqr.model.Statistic;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import by.kkc.web.isqr.repository.StatisticRepository;
import org.springframework.stereotype.Repository;

/**
 * A simple JDBC-based implementation of the {@link by.kkc.web.isqr.repository.StatisticRepository} interface.
 */
@Repository
public class JdbcStatisticRepositoryImpl implements StatisticRepository {

    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcInsert insertStatistic;

    @Autowired
    public JdbcStatisticRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);

        this.insertStatistic = new SimpleJdbcInsert(dataSource)
                .withTableName("statistics")
                .usingGeneratedKeyColumns("id");
    }


    @Override
    public void save(Statistic statistic) throws DataAccessException {
        if (statistic.isNew()) {
            Number newKey = this.insertStatistic.executeAndReturnKey(
                    createStatisticParameterSource(statistic));
            statistic.setId(newKey.intValue());
        } else {
            throw new UnsupportedOperationException("Statistic update not supported");
        }
    }

    @Override
    public List<Statistic> findByServiceId(Integer serviceId) {

        return null;
    }

    public void deletePet(int id) throws DataAccessException {
        this.jdbcTemplate.update("DELETE FROM pets WHERE id=?", id);
    }


    /**
     * Creates a {@link MapSqlParameterSource} based on data values from the supplied {@link by.kkc.web.isqr.model.Statistic} instance.
     */
    private MapSqlParameterSource createStatisticParameterSource(Statistic statistic) {
        return new MapSqlParameterSource()
                .addValue("id", statistic.getId())
                .addValue("date", statistic.getDate().toDate())
                .addValue("service_id", statistic.getService().getId())
                .addValue("response_id", statistic.getResponse().getId())
                .addValue("comment_id", statistic.getComment().getId());
    }

    @Override
    public List<Statistic> findByResponseId(Integer responseId) {
        final List<Statistic> statistics = this.jdbcTemplate.query(
                "SELECT id, date, service_id, response_id, comment_id FROM statistics WHERE response_id=?",
                new ParameterizedRowMapper<Statistic>() {
                    @Override
                    public Statistic mapRow(ResultSet rs, int row) throws SQLException {
                        Statistic statistic = new Statistic();
                        statistic.setId(rs.getInt("id"));
                        Date statisticDate = rs.getDate("date");
                        statistic.setDate(new DateTime(statisticDate));
                        statistic.setService(rs.getInt("service_id"));
                        statistic.setResponse(rs.getInt("response_id"));
                        statistic.setDescription(rs.getString("description"));
                        return statistic;
                    }
                },
                responseId);
        return statistics;
    }

}
