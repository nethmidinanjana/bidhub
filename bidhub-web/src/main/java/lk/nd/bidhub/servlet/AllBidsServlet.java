package lk.nd.bidhub.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.nd.bidhub.model.AuctionItem;

import java.io.IOException;
import java.util.List;

@WebServlet("/all-bids")
public class AllBidsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<AuctionItem> items = (List<AuctionItem>) getServletContext().getAttribute("auctionItems");

        req.setAttribute("items", items);
        req.getRequestDispatcher("/all-bids.jsp").forward(req, resp);
    }
}
