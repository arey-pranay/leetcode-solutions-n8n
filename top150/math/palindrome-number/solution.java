class Solution {
    public boolean isPalindrome(int x) { 
        if(x<0) return false;
        int sum =0;
        int copy = x;
        while(x>0){
            int r = x%10;
            sum = sum*10 + r;
            x=x/10;
        }
        return sum==copy;
    }
}

// class Solution {
//     public boolean isPalindrome(int x) {
//         if(x<0) return false;
//         int maxPower = (int)Math.log10(x);
//         int left = maxPower;
//         int right = 0;
//         while(left > right){
            
//             int firstDigit = (x/(int)Math.pow(10,left))%10;
//             int lastDigit = (x/(int)Math.pow(10,right))%10;
           
//             if(firstDigit != lastDigit) return false;
//             left--;
//             right++;
//         }
//         return true;
  
//     }
// }