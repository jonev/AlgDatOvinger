package onving1KompleksitetTidtakning;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by JoneSkole on 21.08.2017.
 */
public class o1 {
    public static void main(String[] args) {
        int totalDays = 1000000;
        // int[] kursforandring = {-1, 3, -9, 2, 2, -1, +2, -1, -5};
        int[] stockChange = new int[totalDays];
        for (int i = 0; i < totalDays; i++) {
            stockChange[i] = ThreadLocalRandom.current().nextInt(-10, 10 + 1);
        }

        int buyDay = 0;
        int salesDay = 1;
        int highestdiff = 0;
        int buyPrice = 0;
        int salesPrice;

        for (int numberOfDays = 10; numberOfDays < totalDays; numberOfDays*=10) {

            for (int numberOfTries = 0; numberOfTries < 5; numberOfTries++) { // tidsmålingsløkke

                long start = System.nanoTime();
                // kode for algoritmen
                for (int tempBuyDay = 0; tempBuyDay < numberOfDays-1; tempBuyDay++) { // 2n + 1
                    buyPrice += stockChange[tempBuyDay]; // 2
                    salesPrice = buyPrice; // 1
                    for (int tempSalesDay = tempBuyDay+1; tempSalesDay < numberOfDays; tempSalesDay++) { // 2n + 1
                        salesPrice += stockChange[tempSalesDay]; // 2
                        int currentdiff = salesPrice - buyPrice; // 1
                        if(currentdiff > highestdiff){ // 1
                            highestdiff = currentdiff; // 1
                            buyDay = tempBuyDay; // 1
                            salesDay = tempSalesDay; // 1
                        }
                    }
                }
                // 6n * 9n + 1 tregeste
                // 6n * 4n + 1 raskeste
                // analyse n^2

                long timeSpent = System.nanoTime() - start;
                System.out.println("Antall: " + numberOfDays + " diff: " + highestdiff + ", Kjop: " + (buyDay+1) + " , salg: " + (salesDay+1) + " tidsforbruk: " + timeSpent);


            }
        }

    }

}
