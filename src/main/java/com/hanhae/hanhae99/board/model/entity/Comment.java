package com.hanhae.hanhae99.board.model.entity;

import com.hanhae.hanhae99.board.model.request.CommentRequest;
import com.hanhae.hanhae99.global.model.entity.AuditingFields;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
public class Comment extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long pid;

    @Column
    @Setter
    private String name;

    @Setter
    @Column
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_pid")
    private Board board;

    protected Comment() {
    }

    private Comment(String content, Board board, String name) {
        this.content = content;
        this.board = board;
        this.name = name;
    }

    public static Comment getEntity(CommentRequest request, Board board, String name){
        return new Comment(request.content(), board, name);
    }

}
