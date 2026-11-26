class Solution {
    public boolean isValid(String s) {
        Stack<Character> st = new Stack<>();

        for (char c : s.toCharArray()) {
            // If opening bracket, push it
            if (c == '(' || c == '{' || c == '[') {
                st.push(c);
            } 
            // If closing bracket
            else {
                if (st.isEmpty()) return false;
                char top = st.pop();
                if ((c == ')' && top != '(') ||
                    (c == '}' && top != '{') ||
                    (c == ']' && top != '[')) {
                    return false;
                }
            }
        }
        // If stack is empty, all opened were closed
        return st.isEmpty();
    }
}