package lk.nd.bidhub.interfaces;

import lk.nd.bidhub.dto.BidMessage;

public interface BidBroadcaster {
    void broadcastBid(BidMessage bidMessage);
}

