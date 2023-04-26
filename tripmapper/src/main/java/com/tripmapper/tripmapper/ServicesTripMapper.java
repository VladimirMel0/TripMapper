package com.tripmapper.tripmapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import reactor.core.publisher.Mono;

@Service
public class ServicesTripMapper {
    private WebClient webclient;

    public ServicesTripMapper(WebClient.Builder builder,
    @Value("${open.api.key}") String apiKey){
        this.webclient = builder
        .baseUrl("https://api.openai.com/v1/completions")
        .defaultHeader("Content-Type", "application/json")
        .defaultHeader("Authorization:", String.format("Bearer %s", apiKey))
        .build();
    }

    public Mono<ChatGPTResponse> createTripMapper(String destiny){
        ChatGPTRequest request = createTripRequest(destiny);{

            return webclient.post().bodyValue(request)
            .retrieve()
            .bodyToMono(ChatGPTResponse.class);
        }
    };

    private ChatGPTRequest createTripRequest(String destiny){
    String question = "Quero que você crie um roteiro para uma viagem para a cidade de:"+ destiny ;

    return new ChatGPTRequest(
        "text-davinci-003", question, 0.9,
        2000, 1.0, 0.0, 0.6);
  
}
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
record  ChatGPTRequest(String model, String prompt,Double temperature, Integer maxTokens, Double topP, 
                        Double frequencyPenalty, Double presencePenalty ){

                        }
record ChatGPTResponse(List<Choice> choices){

}
record Choice(String text){

}
}
