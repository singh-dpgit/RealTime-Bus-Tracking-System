package com.radharath.Radharath_backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="stops")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Stop {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;


    @Column(nullable=false,length=100)
    private String name;

    @Column(nullable=false)
    private Double latitude;


    @Column(nullable=false)
    private Double longitude;
}
