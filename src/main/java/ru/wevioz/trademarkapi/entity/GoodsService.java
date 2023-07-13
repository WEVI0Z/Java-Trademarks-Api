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
@Table(name = "goods_services")
public class GoodsService {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "goods_service_seq_generator")
    @SequenceGenerator(
            name = "goods_service_seq_generator",
            sequenceName = "goods_services_id_seq",
            allocationSize = 1
    )
    @Column(nullable = false, unique = true)
    private int id;

    @Column(name = "classification_number")
    private int classificationVersion;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "goods_services_detail_id")
    @JsonIgnore
    private GoodsServicesDetail goodsServicesDetail;

    @OneToOne(mappedBy = "goodsService", cascade = {CascadeType.ALL})
    private ClassDescriptionDetail classDescriptionDetail;
}
