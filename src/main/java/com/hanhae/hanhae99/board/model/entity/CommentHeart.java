package com.hanhae.hanhae99.board.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hanhae.hanhae99.global.model.entity.AuditingFields;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Entity
@Builder
@AllArgsConstructor
@Getter
public class CommentHeart extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pid;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_pid")
    private Comment comment;

    @Column
    private String username;


    protected CommentHeart() {}
}
