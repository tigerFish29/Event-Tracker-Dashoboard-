package com.eventtracker.events.domain;

import java.time.LocalDate;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.ManyToOne;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Events {

     @Id
     @Column(nullable = false, updatable = false)
     @GeneratedValue(strategy = GenerationType.SEQUENCE)
     
     private Long id;

     @Column(nullable = false)
     private LocalDate date;

     @Column(nullable = false)
     private String title;

     @Column(nullable = false)
     private String description;

     @ManyToOne(fetch = FetchType.LAZY)
     @JoinColumn(name = "events_id", nullable = false)
     private CollectionEvents events;
    

    // to string {} 
    @Override
    public String toString() {
        return "Events [date=" + date + ", description=" + description + ", events=" + events + ", id=" + id
                + ", title=" + title + "]";
    }
    

    // hashcode {} 

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((events == null) ? 0 : events.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Events other = (Events) obj;
        if (date == null) {
            if (other.date != null)
                return false;
        } else if (!date.equals(other.date))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (events == null) {
            if (other.events != null)
                return false;
        } else if (!events.equals(other.events))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        return true;
    }


    


     

     
}