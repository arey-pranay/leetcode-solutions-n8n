class Solution {
    public boolean isHappy(int n) {
        HashSet<Integer> found = new HashSet<>();
        while(n!=1){
            int sum = 0;
            while(n>0){
                sum += (n%10)*(n%10);
                n = n/10;
            }
            n = sum;
            if(found.contains(sum)) return false;
            found.add(sum);
        }
        return true;
    }
}