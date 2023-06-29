package com.hanhae.hanhae99.board.controller;

import com.hanhae.hanhae99.board.service.BoardHeartService;
import com.hanhae.hanhae99.certification.model.UserDetailsImpl;
import com.hanhae.hanhae99.global.model.response.JsonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/heart")
public class HeartController {

    private final BoardHeartService heartService;

    @PostMapping("/board/{id}")
    public JsonResponse<String> clickHeart(
            @PathVariable("id") Long boardId,
            @AuthenticationPrincipal UserDetailsImpl userDetails){
        String response = heartService.clickHeart(boardId, userDetails);
        return JsonResponse.success(response);

    }

}
