<%@ page import="java.util.List" %>
<%@ page import="lk.nd.bidhub.model.AuctionItem" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Comparator" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="lk.nd.bidhub.beans.AuctionManagerBean" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>BidHub - Online Auction Platform</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <!-- Include Navigation -->
    <jsp:include page="navbar.jsp" />
    
    <!-- Hero Section -->
    <section class="hero">
        <div class="hero-content">
            <h1>Welcome to BidHub</h1>
            <p>Your premier destination for online auctions. Discover unique items, place bids, and win amazing deals!</p>
            <a href="all-bids" class="cta-button">Start Bidding</a>
        </div>
    </section>
    
    <!-- Featured Auctions -->
    <section class="featured-auctions">
        <div class="container">
            <h2>Featured Auctions</h2>
            <div class="auction-grid">
                <%
                    AuctionManagerBean auctionManagerBean = (AuctionManagerBean) application.getAttribute("auctionManager");
                    List<AuctionItem> items = auctionManagerBean.getAuctionItems();

                    List<AuctionItem> featuredItems = new ArrayList<>();
                    if(items != null && items.size() >= 3){
                        items.sort(Comparator.comparing(AuctionItem::getEndDateTime));
                        featuredItems = items.subList(0, 3);
                    }

                    for(AuctionItem item: featuredItems){
                        NumberFormat formatter = NumberFormat.getInstance();
                        %>
                            <div class="auction-card">
                                <img src="<%= item.getImageUrl() %>" alt="Vintage Watch" class="auction-image">
                                <div class="auction-info">
                                    <h3><%= item.getTitle() %></h3>
                                    <p class="current-bid">Current Bid: Rs. <%= formatter.format(item.getCurrentBid())  %>.00 </p>
                                    <a href="bid.jsp?id=<%= item.getId() %>" class="view-button">View</a>
                                </div>
                            </div>
                        <%
                    }
                %>
            </div>
        </div>
    </section>
    
    <!-- Include Footer -->
    <jsp:include page="footer.jsp" />
</body>
</html>