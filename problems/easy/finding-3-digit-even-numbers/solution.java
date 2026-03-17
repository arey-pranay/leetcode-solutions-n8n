class Solution {
    LinkedHashSet<Integer> arr = new LinkedHashSet<>();
    int[] digits;
    public int[] findEvenNumbers(int[] ip) {
        boolean[] used = new boolean[ip.length];
        Arrays.sort(ip);
        digits = ip;
        evenfunc(0,used);
        int[] res = new int[arr.size()];
        int i=0;
        for(int val : arr) res[i++]= val;
        return res;
    }

    public void evenfunc(int ans,boolean[] used){
        if(ans<1000 && ans>99) {
            if(ans%2==0) arr.add(ans);
            return;
        }
        else{
            for(int i = 0 ; i<digits.length;i++){
                if(used[i]) continue;
                else {
                    ans = ans*10+digits[i];
                    if(ans==0) continue;
                    used[i]=true;
                    evenfunc(ans,used);
                    used[i]=false;
                    ans /=10;
                }
            }
        }
        return;
    }
}