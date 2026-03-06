class Solution {
    public boolean checkOnesSegment(String s) {
        int i =0;
        int n = s.length();
        boolean foundOne = false;
        // boolean zero= false;
        while(i<n){
            // if(s.charAt(i)=='0') zero = true;
            // if(s.charAt(i)=='1' && zero==true) return false;
            // i++;
            if(s.charAt(i) == '1'){
                if(foundOne) return false;
                while(i<n && s.charAt(i)=='1') i++;
            }
            foundOne= true;
            i++;
        }
        return true;
    }
   
}

