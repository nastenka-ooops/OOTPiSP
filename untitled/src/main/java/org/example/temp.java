/*
package org.example;

import java.util.*;

public class temp {
}
*/
/*      Пошел как-то лесник в лес по грибы, да не в абы какой лес! В клетке либо
        трава зеленая, либо грибочки белые, либо кусты кусачие. Кусачие кусты,
        разумеется, непроходимые. Трава зеленая скучная, а грибочки белые, разумеется,
        по-настоящему интересные.

        Лес можно представить в виде клетчатой таблицы размера п х 3. Свою дорогу лесник
        начинает в любой из трех клеток первой строки. После чего каждый раз он может
        переместиться на следующую строку в соседнюю по углу или стороне клетку, если
        такая существуют и там не кусты кусачие. Более формально, находясь в клетке (i, j)
        он может переместиться в одну из трех доступных для прохода клеток (і + 1, j - 1),
        (i + 1, j) и (і + 1, j + 1), если они существуют и там нет кустов.

        Леснику, конечно же, интересны грибочки белые, поэтому он хочет знать, какое
        максимальное их количество он может посетить за прогулку. Если лесник упирается
        в клетку, из которой никуда не может пойти, он заканчивает свою прогулку.

        Формат входных данных

        В первой строке задано число п - количество строк в лесу (1 ≤ n ≤ 104).
        В следующих и строках дано по три символа, характеризующих данную строку.
        Каждый символ равен «.», если в клетке только трава зеленая, «С», если в этой
        клетке растут грибочки белые, и «W», если кусты кусачие. Если в первой строке во
        всех клетках находятся кусты, прогулка лесника заканчивается, не успев начаться.

        Формат выходных данных

        Выведите одно число такую прогулку. наибольшее количество грибов, которые
        лесник сможет собрать за одну*//*


package org.example;

        import java.util.*;

public class Main {
    public static class Point {
        private int X;
        private int Y;
        private char value;
        private int dist;
        private boolean isGrass = false;
        private boolean isMushroom = false;
        private boolean isBush = false;

        public Point(int x, int y, char value, int dist) {
            X = x;
            Y = y;
            this.value = value;
            this.dist = dist;

            if (value == '.') {
                this.isGrass=true;
            }
            if (value == 'C') {
                this.isMushroom=true;
            }
            if (value == 'W') {
                this.isBush=true;
            }
        }


        public int getX() {
            return X;
        }


        public int getY() {
            return Y;
        }


        public char getValue() {
            return value;
        }

        public void setX(int x) {
            X = x;
        }

        public void setY(int y) {
            Y = y;
        }

        public void setValue(char value) {
            this.value = value;
        }

        public int getDist() {
            return dist;
        }

        public void setDist(int dist) {
            this.dist = dist;
        }

        public boolean isGrass() {
            return isGrass;
        }

        public void setGrass(boolean grass) {
            isGrass = grass;
        }

        public boolean isMushroom() {
            return isMushroom;
        }

        public void setMushroom(boolean mushroom) {
            isMushroom = mushroom;
        }

        public boolean isBush() {
            return isBush;
        }

        public void setBush(boolean bush) {
            isBush = bush;
        }
    }

    static Point[][] forest;
    static int n;
    static final int m = 3;
    static Point start;
    static final int[] col = {-1, 0, 1};
    static final int[] row = {1, 1, 1};

    private static boolean isValid(int row, int col) {
        return (row >= 0) && (row < n) && (col >= 0) && (col < m)
                && !forest[row][col].isBush();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = Integer.parseInt(scanner.nextLine());
        forest = new Point[n][m];

        for (int i = 0; i < n; i++) {
            String temp = scanner.nextLine();
            for (int j = 0; j < m; j++) {
                forest[i][j] = new Point(i, j, temp.charAt(j), 0);
            }
        }
        ArrayList<Integer> results = new ArrayList<>();
        results.add(-1);
        for (int i = 0; i < m; i++) {

            Queue<Point> q = new ArrayDeque<>();

            start = new Point(0, i, forest[0][i].getValue(), 0);

            if (isValid(start.getX(), start.getY())) {
                q.add(new Point(start.getX(), start.getY(), start.getValue(), 0));
            }

            while (!q.isEmpty()) {
                Point point = q.poll();

                int tempX = point.getX();
                int tempY = point.getY();
                int dist = point.dist;

                if (tempX == n-1) {
                    results.add(dist);
                }

                for (int k = 0; k < 3; k++) {
                    if (isValid(tempX + row[k], tempY + col[k])) {
                        if (forest[tempX + row[k]][tempY + col[k]].isMushroom()){
                            q.add(new Point(tempX + row[k], tempY + col[k],
                                    forest[tempX + row[k]][tempY + col[k]].getValue(), dist+1));
                        } else {
                            q.add(new Point(tempX + row[k], tempY + col[k],
                                    forest[tempX + row[k]][tempY + col[k]].getValue(), dist));
                        }
                    }
                }
            }
        }
        results.sort(Comparator.reverseOrder());
        System.out.println(results.get(0));
    }
}
        */
