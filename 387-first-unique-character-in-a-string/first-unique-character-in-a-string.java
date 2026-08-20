class Solution {
    public int firstUniqChar(String s) {
        //        for (int i = 0; i < s.length(); i++) {
        //     char f = s.charAt(i);

        //     int sum = 0;

        //     for (int j = 0; j < s.length(); j++) {
        //         if (f == s.charAt(j)) {
        //             sum++;
        //         }
        //     }

        //     if (sum == 1) {
        //         return i;
        //     }
        // }

        // return -1;

        HashMap<Character,Integer> map = new HashMap<>();

        for(int i=0;i<s.length(); i++){
            char ch = s.charAt(i);
            map.put(ch,map.getOrDefault(ch,0)+1);
        }


        for(int i=0; i<s.length(); i++){
            char ch = s.charAt(i);


            if(map.get(ch)== 1){
                return i;
            }
        }

        return -1;


    }
}