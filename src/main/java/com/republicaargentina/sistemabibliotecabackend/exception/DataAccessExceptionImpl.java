package com.republicaargentina.sistemabibliotecabackend.exception;

import org.springframework.dao.DataAccessException;

public class DataAccessExceptionImpl extends DataAccessException {
    public DataAccessExceptionImpl(Throwable cause) {
        super("Error al acceder a los datos. Inténtelo mas tarde.", cause);
    }
}
