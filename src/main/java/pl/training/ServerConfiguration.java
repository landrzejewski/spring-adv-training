package pl.training;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianServiceExporter;

@Configuration
public class ServerConfiguration {

   /* @Bean(name = "/users")
    public HttpInvokerServiceExporter usersService() {
        var exporter = new HttpInvokerServiceExporter();
        exporter.setServiceInterface(UsersService.class);
        exporter.setService(new ArrayListUsersService());
        return exporter;
    }*/

    @Bean(name = "/users")
    public HessianServiceExporter usersService() {
        var exporter = new HessianServiceExporter();
        exporter.setServiceInterface(UsersService.class);
        exporter.setService(new ArrayListUsersService());
        return exporter;
    }

}
