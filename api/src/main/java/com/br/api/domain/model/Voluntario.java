package com.br.api.domain.model;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
public class Voluntario {

    private static final String SEQUENCE = "VOLUNTARIO_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE)
    @SequenceGenerator(name = SEQUENCE, sequenceName = SEQUENCE, allocationSize = 1)
    private Long id;

    @Column(name = "cpf", unique = true)
    private String cpf;

    @ManyToMany
    @JoinTable(
            name = "voluntario_seguido_ong",
            joinColumns = @JoinColumn(name = "voluntario_id"),
            inverseJoinColumns = @JoinColumn(name = "ong_id"
            ))
    private List<Ong> ongsSeguidas;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "voluntario_interesse",
            joinColumns = @JoinColumn(name = "voluntario_id"),
            inverseJoinColumns = @JoinColumn(name = "interesse_id"))
    private List<Interesse> interesses;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private Usuario usuario;
}
