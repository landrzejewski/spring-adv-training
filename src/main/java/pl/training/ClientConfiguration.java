package pl.training;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;

@Configuration
public class ClientConfiguration {

    /*@Bean
    public HttpInvokerProxyFactoryBean usersServiceRemote() {
        var factoryBean = new HttpInvokerProxyFactoryBean();
        factoryBean.setServiceInterface(UsersService.class);
        factoryBean.setServiceUrl("http://localhost:8080/users");
        return factoryBean;
    }*/

    @Bean
    public HessianProxyFactoryBean usersServiceRemote() {
        var factoryBean = new HessianProxyFactoryBean();
        factoryBean.setServiceInterface(UsersService.class);
        factoryBean.setServiceUrl("http://localhost:8080/users");
        return factoryBean;
    }

}
