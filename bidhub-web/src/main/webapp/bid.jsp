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
                        <span class="amount">Rs. <%=
                        fomatter.format(item.getCurrentBid()) %>.00</span>
                    </div>
                    
                    <div class="countdown" data-end-time="<%= item.getEndDateTime().toString() %>">
                        <span class="label">Time Remaining:</span>
                        <span class="time"></span>
                    </div>
                </div>
                
                <div class="bid-form">
                    <label for="bid-amount">Your Bid Amount:</label>
                    <div class="bid-input-group">
                        <span class="currency">$</span>
                        <input type="number" id="bid-amount" class="bid-input" placeholder="2500" min="2451">
                        <button class="submit-bid-button">Place Bid</button>
                    </div>
                    <p class="min-bid-note">Minimum bid: $2,451</p>
                </div>
            </div>
        </div>
        
        <!-- Recent Bids Section -->
        <div class="recent-bids">
            <h2>Recent Bids</h2>
            <div class="bid-history">
                <div class="bid-entry">
                    <span class="bidder">john_collector</span>
                    <span class="bid-amount">$2,450</span>
                    <span class="bid-time">2 minutes ago</span>
                </div>
                <div class="bid-entry">
                    <span class="bidder">watch_enthusiast</span>
                    <span class="bid-amount">$2,400</span>
                    <span class="bid-time">15 minutes ago</span>
                </div>
                <div class="bid-entry">
                    <span class="bidder">vintage_lover</span>
                    <span class="bid-amount">$2,350</span>
                    <span class="bid-time">1 hour ago</span>
                </div>
                <div class="bid-entry">
                    <span class="bidder">timepiece_pro</span>
                    <span class="bid-amount">$2,300</span>
                    <span class="bid-time">3 hours ago</span>
                </div>
                <div class="bid-entry">
                    <span class="bidder">auction_master</span>
                    <span class="bid-amount">$2,200</span>
                    <span class="bid-time">1 day ago</span>
                </div>
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

    <script>
        function startCountDown(){
            const countDownElements = document.querySelectorAll(".countdown");

            countDownElements.forEach(el => {
                const endTimeString = el.dataset.endTime;
                const countDownSpan = el.querySelector(".time");

                const endTime = new Date(endTimeString);

                function updateCountDown(){
                    const now = new Date();
                    const timeDiff = endTime - now;

                    if(timeDiff <= 0){
                        countDownSpan.textContent = "Expired.";
                        clearInterval(timer);
                        return;
                    }

                    const seconds = Math.floor(timeDiff / 1000);
                    const days = Math.floor(seconds / (3600 * 24));
                    const hours = Math.floor((seconds % (3600 * 24)) / 3600);
                    const minutes = Math.floor((seconds % 3600) / 60);
                    const secs = seconds % 60;

                    countDownSpan.textContent = days + " days " + hours + " hours " + minutes + " minutes " + secs + " seconds.";
                }

                updateCountDown();
                const timer = setInterval(updateCountDown, 1000);
            })
        }

        window.addEventListener("DOMContentLoaded", startCountDown);
    </script>
</body>
</html>