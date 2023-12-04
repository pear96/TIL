package BFS_DFS;

import java.util.ArrayDeque;
import java.util.HashMap;

class PG_단어변환 {
    public int solution(String begin, String target, String[] words) {
        HashMap<String, Integer> visited = new HashMap<>();
        for (String word: words) {
            visited.put(word, 0);
        }
        if (!visited.containsKey(target)) return 0;
        
        int wordSize = begin.length();

        ArrayDeque<String> q = new ArrayDeque<>();
        q.add(begin);
        visited.put(begin, 1);
        
        while(!q.isEmpty()) {
            String now = q.poll();
            
            for (String word : words) {
                if (visited.get(word) == 0) {
                    int diff = 0;
                    for (int j = 0; j < wordSize; j++) {
                        if (now.charAt(j) != word.charAt(j)) diff++;
                    }
                    
                    if (diff == 1) {
                        visited.put(word, visited.get(now)+1);
                        q.add(word);
                    }
                }
            }
        }
        
        return visited.get(target)-1;
    }
}
