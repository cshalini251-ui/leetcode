class Solution {
    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;
        int low = 1, high = n, ans = n;

        // Precompute powers for rolling hash
        long[] pow = new long[n + 1];
        long base = 91138233L;
        pow[0] = 1;
        for (int i = 1; i <= n; i++) {
            pow[i] = pow[i - 1] * base;
        }

        // Prefix hash
        long[] pref = new long[n + 1];
        for (int i = 0; i < n; i++) {
            pref[i + 1] = pref[i] * base + (nums[i] + 1007);
        }

        while (low <= high) {
            int mid = (low + high) / 2;
            if (hasUnique(nums, mid, pref, pow, base)) {
                ans = mid;
                high = mid - 1; // try smaller
            } else {
                low = mid + 1; // need bigger
            }
        }
        return ans;
    }

    private boolean hasUnique(int[] nums, int len, long[] pref, long[] pow, long base) {
        int n = nums.length;
        java.util.HashMap<Long, Integer> map = new java.util.HashMap<>();

        for (int i = 0; i + len <= n; i++) {
            // hash of nums[i .. i+len-1]
            long hash = pref[i + len] - pref[i] * pow[len];
            map.put(hash, map.getOrDefault(hash, 0) + 1);
        }

        // If any hash appears exactly once -> unique found
        for (int count : map.values()) {
            if (count == 1) return true;
        }
        return false;
    }
}