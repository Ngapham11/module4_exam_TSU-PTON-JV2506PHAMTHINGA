package com.ra.model.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DataResponse<T> {
    private String message;
    private T data;
    private int status;
}
