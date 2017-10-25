package com.creat.outnet.config;

import com.creat.outnet.service.ProduceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import org.springframework.remoting.rmi.RmiServiceExporter;

/**
 * Created by whz on 2017/10/1.
 */
@Configuration
public class RMIConfig {

    @Bean
    public RmiProxyFactoryBean rmiProxyFactoryBean() {
        RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
        rmiProxyFactoryBean.setServiceUrl("rmi://123.206.54.125:8888/login");
        //rmiProxyFactoryBean.setServiceUrl("rmi://127.0.0.1:8888/login");
        rmiProxyFactoryBean.setServiceInterface(ProduceService.class);
        return rmiProxyFactoryBean;
    }
}
