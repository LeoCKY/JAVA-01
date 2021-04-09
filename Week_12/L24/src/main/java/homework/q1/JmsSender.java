package homework.q1;

import homework.beans.Order;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JmsSender {
    
    public static void main( String[] args )
    {
        Order order = new Order(1, 0, "Siu Yu" , null, null);
        
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:springjms-sender.xml");
        
        SendService sendService = (SendService)context.getBean("sendService");
        
        sendService.send(order);
        
        System.out.println("send successfully, please visit http://localhost:8161/admin to see it");
    }
    
}
