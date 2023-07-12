package ru.wevioz.trademarkapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "trademarks")
public class Trademark {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trademark_seq_generator")
    @SequenceGenerator(name = "trademark_seq_generator", sequenceName = "trademarks_id_seq", allocationSize = 1)
    @Column(nullable = false, unique = true)
    private int id;

    @Column(name = "application_number")
    private int applicationNumber;

    @Column(name = "kind_mark")
    private String kindMark;

    @Column(name = "mark_feature")
    private String markFeature;

    @Column(name = "mark_current_status_code")
    private String markCurrentStatusCode;

    @OneToOne(mappedBy = "trademark")
    private WordMarkSpecification wordMarkSpecification;

    @OneToMany(mappedBy = "trademark")
    private List<GoodsServicesDetail> goodsServicesDetails;
}
