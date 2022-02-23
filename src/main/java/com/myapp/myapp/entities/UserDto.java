package com.myapp.myapp.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.myapp.myapp.web.UserController.ValidEmail;

import org.springframework.data.annotation.Id;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;



@Data @AllArgsConstructor @ToString
@NoArgsConstructor
public class UserDto {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    @NotNull
    @NotEmpty
    @Size(min = 5, max = 10)
    private String firstName;
    
    @NotNull
    @NotEmpty
    private String lastName;
    
    @NotNull
    @NotEmpty
    private String password;

    @NotNull
    @NotEmpty
    private String matchingPassword;
    
    @ValidEmail
    @NotNull
    @NotEmpty
    private String email;

}
