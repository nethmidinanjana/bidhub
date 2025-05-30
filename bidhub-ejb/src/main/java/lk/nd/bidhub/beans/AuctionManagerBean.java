package lk.nd.bidhub.beans;

import jakarta.ejb.Stateless;
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

    public boolean placeBid(String itemId, double bidAmount){
        AuctionItem item = getAuctionItemById(itemId);
        if(item != null && LocalDateTime.now().isBefore(item.getEndDateTime())){
            if(bidAmount > item.getCurrentBid()){
                item.setCurrentBid(bidAmount);
                return true;
            }
        }

        return false;
    }
}
