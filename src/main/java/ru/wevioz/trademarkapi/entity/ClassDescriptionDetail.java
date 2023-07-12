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
@Table(name = "class_description_details")
public class ClassDescriptionDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "class_description_detail_seq_generator")
    @SequenceGenerator(
            name = "class_description_detail_seq_generator",
            sequenceName = "class_description_details_id_seq",
            allocationSize = 1
    )
    @Column(nullable = false, unique = true)
    private int id;

    @OneToOne
    @JoinColumn(name = "goods_service_id")
    @JsonIgnore
    private GoodsService goodsService;

    @OneToMany(mappedBy = "goodsService")
    private List<ClassDescription> classDescriptions;
}
