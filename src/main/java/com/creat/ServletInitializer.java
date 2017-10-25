package com.creat;

import com.creat.outnet.listener.MyApplicationListener;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Created by whz on 2017/9/18.
 */
public class ServletInitializer extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        application.application().addListeners(new MyApplicationListener());
        return application.sources(DemoApplication.class);
    }
}
