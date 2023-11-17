package com.app.project.entity;

import lombok.Data;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "DEPENDENT_NIPUN")
public class Dependent implements Serializable {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DEPENDENT_ID")
    private String depId;

    @Column(name = "DEPENDENT_NAME")
    private String depName;

    @Column(name = "DEPENDENT_AGE")
    private int depAge;

    @Column(name = "DEPENDENT_GENDER")
    private String depGender;





}
