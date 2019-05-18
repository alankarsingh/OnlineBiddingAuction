/*
 * An interface to get the bid amount from all the bidders
 */
public interface Bidder {
   
	/*
	 * @param bid amount from the each bidder
	 */
    public Bid getBidAmount(Bid lastBid);
}