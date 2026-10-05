package com.radharath.Radharath_backend.entity; // apne main class ke package se match karo

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "route_stops",
        uniqueConstraints = {
                // ek route mein same stop do baar nahi aa sakta
                @UniqueConstraint(name = "uk_route_stop", columnNames = {"route_id", "stop_id"}),
                // ek route mein ek position (order) par do stops nahi ho sakte
                @UniqueConstraint(name = "uk_route_order", columnNames = {"route_id", "stop_order"})
        }
)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class RouteStop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "route_id", nullable = false)
    private Route route;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stop_id", nullable = false)
    private Stop stop;

    @Column(nullable = false)
    private Integer stopOrder;
}