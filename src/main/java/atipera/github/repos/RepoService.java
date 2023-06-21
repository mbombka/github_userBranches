package atipera.github.repos;

import atipera.github.models.*;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
public class RepoService {

    private final WebClient webClient;

    public RepoService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.github.com").build();
    }

    public Flux<RepoInfo>  getAllRepositories(String username) {
        return webClient.get()
                .uri("/users/{username}/repos", username)
                .retrieve()
                .bodyToFlux(RepoInfo.class)
                .filter(repo -> !repo.fork())
                .flatMap(repo -> this.listBranches(username, repo.name())
                        .collectList()
                        .map(branches -> new RepoInfo(repo.name(), repo.owner(), repo.fork(), branches)));
    }

    public Flux<RepoBranch> listBranches(String username, String repoName) {
        return webClient.get()
                .uri("/repos/{username}/{repo}/branches", username, repoName)
                .retrieve()
                .bodyToFlux(RepoBranch.class);
    }
}
