package lk.nd.bidhub.servlet;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.nd.bidhub.beans.AuctionManagerBean;
import lk.nd.bidhub.dto.BidMessage;
import lk.nd.bidhub.jms.BidMessageProducer;
import lk.nd.bidhub.model.AuctionItem;
import lk.nd.bidhub.model.User;

import java.io.IOException;
import java.time.LocalDateTime;

@MultipartConfig
@WebServlet("/place-bid")
public class PlaceBidServlet extends HttpServlet {

    @EJB
    private BidMessageProducer producer;

    @EJB
    private AuctionManagerBean auctionManager;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User loggedUser = (User) req.getSession().getAttribute("user");

        if(loggedUser == null){
            resp.getWriter().write("login required");
            return;
        }

        String itemId = req.getParameter("itemId");
        String bidAmountStr = req.getParameter("bidAmount");

        if (bidAmountStr == null || bidAmountStr.trim().isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing or invalid bid amount.");
            return;
        }

        double bidAmount = Double.parseDouble(bidAmountStr);

        if(bidAmount <= 0){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Bid Amount must be greater than 0");
            return;
        }

        BidMessage bidMessage = new BidMessage();
        bidMessage.setItemId(itemId);
        bidMessage.setBidderEmail(loggedUser.getEmail());
        bidMessage.setAmount(bidAmount);
        bidMessage.setTimestamp(LocalDateTime.now().toString());

        boolean placed = auctionManager.placeBid(bidMessage);

        if(placed){
            producer.sendBidMessage(bidMessage);
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write("success");
        }else{
            resp.getWriter().write("Bid too low or auction ended");
        }

    }
}
