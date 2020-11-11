package org.jagodzinski.spring_rabbit_chat_app_receive;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientMQ {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/receiveMessage")
    public String get(){
        Object message = rabbitTemplate.receiveAndConvert("firstQueue");
        return message.toString();
    }

    @RabbitListener(queues = "firstQueue")
    public void listener(String s){
        System.out.println(s);
    }
}
