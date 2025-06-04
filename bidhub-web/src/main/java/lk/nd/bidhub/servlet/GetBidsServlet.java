package lk.nd.bidhub.servlet;

import com.google.gson.Gson;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.nd.bidhub.beans.AuctionManagerBean;
import lk.nd.bidhub.dto.BidMessage;
import lk.nd.bidhub.model.AuctionItem;

import java.io.IOException;
import java.util.List;

@WebServlet("/get-bids")
public class GetBidsServlet extends HttpServlet {

    @EJB
    AuctionManagerBean auctionManager;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String itemId = req.getParameter("itemId");

        AuctionItem item = auctionManager.getAuctionItemById(itemId);

        if(item == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        List<BidMessage> bids = item.getBids();

        resp.setContentType("application/json");
        try {
            new Gson().toJson(bids, resp.getWriter());
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(500, "Error serializing bids: " + e.getMessage());
        }

    }
}