/*int amount;
        int[] marks;
        Scanner scanner = new Scanner(System.in);
        amount = scanner.nextInt();
        marks = new int[amount];
        for (int i = 0; i < amount; i++) {
            marks[i] = scanner.nextInt();
        }

        int max5 = -1;
        for (int i = 0; i < marks.length - 6; i++) {
            int temp5 = 0;
            for (int j = 0; j < 7; j++) {
                if (marks[i + j] == 2 || marks[i + j] == 3) {
                    temp5 = -1;
                    break;
                }
                if (marks[i + j] == 5) {
                    temp5++;
                }
            }
            if (temp5 > max5) {
                max5 = temp5;
            }
        }
        System.out.println(max5);
    }*//*

    */
/*public static class FileNode {
        private String value;
        private Map<String, FileNode> children;

        public String getValue() {
            return value;
        }

        public FileNode(String value) {
            this.value = value;
            this.children = new HashMap<>();
        }

        public void addChild(FileNode child) {
            children.put(child.getValue(), child);
        }

        public Set<String> getChildrenNames() {
            return children.keySet();
        }

        public FileNode getChild(String name) {
            return children.get(name);
        }
    }

    static int amount;
    static String[] directories;
    static FileNode root;

    static int index;


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        amount = Integer.parseInt(scanner.nextLine());
        directories = new String[amount];
        for (int i = 0; i < amount; i++) {
            directories[i] = scanner.nextLine();
        }


        for (int i = 0; i < amount; i++) {
            index = 0;
            String[] folders = directories[i].split("/");
                addDirectories(folders);
        }

        dfs(root,0);

    }

    private static void addDirectories(String[] path) {
        if (root==null){
            root = new FileNode(path[0]);
        }
        FileNode curr = root;
        for (int i = 1; i < path.length; i++) {
            String directory = path[i];
            FileNode child = curr.getChild(directory);
            if (child == null) {
                child = new FileNode(directory);
                curr.addChild(child);
            }
            curr = child;
        }
    }
    private static void dfs(FileNode root, int level){
        StringBuilder out = new StringBuilder();
        for (int i = 0; i <level ; i++) {
            out.append("  ");
        }
        System.out.println(out+root.getValue());
        for (String subdirectory :
                root.getChildrenNames()) {
            dfs(root.getChild(subdirectory), level+1);
        }
    }*//*


*/
/*private void dfs(TreeNode node, int level) {
            StringBuilder indent = new StringBuilder();
            for (int i = 0; i < level; i++) {
                indent.append("  ");
            }
            System.out.println(indent + node.name);
            for (String childName : node.getChildrenNames()) {
                dfs(node.getChild(childName), level + 1);
            }
        }*//*



    */
/*static int amount;
    static int[] marks;

    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        amount = scanner.nextInt();
        marks = new int[amount];
        for (int i = 0; i < amount; i++) {
            marks[i] = scanner.nextInt();
        }

        int max5 = -1;
        for (int i = 0; i < marks.length - 6; i++) {
            int temp5 = 0;
            for (int j = 0; j < 7; j++) {
                if (marks[i + j] == 2 || marks[i + j] == 3) {
                    temp5 = -1;
                    break;
                }
                if (marks[i + j] == 5) {
                    temp5++;
                }
            }
            if (temp5 > max5) {
                max5 = temp5;
            }
        }
        System.out.println(max5);
    }*/