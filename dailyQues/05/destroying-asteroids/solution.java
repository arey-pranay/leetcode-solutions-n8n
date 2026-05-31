class Solution {
    public boolean asteroidsDestroyed(int mass, int[] asteroids) {
        Arrays.sort(asteroids);
        long total = mass;
        for(int m : asteroids) if(m > total) return false; else total+=m;
        return true;
    }
}