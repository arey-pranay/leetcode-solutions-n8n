class Solution {

    public List<String> findWords(char[][] board, String[] words) {
        TrieNode root = buildTrie(words);
        List<String> result = new ArrayList<>();

        int m = board.length;
        int n = board[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(board, i, j, root, result);
            }
        }

        return result;
    }

    public void dfs(char[][] board, int i, int j, TrieNode node, List<String> result) {
        char c = board[i][j];

        // stop if visited or no matching child
        if (c == '#' || node.children[c - 'a'] == null) return;

        node = node.children[c - 'a'];

        // found a word
        if (node.word != null) {
            result.add(node.word);
            node.word = null; // avoid duplicates
        }

        // mark visited
        board[i][j] = '#';

        int[] dir = {-1, 0, 1, 0, -1};
        for (int d = 0; d < 4; d++) {
            int ni = i + dir[d];
            int nj = j + dir[d + 1];

            if (ni >= 0 && nj >= 0 && ni < board.length && nj < board[0].length) {
                dfs(board, ni, nj, node, result);
            }
        }

        // backtrack
        board[i][j] = c;
    }

    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String word = null;
    }

    public TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();

        for (String word : words) {
            TrieNode node = root;

            for (char c : word.toCharArray()) {
                int idx = c - 'a';

                if (node.children[idx] == null) {
                    node.children[idx] = new TrieNode();
                }

                node = node.children[idx];
            }

            node.word = word;
        }

        return root;
    }
}