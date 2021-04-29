package Dao;

import Models.Tokens;

public interface TokensDao {
    void create(Tokens token);
    void update(Tokens token);
    void delete(Tokens token);
    Tokens readByToken(String token);
}
