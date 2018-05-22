package com.ellis.memberplanet.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Api {

        private Retrofit retro = null;

        public Retrofit getRetro() {

            if (retro==null) {
                retro = new Retrofit.Builder()
                        .baseUrl("http://bfb17038.ngrok.io/memberplanet/APIs/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                //.baseUrl("http://farmconnect.leefranco.com/farmconnect/")
            }

            return retro;
        }
    }


