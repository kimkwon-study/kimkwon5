package com.hanhae.hanhae99.board.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hanhae.hanhae99.board.model.request.CommentRequest;
import com.hanhae.hanhae99.board.model.response.CommentResponse;
import com.hanhae.hanhae99.global.model.entity.AuditingFields;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_pid")
    private Board board;

    @JsonIgnore
    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL)
    private List<CommentHeart> commentHearts = new ArrayList<>();

    protected Comment() {
    }

}
