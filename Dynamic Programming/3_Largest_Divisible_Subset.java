import java.util.*;

class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums); // Sort the array for efficient divisibility checking

        //memoization
        HashMap<String,List<Integer>> memo=new HashMap<>();

        List<Integer> result = helper(0,-1, nums, memo);
        
        return result;
    }

    // Helper method with memoization
    public List<Integer> helper(int index, int prevIndex, int[] nums, HashMap<String,List<Integer>> memo) {
        
        if (index >= nums.length) {
            return new ArrayList<>();
        }

        String key = index+","+prevIndex;

        if(memo.containsKey(key)){
            return memo.get(key);
        }

        List<Integer> result = new ArrayList<>();

        // Check if the current number can be added to the subset
        List<Integer> take = new ArrayList<>();

        if (prevIndex==-1 || nums[index]%nums[prevIndex]==0) {
            take.add(nums[index]);
            take.addAll(helper(index+1,index,nums,memo));
        }

        // Skip the current number
        List<Integer> nottake = helper(index + 1,prevIndex,nums,memo);

        if(take.size()>nottake.size()){
            result = take;
        }else{
            result = nottake;
        }

        memo.put(key,result);

        return result;
    }

   
}