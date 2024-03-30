import java.util.ArrayList;

/**
 * The Stock class implements static methods for finding the average price in an array or arraylist
 * of stock, the maximum price, the cumulative prices of the stock at each position and the occurrences
 * of a target value in the stock
 */
public class Stock {

    public static float calculateAveragePrice(ArrayList<Float> prices) {
        float average = 0;
        for (Float price : prices) average += price;
        return average / prices.size();
    }

    public static float calculateAveragePrice(float[] prices) {
        float average = 0;
        for (float price : prices) average += price;
        return average / prices.length;
    }

    public static float findMaximumPrice(float[] prices) {
        float max = 0;
        for (float price : prices) max = Math.max(price, max);
        return max;
    }

    public static float findMaximumPrice(ArrayList<Float> prices) {
        float max = 0;
        for (float price : prices) max = Math.max(price, max);
        return max;
    }

    public static int countOccurrences(float[] prices, float target) {
        int count = 0;
        for (float price : prices) count += (price == target ? 1 : 0);
        return count;
    }

    public static ArrayList<Float> computeCumulativeSum(ArrayList<Float> prices) {
        ArrayList<Float> cumulative = new ArrayList<>();
        float totalSoFar = 0;
        for (Float price : prices) {
            cumulative.add(price + totalSoFar);
            totalSoFar += price;
        }
        return cumulative;
    }

    public static void main(String[] args) {
        float[] prices = {20, 40, 10, 10, 50, 67, 94, 98, 65, 45};
        ArrayList<Float> pricesList = new ArrayList<>();
        for (float price : prices) pricesList.add(price);
        System.out.println("The average price of the ArrayList Stock is: " + calculateAveragePrice(pricesList));
        System.out.println("The average price of the Array Stock is: " + calculateAveragePrice(prices));
        System.out.println("The maximum price of the ArrayList Stock is: " + findMaximumPrice(pricesList));
        System.out.println("The maximum price of the Array Stock is: " + findMaximumPrice(prices));
        System.out.println("The cumulative sum of the ArrayList Stock is: " + computeCumulativeSum(pricesList));
        System.out.println("The occurrence of 10 in the stock is: " + countOccurrences(prices, 10));

    }
}
