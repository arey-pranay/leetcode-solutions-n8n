class Solution {
    List<String> ans = new ArrayList<>();
    Map<Character, List<Character>> hm = Map.of(
        '2',List.of('a','b','c'),
        '3',List.of('d','e','f'),
        '4',List.of('g','h','i'),
        '5',List.of('j','k','l'),
        '6',List.of('m','n','o'),
        '7',List.of('p','q','r','s'),
        '8',List.of('t','u','v'),
        '9',List.of('w','x','y','z')
    );
    public List<String> letterCombinations(String digits) {
        func(digits,0,new StringBuilder(""));
        return ans;
    }
    public void func(String digits, int i, StringBuilder sb){
        if(i==digits.length()) { ans.add(new String(sb.toString())); return;} 
        List<Character> chars = hm.get(digits.charAt(i));
        for(int x=0;x<chars.size();x++){
            sb.append(chars.get(x));
            func(digits,i+1,sb);
            sb.deleteCharAt(sb.length()-1);
        }
    }
}