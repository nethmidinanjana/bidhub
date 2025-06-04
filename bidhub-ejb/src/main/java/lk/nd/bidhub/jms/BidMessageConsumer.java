package lk.nd.bidhub.jms;

import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.EJB;
import jakarta.ejb.MessageDriven;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.ObjectMessage;
import lk.nd.bidhub.beans.AuctionManagerBean;
import lk.nd.bidhub.dto.BidMessage;
import lk.nd.bidhub.websocket.BidWebSocket;

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

                BidWebSocket.broadcastBid(bidMessage);
                System.out.println("Received bidMessage: "+bidMessage);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
