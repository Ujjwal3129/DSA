class Solution {
    public int totalNumbers(int[] digits) {
        int[] count = new int[10];

        for (int digit : digits) {
            count[digit]++;
        }

        int ans = 0;

        for (int num = 100; num <= 999; num++) {
            if (num % 2 != 0) {
                continue;
            }

            int a = num / 100;
            int b = (num / 10) % 10;
            int c = num % 10;

            int[] used = new int[10];
            used[a]++;
            used[b]++;
            used[c]++;

            boolean possible = true;

            for (int i = 0; i < 10; i++) {
                if (used[i] > count[i]) {
                    possible = false;
                    break;
                }
            }

            if (possible) {
                ans++;
            }
        }

        return ans;
    }
}