<%@ page import="java.util.List" %>
<%@ page import="lk.nd.bidhub.model.AuctionItem" %>
<%@ page import="lk.nd.bidhub.beans.AuctionManagerBean" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="lk.nd.bidhub.dto.BidMessage" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.TimeZone" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.time.LocalDateTime" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bid Now - BidHub</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <!-- Include Navigation -->
    <jsp:include page="navbar.jsp" />
    
    <div class="container">

        <%
            String itemId = request.getParameter("id");
            System.out.println("ItemId: "+itemId);
            AuctionItem item = null;

            if(itemId != null){

                AuctionManagerBean auctionManagerBean = (AuctionManagerBean) application.getAttribute("auctionManager");
                item = auctionManagerBean.getAuctionItemById(itemId);

                NumberFormat fomatter = NumberFormat.getInstance();

        %>

        <div class="bid-detail">

            <div class="product-image-section">
                <img src="<%= item.getImageUrl() %>" alt="Vintage Rolex Watch" class="product-image">
            </div>
            
            <div class="product-info-section">
                <h1><%= item.getTitle() %></h1>
                <p class="product-description">
                    <%= item.getDescription() %>
                </p>
                
                <div class="bid-info">
                    <div class="current-bid-large">
                        <span class="label">Current Highest Bid:</span>
                        <span class="amount" id="current-bid">Rs. <%=
                        fomatter.format(item.getCurrentBid()) %>.00</span>
                    </div>
                    
                    <div class="countdown" data-end-time="<%= item.getEndDateTime().toString() %>">
                        <span class="label">Time Remaining:</span>
                        <span class="time"></span>
                    </div>
                </div>

                <div id="bidForm" class="bid-form">
                    <label for="bid-amount">Your Bid Amount:</label>
                    <div class="bid-input-group">
                        <span class="currency">Rs.</span>
                        <input type="number" id="bid-amount" name="bidAmount" class="bid-input"
                               min="<%= item.getCurrentBid() + 1 %>" required>
                        <button type="button" class="submit-bid-button" onclick="placeBid(<%= item.getId() %>)">Place Bid</button>
                    </div>
                    <p class="min-bid-note">
                        Minimum bid: Rs. <%= fomatter.format(item.getCurrentBid()) %>.00
                    </p>
                </div>

            </div>
        </div>
        
        <!-- Recent Bids Section -->
        <div class="recent-bids">
            <h2>Recent Bids</h2>
            <div class="bid-history" id="bid-history-container">
                <%
                    List<BidMessage> bids = item.getBids();
                    java.text.NumberFormat formatter = java.text.NumberFormat.getInstance(new java.util.Locale("en", "IN"));
                    for (int i = bids.size() - 1; i >= 0; i--) {
                        BidMessage bid = bids.get(i);
                        String username = bid.getBidderEmail().split("@")[0];
                        double amount = bid.getAmount();

                        String formattedTime = bid.getTimestamp(); // fallback
                        try {
                            java.time.format.DateTimeFormatter inputFormatter = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS");
                            java.time.LocalDateTime ldt = java.time.LocalDateTime.parse(bid.getTimestamp(), inputFormatter);
                            java.time.format.DateTimeFormatter outputFormatter = java.time.format.DateTimeFormatter.ofPattern("MMM d, yyyy, h:mm a");
                            formattedTime = ldt.format(outputFormatter);
                        } catch (Exception e) {}

                %>
                <div class="bid-entry">
                    <span class="bidder"><%= username %></span>
                    <span class="bid-amount">Rs. <%= formatter.format(amount) %>.00</span>
                    <span class="bid-time"><%= formattedTime %></span>
                </div>
                <%
                    }
                    if (bids.isEmpty()) {
                %>
                <div class="bid-entry" id="no-bids">No bids yet.</div>
                <%
                    }
                %>
            </div>

        </div>

        <%
        }else {
        %>
        <p style="color:red;">Invalid or missing item ID.</p>
        <%
            }
        %>
    </div>
    
    <!-- Include Footer -->
    <jsp:include page="footer.jsp" />

    <script src="script.js"></script>

</body>
</html>