package com.hanhae.hanhae99.board.model.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BoardHeart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pid;



    @Column
    private String username;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    public BoardHeart( String username, Board board) {

        this.username = username;
        this.board = board;
    }
}
