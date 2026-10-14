class Solution {
    public String maxSumOfSquares(int num, int sum) {
        if(num*9 < sum) return "";
        
        StringBuilder ans = new StringBuilder("");
        int nines = sum/9; // since we need the biggest number, use as many 9s in the left as possible
        sum %= 9; // then append the remainder
        ans.append("9".repeat(nines));
        if(sum > 0) ans.append(sum);
        ans.append("0".repeat(num - ans.length())); // based on howmanyever digits are yet to be added, add 0s at the end
        
        return ans.toString();
    }
}


// start from left. take 9 if possible. otherwise take remNum
// if all are filled with 9 and rem>0