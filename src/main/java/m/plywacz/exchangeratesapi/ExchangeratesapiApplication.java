package m.plywacz.exchangeratesapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/** When adding reading to DB it is saved with incorrect date i.e backed one day
 *  related with this https://bugs.mysql.com/bug.php?id=93444 :
 */

//todo add exception handling
    //todo add verifying json input
    //todo add tests
@SpringBootApplication
public class ExchangeratesapiApplication {
    public static void main(String[] args) {
        SpringApplication.run(ExchangeratesapiApplication.class, args);
    }

}

