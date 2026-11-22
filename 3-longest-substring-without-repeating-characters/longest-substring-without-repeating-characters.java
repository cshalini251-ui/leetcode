class Solution {
    public int lengthOfLongestSubstring(String s) {
        int max=0;
        for(int i=0;i<s.length();i++)
        {
            String temp="";
            for(int j=i;j<s.length();j++){
                char c=s.charAt(j);
                if(temp.contains(c +"")){
                    break;
                }
                temp=temp+c;
                if(temp.length()>max){
                    max=temp.length();
                }
            }
        }
                return max;
    }
        
    
}