package com.br.api.domain.model;

import com.br.api.domain.enumaration.Especie;
import com.br.api.domain.enumaration.Genero;
import com.br.api.domain.enumaration.Porte;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Animal {

    private static final String SEQUENCE = "ANIMAL_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE)
    @SequenceGenerator(name = SEQUENCE, sequenceName = SEQUENCE, allocationSize = 1)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Especie especie;

    @Enumerated(EnumType.STRING)
    private Genero genero;

    @Enumerated(EnumType.STRING)
    private Porte porte;

    private LocalDate dataDeNascimento;

    private String nome;

    private Boolean isExcluido;

    private String raca;

    private String situacaoVacinal;

    private String caracteristicas;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_ong")
    private Ong dono;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_animal", referencedColumnName = "id")
    private List<Foto> fotos;

}
