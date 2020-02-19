package m.plywacz.exchangeratesapi.repo;
/*
Author: BeGieU
Date: 09.02.2020
*/

import m.plywacz.exchangeratesapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
}
