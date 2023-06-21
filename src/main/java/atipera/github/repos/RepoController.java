package atipera.github.repos;

import atipera.github.models.RepoInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class RepoController {

    private final RepoService repoService;

    public RepoController(RepoService repoService) {
        this.repoService = repoService;
    }

    @GetMapping("/{username}")
    public Flux<RepoInfo> getRepos(@PathVariable String username,
                                   @RequestHeader("Accept") String acceptHeader) {
        if (!"application/json".equals(acceptHeader)) {
            throw new IllegalArgumentException("Header 'Accept' should be 'application/json'");
        }
        return repoService.getAllRepositories(username);
    }


    
}
