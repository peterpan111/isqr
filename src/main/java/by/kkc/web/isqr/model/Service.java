package by.kkc.web.isqr.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.core.style.ToStringCreator;

/**
 * Simple JavaBean domain object representing an service.
 *
 */
@Entity
@Table(name = "services")
public class Service extends NamedEntity {

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "services_responses", joinColumns = {
            @JoinColumn(name = "service_id", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "response_id",
                    nullable = false, updatable = false) })
    private Set<Response> responses;


    protected void setResponseInternal(Set<Response> responses) {
        this.responses = responses;
    }

    protected Set<Response> getResponsesInternal() {
        if (this.responses == null) {
            this.responses = new HashSet<Response>();
        }
        return this.responses;
    }

    @XmlElement
    public List<Response> getResponses() {
        List<Response> sortedResponses = new ArrayList<Response>(getResponsesInternal());
        PropertyComparator.sort(sortedResponses, new MutableSortDefinition("name", true, true));
        return Collections.unmodifiableList(sortedResponses);
    }

    public int getNrOfResponses() {
        return getResponsesInternal().size();
    }

    public void addResponse(Response response) {
        getResponsesInternal().add(response);
    }




    /**
     * Return the Response with the given name, or null if none found for this Service.
     *
     * @param name to test
     * @return true if response name is already in use
     */
    public Response getResponse(String name, boolean ignoreNew) {
        name = name.toLowerCase();
        for (Response response : getResponsesInternal()) {
            if (!ignoreNew || !response.isNew()) {
                String compName = response.getName();
                compName = compName.toLowerCase();
                if (compName.equals(name)) {
                    return response;
                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return new ToStringCreator(this)
                .append("id", this.getId())
                .append("name", this.getName())
                .append("description", this.getDescription())
                .toString();
    }
}
