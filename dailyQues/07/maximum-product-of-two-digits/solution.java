class Solution {
    public int maxProduct(int n) {
        int maxNum=0,maxProd=0;
        while(n!=0){
            int rem = n%10;
            maxProd = Math.max(maxProd,maxNum*rem);
            maxNum = Math.max(maxNum,rem);
            n/=10;
        }
        return maxProd;
    }
}