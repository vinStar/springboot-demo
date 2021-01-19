package com.example.springboot.generics;

public interface UserOperationHandler<DATA, OPERATION extends UserOperation<DATA>> {
    Class<OPERATION> getOperationClass();

    void onStart(OPERATION operation);


    default Exception safeStart(OPERATION operation) {
        try {
            onStart(operation);
        } catch (Exception ex) {
            return ex;
        }
        return null;
    }
}
