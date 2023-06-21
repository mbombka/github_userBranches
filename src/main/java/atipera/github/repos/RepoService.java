package atipera.github.repos;

import atipera.github.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class RepoService {

    private final WebClient webClient;

    public RepoService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.github.com").build();
    }

    public Flux<RepoInfoRaw>  getAllRepositories(String username) {
        return webClient.get()
                .uri("/users/{username}/repos", username)
                .retrieve()
                .bodyToFlux(RepoInfoRaw.class)
                .filter(repo -> !repo.fork())
                .flatMap(repo -> this.listBranches(username, repo.name())
                        .collectList()
                        .map(branches -> new RepoInfoRaw(repo.name(), repo.owner(), repo.fork(), branches)));

    }

    public Flux<RepoBranchRaw> listBranches(String username, String repoName) {
        return webClient.get()
                .uri("/repos/{username}/{repo}/branches", username, repoName)
                .retrieve()
                .bodyToFlux(RepoBranchRaw.class);


    }
}
