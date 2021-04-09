package homework.q1;

import com.alibaba.fastjson.JSON;
import homework.beans.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

@Component
public class SendService {

    @Autowired
    JmsTemplate jmsTemplate;


    public void send(Order order) {
        jmsTemplate.send("l24.q1.queue", new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createObjectMessage(JSON.toJSONString(order));
            }
        });

    }
}
