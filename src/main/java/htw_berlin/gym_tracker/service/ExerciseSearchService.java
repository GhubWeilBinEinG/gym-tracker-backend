package htw_berlin.gym_tracker.service;

import htw_berlin.gym_tracker.entity.ExerciseSearchResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Arrays;
import java.util.List;

@Service
public class ExerciseSearchService {

    private final RestClient restClient;

    public ExerciseSearchService(@Value("${api.ninjas.key}") String apiKey) {
        this.restClient = RestClient.builder()
                .baseUrl("https://api.api-ninjas.com/v1")
                .defaultHeader("X-Api-Key", apiKey)
                .build();
    }

    public List<ExerciseSearchResult> searchByMuscle(String muscle) {
        ExerciseSearchResult[] results = restClient.get()
                .uri("/exercises?muscle={muscle}", muscle)
                .retrieve()
                .body(ExerciseSearchResult[].class);
        return results != null ? Arrays.asList(results) : List.of();
    }

    public List<ExerciseSearchResult> searchByName(String name) {
        ExerciseSearchResult[] results = restClient.get()
                .uri("/exercises?name={name}", name)
                .retrieve()
                .body(ExerciseSearchResult[].class);
        return results != null ? Arrays.asList(results) : List.of();
    }
}