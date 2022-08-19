package leetcode;

import java.util.List;

/**
 * Simulate skip list
 */
public class N1206_h {

    class Skiplist {
        SkipNode start;
        SkipNode end;
        int length;
        int level;

        class SkipNodeLevel {
            SkipNode nextNode;
            int skip;
        }

        class SkipNode {
            Object obj;
            int score;
            List<SkipNodeLevel> level;
        }

        public Skiplist() {
            this.start = new SkipNode();
            this.end = new SkipNode();
            this.length = 0;
            this.level = 0;
        }

        public boolean search(int target) {
            // todo
            return false;
        }

        public void add(int num) {

        }

        public boolean erase(int num) {
            return false;
        }
    }

}
