import java.util.Arrays;

public class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int closestSum = nums[0] + nums[1] + nums[2]; // Initialize with the first possible sum
        
        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1, right = nums.length - 1;

            while (left < right) {
                int currentSum = nums[i] + nums[left] + nums[right];

                // Update closestSum if the currentSum is closer to the target
                if (Math.abs(target - currentSum) < Math.abs(target - closestSum)) {
                    closestSum = currentSum;
                }

                // Adjust pointers
                if (currentSum < target) {
                    left++;
                } else if (currentSum > target) {
                    right--;
                } else {
                    // Exact match found
                    return target;
                }
            }
        }

        return closestSum;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {-1, 2, 1, -4};
        int target = 1;
        int result = solution.threeSumClosest(nums, target);
        System.out.println("Closest sum: " + result);
    }
}
