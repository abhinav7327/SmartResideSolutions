package com.smartresidesolutions.api;

import com.smartresidesolutions.model.Register;
import com.smartresidesolutions.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface LoginApiInterface {

    /*@GET("/api/unknown")
    Call<LoginBean> doGetListResources();*/

    @POST("/examples/login.json")
    Call<User> getUser(@Body User user);

    @POST("/examples/register.json")
    Call<Register> registerUser(@Body Register register);
    //@GET("/examples/register")
    //Call<User> registerUser(@body user);
    /*@GET("/api/users?")
    Call<UserList> doGetUserList(@Query("page") String page);

    @FormUrlEncoded
    @POST("/api/users?")
    Call<UserList> doCreateUserWithField(@Field("name") String name, @Field("job") String job);*/
}

