import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class KillAllAndReturnHome {

    static class Position {
        int x, y;

        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "(" + x + "," + y + ")";
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Position position = (Position) obj;
            return x == position.x && y == position.y;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Position> soldiers = new ArrayList<>();

        System.out.println("Enter number of soldiers: ");
        int numSoldiers = scanner.nextInt();

        for (int i = 1; i <= numSoldiers; i++) {
            System.out.println("Enter coordinates for soldier " + i + ": ");
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            soldiers.add(new Position(x, y));
        }

        System.out.println("Enter the coordinates for your “special” castle: ");
        int startX = scanner.nextInt();
        int startY = scanner.nextInt();
        Position castle = new Position(startX, startY);

        List<List<Position>> uniquePaths = findPaths(castle, soldiers);

        System.out.println("Thanks. There are " + uniquePaths.size() + " unique paths for your ‘special_castle’");

        for (int i = 0; i < uniquePaths.size(); i++) {
            System.out.println("Path " + (i + 1) + ":");
            List<Position> path = uniquePaths.get(i);
            for (Position pos : path) {
                System.out.print(pos + " ");
            }
            System.out.println();
        }
    }

    private static List<List<Position>> findPaths(Position castle, List<Position> soldiers) {
        List<List<Position>> uniquePaths = new ArrayList<>();
        List<Position> currentPath = new ArrayList<>();
        currentPath.add(castle); // Start from the castle position
        findPathsHelper(castle, soldiers, currentPath, uniquePaths);
        return uniquePaths;
    }

    private static void findPathsHelper(Position castle, List<Position> soldiers, List<Position> currentPath, List<List<Position>> uniquePaths) {
        if (soldiers.isEmpty()) {
            if (castle.equals(currentPath.get(0))) {
                uniquePaths.add(new ArrayList<>(currentPath));
            }
            return;
        }

        for (Position soldier : soldiers) {
            List<Position> remainingSoldiers = new ArrayList<>(soldiers);
            remainingSoldiers.remove(soldier);
            currentPath.add(soldier);
            Position newCastle = new Position(soldier.x, soldier.y);
            findPathsHelper(newCastle, remainingSoldiers, currentPath, uniquePaths);
            currentPath.remove(currentPath.size() - 1);
        }
    }
}
