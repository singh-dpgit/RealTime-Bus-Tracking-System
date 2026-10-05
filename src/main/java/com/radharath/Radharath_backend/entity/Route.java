package com.radharath.Radharath_backend.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="routes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false,unique = true,length=20)
    private String routeNumber;

    @Column(nullable=false,length=100)
    private String source;

    @Column(nullable = false,length=100)
    private String destination;
}
