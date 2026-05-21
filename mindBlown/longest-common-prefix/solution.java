// class Solution {
//     class Node{
//         Node[] children;
//         String word;
//         Node(){
//             this.children = new Node[26];
//             this.word = "";
//         }
//     }
//     public void add(String s){
//         Node curr = root;
//         StringBuilder sb = new StringBuilder("");
//         for(char c : s.toCharArray()){
//             int i= c-'a';
//             if(curr.children[i] == null) curr.children[i] = new Node();
//             curr = curr.children[i];
//             sb.append(c);
//             curr.word = sb.toString();
//         }
//     }
//     public String check(String s){
//         Node curr = root;
//         for(char c : s.toCharArray()){
//             int i = c-'a';
//             if(curr.children[i] == null) return curr.word;
//             else curr = curr.children[i];
//         }
//         return curr.word;
//     }
//     Node root = new Node();
    
//     public String longestCommonPrefix(String[] strs) {
//         add(strs[0]);
//         String ans = strs[0];
//         for(int i=1;i<strs.length;i++){
//             String temp = check(strs[i]);
//             if(temp.length() < ans.length()) ans = temp;
//         }
//         return ans.toString();
//     }
// }


class Solution {
    public String longestCommonPrefix(String[] strs) {
        Arrays.sort(strs);
        String s1 = strs[0];
        String s2 = strs[strs.length-1];
        int idx = 0;
        while(idx < s1.length() && idx < s2.length()){
            if(s1.charAt(idx) == s2.charAt(idx)){
                idx++;
            }else{
                break;
            }
        }
        return s1.substring(0, idx);
    }
}

//string m sorting alphabets k hisaab se prefix mtlb uske according hi hoti hai naa ki length k 



//   []
// /    \
// f
// /
// l
// /\
// o i
// /  \
// w  g
// /   \
// e   h 
// /    \
// r    t