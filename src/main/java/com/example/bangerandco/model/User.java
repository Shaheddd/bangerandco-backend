package com.example.bangerandco.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")

public class User {

    @Id
    @GeneratedValue
    private int userId;
    private String fullName;
    private String nic;
    private String roleType;
    private String email;
    private String password;

    @JsonFormat(pattern ="dd/mm/yyyy")
    private Date dateOfBirth;

}
