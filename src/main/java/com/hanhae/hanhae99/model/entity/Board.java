package com.hanhae.hanhae99.model.entity;

import jakarta.persistence.*;
import lombok.*;

@ToString
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Board extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pid;

    @Column
    private String name;

    @Column
    private String title;
    @Column
    private String content;

    @Column
    private String password;



}
