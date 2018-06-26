package com.ellis.memberplanet.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Api {

        private Retrofit retro = null;


        public Retrofit getRetro() {

            String url = "http://e2aea4eb.ngrok.io/memberplanet/APIs/";
            if (retro==null) {
                retro = new Retrofit.Builder()
                        .baseUrl(url)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                //.baseUrl("http://farmconnect.leefranco.com/farmconnect/")
            }

            return retro;
        }
    }


