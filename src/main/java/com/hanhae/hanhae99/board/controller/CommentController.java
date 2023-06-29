package com.hanhae.hanhae99.board.controller;

import com.hanhae.hanhae99.board.model.request.CommentRequest;
import com.hanhae.hanhae99.board.model.response.CommentResponse;
import com.hanhae.hanhae99.board.service.CommentService;
import com.hanhae.hanhae99.certification.model.UserDetailsImpl;
import com.hanhae.hanhae99.global.model.response.JsonResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{id}")
    public JsonResponse<CommentResponse> save(@RequestBody CommentRequest commentRequest,
                                              @PathVariable("id") Long boardPid,
                                              @AuthenticationPrincipal UserDetailsImpl userDetails) {
        CommentResponse commentResponse = commentService.saveComment(commentRequest, boardPid, userDetails.getUsername());
        return JsonResponse.success(commentResponse);
    }

    @PutMapping("/{id}")
    public JsonResponse<CommentResponse> update(@RequestBody CommentRequest commentRequest,
                                                @PathVariable("id") Long commentPid,
                                                @AuthenticationPrincipal UserDetailsImpl userDetails) {
        CommentResponse commentResponse = commentService.updateComment(commentRequest, commentPid, userDetails);
        return JsonResponse.success(commentResponse);
    }

    @DeleteMapping("/{id}")
    public JsonResponse<String> delete(@PathVariable("id") Long boardPid,
                                       @AuthenticationPrincipal UserDetailsImpl userDetails) {
        String commentResponse = commentService.deleteComment(boardPid, userDetails);
        return JsonResponse.success(commentResponse);
    }

}
