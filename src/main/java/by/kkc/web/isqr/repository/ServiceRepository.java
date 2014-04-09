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

import java.util.Collection;

import by.kkc.web.isqr.model.Service;
import org.springframework.dao.DataAccessException;

/**
 * Repository class for <code>Service</code> domain objects All method names are compliant with Spring Data naming
 * conventions so this interface can easily be extended for Spring Data See here: http://static.springsource.org/spring-data/jpa/docs/current/reference/html/jpa.repositories.html#jpa.query-methods.query-creation
 */
public interface ServiceRepository {

    /**
     * Retrieve <code>Service</code>s from the data store by  name, returning all service whose name <i>starts</i>
     * with the given name.
     *
     * @param name Value to search for
     * @return a <code>Collection</code> of matching <code>Service</code>s (or an empty <code>Collection</code> if none
     *         found)
     */
    Collection<Service> findByName(String name) throws DataAccessException;

    /**
     * Retrieve an <code>Service</code> from the data store by id.
     *
     * @param id the id to search for
     * @return the <code>Service</code> if found
     * @throws org.springframework.dao.DataRetrievalFailureException
     *          if not found
     */
    Service findById(int id) throws DataAccessException;


    /**
     * Save an <code>Service</code> to the data store, either inserting or updating it.
     *
     * @param service the <code>Service</code> to save
     *
     */
    void save(Service service) throws DataAccessException;

    /**
     * Delete an <code>Service</code> to the data store.
     *
     * @param service the <code>Service</code> to delete

     */
    void delete(Service service) throws DataAccessException;


}
