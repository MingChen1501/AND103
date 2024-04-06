package org.minhtc.and103.infrastructure.service;

import org.minhtc.and103.data.payload.login.LoginRequest;
import org.minhtc.and103.data.payload.login.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthService {
    @POST("login")
    Call<LoginResponse> login(@Body LoginRequest body);
}
