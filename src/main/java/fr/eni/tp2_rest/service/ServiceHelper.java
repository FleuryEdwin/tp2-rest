package fr.eni.tp2_rest.service;

public class ServiceHelper {

    static <T> ServiceResponse<T> buildResponse(int code, String message, T data) {
        ServiceResponse<T> serviceResponse = new ServiceResponse<>();

        serviceResponse.code = code;
        serviceResponse.message = message;
        serviceResponse.data = data;

        // TODO : logger
        System.out.printf("Response code: %d%n", code);

        return serviceResponse;
    }

}
