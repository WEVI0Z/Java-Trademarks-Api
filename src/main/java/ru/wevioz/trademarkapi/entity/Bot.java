package ru.wevioz.trademarkapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "bots")
public class Bot {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "plan_seq_generator")
    @SequenceGenerator(name = "plan_seq_generator", sequenceName = "plans_id_seq", allocationSize = 1)
    @Column(nullable = false, unique = true)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "status")
    private boolean status = false;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "user_id")
    private User user;
}
