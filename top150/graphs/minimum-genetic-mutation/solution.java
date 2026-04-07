class Solution {
    public int minMutation(String startGene, String endGene, String[] bank) {
       HashSet<String> bankset = new HashSet<>(Arrays.asList(bank));
       Queue<String> q = new LinkedList<>();
       q.add(startGene);
       int level = 0;
       char[] chars = new char[]{'A','C','G','T'};
       while(!q.isEmpty()){
        int sz = q.size();
            while(sz-- > 0){
                String gene =  q.remove();
                if(gene.equals(endGene)) return level; 
                for(int i =0;i<8;i++){
                    for(char c : chars){
                        StringBuilder sb = new StringBuilder(gene);
                            sb.setCharAt(i,c);
                            String temp = sb.toString();
                            if(bankset.contains(temp)){
                                q.add(temp);
                                bankset.remove(temp);
                            }
                    }
                }
            }
            level++;
       }
       return -1;
    }
    // because we are storing level-wise.
    // class Pair{
    //     String gene;
    //     int count;
    //     Pair(String g, int c){
    //         this.gene = g;
    //         this.count = c;
    //     }
        
    //     @Override
    //     public String toString(){
    //         return this.gene + " " + this.count;
    //     }
    // }
    
}