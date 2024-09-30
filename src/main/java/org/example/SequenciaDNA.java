/*
 * Authors: João Eduardo e Givanildo
 * Classe responsável por realizar o cálculo e análise dos nucleotídeos na sequência de DNA
 */

package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SequenciaDNA {

    public int[] calculaNucleotideos(String filePath) throws IOException {
        String sequencia = new String(Files.readAllBytes(Paths.get(filePath))).toUpperCase();
        int[] nucleotidesCount = new int[5];

        for (char nucleotide : sequencia.toCharArray()) {
            switch (nucleotide) {
                case 'A':
                    nucleotidesCount[0]++;
                    break;
                case 'C':
                    nucleotidesCount[1]++;
                    break;
                case 'G':
                    nucleotidesCount[2]++;
                    break;
                case 'T':
                    nucleotidesCount[3]++;
                    break;
                default:
                    nucleotidesCount[4]++;
            }
        }

        if (nucleotidesCount[4] > (sequencia.length() * 0.1)) {
            return null;
        }

        return nucleotidesCount;
    }
}
