package com.hanhae.hanhae99.global.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JsonResponse<T> {

    private String result_code;
    private T result;


    public static <T> JsonResponse<T> error(String errorcode, T errorCode){
        return new JsonResponse<>(errorcode,errorCode);
    }

    public static <T> JsonResponse<T> success(T result){
        return new JsonResponse<>("Success",result);
    }

}
