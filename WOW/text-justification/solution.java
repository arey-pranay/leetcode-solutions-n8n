class Solution {
    List<String> ans = new ArrayList<>();
    int maxW;
    public List<String> fullJustify(String[] words, int maxWidth) {
      maxW = maxWidth; startNewLine(0,words);
      return ans;
    }
    public void startNewLine(int wordI, String[] words){
        int widthTaken = 0;
        List<String> list  = new ArrayList<>();
        for(int i=wordI; i<words.length;i++){
            String word = words[i];
            int wordWidth = word.length();
            if((widthTaken + wordWidth) > maxW){
                ans.add(fullJustify(list));
                startNewLine(i,words);
                return;
            }
            list.add(word);
            widthTaken += wordWidth+1;
        }
        ans.add(leftJustify(list));
        return;
    }
    public String fullJustify(List<String> words){
        int count = words.size();
        StringBuilder sb = new StringBuilder("");
        
        int totalLen = 0;
        for(String word : words) totalLen+=word.length();
        
        int spaces = maxW-totalLen;
        
        if(count==1){
            sb.append(words.get(count-1));
            if(count==1) sb.append((" ").repeat(spaces));
            return sb.toString();
        }
        
        int spacePerWord = spaces/(count-1);
        int extraSpaces = spaces%(count-1);
        
        for(int i=0;i<count-1;i++){
            sb.append(words.get(i));
            sb.append((" ").repeat(spacePerWord));
            if(extraSpaces > 0){sb.append(" "); extraSpaces--;}
        }
        
        sb.append(words.get(count-1));
        return sb.toString();
    }
    public String leftJustify(List<String> words){
        StringBuilder sb = new StringBuilder("");
        for(int i=0;i<words.size()-1;i++){
            sb.append(words.get(i));
            sb.append(" ");
        }
        
        sb.append(words.get(words.size()-1));
        sb.append((" ").repeat(maxW-sb.length()));
        
        return sb.toString();
    }
}

