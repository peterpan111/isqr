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
import javax.persistence.Query;

import by.kkc.web.isqr.model.Statistic;
import by.kkc.web.isqr.repository.StatisticRepository;
import org.springframework.stereotype.Repository;

/**
 * JPA implementation of the ISQRService interface using EntityManager.
 * <p/>
 * <p>The mappings are defined in "orm.xml" located in the META-INF directory.
 *
 * @author Mike Keith
 * @author Rod Johnson
 * @author Sam Brannen
 * @author Michael Isvy
 * @since 22.4.2006
 */
@Repository
public class JpaStatisticRepositoryImpl implements StatisticRepository {

    @PersistenceContext
    private EntityManager em;


    @Override
    public void save(Statistic statistic) {
    	if (statistic.getId() == null) {
    		this.em.persist(statistic);
    	}
    	else {
    		this.em.merge(statistic);
    	}
    }


    @Override
    @SuppressWarnings("unchecked")
    public List<Statistic> findByPetId(Integer petId) {
        Query query = this.em.createQuery("SELECT visit FROM Visit v where v.pets.id= :id");
        query.setParameter("id", petId);
        return query.getResultList();
    }

}
