package br.com.alura.screenmatch.service;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;
import com.theokanning.openai.OpenAiHttpException;

public class ConsultaChatGPT {
    public static String obterTraducao(String texto) {
        OpenAiService service = new OpenAiService("SUA_CHAVE_AQUI");

        CompletionRequest requisicao = CompletionRequest.builder()
                .model("gpt-3.5-turbo-instruct")
                .prompt("traduza para o português o texto: " + texto)
                .maxTokens(1000)
                .temperature(0.7)
                .build();

        try {
            var resposta = service.createCompletion(requisicao);
            return resposta.getChoices().get(0).getText().trim();
        } catch (OpenAiHttpException e) {
            // Tratamento do erro de cota excedida
            System.err.println("Erro na chamada da API do OpenAI: " + e.getMessage());
            return "Tradução não disponível no momento devido a limite de cota excedido.";
        }
    }
}