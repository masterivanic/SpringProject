package com.myapp.myapp.entities;

import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size; 
import groovyjarjarantlr4.v4.runtime.misc.NotNull; 

@Entity
@Data @AllArgsConstructor
public class User {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@NotNull
	@Size(min = 5, max = 10)
    private String username;

    @NotNull
    private String email;
    @NotNull

    private String password;
    private boolean isAdmin;

}
