class Solution {
    public boolean detectCapitalUse(String word) {

        if(word.equals(word.toLowerCase())){
            return true;
        }

        if(word.equals(word.toUpperCase())){
            return true;
        }



        if(Character.isUpperCase(word.charAt(0))){
            for(int i=1; i<word.length(); i++){
                if(!Character.isLowerCase(word.charAt(i))){
                    return false;
                }
                
            }
            return true;
        }



        // for (int i = 0; i < word.length(); i++) {
        //     if (Character.isUpperCase(word.charAt(i)) || Character.isLowerCase(word.charAt(i))
        //             || Character.isTitleCase(word.charAt(i))) {
        //         return true;
        //     }
        // }
        return false;
    }
}