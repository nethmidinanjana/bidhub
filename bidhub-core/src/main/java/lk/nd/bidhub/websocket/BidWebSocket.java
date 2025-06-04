package lk.nd.bidhub.websocket;

import com.google.gson.Gson;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import lk.nd.bidhub.dto.BidMessage;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/live-bids")
public class BidWebSocket {
    private static final Set<Session> clients = ConcurrentHashMap.newKeySet();

    @OnOpen
    public void onOpen(Session session) {
        clients.add(session);
        System.out.println("Client connected: " + session.getId());
    }

    @OnClose
    public void onClose(Session session) {
        clients.remove(session);
        System.out.println("Client disconnected: " + session.getId());
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        clients.remove(session);
        throwable.printStackTrace();
    }

    public static void broadcastBid(BidMessage bidMessage){
        String json = new Gson().toJson(bidMessage);

        for (Session client : clients) {
            if(client.isOpen()){
                try{
                    client.getBasicRemote().sendText(json);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}
