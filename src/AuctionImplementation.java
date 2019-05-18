import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Logger;
/*
 * Auction Implementation class is created to show the bidding process.
 */
public class AuctionImplementation implements Auction {

    private static final Logger LOG = Logger.getLogger(AuctionImplementation.class.getName());
    private final Item item;

    public AuctionImplementation(Item itemForSale) {
        this.item = itemForSale;
    }

    public Item offeredItem() {
        return this.item;
    }

	/*
	 *
	 * Find out the winning amount and winner, once all the information has been entered site
     *
     * @param allBbidders List of all the participating bidders in the order
     * @param baseBidAmount Enter a amount to start a bid.
     * @return Winning bid amount with bidder name.
	 */
    @Override
    public Bid process(CopyOnWriteArrayList<Bidder> allBidders, Bid baseBidAmount) {

        if (allBidders == null)
            throw new IllegalArgumentException("There are no bidders in this auction yet.");

        if (baseBidAmount == null)
            baseBidAmount = new Bid(null, 0);

        LOG.info("_________________________________________________"+
        		"\n"+"This round is starting for " + this.offeredItem());

        boolean validBids = false;

        // To iterate through all the participating bidders
        for (Bidder bidder : allBidders) {
            Bid bid = bidder.getBidAmount(baseBidAmount);
            if(bid == null) continue;

            LOG.info("Auction done: " + bid);
            if (bid.compareTo(baseBidAmount) > 0) {
                LOG.info("This bid amount is highest.");
                baseBidAmount = bid;
                validBids = true;
            }

            if (bid.compareTo(baseBidAmount) == 0 && baseBidAmount.getBidder() != null) {
                LOG.info("There is a tie");
                if (allBidders.indexOf(bid.getBidder()) < allBidders.indexOf(baseBidAmount.getBidder()))
                    baseBidAmount = bid;
            }
        }

        LOG.info("Max bid amount of this round: " + baseBidAmount);
        if (validBids) {
            baseBidAmount = process(allBidders, baseBidAmount);
        } else if (baseBidAmount.getBidder() != null) {
            LOG.info("Auction is done! " + baseBidAmount + ". " + this.offeredItem() + " is sold.");
        } else if (baseBidAmount.getBidder() == null)
            LOG.info("No one is showing the interest for this item. Hence auction is stopped for " + this.offeredItem());

        return baseBidAmount;
    }
}
