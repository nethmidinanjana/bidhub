package lk.nd.bidhub.jms;

import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.MessageDriven;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.ObjectMessage;
import lk.nd.bidhub.dto.BidMessage;

@MessageDriven(
        activationConfig = {
                @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/bidTopic"),
                @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "jakarta.jms.Topic")
        }
)
public class BidMessageConsumer implements MessageListener {
    @Override
    public void onMessage(Message message) {

        try {
            if(message instanceof ObjectMessage){
                ObjectMessage objectMessage = (ObjectMessage) message;
                BidMessage bidMessage = (BidMessage) objectMessage.getObject();

                System.out.println("Received bidMessage: "+bidMessage);

                //TODO: update storage notify front-end
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
