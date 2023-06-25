package com.hanhae.hanhae99.certification.controller;

import com.hanhae.hanhae99.certification.model.request.RegisterRequest;
import com.hanhae.hanhae99.certification.service.RegisterService;
import com.hanhae.hanhae99.global.model.response.JsonResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RegisterController {

    private final RegisterService service;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public JsonResponse<String> register(@Valid @RequestBody RegisterRequest req) {
        String msg = service.register(req);
        return JsonResponse.success(msg);
    }


}
