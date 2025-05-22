package com.aye.web.utill;


import com.aye.web.dto.ApiResponseDto;

public class ResponseUtils {

    public static <T> ApiResponseDto<T> success(int status, String message, T responseData) {
        return new ApiResponseDto<>(status, message, responseData);
    }

    public static <T> ApiResponseDto<T> error(int status, String message) {
        return new ApiResponseDto<>(status, message, null);
    }

}
