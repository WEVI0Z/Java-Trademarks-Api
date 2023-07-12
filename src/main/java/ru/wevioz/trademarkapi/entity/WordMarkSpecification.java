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
@Table(name = "word_mark_specifications")
public class WordMarkSpecification {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "word_mark_specification_seq_generator")
    @SequenceGenerator(
            name = "word_mark_specification_seq_generator",
            sequenceName = "word_mark_specifications_id_seq",
            allocationSize = 1
    )
    @Column(nullable = false, unique = true)
    private int id;

    @Column(name = "mark_verbal_element_text")
    private String markVerbalElementText;

    @OneToOne
    @JoinColumn(name = "trademark_id")
    @JsonIgnore
    private Trademark trademark;
}
