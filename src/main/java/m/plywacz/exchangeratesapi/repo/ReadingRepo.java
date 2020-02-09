package m.plywacz.exchangeratesapi.repo;
/*
Author: BeGieU
Date: 09.02.2020
*/

import m.plywacz.exchangeratesapi.model.Reading;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReadingRepo extends JpaRepository<Reading, Long> {
}
