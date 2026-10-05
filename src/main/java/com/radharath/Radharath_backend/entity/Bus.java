package com.radharath.Radharath_backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="buses")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length=20,unique = true)
    private String busNumber;

    @Column(nullable = false,length=20,unique = true)
    private String registrationNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BusType busType;

    @Column(nullable=false)
    private int capacity;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    @Column(nullable = false)
    private BusStatus busStatus=BusStatus.PENDING;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="registered_by",nullable = false)
    private User registeredBy;
}
