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
package by.kkc.web.isqr.model;

import javax.persistence.*;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Simple JavaBean domain object representing a statistic response.

 */
@Entity
@Table(name = "statistics")
public class Statistic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;


    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public boolean isNew() {
        return (this.id == null);
    }

    /**
     * Holds value of property date.
     */
    @Column(name = "date")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private DateTime date;

    /**
     * Holds value of property response.
     */
    @ManyToOne
    @JoinColumn(name = "response_id")
    private Response response;

    /**
     * Holds value of property service.
     */
    @ManyToOne
    @JoinColumn(name = "service_id")
    private Service service;

    /**
     * Holds value of property response.
     */
    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment comment;
    /**
     * Creates a new instance of Statistic for the current date
     */
    public Statistic() {
        this.date = new DateTime();
    }


    /**
     * Getter for property date.
     *
     * @return Value of property date.
     */
    public DateTime getDate() {
        return this.date;
    }

    /**
     * Setter for property date.
     *
     * @param date New value of property date.
     */
    public void setDate(DateTime date) {
        this.date = date;
    }


    /**
     * Getter for property service.
     *
     * @return Value of property service.
     */
    public Service getService() {
        return this.service;
    }

    /**
     * Setter for property service.
     *
     * @param service New value of property service.
     */
    public void setService(Service service) {
        this.service = service;
    }

    /**
     * Getter for property comment.
     *
     * @return Value of property comment.
     */
    public Comment getComment() {
        return this.comment;
    }

    /**
     * Setter for property comment.
     *
     * @param comment New value of property comment.
     */
    public void setComment(Comment comment) {
        this.comment = comment;
    }

    /**
     * Getter for property response.
     *
     * @return Value of property response.
     */
    public Response getResponse() {
        return this.response;
    }

    /**
     * Setter for property response.
     *
     * @param response New value of property response.
     */
    public void setResponse(Response response) {
        this.response = response;
    }
}
