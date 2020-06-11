package pers.model.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
//@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(generator = "increment")
    private Integer id;
    // @Column(name = "name")
    private String name;
    private Integer age;
    private String email;


}
