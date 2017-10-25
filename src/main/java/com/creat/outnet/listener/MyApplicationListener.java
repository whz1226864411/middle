package com.creat.outnet.listener;

import com.creat.outnet.component.Worker;
import com.creat.outnet.service.ProduceService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * Created by Administrator on 2017/10/1.
 */
public class MyApplicationListener implements ApplicationListener<ContextRefreshedEvent>{

    private ProduceService produceService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        ApplicationContext context = contextRefreshedEvent.getApplicationContext();
        produceService = context.getBean(ProduceService.class);
        for(int i = 0; i < 3; i++){
            Thread work = new Thread(new Worker(produceService,i));
            work.start();
        }
    }
}
