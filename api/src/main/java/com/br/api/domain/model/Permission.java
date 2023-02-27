package com.br.api.domain.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@Builder
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
public class Permission {

    public Permission() {
    }

    public Permission(String permissao) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String name;
}

