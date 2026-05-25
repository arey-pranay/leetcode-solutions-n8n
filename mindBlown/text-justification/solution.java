class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> arr = new ArrayList<>();
        int i =0;
        ArrayList<String> temp = new ArrayList<>();
        while(i<words.length){
            int total = 0;
            temp = new ArrayList<>();
            int spaceNeeded = maxWidth;
            while(i<words.length && total + words[i].length() <= maxWidth){
                String word = words[i];
                total += word.length()+1;
                temp.add(word);
                spaceNeeded -= word.length();
                i++;
            }
            arr.add(addSpaces(temp,spaceNeeded));
        }
        arr.remove(arr.size()-1);
        arr.add(justifyLeft(temp, maxWidth));
        return arr;
    }
    public String addSpaces(List<String> arr, int spaceNeeded){
        int words = arr.size();
        StringBuilder sb = new StringBuilder(arr.get(0));
        if(words==1){
            sb.append(" ".repeat(spaceNeeded));
            return sb.toString();
        }
        int perWord = spaceNeeded/(words-1);
        int extraFrom = (spaceNeeded % (words-1));
        for(int i=1;i<words;i++){
            sb.append(" ".repeat(perWord));
            if(i <= extraFrom){
                sb.append(" ");
                spaceNeeded--;
            }
            sb.append(arr.get(i));
            spaceNeeded -= perWord;
        }
        return sb.toString();
    }
    public String justifyLeft(ArrayList<String> arr,int maxLength){
        StringBuilder sb = new StringBuilder(arr.get(0));
        for(int i=1;i<arr.size();i++){
            sb.append(" ");
            sb.append(arr.get(i));
        }
        int moreSpaces = maxLength-sb.length();
        sb.append(" ".repeat(moreSpaces));
        return sb.toString();
    }  
}