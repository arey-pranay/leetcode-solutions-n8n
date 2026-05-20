class Solution {
    public int romanToInt(String s) {
        HashMap<Character,Integer> hm = new HashMap<>();
        hm.put('V',5);
        hm.put('L',50);
        hm.put('D',500);
        hm.put('M',1000);
        int i =-1;
        int ans =0;
        int n = s.length();
        while(i<n-1){
            i++;
            switch(s.charAt(i)){
                case 'I':
                    if(i<n-1 && s.charAt(i+1)=='V'){
                        ans += 4;
                        i++;
                    } else if(i<n-1 && s.charAt(i+1)=='X'){
                        ans += 9;
                        i++;
                    } else ans++;
                    break;
                case 'X':
                    if(i<n-1 && s.charAt(i+1)=='L'){
                        ans += 40;
                        i++;
                    } else if(i<n-1 && s.charAt(i+1)=='C'){
                        ans += 90;
                        i++;
                    } else ans+= 10;
                    
                    break;
                case 'C':
                    if(i<n-1 && s.charAt(i+1)=='D'){
                        ans += 400;
                        i++;
                    } else if(i<n-1 && s.charAt(i+1)=='M'){
                        ans += 900;
                        i++;
                    } else ans += 100;
                    break;
                default:
                    ans += hm.get(s.charAt(i));
                    break;
            }    
        }
        return ans;
    }
}