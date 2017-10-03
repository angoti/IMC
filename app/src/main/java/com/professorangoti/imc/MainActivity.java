package com.professorangoti.imc;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.DecimalFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calcula();
    }

    public void calcula() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.11/json/") 
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        ApiEndpoint apiService = retrofit.create(ApiEndpoint.class);
        Call<Dados> call = apiService.obterDados();

        call.enqueue(new Callback<Dados>() {//chamada assíncrona
            public void onResponse(Call<Dados> call, Response<Dados> response) {
                Dados dados = response.body();
                double peso = dados.getPeso();
                double altura = dados.getAltura();
                double imc = peso / (altura * altura);
                DecimalFormat formato = new DecimalFormat("0.00");
                ((TextView) findViewById(R.id.imc_id)).setText(formato.format(imc) + "");
                ImageView img = (ImageView) findViewById(R.id.imagem);
                if (imc < 18.5)
                    img.setImageResource(R.drawable.images1);
                else if (imc >= 18.5 && imc < 25)
                    img.setImageResource(R.drawable.images2);
                else if (imc >= 25 && imc < 30)
                    img.setImageResource(R.drawable.images3);
                else
                    img.setImageResource(R.drawable.images4);
            }

            public void onFailure(Call<Dados> call, Throwable t) {
                Log.i("teste", t.getStackTrace().toString());
            }
        });
    }
}
