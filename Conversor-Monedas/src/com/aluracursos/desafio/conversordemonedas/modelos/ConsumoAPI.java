package com.aluracursos.desafio.conversordemonedas.modelos;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class ConsumoAPI  {
    String URL_BASE = "https://v6.exchangerate-api.com/v6/";
    String API_KEY = "77d2582820a71765eb9046a8";
    Scanner teclado = new Scanner(System.in);
    private double valorUsuario = 0.0;

    public String solicitudAPI(String monedaBase, String monedaDestino){
        System.out.println("Ingresa la cantidad a convertir: ");
         valorUsuario = teclado.nextDouble();
        System.out.println("***: " + valorUsuario);
        //Crea la dirección url
        URI direccion = URI.create(URL_BASE + API_KEY+ "/pair/" + monedaBase +"/" + monedaDestino + "/" + valorUsuario);

        //Hace la solicitud al API para obtener el usd
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();
        try {
        HttpResponse<String> response =client
            .send(request, HttpResponse.BodyHandlers.ofString());
            String json = response.body();
            return  json;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public double getValorUsuario() {
        return valorUsuario;
    }

    public void setValorUsuario(double valorUsuario) {
        this.valorUsuario = valorUsuario;
    }
}
