class Solution {
    class Pair{
        String word;
        int level;
        Pair(String w, int l){
            this.word = w;
            this.level = l;
        }
    }
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {    
        HashSet<String> wordSet = new HashSet<>(wordList);
        HashSet<String> visited = new HashSet<>();
        
        if(!wordSet.contains(endWord)) return 0;
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(beginWord,1));
        visited.add(beginWord);
        while(!q.isEmpty()){
            Pair currPair = q.poll();
            String currWord = currPair.word;
            int currLevel = currPair.level;
            for(int i =0;i<currWord.length();i++){
                char[] wordArray = currWord.toCharArray();
                for(char c='a';c<='z';c++){
                    wordArray[i] = c;
                    String newWord = new String(wordArray);
                    if(wordSet.contains(newWord) && !visited.contains(newWord)){
                        if(newWord.equals(endWord)) return currLevel+1;
                        q.offer(new Pair(newWord,currLevel+1));
                        visited.add(newWord);
                    }           
                }
            }
        }
        return 0;
    }
}
