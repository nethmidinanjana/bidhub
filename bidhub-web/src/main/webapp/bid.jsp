<%@ page import="java.util.List" %>
<%@ page import="lk.nd.bidhub.model.AuctionItem" %>
<%@ page import="lk.nd.bidhub.beans.AuctionManagerBean" %>
<%@ page import="java.text.NumberFormat" %>
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