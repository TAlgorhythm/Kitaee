import java.util.*;

class Solution {
    
    static class Genre implements Comparable<Genre>{
        private String name;
        private int playCount;
        
        public Genre(String name, int playCount) {
            this.name = name;
            this.playCount = playCount;
        }
        
        @Override
        public int compareTo(Genre o) {
            return o.playCount - this.playCount;
        }
    }
    
    static class GenrePlayInfo {
        private int index;
        private int playCount;
        
        public GenrePlayInfo(int index, int playCount) {
            this.index = index;
            this.playCount = playCount;
        }
    }
    
    static HashMap<String, Integer> album = new HashMap<>();
    static HashMap<String, List<GenrePlayInfo>> genrePlayMap = new HashMap<>();
    static PriorityQueue<Genre> genreRanks = new PriorityQueue<>();
    static List<Integer> answer = new ArrayList<>();
    
    public int[] solution(String[] genres, int[] plays) {
        for(int i=0; i<genres.length; i++) {
            if(album.containsKey(genres[i])) {
                album.put(genres[i], album.get(genres[i])+plays[i]);
                List<GenrePlayInfo> playList = genrePlayMap.get(genres[i]);
                playList.add(new GenrePlayInfo(i,plays[i]));
            } else {
                album.put(genres[i], plays[i]);
                List<GenrePlayInfo> newPlayList = new ArrayList<>();
                newPlayList.add(new GenrePlayInfo(i, plays[i]));
                genrePlayMap.put(genres[i], newPlayList);
            }
        }
        
        for (Map.Entry<String, Integer> entry : album.entrySet()) {
            String genre = entry.getKey();
            Integer playCount = entry.getValue();
            genreRanks.offer(new Genre(genre, playCount));
        }
        
        while(!genreRanks.isEmpty()) {
            int count = 2;
            String targetGenre = genreRanks.poll().name;
            List<GenrePlayInfo> targetGenrePlayList = genrePlayMap.get(targetGenre);
            Collections.sort(targetGenrePlayList, new Comparator<>(){
                
                @Override
                public int compare(GenrePlayInfo o1, GenrePlayInfo o2) {
                    return o2.playCount - o1.playCount;
                }
            });
            
            for(int i=0; i<targetGenrePlayList.size(); i++) {
                answer.add(targetGenrePlayList.get(i).index);
                count-=1;
                if(count == 0) {
                    break;
                }
            }
        }
        
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}
