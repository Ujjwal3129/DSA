class Solution {
    Set<String> result = new HashSet<>();

    public List<String> removeInvalidParentheses(String s) {
        int leftRemove = 0;
        int rightRemove = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                leftRemove++;
            } else if (c == ')') {
                if (leftRemove > 0) {
                    leftRemove--;
                } else {
                    rightRemove++;
                }
            }
        }

        dfs(s, 0, 0, leftRemove, rightRemove, new StringBuilder());

        return new ArrayList<>(result);
    }

    private void dfs(String s, int index, int balance,
                     int leftRemove, int rightRemove,
                     StringBuilder current) {

        if (balance < 0) return;

        if (index == s.length()) {
            if (balance == 0 && leftRemove == 0 && rightRemove == 0) {
                result.add(current.toString());
            }
            return;
        }

        char c = s.charAt(index);

        if (c == '(') {
            if (leftRemove > 0) {
                dfs(s, index + 1, balance,
                    leftRemove - 1, rightRemove, current);
            }

            current.append(c);
            dfs(s, index + 1, balance + 1,
                leftRemove, rightRemove, current);
            current.deleteCharAt(current.length() - 1);

        } else if (c == ')') {
            if (rightRemove > 0) {
                dfs(s, index + 1, balance,
                    leftRemove, rightRemove - 1, current);
            }

            if (balance > 0) {
                current.append(c);
                dfs(s, index + 1, balance - 1,
                    leftRemove, rightRemove, current);
                current.deleteCharAt(current.length() - 1);
            }

        } else {
            current.append(c);
            dfs(s, index + 1, balance,
                leftRemove, rightRemove, current);
            current.deleteCharAt(current.length() - 1);
        }
    }
}