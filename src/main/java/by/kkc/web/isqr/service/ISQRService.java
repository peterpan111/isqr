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
package by.kkc.web.isqr.service;

import java.util.Collection;

import by.kkc.web.isqr.model.*;
import org.springframework.dao.DataAccessException;
import by.kkc.web.isqr.model.Service;


/**
 * Mostly used as a facade for all ISQR controllers

 */
public interface ISQRService {

    public Service findServiceById(int id) throws DataAccessException;

    public Response findResponseById(int id) throws DataAccessException;

    public void saveResponse(Response response) throws DataAccessException;

    public void deleteResponse(Response response) throws DataAccessException;

    public void saveStatistic(Statistic statistic) throws DataAccessException;

    public Collection<Comment> findComments() throws DataAccessException;

    public void saveService(Service service) throws DataAccessException;

    public void saveComment(Comment service) throws DataAccessException;

    public void deleteService(Service service) throws DataAccessException;

    Collection<Service> findServiceByName(String name) throws DataAccessException;

}
