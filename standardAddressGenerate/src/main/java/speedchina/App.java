package speedchina;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import speedchina.addresselement.service.impl.AddressElementServiceImpl;

/**
 * @author 11852
 */
@SpringBootApplication
public class App {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(App.class, args);
        System.out.println("application is started...");
        AddressElementServiceImpl addressElementService = ctx.getBean(AddressElementServiceImpl.class);
        addressElementService.generateStandardAddress();
    }
}
