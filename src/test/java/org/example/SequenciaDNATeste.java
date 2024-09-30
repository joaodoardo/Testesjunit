/*
 * Authors: João Eduardo e Givanildo
 * Classe de testes unitários para verificar a funcionalidade da classe SequenciaDNA
 */

package org.example;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class SequenciaDNATeste {

    private Path arquivoValido;
    private Path arquivoInvalido;
    private Path arquivoComCaracteresInvalidos;
    private Path arquivoInexistente;

    @BeforeEach
    void setUp() throws IOException {
        arquivoValido = Files.createTempFile("DNAvalido", ".txt");
        Files.write(arquivoValido, "AAAGTCTGAC".getBytes());

        arquivoInvalido = Files.createTempFile("DNAinvalido", ".txt");
        Files.write(arquivoInvalido, "ABC TEM FALHA".getBytes());

        arquivoComCaracteresInvalidos = Files.createTempFile("DNACaracteresInvalidos", ".txt");
        Files.write(arquivoComCaracteresInvalidos, "AACTGTCGBA".getBytes());

        arquivoInexistente = Paths.get("arquivo_inexistente.txt");
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(arquivoValido);
        Files.deleteIfExists(arquivoInvalido);
        Files.deleteIfExists(arquivoComCaracteresInvalidos);
    }

    @Test
    @DisplayName("Verifica se o método retorna corretamente os valores de A, C, G, T para uma sequência válida")
    void testValidDNASequence() throws IOException {
        SequenciaDNA sequencer = new SequenciaDNA();
        int[] expected = {4, 2, 2, 2, 0};
        assertArrayEquals(expected, sequencer.calculaNucleotideos(arquivoValido.toString()));
    }

    @Test
    @DisplayName("Verifica se o método retorna null quando mais de 10% dos caracteres são inválidos")
    void testInvalidDNASequence() throws IOException {
        SequenciaDNA sequencer = new SequenciaDNA();
        assertNull(sequencer.calculaNucleotideos(arquivoInvalido.toString()));
    }

    @Test
    @DisplayName("Verifica se o método conta corretamente caracteres inválidos dentro do limite permitido")
    void testDNAWithInvalidChars() throws IOException {
        SequenciaDNA sequencer = new SequenciaDNA();
        int[] expected = {3, 2, 2, 2, 1};
        assertArrayEquals(expected, sequencer.calculaNucleotideos(arquivoComCaracteresInvalidos.toString()));
    }

    @Test
    @DisplayName("Verifica se o método lança exceção quando o arquivo não é encontrado")
    void testFileNotFound() {
        SequenciaDNA sequencer = new SequenciaDNA();
        assertThrows(IOException.class, () -> sequencer.calculaNucleotideos(arquivoInexistente.toString()));
    }
}
