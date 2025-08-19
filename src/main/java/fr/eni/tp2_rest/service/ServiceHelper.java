package fr.eni.tp2_rest.service;

public class ServiceHelper {

    static <T> ServiceResponse<T> buildResponse(int code, T data) {
        ServiceResponse<T> serviceResponse = new ServiceResponse<>();

        serviceResponse.code = code;
        serviceResponse.data = data;

        // TODO : logger
        System.out.printf("Response code: %d%n", code);

        return serviceResponse;
    }

}
