package lk.nd.bidhub.jms;

import jakarta.annotation.Resource;
import jakarta.ejb.Stateless;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.JMSContext;
import jakarta.jms.Topic;
import lk.nd.bidhub.dto.BidMessage;

@Stateless
public class BidMessageProducer {

    @Resource(lookup = "jms/bidConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource(lookup = "jms/bidTopic")
    private Topic bidTopic;

    public void sendBidMessage(BidMessage bidMessage) {
        try (JMSContext context = connectionFactory.createContext()){
            context.createProducer().send(bidTopic, bidMessage);
            System.out.println("Sent bid message: "+bidMessage);
        }
    }

}
