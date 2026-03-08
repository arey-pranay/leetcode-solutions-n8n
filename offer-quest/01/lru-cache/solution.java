class LRUCache {

    HashMap<Integer, Integer> lru;
    TreeMap<Integer, Integer> levels;
    int[] freq;
    int cap;
    int max;

    public LRUCache(int capacity) {

        lru = new HashMap<>();
        levels = new TreeMap<>();
        freq = new int[(int)1e6 + 1];
        cap = capacity;
        max = 0;
    }

    public int get(int key) {

        if (!lru.containsKey(key))
            return -1;

        // remove old level
        int oldLevel = freq[key];
        levels.remove(oldLevel);

        // assign new level
        max++;
        levels.put(max, key);
        freq[key] = max;

        return lru.get(key);
    }

    public void put(int key, int value) {

        // if key already exists
        if (lru.containsKey(key)) {

            // update value
            lru.put(key, value);

            // remove old level
            int oldLevel = freq[key];
            levels.remove(oldLevel);

            // assign new level
            max++;
            levels.put(max, key);
            freq[key] = max;

            return;
        }

        // if capacity full → remove LRU
        if (lru.size() == cap) {
            remove();
        }

        // insert new key
        max++;
        lru.put(key, value);
        levels.put(max, key);
        freq[key] = max;
    }

    public void remove() {

        int toRemoveLevel = levels.firstKey();
        int toRemoveKey = levels.get(toRemoveLevel);

        levels.remove(toRemoveLevel);
        lru.remove(toRemoveKey);

        freq[toRemoveKey] = -1;
    }
}