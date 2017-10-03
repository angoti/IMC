package com.professorangoti.imc;

import com.professorangoti.imc.Dados;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiEndpoint {
    @GET("json")
    Call<Dados> obterDados();
}
