class Solution {
    int n, k;
    long[][] tree;
    int[] product;

    void merge(int node, int left, int right) {
        for (int i = 0; i < k; i++) {
            tree[node][i] = tree[left][i];
        }

        for (int i = 0; i < k; i++) {
            int rem = (product[left] * i) % k;
            tree[node][rem] += tree[right][i];
        }

        product[node] = (product[left] * product[right]) % k;
    }

    void build(int node, int l, int r, int[] nums) {
        if (l == r) {
            int rem = nums[l] % k;
            tree[node][rem] = 1;
            product[node] = rem;
            return;
        }

        int mid = (l + r) / 2;

        build(node * 2, l, mid, nums);
        build(node * 2 + 1, mid + 1, r, nums);

        merge(node, node * 2, node * 2 + 1);
    }

    void update(int node, int l, int r, int idx, int value) {
        if (l == r) {
            for (int i = 0; i < k; i++) {
                tree[node][i] = 0;
            }

            int rem = value % k;
            tree[node][rem] = 1;
            product[node] = rem;
            return;
        }

        int mid = (l + r) / 2;

        if (idx <= mid) {
            update(node * 2, l, mid, idx, value);
        } else {
            update(node * 2 + 1, mid + 1, r, idx, value);
        }

        merge(node, node * 2, node * 2 + 1);
    }

    long[] query(int node, int l, int r, int ql, int qr) {
        if (ql <= l && r <= qr) {
            long[] res = tree[node].clone();
            return res;
        }

        int mid = (l + r) / 2;

        if (qr <= mid) {
            return query(node * 2, l, mid, ql, qr);
        }

        if (ql > mid) {
            return query(node * 2 + 1, mid + 1, r, ql, qr);
        }

        long[] left = query(node * 2, l, mid, ql, qr);
        long[] right = query(node * 2 + 1, mid + 1, r, ql, qr);

        int leftProduct = getProduct(node, l, r, ql, Math.min(qr, mid));

        long[] res = new long[k];

        for (int i = 0; i < k; i++) {
            res[i] = left[i];
        }

        for (int i = 0; i < k; i++) {
            int rem = (leftProduct * i) % k;
            res[rem] += right[i];
        }

        return res;
    }

    int getProduct(int node, int l, int r, int ql, int qr) {
        if (ql > qr) {
            return 1 % k;
        }

        if (ql <= l && r <= qr) {
            return product[node];
        }

        int mid = (l + r) / 2;

        if (qr <= mid) {
            return getProduct(node * 2, l, mid, ql, qr);
        }

        if (ql > mid) {
            return getProduct(node * 2 + 1, mid + 1, r, ql, qr);
        }

        int left = getProduct(node * 2, l, mid, ql, qr);
        int right = getProduct(node * 2 + 1, mid + 1, r, ql, qr);

        return (left * right) % k;
    }

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.n = nums.length;
        this.k = k;

        tree = new long[4 * n][k];
        product = new int[4 * n];

        build(1, 0, n - 1, nums);

        int[] result = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int index = queries[i][0];
            int value = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];

            update(1, 0, n - 1, index, value);

            long[] ans = query(1, 0, n - 1, start, n - 1);

            result[i] = (int) ans[x];
        }

        return result;
    }
}