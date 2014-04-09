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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import by.kkc.web.isqr.model.Response;
import by.kkc.web.isqr.model.Service;
import by.kkc.web.isqr.model.Statistic;
import by.kkc.web.isqr.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.orm.ObjectRetrievalFailureException;
import by.kkc.web.isqr.repository.ResponseRepository;
import by.kkc.web.isqr.repository.StatisticRepository;
import by.kkc.web.isqr.util.EntityUtils;
import org.springframework.stereotype.Repository;

/**
 * @author Ken Krebs
 * @author Juergen Hoeller
 * @author Rob Harrop
 * @author Sam Brannen
 * @author Thomas Risberg
 * @author Mark Fisher
 */
@Repository
public class JdbcResponseRepositoryImpl implements ResponseRepository {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private SimpleJdbcInsert insertPet;

    private ServiceRepository serviceRepository;

    private StatisticRepository statisticRepository;


    @Autowired
    public JdbcResponseRepositoryImpl(DataSource dataSource, ServiceRepository serviceRepository, StatisticRepository statisticRepository) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

        this.insertPet = new SimpleJdbcInsert(dataSource)
                .withTableName("pets")
                .usingGeneratedKeyColumns("id");

        this.serviceRepository = serviceRepository;
        this.statisticRepository = statisticRepository;
    }

    @Override
    public List<PetType> findPetTypes() throws DataAccessException {
        Map<String, Object> params = new HashMap<String, Object>();
        return this.namedParameterJdbcTemplate.query(
                "SELECT id, name FROM types ORDER BY name",
                params,
                ParameterizedBeanPropertyRowMapper.newInstance(PetType.class));
    }

    @Override
    public Response findById(int id) throws DataAccessException {
        JdbcResponse pet;
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("id", id);
            pet = this.namedParameterJdbcTemplate.queryForObject(
                    "SELECT id, name, birth_date, type_id, owner_id FROM pets WHERE id=:id",
                    params,
                    new JdbcPetRowMapper());
        } catch (EmptyResultDataAccessException ex) {
            throw new ObjectRetrievalFailureException(Response.class, new Integer(id));
        }
        Service service = this.serviceRepository.findById(pet.getOwnerId());
        service.addPet(pet);
        pet.setType(EntityUtils.getById(findPetTypes(), PetType.class, pet.getTypeId()));

        List<Statistic> statistics = this.statisticRepository.findByPetId(pet.getId());
        for (Statistic statistic : statistics) {
            pet.addVisit(statistic);
        }
        return pet;
    }

    @Override
    public void save(Response response) throws DataAccessException {
        if (response.isNew()) {
            Number newKey = this.insertPet.executeAndReturnKey(
                    createPetParameterSource(response));
            response.setId(newKey.intValue());
        } else {
            this.namedParameterJdbcTemplate.update(
                    "UPDATE pets SET name=:name, birth_date=:birth_date, type_id=:type_id, " +
                            "owner_id=:owner_id WHERE id=:id",
                    createPetParameterSource(response));
        }
    }

    /**
     * Creates a {@link MapSqlParameterSource} based on data values from the supplied {@link by.kkc.web.isqr.model.Response} instance.
     */
    private MapSqlParameterSource createPetParameterSource(Response response) {
        return new MapSqlParameterSource()
                .addValue("id", response.getId())
                .addValue("name", response.getName())
                .addValue("birth_date", response.getBirthDate().toDate())
                .addValue("type_id", response.getType().getId())
                .addValue("owner_id", response.getService().getId());
    }

}
