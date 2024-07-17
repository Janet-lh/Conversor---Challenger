package com.aluracursos.desafio.conversordemonedas.modelos;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ConvierteDatos {

    public  TasaConversionAPI convierteDatos (String json){
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .setPrettyPrinting()
                .create();

        TasaConversionAPI tasaConversionAPI = gson.fromJson(json, TasaConversionAPI.class);
        //System.out.println("Tasa de Conversion API: " + tasaDeConversionApi);

        TasaDeConversion tasaDeConversion = new TasaDeConversion(tasaConversionAPI);
        //System.out.println("Tasa de Conversion objeto: " + tasaDeConversion);

        return tasaConversionAPI;
    }

}
