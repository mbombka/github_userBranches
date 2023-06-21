package atipera.github.models;

import java.util.List;

public record RepoInfoMapped(String name, String ownerLogin, List<RepoBranchRaw> branches) {
}
