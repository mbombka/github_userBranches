package atipera.github.models;

public record RepoBranch(String name, Commit commit ) {

}
record Commit(String sha, String url){
}