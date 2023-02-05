package io.b1ruk.proj.irrigationDemo.irrigationDemo.model;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "crop")
public class Crop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "water_requried_amount")
    private String waterRequiredAmount;

    @OneToMany(mappedBy = "crop")
    private Set<Land> lands=new HashSet<>();

}
