import java.util.*;
class Solution {
    public int findMaxForm(String[] strs, int m, int n) {
        // Memoization map to store results for (index, m, n)
        Map<String, Integer> memo = new HashMap<>();
        return helper(0, strs, m, n, memo);
    }

    // Recursive helper function with memoization
    private int helper(int index, String[] strs, int m, int n, Map<String, Integer> memo) {
        // Base case: no more strings to process
        if (index == strs.length) {
            return 0;
        }

        // Generate the key for memoization
        String key = index + "," + m + "," + n;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        // Count zeros and ones in the current string
        String currentStr = strs[index];
        int zeros = 0, ones = 0;
        for (char c : currentStr.toCharArray()) {
            if (c == '0') zeros++;
            else ones++;
        }

        // Option 1: Do not take the current string
        int notTake = helper(index + 1, strs, m, n, memo);

        // Option 2: Take the current string (if possible)
        int take = 0;
        if (m >= zeros && n >= ones) {
            take = 1 + helper(index + 1, strs, m - zeros, n - ones, memo);
        }

        // Store the result in the memo and return the maximum of both options
        int result = Math.max(take, notTake);
        memo.put(key, result);
        return result;
    }
}

