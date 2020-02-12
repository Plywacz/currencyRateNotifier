package m.plywacz.exchangeratesapi.repo;
/*
Author: BeGieU
Date: 09.02.2020
*/

import m.plywacz.exchangeratesapi.model.Currency;
import m.plywacz.exchangeratesapi.model.Notification;
import m.plywacz.exchangeratesapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepo extends JpaRepository<Notification, Long> {
    boolean existsByCurrencyAndFrequencyAndSendingValueAndUserId(Currency currency, int frequency, double sendingValue, Long userId);
}
