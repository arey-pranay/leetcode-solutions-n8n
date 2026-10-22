class Solution {
    Map<Set<String>, Set<Integer>> memo = new HashMap<>();

    public int[] smallestSufficientTeam(String[] reqSkills, List<List<String>> people) {
        HashSet<String> req = new HashSet<>();
        for (String s : reqSkills)
            req.add(s);
        Set<Integer> team = func(req, people);
        int[] ans = new int[team.size()];
        int i = 0;
        for (int person : team)
            ans[i++] = person;
        return ans;
    }

    public Set<Integer> func(Set<String> req, List<List<String>> people) {
        if (req.isEmpty())
            return new HashSet<>();
        if (memo.containsKey(req))
            return memo.get(req);
        Set<Integer> ans = null;

        String need = req.iterator().next(); // NEW: pick one uncovered skill

        for (int i = 0; i < people.size(); i++) {
            if (!people.get(i).contains(need))
                continue; // NEW: only people who have it
            Set<String> temp = new HashSet(req);
            temp.removeAll(people.get(i));
            // old "if(temp.size() == req.size()) continue;" is no longer needed,
            // anyone who has `need` always shrinks req
            Set<Integer> team = new HashSet<>(func(temp, people));
            team.add(i);
            if (ans == null || team.size() < ans.size())
                ans = team;
        }

        memo.put(req, ans);
        return ans;
    }
}
// team = [people chosen] of min length, that cover all skills
