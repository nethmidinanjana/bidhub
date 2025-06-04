package lk.nd.bidhub.model;

import lk.nd.bidhub.dto.BidMessage;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AuctionItem implements Serializable {
    private String id;
    private String title;
    private String description;
    private double minPrice;
    private double currentBid;
    private String imageUrl;
    private LocalDateTime endDateTime;
    private final List<BidMessage> bids = new ArrayList<>();

    public AuctionItem() {
    }

    public AuctionItem(String id, String title, String description, double minPrice, double currentBid, String imageUrl, LocalDateTime endDateTime) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.minPrice = minPrice;
        this.currentBid = currentBid;
        this.imageUrl = imageUrl;
        this.endDateTime = endDateTime;
    }

    public void addBids(BidMessage bidMessage){
        this.currentBid = bidMessage.getAmount();
        bids.add(bidMessage);
    }

    public List<BidMessage> getBids() {
        return bids;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
    }

    public double getCurrentBid() {
        return currentBid;
    }

    public void setCurrentBid(double currentBid) {
        this.currentBid = currentBid;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }
}
