import java.util.logging.Logger;

/*
 * This class depicts the all the process of bidding of the items and status of Bidders.
 */
public class BidderImplementation implements Bidder {

	private static final Logger LOG = Logger.getLogger(BidderImplementation.class.getName());

	private final int maximumBid;
	private final int increment;
	private final int startBid;
	private final String bidderName;

	/*
	 * This method does the operation on bid amount. 
	 * It determines whether a bid amount is valid, what is the final bid amount or value should be incremented?
	 * 
	 * @param name of bidder
	 * @param start bid amount 
	 * @param maximum bid
	 * @param incremented amount
	 * 
	 * @return none
	 */
	public BidderImplementation(String name, int startBid, int maxBid, int increment) {
		// Status of the bidders.
		if ((maxBid - startBid) < 0) {
			throw new IllegalArgumentException("Your final bid amount should be the more than your previous bids.");
		}else if ((maxBid >> 31 != 0) || (increment >> 31 != 0) || (startBid >> 31 != 0)) {
			throw new IllegalArgumentException("This bidding amount is not valid.");
		}else if ((startBid + increment) > maxBid || ((maxBid - startBid) > 0 && (increment == 0))) {
			throw new IllegalArgumentException("Value can not be incremented.");
		}

		this.maximumBid = maxBid;
		this.increment = increment;
		this.startBid = startBid;
		this.bidderName = name;
	}

	/*
	 * (non-Javadoc)
	 * @see Bidder#getBidAmount(Bid)
	 * 
	 * This method asks the bidder for final amount considering the maximum bid into the consideration.
	 */
	@Override
	public Bid getBidAmount(Bid finalBid) {

		if (finalBid == null)
			throw new IllegalArgumentException("This Bid value is not valid.");

		Bid nextBid = null;
		int currentBid = this.startBid;
		
		if (this.maximumBid >= finalBid.getAmount()) {
			LOG.info(bidderName + ": is bidding a new amount.");
			do {
				currentBid = currentBid + this.increment;
				if (currentBid > this.maximumBid) {
					LOG.info(bidderName + ": Sorry your limit exceeded.");
					currentBid = this.maximumBid;
					break;
				}
			}while (currentBid <= finalBid.getAmount());
			
			nextBid = new Bid(this, currentBid);
		}else if (finalBid.getBidder() == null) {
			LOG.info(bidderName + ": First post!");
			nextBid = new Bid(this, this.startBid);
		} else if (this.equals(finalBid.getBidder())) {
			LOG.info(bidderName + ": I will keep myself out of this bid for now.");
			nextBid = finalBid;
		} 

		return nextBid;
	}

	public String toString() {
		return "Bidder: " + bidderName;
	}
}
