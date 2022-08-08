package com.eventtracker.events.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class Users {

    public class UsersDTO {
        
        private Long id;

        @NotNull
        @Size(max = 255)
        private String firstName;

        @NotNull
        @Size(max = 255)
        private String lastName; 

        @NotNull 
        @Size
        private String email; 

        @Size(max = 255)
        private String address;
    }

    @Override
    public String toString() {
        return "Users []";
    }  
    
}
