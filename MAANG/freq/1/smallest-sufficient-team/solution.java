class Solution {
    Map<Set<String>, Set<Integer>> memo = new HashMap<>();
    public int[] smallestSufficientTeam(String[] reqSkills, List<List<String>> people) {
        HashSet<String> req = new HashSet<>();
        for(String s : reqSkills) req.add(s);
        Set<Integer> team = func(req,people);
        int[] ans = new int[team.size()];
        int i =0;
        for(int person : team) ans[i++] = person;
        return ans;
    }
    public Set<Integer> func(Set<String> req , List<List<String>> people){
        if(req.isEmpty()) return new HashSet<>();
        if(memo.containsKey(req)) return memo.get(req);
        Set<Integer> ans = null;
        String nextSkill = req.iterator().next();
        for(int i=0;i<people.size();i++){
            if(!people.get(i).contains(nextSkill)) continue;
            Set<String> temp = new HashSet(req);
            temp.removeAll(people.get(i));// list lekr list k andr ki saari cheezein hataata hai 
            Set<Integer> team = new HashSet<>(func(temp,people));
            team.add(i);
            if(ans==null || team.size() < ans.size()) ans = team;
        }
        memo.put(req, ans);
        return ans;
    }
}
        // team = [people chosen] of min length, that cover all skills
