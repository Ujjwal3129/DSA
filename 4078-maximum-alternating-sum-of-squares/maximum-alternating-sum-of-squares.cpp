class Solution {
public:
    long long maxAlternatingSum(vector<int>& nums) {
        vector<long long> squares;

        for (int num : nums) {
            squares.push_back(1LL * num * num);
        }

        sort(squares.begin(), squares.end(), greater<long long>());

        int n = squares.size();
        int positive = (n + 1) / 2;
        long long sum = 0;

        for (int i = 0; i < positive; i++) {
            sum += squares[i];
        }

        for (int i = positive; i < n; i++) {
            sum -= squares[i];
        }

        return sum;
    }
};