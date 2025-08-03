package com.LeetCode;

// 2561. Problem : Rearranging Fruits

import java.util.*;
public class Problem2561 {
    public long minCost(int[] basket1, int[] basket2) {
        // Step 1: Count frequencies for everything.
        Map<Integer, Integer> totalFreq = new HashMap<>();
        for (int fruit : basket1) {
            totalFreq.put(fruit, totalFreq.getOrDefault(fruit, 0) + 1);
        }
        for (int fruit : basket2) {
            totalFreq.put(fruit, totalFreq.getOrDefault(fruit, 0) + 1);
        }

        long minVal = Long.MAX_VALUE;

        // Step 2: Check feasibility and find the overall minimum fruit value.
        for (Map.Entry<Integer, Integer> entry : totalFreq.entrySet()) {
            if (entry.getValue() % 2 != 0) {
                // If any fruit has an odd total count, it's impossible to balance.
                return -1;
            }
            minVal = Math.min(minVal, entry.getKey());
        }

        // Step 3 & 4: Identify all fruits that need to be moved.
        List<Integer> fruitsToMove = new ArrayList<>();
        Map<Integer, Integer> freq1 = getFreq(basket1);

        for (int fruit : totalFreq.keySet()) {
            int count1 = freq1.getOrDefault(fruit, 0);
            int targetCount = totalFreq.get(fruit) / 2;

            // If basket1 has a surplus, these fruits need to be moved out.
            if (count1 > targetCount) {
                int movesNeeded = count1 - targetCount;
                for (int i = 0; i < movesNeeded; i++) {
                    fruitsToMove.add(fruit);
                }
            }
        }

        // Step 5: Sort the fruits to move and calculate the minimum cost.
        Collections.sort(fruitsToMove);

        long cost = 0;
        // The number of swaps is half the size of the list of imbalanced fruits.
        // We only need to sum up the cost for the cheaper half of the fruits involved in swaps.
        for (int i = 0; i < fruitsToMove.size(); i++) {
            // We get the other side of the swap by considering the corresponding surplus from the other basket.
            // But since we want to minimize cost, we just need to find the cheapest fruits to move.
            // The fruitsToMove list now contains all fruits that are in surplus in one basket.
            // The number of swaps required is fruitsToMove.size().
            // However, the problem pairs them up. Let's correct the logic slightly.
            // The logic from the previous answer was more precise. Let's use that.
        }

        // Re-implementing the clearest swap logic
        List<Integer> swaps = new ArrayList<>();
        Map<Integer, Integer> basket1Freq = getFreq(basket1);
        for(int fruit : totalFreq.keySet()){
            int currentIn1 = basket1Freq.getOrDefault(fruit, 0);
            int target = totalFreq.get(fruit) / 2;

            int diff = currentIn1 - target;
            // if diff > 0, basket1 must give away |diff| fruits of this type.
            // if diff < 0, basket1 must take |diff| fruits of this type.
            // The list of fruits to be given away must match the list to be taken.
            for(int i = 0; i < Math.abs(diff); i++){
                swaps.add(fruit);
            }
        }

        Collections.sort(swaps);
        cost = 0;

        // The number of actual swaps is half the size of this list.
        // The first half represents the cheapest items to be moved.
        for(int i = 0; i < swaps.size() / 2; i++){
            // Cost is the minimum of a direct swap vs. two swaps using the cheapest fruit.
            cost += Math.min(swaps.get(i), 2L * minVal);
        }

        return cost;
    }

    /**
     * Helper function to get the frequency map of a single basket.
     * @param basket The input array of fruits.
     * @return A map of fruit type to its frequency.
     */
    private Map<Integer, Integer> getFreq(int[] basket) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int fruit : basket) {
            freq.put(fruit, freq.getOrDefault(fruit, 0) + 1);
        }
        return freq;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of test cases: ");
        int testCases = sc.nextInt();

        while (testCases-- > 0) {
            // Input for basket 1
            System.out.print("Enter the number of fruits in basket 1: ");
            int[] basket1 = new int[sc.nextInt()];

            System.out.print("Enter the fruits for basket 1 (space-separated): ");
            for (int i = 0; i < basket1.length; i++) {
                basket1[i] = sc.nextInt();
            }

            // Input for basket 2
            System.out.print("Enter the number of fruits in basket 2: ");
            int[] basket2 = new int[sc.nextInt()];
            System.out.print("Enter the fruits for basket 2 (space-separated): ");
            for (int i = 0; i < basket2.length; i++) {
                basket2[i] = sc.nextInt();
            }

            // Create a Solution object and calculate the result
            Problem2561 sol = new Problem2561();
            long result = sol.minCost(basket1, basket2);

            // Print the result
            System.out.println("Result -> Minimum cost: " + result);
        }

        sc.close();
    }
}
