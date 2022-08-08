package com.eventtracker.events.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CollectionEventsDTO {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String name; 

    @NotNull 
    @Size(max = 255)
    private String address; 

    @NotNull
    @Size(max = 255)
    private String city;

    @NotNull
    @Size(max = 255)
    private String region;

    @NotNull
    @Size(max = 255)
    private String postcode;

    @NotNull
    @Size(max = 255)
    private String country;

    @NotNull 
    @Size(max = 255)
    private String continent;

    // to string [] 
    @Override
    public String toString() {
        return "CollectionEventsDTO [address=" + address + ", city=" + city + ", continent=" + continent + ", country="
                + country + ", id=" + id + ", name=" + name + ", postcode=" + postcode + ", region=" + region + "]";
    }
    
    // generate hashcode [] 
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((address == null) ? 0 : address.hashCode());
        result = prime * result + ((city == null) ? 0 : city.hashCode());
        result = prime * result + ((continent == null) ? 0 : continent.hashCode());
        result = prime * result + ((country == null) ? 0 : country.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((postcode == null) ? 0 : postcode.hashCode());
        result = prime * result + ((region == null) ? 0 : region.hashCode());
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
        CollectionEventsDTO other = (CollectionEventsDTO) obj;
        if (address == null) {
            if (other.address != null)
                return false;
        } else if (!address.equals(other.address))
            return false;
        if (city == null) {
            if (other.city != null)
                return false;
        } else if (!city.equals(other.city))
            return false;
        if (continent == null) {
            if (other.continent != null)
                return false;
        } else if (!continent.equals(other.continent))
            return false;
        if (country == null) {
            if (other.country != null)
                return false;
        } else if (!country.equals(other.country))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (postcode == null) {
            if (other.postcode != null)
                return false;
        } else if (!postcode.equals(other.postcode))
            return false;
        if (region == null) {
            if (other.region != null)
                return false;
        } else if (!region.equals(other.region))
            return false;
        return true;
    } 

    

    
    
}
