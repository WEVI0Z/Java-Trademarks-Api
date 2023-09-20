package ru.wevioz.trademarkapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "plans")
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "plan_seq_generator")
    @SequenceGenerator(name = "plan_seq_generator", sequenceName = "plans_id_seq", allocationSize = 1)
    @Column(nullable = false, unique = true)
    private int id;

    @Column(name = "type")
    private String type;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "start_date")
    private LocalDate startDate;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "user_id")
    private User user;
}
