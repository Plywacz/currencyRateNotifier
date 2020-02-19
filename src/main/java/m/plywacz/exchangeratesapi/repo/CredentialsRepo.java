package m.plywacz.exchangeratesapi.repo;
/*
Author: BeGieU
Date: 18.02.2020
*/

import m.plywacz.exchangeratesapi.model.Credentials;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CredentialsRepo extends JpaRepository<Credentials, Long> {
    Credentials findByUsername(String username);
    boolean existsByUsername(String username);
}
