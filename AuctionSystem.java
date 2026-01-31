import java.util.Scanner;

public class AuctionSystem {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.print("Enter vehicle registration number: ");
        String regNumber = input.next();

        System.out.print("Enter vehicle cost: ");
        double vehicleCost = input.nextDouble();

        System.out.print("Enter balance left on vehicle: ");
        double balance = input.nextDouble();

        System.out.print("Enter total deposits made on vehicle: ");
        double deposits = input.nextDouble();

        System.out.print("Enter total expenses incurred on vehicle: ");
        double expenses = input.nextDouble();

        System.out.print("\nEnter bid amount for Bidder 1: ");
        double bid1 = input.nextDouble();

        System.out.print("Enter bid amount for Bidder 2: ");
        double bid2 = input.nextDouble();

        System.out.print("Enter bid amount for Bidder 3: ");
        double bid3 = input.nextDouble();

        double highestBid;
        int highestBidder;

        if (bid1 >= bid2 && bid1 >= bid3) {
            highestBid = bid1;
            highestBidder = 1;
        } else if (bid2 >= bid1 && bid2 >= bid3) {
            highestBid = bid2;
            highestBidder = 2;
        } else {
            highestBid = bid3;
            highestBidder = 3;
        }

        double totalRevenue = highestBid + deposits;
        double totalCost = vehicleCost + expenses;
        double profitOrLoss = totalRevenue - totalCost;

        System.out.println("\n--- Auction Result ---");
        System.out.println("Highest Bidder: Bidder " + highestBidder);
        System.out.println("Highest Bid Amount: " + highestBid);

        System.out.println("\n--- Vehicle Details ---");
        System.out.println("Registration Number: " + regNumber);
        System.out.println("Vehicle Cost: " + vehicleCost);
        System.out.println("Balance Left: " + balance);

        System.out.println("\n--- Financial Summary ---");
        System.out.println("Total Revenue: " + totalRevenue);
        System.out.println("Total Cost: " + totalCost);

        if (profitOrLoss > 0) {
            System.out.println("Profit Made: " + profitOrLoss);
        } else if (profitOrLoss < 0) {
            System.out.println("Loss Incurred: " + Math.abs(profitOrLoss));
        } else {
            System.out.println("No Profit, No Loss");
        }
    }
}
