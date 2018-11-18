package com.smartresidesolutions.api;

import com.smartresidesolutions.model.Register;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RegisterApiInterface {

    //@POST("/examples/login.json")
    Call<Register> getUser(@Body Register register);
}
