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

/**
 * Simple business object representing a response.
 *
 */
@Entity
@Table(name = "responses")
public class Response extends NamedEntity {

    private Set<Service> services = new HashSet<Service>(0);

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "responses")
    public Set<Service> getServices() {
        return this.services;
    }

    public void setServices(Set<Service> services) {
        this.services = services;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "responses_comments", joinColumns = @JoinColumn(name = "response_id"),
            inverseJoinColumns = @JoinColumn(name = "comment_id"))
    private Set<Comment> comments;


    protected void setCommentsInternal(Set<Comment> comments) {
        this.comments = comments;
    }

    protected Set<Comment> getCommentsInternal() {
        if (this.comments == null) {
            this.comments = new HashSet<Comment>();
        }
        return this.comments;
    }

    @XmlElement
    public List<Comment> getComments() {
        List<Comment> sortedComments = new ArrayList<Comment>(getCommentsInternal());
        PropertyComparator.sort(sortedComments, new MutableSortDefinition("name", true, true));
        return Collections.unmodifiableList(sortedComments);
    }

    public int getNrOfComments() {
        return getCommentsInternal().size();
    }

    public void addComment(Comment comment) {
        getCommentsInternal().add(comment);
    }




}
