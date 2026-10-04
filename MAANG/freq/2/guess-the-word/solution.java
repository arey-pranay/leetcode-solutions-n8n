/**
 * // This is the Master's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface Master {
 *     public int guess(String word) {}
 * }
 
 */
 // problem is to minimize guess k calls.
 // random humne isliye lagaya taaki eligible arraylist me se 1 valid index le ske aur baar baar same same index naa aajaye
class Solution {
    public void findSecretWord(String[] words, Master master) {
        int run =0;
        List<String> eligible = new ArrayList<>();
        for(String word : words) eligible.add(word);
        Random random = new Random();
        while(run < 30){
            int index = random.nextInt(eligible.size()); 
            String word = eligible.get(index);
            List<String> newer = new ArrayList<>();
            int match = master.guess(word); // goal is not n or n^2, it is to minimize the calls made to guess
            if(match==6) return;
            for(String word2 : eligible){
                if(charMatches(word,word2) == match){
                    newer.add(word2);
                }
            }
            eligible = newer;
            run++;
        }
    }
    public int charMatches(String word1, String word2){
        int match = 0;
        for(int i=0;i<6;i++) if(word1.charAt(i)==word2.charAt(i)) match++;
        return match;
    }
}