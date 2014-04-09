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

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import by.kkc.web.isqr.model.Service;
import by.kkc.web.isqr.model.Statistic;
import by.kkc.web.isqr.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.orm.ObjectRetrievalFailureException;
import by.kkc.web.isqr.repository.StatisticRepository;
import by.kkc.web.isqr.util.EntityUtils;
import org.springframework.stereotype.Repository;

/**
 * A simple JDBC-based implementation of the {@link by.kkc.web.isqr.repository.ServiceRepository} interface.
 */
@Repository
public class JdbcServiceRepositoryImpl implements ServiceRepository {

    private StatisticRepository statisticRepository;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private SimpleJdbcInsert insertService;

    @Autowired
    public JdbcServiceRepositoryImpl(DataSource dataSource, NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                                     StatisticRepository statisticRepository) {

        this.insertService = new SimpleJdbcInsert(dataSource)
                .withTableName("services")
                .usingGeneratedKeyColumns("id");

        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

        this.statisticRepository = statisticRepository;
    }


    /**
     * Loads {@link by.kkc.web.isqr.model.Service Service} from the data store by name, returning all services whose name <i>starts</i> with
     * the given name; also loads the {@link by.kkc.web.isqr.model.Response Responses} and {@link by.kkc.web.isqr.model.Statistic Visits} for the corresponding services, if not
     * already loaded.
     */
    @Override
    public Collection<Service> findByName(String name) throws DataAccessException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name + "%");
        List<Service> services = this.namedParameterJdbcTemplate.query(
                "SELECT id, name, description FROM services WHERE name like :name ",
                params,
                ParameterizedBeanPropertyRowMapper.newInstance(Service.class)
        );
        loadOwnersPetsAndVisits(services);
        return services;
    }

    /**
     * Retrieve <code>Service</code>s from the data store by  name, returning all service whose name <i>starts</i>
     * with the given name.
     *
     * @param name Value to search for
     * @return a <code>Collection</code> of matching <code>Service</code>s (or an empty <code>Collection</code> if none
     * found)
     */
    @Override
    public Collection<Service> findByName(String name) throws DataAccessException {
        return null;
    }

    /**
     * Loads the {@link by.kkc.web.isqr.model.Service} with the supplied <code>id</code>; also loads the {@link by.kkc.web.isqr.model.Response Pets} and {@link by.kkc.web.isqr.model.Statistic Visits}
     * for the corresponding service, if not already loaded.
     */
    @Override
    public Service findById(int id) throws DataAccessException {
        Service service;
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("id", id);
            service = this.namedParameterJdbcTemplate.queryForObject(
                    "SELECT id, first_name, last_name, address, city, telephone FROM owners WHERE id= :id",
                    params,
                    ParameterizedBeanPropertyRowMapper.newInstance(Service.class)
            );
        } catch (EmptyResultDataAccessException ex) {
            throw new ObjectRetrievalFailureException(Service.class, id);
        }
        loadPetsAndVisits(service);
        return service;
    }

    public void loadPetsAndVisits(final Service service) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", service.getId().intValue());
        final List<JdbcResponse> pets = this.namedParameterJdbcTemplate.query(
                "SELECT id, name, birth_date, type_id, owner_id FROM pets WHERE owner_id=:id",
                params,
                new JdbcPetRowMapper()
        );
        for (JdbcResponse pet : pets) {
            service.addPet(pet);
            pet.setType(EntityUtils.getById(getPetTypes(), PetType.class, pet.getTypeId()));
            List<Statistic> statistics = this.statisticRepository.findByPetId(pet.getId());
            for (Statistic statistic : statistics) {
                pet.addVisit(statistic);
            }
        }
    }

    @Override
    public void save(Service service) throws DataAccessException {
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(service);
        if (service.isNew()) {
            Number newKey = this.insertService.executeAndReturnKey(parameterSource);
            service.setId(newKey.intValue());
        } else {
            this.namedParameterJdbcTemplate.update(
                    "UPDATE owners SET first_name=:firstName, last_name=:lastName, address=:address, " +
                            "city=:city, telephone=:telephone WHERE id=:id",
                    parameterSource);
        }
    }

    /**
     * Delete an <code>Service</code> to the data store.
     *
     * @param service the <code>Service</code> to delete
     */
    @Override
    public void delete(Service service) throws DataAccessException {

    }

    public Collection<PetType> getPetTypes() throws DataAccessException {
        return this.namedParameterJdbcTemplate.query(
                "SELECT id, name FROM types ORDER BY name", new HashMap<String, Object>(),
                ParameterizedBeanPropertyRowMapper.newInstance(PetType.class));
    }

    /**
     * Loads the {@link by.kkc.web.isqr.model.Response} and {@link by.kkc.web.isqr.model.Statistic} data for the supplied {@link List} of {@link by.kkc.web.isqr.model.Service Owners}.
     *
     * @param services the list of owners for whom the pet and statistic data should be loaded
     * @see #loadPetsAndVisits(by.kkc.web.isqr.model.Service)
     */
    private void loadOwnersPetsAndVisits(List<Service> services) {
        for (Service service : services) {
            loadPetsAndVisits(service);
        }
    }


}
