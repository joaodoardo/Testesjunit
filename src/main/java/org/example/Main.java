/*
 * Authors: João Eduardo e Givanildo
 * Classe principal para execução da análise de sequência de DNA a por um arquivo de texo
 */

package org.example;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        SequenciaDNA sequenciaDNA = new SequenciaDNA();
        try {
            String filePath = "arquivo_exemplo_atividadeTDD.txt";
            int[] result = sequenciaDNA.calculaNucleotideos(filePath);

            if (result != null) {
                System.out.println("A: " + result[0]);
                System.out.println("C: " + result[1]);
                System.out.println("G: " + result[2]);
                System.out.println("T: " + result[3]);
                System.out.println("Caracteres inválidos: " + result[4]);
            } else {
                System.out.println("Caracteres inválidos excedem 10% da sequência");
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }
}
