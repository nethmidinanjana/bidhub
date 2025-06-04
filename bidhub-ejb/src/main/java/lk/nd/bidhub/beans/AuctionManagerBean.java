package lk.nd.bidhub.beans;

import jakarta.ejb.Stateless;
import lk.nd.bidhub.dto.BidMessage;
import lk.nd.bidhub.model.AuctionItem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Stateless
public class AuctionManagerBean {

    private final List<AuctionItem> auctionItemList = Collections.synchronizedList(new ArrayList<>());

    public void addAuctionItem(AuctionItem item){
        auctionItemList.add(item);
    }

    public List<AuctionItem> getAuctionItems(){
        return new ArrayList<>(auctionItemList);
    }

    public AuctionItem getAuctionItemById(String id){
        for(AuctionItem item: auctionItemList){
            if(item.getId().equals(id)){
                return item;
            }
        }

        return null;
    }

    public boolean placeBid(BidMessage bid){
        AuctionItem item = getAuctionItemById(bid.getItemId());
        if(item != null && LocalDateTime.now().isBefore(item.getEndDateTime())){
            if(bid.getAmount() > item.getCurrentBid()){
                item.addBids(bid);
                return true;
            }
        }

        return false;
    }
}
