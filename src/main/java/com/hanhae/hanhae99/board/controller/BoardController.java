package com.hanhae.hanhae99.board.controller;

import com.hanhae.hanhae99.board.service.BoardService;
import com.hanhae.hanhae99.global.model.response.JsonResponse;
import com.hanhae.hanhae99.board.model.request.BoardSaveRequest;
import com.hanhae.hanhae99.board.model.response.BoardResponse;
import com.hanhae.hanhae99.global.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BoardController {

    private final BoardService service;

    @GetMapping("/findall")
    public JsonResponse<List<BoardResponse>> findall() {
        List<BoardResponse> responseList = service.findAll();
        return JsonResponse.success(responseList);
    }

    @PostMapping("/save")
    public JsonResponse<BoardResponse> save(
            @RequestBody @Valid BoardSaveRequest request,
            HttpServletRequest req
    ) {
        BoardResponse boardResponse = service.save(request, req);
        return JsonResponse.success(boardResponse);
    }

    @GetMapping("/find/{id}")
    public JsonResponse<BoardResponse> findbyId(@PathVariable("id") String id) {
        BoardResponse response = service.findById(Long.parseLong(id));
        return JsonResponse.success(response);
    }

    @PutMapping("/update/{id}")
    public JsonResponse<BoardResponse> update(@PathVariable("id") String id,
                                              @RequestBody @Valid BoardSaveRequest req,
                                              HttpServletRequest request
    ) {
        BoardResponse response = service.updateBoard(Long.parseLong(id), req, request);
        return JsonResponse.success(response);
    }

    @DeleteMapping("/delete/{id}")
    public JsonResponse<String> update(@PathVariable("id") String id,
                                       HttpServletRequest req) {
        String response = service.deleteBoard(Long.parseLong(id), req);
        return JsonResponse.success(response);
    }

}
