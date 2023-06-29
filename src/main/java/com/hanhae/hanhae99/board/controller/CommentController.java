package com.hanhae.hanhae99.board.controller;

import com.hanhae.hanhae99.board.model.request.CommentRequest;
import com.hanhae.hanhae99.board.model.response.CommentResponse;
import com.hanhae.hanhae99.board.service.CommentService;
import com.hanhae.hanhae99.global.model.response.JsonResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{id}")
    public JsonResponse<CommentResponse> save(@RequestBody CommentRequest commentRequest,
                                              @PathVariable("id") Long boardPid,
                                              HttpServletRequest req) {
        CommentResponse commentResponse = commentService.saveComment(commentRequest, boardPid, req);
        return JsonResponse.success(commentResponse);
    }

    @PutMapping("/{id}")
    public JsonResponse<CommentResponse> update(@RequestBody CommentRequest commentRequest,
                                                @PathVariable("id") Long commentPid,
                                                HttpServletRequest req) {
        CommentResponse commentResponse = commentService.updateComment(commentRequest, commentPid, req);
        return JsonResponse.success(commentResponse);
    }

    @DeleteMapping("/{id}")
    public JsonResponse<String> delete(@PathVariable("id") Long boardPid,
                                       HttpServletRequest req) {
        String commentResponse = commentService.deleteComment(boardPid, req);
        return JsonResponse.success(commentResponse);
    }

}
