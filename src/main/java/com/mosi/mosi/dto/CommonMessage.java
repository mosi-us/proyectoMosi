package com.mosi.mosi.dto;

import com.mosi.mosi.constantes.CommonMessageConstant;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class CommonMessage implements CommonMessageConstant {

    public static Map<String, Object> commonErrorMessage(HttpServletResponse response){
        Map<String, Object> errorMessage = new HashMap<String, Object>();

        errorMessage.put(MESSAGE, COMMON_MESSAGE_ERROR);
        errorMessage.put(TYPE, MESSAGE_TYPE_ERROR);

        return errorMessage;
    }

    public static Map<String, Object> commonExceptionErrorMessage(HttpServletResponse response){
        Map<String, Object> errorMessage = new HashMap<String, Object>();

        errorMessage.put(MESSAGE, COMMON_EXCEPTION_MESSAGE_ERROR);
        errorMessage.put(TYPE, MESSAGE_TYPE_ERROR);

        return errorMessage;
    }

    public static Map<String, Object> commonExpiredTokenException(HttpServletResponse response){
        Map<String, Object> errorMessage = new HashMap<String, Object>();

        errorMessage.put(MESSAGE, EXPIRED_TOKEN_MESSAGE);
        errorMessage.put(MESSAGE_2, EXPIRED_TOKEN_MESSAGE);
        errorMessage.put(TYPE, MESSAGE_TYPE_ERROR);

        return errorMessage;
    }

    public static Map<String, Object> commonInvalidTokenMessage(HttpServletResponse response){
        Map<String, Object> errorMessage = new HashMap<String, Object>();

        errorMessage.put(MESSAGE, INVALID_TOKEN_MESSAGE);
        errorMessage.put(TYPE, MESSAGE_TYPE_ERROR);

        return errorMessage;
    }

    public static Map<String, Object> invalidUserQuery(HttpServletResponse response){
        Map<String, Object> errorMessage = new HashMap<String, Object>();

        errorMessage.put(MESSAGE, INVALID_TOKEN_CONSULT);
        errorMessage.put(TYPE, MESSAGE_TYPE_ERROR);

        return errorMessage;
    }

    public static Map<String, Object> commonInvalidChannelMessage(HttpServletResponse response){
        Map<String, Object> errorMessage = new HashMap<String, Object>();
        errorMessage.put(MESSAGE, INVALID_CHANNEL);
        errorMessage.put(TYPE, MESSAGE_TYPE_ERROR);

        return errorMessage;
    }

    public static Map<String, Object> commonInvalidUsernameMessage(){
        Map<String, Object> errorMessage = new HashMap<String, Object>();

        errorMessage.put(MESSAGE, MESSAGE_WRONG_USERNAME_PASSWORD);
        errorMessage.put(TYPE, MESSAGE_TYPE_ERROR);

        return errorMessage;
    }
    }
