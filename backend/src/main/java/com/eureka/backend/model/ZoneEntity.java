package com.eureka.backend.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "zona")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ZoneEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String nombre;

    @OneToMany(
        mappedBy = "zona",
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY
    )
    @ToString.Exclude
    @JsonBackReference
    private List<UserEntity> usuarios;


}
