package atipera.github.models;

import java.util.List;

public record RepoInfoRaw(String name, Owner owner, Boolean fork, List<RepoBranchRaw> branches) {

}

