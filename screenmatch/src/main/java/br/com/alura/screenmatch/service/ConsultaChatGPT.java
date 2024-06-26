package br.com.alura.screenmatch.service;

import com.theokanning.openai.OpenAiHttpException;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;

public class ConsultaChatGPT {
    public static String obterTraducao(String texto) {
        OpenAiService service = new OpenAiService(System.getenv("OPENAI_APIKEY"));


        CompletionRequest requisicao = CompletionRequest.builder()
                .model("text-davinci-003")
                .prompt("traduza para o português o texto: " + texto)
                .maxTokens(1000)
                .temperature(0.7)
                .build();


        try {
            var resposta = service.createCompletion(requisicao);
            return resposta.getChoices().get(0).getText().trim();
        } catch (OpenAiHttpException e) {
            // Tratamento do erro de cota excedida ou chave expirada
            // System.err.println("Erro na chamada da API do OpenAI: " + e.getMessage());
            return texto;  // Retorna o texto original se a tradução falhar
        }
    }
}
