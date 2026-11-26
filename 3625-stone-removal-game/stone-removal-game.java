class Solution {
    public boolean canAliceWin(int n) {
        int stones = 10;
        boolean aliceTurn = true;

        while (true) {
            if (n < stones) {
                // Current player cannot move, so loses
                // If it's Alice's turn and she can't move, she loses -> false
                // If it's Bob's turn and he can't move, Alice wins -> true
                return !aliceTurn;
            }
            n = n - stones;
            stones = stones - 1;
            aliceTurn = !aliceTurn;
        }
    }
}