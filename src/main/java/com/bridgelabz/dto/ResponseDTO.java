package com.bridgelabz.dto;

import lombok.Data;

import java.util.Date;

/**
 * purpose: to maintain the response data in an object
 * class contains fields for message and Data
 *
 * @author  Sunil
 * @version 0.0.1
 * @since 10/12/2021
 */
@Data
public class ResponseDTO {
    private String message;
    private Object details;
    private Date timestamp;

    public ResponseDTO(String message, Object details, Date timestamp) {
        this.message = message;
        this.details = details;
        this.timestamp = timestamp;
    }
}
