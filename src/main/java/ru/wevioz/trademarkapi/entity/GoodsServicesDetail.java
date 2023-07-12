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
@Table(name = "goods_services_details")
public class GoodsServicesDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "goods_services_detail_seq_generator")
    @SequenceGenerator(
            name = "goods_services_detail_seq_generator",
            sequenceName = "goods_services_details_id_seq",
            allocationSize = 1
    )
    @Column(nullable = false, unique = true)
    private int id;

    @ManyToOne
    @JoinColumn(name = "trademark_id")
    @JsonIgnore
    private Trademark trademark;

    @OneToOne(mappedBy = "goodsServicesDetail")
    private GoodsService goodsService;
}
