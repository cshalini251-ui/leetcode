class Solution {
    public String convert(String s, int numRows) {
        if (numRows == 1 || numRows >= s.length()) {
            return s;
        }

        StringBuilder[] rows = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            rows[i] = new StringBuilder();
        }

        int cur = 0;
        boolean goingDown = false;

        for (char c : s.toCharArray()) {
            rows[cur].append(c);
            if (cur == 0 || cur == numRows - 1) {
                goingDown = !goingDown;
            }
            cur += goingDown ? 1 : -1;
        }

        StringBuilder ans = new StringBuilder();
        for (StringBuilder row : rows) {
            ans.append(row);
        }
        return ans.toString();
    }
}