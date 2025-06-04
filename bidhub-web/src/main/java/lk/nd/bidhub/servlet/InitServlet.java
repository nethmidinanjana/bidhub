package lk.nd.bidhub.servlet;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import lk.nd.bidhub.beans.AuctionManagerBean;
import lk.nd.bidhub.model.AuctionItem;

import java.time.LocalDateTime;

@WebServlet(urlPatterns = "/init", loadOnStartup = 1)
public class InitServlet extends HttpServlet {

    @EJB
    private AuctionManagerBean auctionManager;

    @Override
    public void init() throws ServletException {

        auctionManager.addAuctionItem(new AuctionItem("1", "Gaming Laptop", "A powerful laptop designed for professionals and gamers, featuring top-tier performance, premium build quality, and cutting-edge specs. Ideal for multitasking, creative work, and smooth gameplay.", 280000 , 280000 , "img/lap.jpeg", LocalDateTime.now().plusMinutes(10)));
        auctionManager.addAuctionItem(new AuctionItem("2", "Vintage Rolex Watch", "A timeless classic from Rolex, this vintage watch showcases elegant craftsmanship and enduring style. Perfect for collectors or those who appreciate luxury heritage.", 350000, 350000, "img/watch.jpg", LocalDateTime.now().plusMinutes(2)));
        auctionManager.addAuctionItem(new AuctionItem("3", "Original Oil Painting", "A unique, hand-painted oil artwork on canvas. This piece adds character and artistic flair to any space—perfect for art lovers and collectors seeking one-of-a-kind decor.", 45000 , 45000 , "img/oil_painting.jpg", LocalDateTime.now().plusHours(8)));
        auctionManager.addAuctionItem(new AuctionItem("4", "1967 Ford Mustang", "An iconic American muscle car with classic styling and raw power. This 1967 Mustang is a prized collector's gem, perfect for vintage car enthusiasts and restorers.", 4500000 , 4500000, "img/mustang.jpeg", LocalDateTime.now().plusHours(56)));
        auctionManager.addAuctionItem(new AuctionItem("5", "Diamond Engagement Ring", "A stunning diamond engagement ring featuring brilliant-cut stones set in a timeless design. An elegant symbol of love and commitment, crafted to sparkle for a lifetime.", 250000 , 250000, "img/ring.jpg", LocalDateTime.now().plusMinutes(33)));
        auctionManager.addAuctionItem(new AuctionItem("6", "Classic Vintage Camera", "A beautifully preserved vintage camera, perfect for collectors or photography lovers who appreciate the charm of analog craftsmanship and old-school film shooting.", 35000 , 35000, "img/cam.jpg", LocalDateTime.now().plusHours(4)));
        auctionManager.addAuctionItem(new AuctionItem("7", "1959 Gibson Les Paul", "A legendary electric guitar known for its rich tone and historic value. The 1959 Gibson Les Paul is a holy grail for musicians and collectors, representing the golden era of rock and blues.", 6000000 , 6000000, "img/guitar.jpg", LocalDateTime.now().plusHours(12)));
        auctionManager.addAuctionItem(new AuctionItem("8", "Diamond Jewellery Set", "An exquisite diamond jewellery set including a necklace, earrings, and bracelet. Designed to dazzle with timeless elegance—ideal for weddings, special occasions, or heirloom gifting.", 750000, 750000, "img/jwellary.jpg", LocalDateTime.now().plusMinutes(19)));
        auctionManager.addAuctionItem(new AuctionItem("9", "Old Silver Coin Collection", "A curated collection of rare and historic silver coins, each with unique markings and stories. A must-have for numismatists and history enthusiasts.", 60000, 60000, "img/silver_coin.jpeg", LocalDateTime.now().plusHours(45)));
        auctionManager.addAuctionItem(new AuctionItem("10", "Antique Gramophone", "A fully functional antique gramophone with a classic horn speaker. A nostalgic piece that brings vintage charm and music history into your home.", 85000 , 85000, "img/gramophone.jpg", LocalDateTime.now().plusHours(10)));
        auctionManager.addAuctionItem(new AuctionItem("11", "Hand-Carved Armchair", "A luxurious armchair made from solid teak wood, featuring intricate hand-carved designs. Combines comfort with traditional craftsmanship, perfect for elegant interiors.", 55000 , 55000, "img/arm_chair.jpg", LocalDateTime.now().plusMinutes(5)));
        auctionManager.addAuctionItem(new AuctionItem("12", "First Edition Classic Novel", "A rare first edition print of a literary classic, preserved in excellent condition. A prized collectible for book lovers and bibliophiles.", 8000, 8000, "img/novel.jpg", LocalDateTime.now().plusHours(6)));


        getServletContext().setAttribute("auctionManager", auctionManager);
    }
}
