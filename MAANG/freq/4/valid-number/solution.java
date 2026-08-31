class Solution {
    public boolean isNumber(String s) {
          int i =0;
          int n = s.length();
          char[] carr = s.toCharArray();
          
          // 1. SIGN?   (SIGN (- or +) <= 1 )
          if(i<n && carr[i]=='+' || carr[i]=='-') i++;
          
          // 2. NUM* (NUM ( 0 to 9 )  >= 0)
          boolean foundDigit = false; //either stage 2 or 4 should have a digit
          while(i<n && Character.isDigit(carr[i])){foundDigit=true; i++;}

          //3. DOT? (DOT ( . ) <= 1 )
          if(i<n && carr[i]=='.') i++;

          //4. NUM* (NUM ( 0 to 9 )  >= 0)
          while(i<n && Character.isDigit(carr[i])){foundDigit=true; i++;}
          if(!foundDigit) return false; // avoid -. like things

          //5. E?   ( e or E ) <= 0 
          if(i<n && (carr[i]=='e' || carr[i]=='E')){
            i++;
             // SIGN (- or +) <= 1  
            if(i<n && (carr[i]=='+' || carr[i]=='-')) i++;

            // NUM ( 0 to 9 )  >=1
            foundDigit = false; // + instead of *, so we need at least one digit
            while(i<n && Character.isDigit(carr[i])){foundDigit=true; i++;}
            if(!foundDigit) return false;
          }
          return i==n;
    }
}