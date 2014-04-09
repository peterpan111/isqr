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
package by.kkc.web.isqr.repository.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import by.kkc.web.isqr.model.Response;
import by.kkc.web.isqr.repository.ResponseRepository;
import org.springframework.stereotype.Repository;

/**
 * JPA implementation of the {@link by.kkc.web.isqr.repository.ResponseRepository} interface.
 *
 * @author Mike Keith
 * @author Rod Johnson
 * @author Sam Brannen
 * @author Michael Isvy
 * @since 22.4.2006
 */
@Repository
public class JpaResponseRepositoryImpl implements ResponseRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @SuppressWarnings("unchecked")
    public List<PetType> findPetTypes() {
        return this.em.createQuery("SELECT ptype FROM PetType ptype ORDER BY ptype.name").getResultList();
    }

    @Override
    public Response findById(int id) {
        return this.em.find(Response.class, id);
    }

    @Override
    public void save(Response response) {
    	if (response.getId() == null) {
    		this.em.persist(response);
    	}
    	else {
    		this.em.merge(response);
    	}
    }

}
