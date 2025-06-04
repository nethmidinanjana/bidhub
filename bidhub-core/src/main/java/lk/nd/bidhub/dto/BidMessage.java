package lk.nd.bidhub.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class BidMessage implements Serializable {

    private String bidderEmail;
    private String itemId;
    private double amount;
    private String timestamp;

    public BidMessage() {
    }

    public BidMessage(String bidderEmail, String itemId, double amount, String timestamp) {
        this.bidderEmail = bidderEmail;
        this.itemId = itemId;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public String getBidderEmail() {
        return bidderEmail;
    }

    public void setBidderEmail(String bidderEmail) {
        this.bidderEmail = bidderEmail;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "BidMessage{"+
                "bidderEmail="+ bidderEmail+
                ", itemId="+ itemId+
                ", amount="+ amount +
                ", timestamp="+timestamp+
                "}";
    }
}
