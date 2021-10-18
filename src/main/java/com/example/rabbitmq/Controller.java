package com.example.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    Logger logger = LoggerFactory.getLogger(Controller.class);

    private final AmqpTemplate template;

    @Autowired
    public Controller(AmqpTemplate template){
        this.template=template;
    }

    @PostMapping("/mail")
    public ResponseEntity<String> mail(@RequestBody String message){
        logger.info("Mail from myQueue");
        template.convertAndSend("myQueue", message);
        return ResponseEntity.ok("Mail successful was added in queue");
    }


}
