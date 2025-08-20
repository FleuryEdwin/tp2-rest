package fr.eni.tp2_rest.service;

public class ServiceResponse<T> {
    public int code;
    public String message;
    public T data;
}
