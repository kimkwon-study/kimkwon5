package com.hanhae.hanhae99.board.controller;

import com.hanhae.hanhae99.board.model.request.CommentRequest;
import com.hanhae.hanhae99.board.service.CommentHeartService;
import com.hanhae.hanhae99.certification.model.UserDetailsImpl;
import com.hanhae.hanhae99.global.model.response.JsonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment/heart")
public class CommentHeartController {

    private final CommentHeartService service;

    @PostMapping("/{id}")
    public JsonResponse<String> heartInput(
            @PathVariable("id") Long boardPid,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ){
        return JsonResponse.success(service.clickHeart(boardPid, userDetails.getUser()));
    }

}
