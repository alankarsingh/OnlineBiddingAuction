import java.util.concurrent.CopyOnWriteArrayList;
/*
 * An interface class is created to send all the participant of the bidder into the process.
 */
public interface Auction {

    /*
     * @param name of all the participating bidders
     * @param starting bid amount
     */
    public Bid process(CopyOnWriteArrayList<Bidder> allBidders, Bid baseBidAmount);

}
