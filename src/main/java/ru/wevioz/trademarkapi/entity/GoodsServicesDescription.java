package ru.wevioz.trademarkapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "goods_services_descriptions")
public class GoodsServicesDescription {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "goods_services_description_seq_generator")
    @SequenceGenerator(
            name = "goods_services_description_seq_generator",
            sequenceName = "goods_services_descriptions_id_seq",
            allocationSize = 1
    )
    @Column(nullable = false, unique = true)
    private int id;

    @Column(name = "language_code")
    private String languageCode;

    @Column(name = "content")
    private String content;

    @ManyToOne
    @JoinColumn(name = "class_description_id")
    @JsonIgnore
    private ClassDescription classDescription;
}
