package com.wiley.schoolJPA.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sid", nullable = false)
    private Integer id;

    @Column(name = "fname")
    private String fName;

    @Column(name = "lname")
    private String lName;

    @ManyToMany(mappedBy = "studentRoster")
    private Set<Course> myClasses;

    public Student() {}

    public Student(Set<Course> myClasses) {
        this.myClasses = myClasses;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }
}
