package ru.wevioz.trademarkapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "class_descriptions")
public class ClassDescription {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "class_description_seq_generator")
    @SequenceGenerator(
            name = "class_description_seq_generator",
            sequenceName = "class_descriptions_id_seq",
            allocationSize = 1
    )
    @Column(nullable = false, unique = true)
    private int id;

    @Column(name = "class_number")
    private int classNumber;

    @ManyToOne
    @JoinColumn(name = "class_description_detail_id")
    @JsonIgnore
    private GoodsService goodsService;

    @OneToMany(mappedBy = "classDescription")
    private List<GoodsServicesDescription> goodsServicesDescriptions;
}
