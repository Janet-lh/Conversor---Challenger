package com.aluracursos.desafio.conversordemonedas.principal;

import com.aluracursos.desafio.conversordemonedas.modelos.ConsumoAPI;
import com.aluracursos.desafio.conversordemonedas.modelos.ConvierteDatos;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) throws IOException, InterruptedException {
        //https://v6.exchangerate-api.com/v6/77d2582820a71765eb9046a8/latest/USD
        //77d2582820a71765eb9046a8
        String monedaBase = "";
        String monedaDestino = "";

        ConsumoAPI objAPI = new ConsumoAPI();
        ConvierteDatos conversor = new ConvierteDatos();
        Scanner teclado = new Scanner(System.in);

        var opcion = -1;
        while (opcion != 0) {
           try {
            String menu = """
                    *******************************
                    Bienvenid@ al conversor de monedas
                    Escoja la operación a realizar:
                    1 - Dólar => Peso Argentino
                    2 - Peso Argentino => Dólar
                    3 - Dólar => Peso Chilerno
                    4 - Peso Chileno => Dólar
                    5 - Real Brasileño => Dólar
                    6 - Dólar => Real Brasileño
                                        
                    0 - Salir                 
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            System.out.println("***: " + opcion);

             if (opcion == 0) {
                 System.out.println("Cerrando la aplicación...");
                 break;
             }

            switch (opcion) {
                case 1:
                    monedaBase = "USD";
                    monedaDestino = "ARS";
                    break;
                case 2:
                    monedaBase = "ARS";
                    monedaDestino = "USD";
                    break;
                case 3:
                    monedaBase = "USD";
                    monedaDestino = "CLP";
                    break;
                case 4:
                    monedaBase = "CLP";
                    monedaDestino = "USD";
                    break;
                case 5:
                    monedaBase = "USD";
                    monedaDestino = "BRL";
                    break;
                case 6:
                    monedaBase = "BRL";
                    monedaDestino = "USD";
                    break;

                default:
                    System.out.println("Valor no válido");
                    break;
            }

            //Solicita la conversion
            String json = objAPI.solicitudAPI(monedaBase, monedaDestino);
            System.out.println(json);

            var converter = conversor.convierteDatos(json);

            BigDecimal resultado = BigDecimal.valueOf(objAPI.getValorUsuario() * converter.conversion_rate());
            System.out.println("El valor de: $" + objAPI.getValorUsuario() + " ["
                    + monedaBase + "] corresponde al valor final de =>>> $"
                    + resultado.setScale(2, RoundingMode.HALF_UP) + " [" + monedaDestino + "].");
                System.out.println(objAPI.getValorUsuario());
           } catch (InputMismatchException e) {
             System.out.println("Ingrese un dato valido");
             teclado.next();
           }

        }
    }
}
