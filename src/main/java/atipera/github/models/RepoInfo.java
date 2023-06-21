package atipera.github.models;

import java.util.List;

public record RepoInfo(String name, Owner owner, Boolean fork, List<RepoBranch> branches) {

}

