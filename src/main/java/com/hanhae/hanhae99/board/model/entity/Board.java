package com.hanhae.hanhae99.board.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hanhae.hanhae99.board.model.response.BoardResponse;
import com.hanhae.hanhae99.board.model.response.CommentResponse;
import com.hanhae.hanhae99.global.model.entity.AuditingFields;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

    @JsonIgnore
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    @JsonIgnore
    @OneToMany( mappedBy = "board", cascade = CascadeType.ALL)
    private List<BoardHeart> boardHearts = new ArrayList<>();

    public static BoardResponse changeEntity(Board board) {

        long board_size = 0 ;
        if(board.getBoardHearts()==null){
            board_size=0;
        }else{
            board_size = board.getBoardHearts().size();
        }

        if(Objects.isNull(board.getComments())){
            return new BoardResponse(board.getTitle(),
                    board.getName(),
                    board.getContent(),
                    board.getCreatedAt().toString(),
                    board_size,
                    new ArrayList<>()
            );
        }else{
            return new BoardResponse(board.getTitle(),
                    board.getName(),
                    board.getContent(),
                    board.getCreatedAt().toString(),
                    0,
                    board.getComments().stream().map(a -> {
                        return new CommentResponse(
                                a.getName(),
                                a.getContent(),
                                a.getCommentHearts().size()
                        );
                    }).collect(Collectors.toList())
            );
        }

    }

//    public static BoardResponse changeEntity(Board board) {
//
//        return new BoardResponse(board.getTitle(),
//                board.getName(),
//                board.getContent(),
//                board.getCreatedAt().toString(),
//                board_size,
//                board.getComments().stream().map(a -> {
//                    return new CommentResponse(
//                            a.getName(),
//                            a.getContent(),
//                            a.getCommentHearts().size()
//                    );
//                }).collect(Collectors.toList())
//        );
//    }


}
