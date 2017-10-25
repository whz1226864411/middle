package com.creat.outnet.component;

import com.creat.outnet.po.Message;
import com.creat.outnet.po.User;
import com.creat.outnet.service.ProduceService;

import java.io.IOException;

/**
 * Created by Administrator on 2017/10/1.
 */
public class Worker implements Runnable{
    private ProduceService produceService;
    private LoginRequest loginRequest;
    private int index;

    public Worker(ProduceService produceService,int index){
        this.produceService = produceService;
        this.index = index;
        this.loginRequest = new LoginRequest();
    }

    @Override
    public void run() {
        while(true){
            try {
                User user = produceService.consume(index);
                if(user != null){
                    Message message = new Message();
                    message.setMsg(loginRequest.login(user.getUserName(),user.getUserPassword()));
                    message.setSessionId(user.getSessionId());
                    produceService.produce(message,index);
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }catch (IOException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
