package com.mosi.mosi.constantes;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

public interface CommonMessageConstant {
    String MESSAGE							= "message";
    String MESSAGE_2				    	= "message2";
    String TYPE								= "type";
    String MESSAGE_TYPE_ERROR				= "error";
    String MESSAGE_TYPE_SUCCESS				= "success";
    String COMMON_EXCEPTION_MESSAGE_ERROR 	= "Lo sentimos, ha ocurrido un error con su consulta, intente nuevamente";
    String COMMON_MESSAGE_ERROR				= "Verifique los datos enviados";
    String EXPIRED_TOKEN_MESSAGE			= "Lo sentimos, su sesión ha finalizado, por favor inicie sesión nuevamente";
    String INVALID_TOKEN_MESSAGE			= "Lo sentimos, ha ocurrido un error con su consulta, intente nuevamente";
    String INVALID_TOKEN_CONSULT			= "Esta cuenta no pertenece al usuario solicitante";
    String INVALID_CHANNEL					= "Canal inválido";
    String MESSAGE_WRONG_USERNAME_PASSWORD	= "El usuario y/o contraseña son incorrectos";

    public final String COMMON_SUCCESS_FINISH_PROCESS 	= "Proceso finalizado exitosamente";
    public final String COMMON_SOAP_EXIT_FAULT_PARAMS 	= "Parámetros de salida del servicio incorrectos";


    Map<String, Object> STATUS_MAP = ImmutableMap.<String, Object>builder()
            .put("403", "Token vencido, por favor inicie sesión nuevamente")
            .put("404", "Problemas con el servidor, por favor intente más tarde")
            .build();
}
