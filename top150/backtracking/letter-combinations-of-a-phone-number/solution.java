class Solution {
    char[][] phone = new char[][]{
        {'-'},
        {'-'},
        {'a','b','c'},
        {'d','e','f'},
        {'g','h','i'},
        {'j','k','l'},
        {'m','n','o'},
        {'p','q','r','s'},
        {'t','u','v'},
        {'w','x','y','z'}
    };
    List<String> ans;
    public List<String> letterCombinations(String digits) {
        ans = new ArrayList<>(); 
        func(digits,0, new StringBuilder(""));
        return ans;
    }
    public void func(String digits, int index, StringBuilder curr){
        if(index == digits.length()){
            ans.add(curr.toString());
            return;
        }
        char[] carr = phone[(int)(digits.charAt(index) - '0')];
        for(char c : carr){
            curr.append(c);
            func(digits, index+1, curr);
            curr.deleteCharAt(curr.length()-1);
        }
        return;
    }
}