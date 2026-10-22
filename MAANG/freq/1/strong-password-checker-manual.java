class Solution {
    public int strongPasswordChecker(String pw) {
        int n = pw.length();
        int lower = 1, upper = 1, digits = 1; // least need
        for(char c : pw.toCharArray()){
            if(Character.isDigit(c)) digits=0;
            else if(Character.isLowerCase(c)) lower=0;
            else if(Character.isUpperCase(c)) upper=0;
        }
        return applyOps(pw,digits+lower+upper);
    }
    public int applyOps(String pw, int insert){
        int n = pw.length();
        int i=0;
        int[] groupArr = new int[n];
        int replace = 0;
        while(i<n){
            int j = i;
            while(i<n && pw.charAt(i)==pw.charAt(j))i++;
            int sz = i-j;
            groupArr[j] = sz;
            if(sz >= 3) replace += sz/3;
        }
        if(n<6) return Math.max(insert,6-n);
        if(n<=20) return Math.max(insert, replace);
        
        int del = n-20;
        int ops = del;
        
        // while del>1, utilise 1 delete op, in case of %3==0 wale groups
        for(i=0 ; i<n && del >= 1 ;i++){
            if(groupArr[i] >= 3 && groupArr[i]%3==0){
                groupArr[i]--; del--;
            }
        }
        
        // while del>2, utilise 2 del op, in case of %3==1 wale groups
        for(i=0 ; i<n && del >= 2 ;i++){
            if(groupArr[i] >= 3 && groupArr[i]%3==1){
                groupArr[i]-=2; del-=2;
            }
        }
        
        // replace for other groups
        replace = 0;
        for(int groupSize : groupArr) replace += groupSize/3;
        
        // use remaining deletions in bundles of 3.
        replace = Math.max(0,replace - del/3);
        
        return ops + Math.max(insert,replace);
   
    }
}
