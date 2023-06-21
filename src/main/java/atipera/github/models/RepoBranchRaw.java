package atipera.github.models;

public record RepoBranchRaw(String name, Commit commit ) {

}
record Commit(String sha, String url){
}