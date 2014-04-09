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
package by.kkc.web.isqr.repository;

import java.util.List;

import by.kkc.web.isqr.model.Response;
import org.springframework.dao.DataAccessException;

/**
 * Repository class for <code>Response</code> domain objects All method names are compliant with Spring Data naming
 * conventions so this interface can easily be extended for Spring Data See here: http://static.springsource.org/spring-data/jpa/docs/current/reference/html/jpa.repositories.html#jpa.query-methods.query-creation
 *
 * @author Ken Krebs
 * @author Juergen Hoeller
 * @author Sam Brannen
 * @author Michael Isvy
 */
public interface ResponseRepository {

    /**
     * Retrieve all <code>Response</code>s from the data store.
     *
     * @return a <code>Collection</code> of <code>Response</code>s
     */
    List<Response> findAllResponse() throws DataAccessException;

    /**
     * Retrieve a <code>Response</code> from the data store by id.
     *
     * @param id the id to search for
     * @return the <code>Response</code> if found
     * @throws org.springframework.dao.DataRetrievalFailureException
     *          if not found
     */
    Response findById(int id) throws DataAccessException;

    /**
     * Retrieve a <code>Response</code> from the data store by name.
     *
     * @param name the id to search for
     * @return the <code>Response</code> if found
     * @throws org.springframework.dao.DataRetrievalFailureException
     *          if not found
     */
    Response findByName(String name) throws DataAccessException;

    /**
     * Save a <code>Response</code> to the data store, either inserting or updating it.
     *
     * @param response the <code>Response</code> to save

     */
    void save(Response response) throws DataAccessException;

    /**
     * Delete a <code>Response</code> to the data store
     *
     * @param response the <code>Response</code> to delete

     */
    void delete(Response response) throws DataAccessException;


}
