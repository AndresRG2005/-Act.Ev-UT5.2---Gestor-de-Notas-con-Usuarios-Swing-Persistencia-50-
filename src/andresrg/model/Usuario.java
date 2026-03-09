package andresrg.model;

public class Usuario {
    private String username;
    private String passwordHash;

    public Usuario(String username, String passwordHash) {
        this.username = username;
        this.passwordHash = passwordHash;
    }

    public String getPasswordHash() {
        return null;
    }

    public Object getUsername() {
        return null;
    }
}
