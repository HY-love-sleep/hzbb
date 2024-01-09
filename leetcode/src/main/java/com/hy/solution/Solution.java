package com.hy.solution;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import com.hy.common.Node;
class Solution {
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new ArrayList<>();

        if (root == null) {
            return res;
        }
        Deque<Node> que = new LinkedList<>();
        que.offer(root);
        while (!que.isEmpty()) {
            int size = que.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                Node current = que.poll();
                assert current != null;
                level.add(current.val);
                for (Node child : current.children) {
                    que.offer(child);
                }
            }
            res.add(level);
        }
        return res;
    }
}