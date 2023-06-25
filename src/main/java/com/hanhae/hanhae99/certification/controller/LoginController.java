package com.hanhae.hanhae99.certification.controller;

import com.hanhae.hanhae99.certification.model.request.LoginRequest;
import com.hanhae.hanhae99.certification.service.LoginService;
import com.hanhae.hanhae99.global.model.response.JsonResponse;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService service;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public JsonResponse<String> login(
            @RequestBody @Valid LoginRequest request,
            HttpServletResponse response
    ) {
        String msg = service.login(response, request);
        return JsonResponse.success(msg);
    }


}
