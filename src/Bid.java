/*
 * This class shows the implementation for final amount and final bidder's name.
 */
public class Bid implements Comparable<Bid> {
   
    private final Bidder bidder;
    private final int amount;

   // This creates a bid with bidder name with amount.
    public Bid(Bidder bidder, int amount) {
        if (amount >> 31 != 0) throw new IllegalArgumentException("Negative bids not allowed");
        this.bidder = bidder;
        this.amount = amount;
    }

    
    public Bidder getBidder() {
        return this.bidder;
    }

    
    public int getAmount() {
        return this.amount;
    }

   /*
    * (non-Javadoc)
    * @see java.lang.Comparable#compareTo(java.lang.Object)
    * This method compares two bid amount and returns a maximum amount.
    */
    public int compareTo(Bid bid) {
        final Integer bidA = this.getAmount();
        final Integer bidB = bid.getAmount();
        return bidA.compareTo(bidB);
    }

   // A string representation.
    public String toString() {
        return  this.getBidder()+" bought the respective item for $" + this.getAmount();
    }
}
