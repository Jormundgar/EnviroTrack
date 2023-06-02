package com.volkov.envirotrackapi.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "sensors")
@NoArgsConstructor
@Getter
@Setter
public class Sensor {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    @NotEmpty(message = "Sensor`s name should not be empty")
    @Size(min = 3, max = 30, message = "Sensor`s name should be between 3 and 30 characters")
    private String name;

    @OneToMany(mappedBy = "sensorOfMeasure")
    private List<Measurement> measurements;
}
