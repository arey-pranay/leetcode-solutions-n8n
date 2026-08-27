class Solution {
    public String minWindow(String s, String t) {
        int m = s.length();
        int n = t.length();
        int min = Integer.MAX_VALUE;
        int start = -1;
        HashMap<Character, Integer> needed = new HashMap<>();
        for (char c : t.toCharArray()) needed.put(c, needed.getOrDefault(c, 0) + 1);
        int wanted = t.length();
        int i = 0;
        for (int j = 0; j < m; j++) {
            char c = s.charAt(j);
            if (needed.containsKey(c)) {//valid character
                int currF = needed.get(c);
                if (currF> 0) wanted--;
                needed.put(c, currF-1);
            }
            while (wanted == 0) {
                if (min > j - i + 1) {
                    min = j - i + 1;
                    start = i;
                }
                char ci = s.charAt(i);
                if (needed.containsKey(ci)) {
                    int nowCount = needed.get(ci) + 1;
                    needed.put(ci, nowCount);
                    if (nowCount > 0) wanted++;
                }
                i++;
            }
        }
        return start == -1 ? "" : s.substring(start, start + min);
    }
}