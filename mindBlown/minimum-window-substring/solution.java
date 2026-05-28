class Solution {
    public String minWindow(String s, String t) {
        if (s.length() < t.length()) return "";
        
        Queue<Integer> validIndex = new LinkedList<>();
        HashMap<Character, Integer> og = new HashMap<>();
        for (char i : t.toCharArray()) og.put(i, og.getOrDefault(i, 0) + 1);

        int index = -1;
        int min = Integer.MAX_VALUE;

        int rem = t.length();
                
        for(int j=0;j<s.length();j++) {
            char curr = s.charAt(j);
            if (og.containsKey(curr)) {
                validIndex.offer(j);
                if (og.get(curr) > 0) rem--;
                og.put(curr, og.get(curr) - 1);
            }
            while (rem == 0) {
                int i = validIndex.poll();
                if (j - i + 1 < min) {
                    min = j - i + 1;
                    index = i;
                }
                char firstMatch = s.charAt(i);
                og.put(firstMatch, og.get(firstMatch) + 1);
                if(og.get(firstMatch) > 0) rem++;
            }
        }
        return index == -1 ? "" : s.substring(index, index + min);
    }
}