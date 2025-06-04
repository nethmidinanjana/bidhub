<%@ page import="java.util.List" %>
<%@ page import="lk.nd.bidhub.model.AuctionItem" %>
<%@ page import="javax.swing.text.NumberFormatter" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="lk.nd.bidhub.beans.AuctionManagerBean" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>All Auctions - BidHub</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <!-- Include Navigation -->
    <jsp:include page="navbar.jsp" />
    
    <div class="container">
        
        <!-- Search Bar -->
<%--        <div class="search-container">--%>
<%--            <input type="text" class="search-input" placeholder="Search auctions...">--%>
<%--            <button class="search-button">Search</button>--%>
<%--        </div>--%>
        
        <!-- Auction Grid -->
        <div class="auction-grid">
            <%
                AuctionManagerBean auctionManagerBean = (AuctionManagerBean) application.getAttribute("auctionManager");
                List<AuctionItem> items = auctionManagerBean.getAuctionItems();

                if(items != null){
                    for(AuctionItem item: items){
                        NumberFormat formatter = NumberFormat.getInstance();
                        %>
                            <div class="auction-card">
                                <img src="<%= item.getImageUrl() %>" alt="Vintage Watch" class="auction-image">
                                <div class="auction-info">
                                    <h3><%= item.getTitle() %></h3>
                                    <p class="current-bid">Current Bid: Rs. <%= formatter.format(item.getCurrentBid()) %>.00 </p>
                                    <a href="bid.jsp?id=<%= item.getId() %>" class="bid-button">Bid Now</a>
                                </div>
                            </div>
                        <%
                    }
                }
            %>

        </div>
    </div>
    
    <!-- Include Footer -->
    <jsp:include page="footer.jsp" />
</body>
</html>