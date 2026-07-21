class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        int curr0 =0;
        int start =-1;
        boolean streak1 = false;
        HashMap<Integer,Integer> hm = new HashMap<>();
        int ones = 0;
        int max=0;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='1'){
                ones++;
                if(!streak1){ 
                    if(start!=-1){
                        int before = hm.get(start);
                        hm.put(start,before+curr0);
                        if(before!=0) max = Math.max(max,hm.get(start));
                    }
                    hm.put(i,curr0);
                    start = i;
                    streak1 = true;
                    curr0=0;
                }
            }
            else {
                curr0++;
                streak1=false;
            }
        } 
        if(start!=-1 && !streak1){
            int before = hm.get(start);
            hm.put(start,before+curr0);
            if(before!=0) max = Math.max(max,hm.get(start));
        }
        return ones+max;
    }
}