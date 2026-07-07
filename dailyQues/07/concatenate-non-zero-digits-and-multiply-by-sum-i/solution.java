class Solution {
    public long sumAndMultiply(int n) {
        int j=0;
        long x = 0;
        while(n!=0){
            long digit = n%10;
            n/=10;
            if(digit != 0) x += digit * Math.pow(10,j++);
        }
        long sum = 0;
        long temp = x;
        while(temp!=0){
            long  digit = temp%10;
            temp/=10;
            sum += digit;
        }
        return sum*x;
    }
}