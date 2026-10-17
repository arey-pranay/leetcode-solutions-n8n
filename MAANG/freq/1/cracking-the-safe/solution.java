class Solution {
    Set<String> vis = new HashSet<>();
    StringBuilder result = new StringBuilder();
    public String crackSafe(int n, int k) {
        // pehla useful sample ye size ka hoga na qki isliye 
        String start = "0".repeat(n-1); //minimum password length-1, so that first append would generate a valid password;
        func(start, n, k);
        result.append(start);
        return result.toString();
    }
    public void func(String curr,int n,int k){
        for(int i=0;i<k;i++){
            char digit = (char)('0'+i);
            String pass = curr + digit;
            if(!vis.contains(pass)){
                vis.add(pass);
                String next = pass.substring(1); // remove k liye
                func(next,n,k);
                result.append(digit);
            }
        }
    }
}

// n length ke k digits wale combination

// =>   
// 01100

// largest common prefix and suffix 
// check suffix of curr string, and match the prefix of all left

// 0