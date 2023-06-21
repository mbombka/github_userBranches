package atipera.github.models;

import java.util.ArrayList;
import java.util.List;

public record Repos(List<RepoInfoMapped> repositoriesList) {
    public Repos(){
        this(new ArrayList<>());
    }
    public void addRepository(RepoInfoMapped repo) {
        repositoriesList.add(repo);
    }

}
