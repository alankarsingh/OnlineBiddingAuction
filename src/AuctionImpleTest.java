import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class AuctionImpleTest {

    private Auction bidProcessor;
    private Item item;
    private Bidder[] bidders = new Bidder[4];
    private Bid winningBid;

    public AuctionImpleTest(Item item, Bidder[] bidders, Bid winningBid) {
        this.item = item;
        this.bidders = bidders;
        this.winningBid = winningBid;
    }

    @Parameterized.Parameters
    public static Collection data() {
        BidderImplementation linda1 = new BidderImplementation("Linda", 170, 240, 3);
        BidderImplementation dave1 = new BidderImplementation("Dave", 30, 70, 4);
        BidderImplementation eric1 = new BidderImplementation("Eric", 20000, 65000, 2000);

        BidderImplementation linda2 = new BidderImplementation("Linda", 160, 243, 7);
        BidderImplementation dave2 = new BidderImplementation("Dave", 30, 70, 3);
        BidderImplementation eric2 = new BidderImplementation("Eric", 10000, 70000, 15000);

        BidderImplementation linda3 = new BidderImplementation("Linda", 190, 240, 4);
        BidderImplementation dave3 = new BidderImplementation("Dave", 40, 90, 2);
        BidderImplementation eric3 = new BidderImplementation("Eric", 22000, 70000, 8000);

        return Arrays.asList(new Object[][]{
                {new Item("Car accessories"),
                        new Bidder[]{linda1, dave1, eric1},
                        new Bid(eric1,65000 )},
                {new Item("Snow Tires"),
                        new Bidder[]{linda2, dave2, eric2},
                        new Bid(dave2, 70)},
                {new Item("Car"),
                        new Bidder[]{linda3, dave3, eric3},
                        new Bid(linda3, 240)}
        });
    }

    @Before
    public void setUp() {
        bidProcessor = new AuctionImplementation(item);
    }

    @Test
    public void testProcess() {
        CopyOnWriteArrayList<Bidder> bidders = new CopyOnWriteArrayList<>(Arrays.asList(this.bidders));
        Bid winningBid = bidProcessor.process(bidders, null);

        assertEquals(this.winningBid.getBidder(), winningBid.getBidder());
        assertEquals(this.winningBid.getAmount(), winningBid.getAmount());
    }
}