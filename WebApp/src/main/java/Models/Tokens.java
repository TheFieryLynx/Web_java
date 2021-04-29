package Models;

import javax.persistence.*;

@Entity
@Table(name = "tokens")
public class Tokens {
    private String token;

    public Tokens() {}

    public Tokens(String token) {
        this.token = token;
    }

    @Id
    @Column(name = "token")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
