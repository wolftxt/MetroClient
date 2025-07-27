
public class LegalMoves {

    public static boolean isMoveLegal(int[] inventory, int x, int y, int[][] plan) {
        if (plan[x][y] != -1) {
            return false;
        }
        if (x >= 3 && x <= 4 && y >= 3 && y <= 4) {
            return false;
        }
        if (getUsableCard(inventory) == -1) {
            return false;
        }
        boolean[][] isLegal = new boolean[8][8];
        boolean[][] copy = null;
        for (int i = 0; i < isLegal.length; i++) {
            for (int j = 0; j < isLegal[0].length; j++) {
                if (i == 0 || i == 7 || j == 0 || j == 7) {
                    isLegal[i][j] = true;
                }
                for (int k = -1; k <= 1; k++) {
                    for (int l = -1; l <= 1; l++) {
                        if (i + k < 0 || i + k > 7 || j + l < 0 || j + l > 7) {
                            continue;
                        }
                        if (plan[i][j] == -1) {
                            continue;
                        }
                        if (plan[i + k][j + l] != -1) {
                            continue;
                        }
                        if (k != 0 && l != 0) {
                            continue;
                        }
                        isLegal[i + k][j + l] = true;
                    }
                }
            }
        }
        for (int i = 0; i < isLegal.length; i++) {
            for (int j = 0; j < isLegal[0].length; j++) {
                if (plan[i][j] != -1) {
                    isLegal[i][j] = false;
                }
                if (i >= 3 && i <= 4 && j >= 3 && j <= 4) {
                    isLegal[i][j] = false;
                }
            }
        }
        copy = DeepCopy.deepCopy(isLegal);
        for (int i = 0; i < isLegal.length; i++) {
            for (int j = 0; j < isLegal[0].length; j++) {
                if (oneSquare(i, j, getUsableCard(inventory), plan)) {
                    isLegal[i][j] = false;
                }
            }
        }

        boolean foundLegalMove = false;
        for (int i = 0; i < isLegal.length; i++) {
            for (int j = 0; j < isLegal[0].length; j++) {
                if (isLegal[i][j]) {
                    foundLegalMove = true;
                }
            }
        }
        if (foundLegalMove) {
            return isLegal[x][y];
        } else {
            return copy[x][y];
        }
    }

    private static boolean oneSquare(int x, int y, int card, int[][] plan) {
        for (int i = 0; i < 8; i++) {
            int connection = Connections.getConnection(card, i);
            int xMove1 = Connections.getXMovement(connection);
            int yMove1 = Connections.getYMovement(connection);

            int xMove2 = Connections.getXMovement(i);
            int yMove2 = Connections.getYMovement(i);

            if (isOutOfPlan(x + xMove1, y + yMove1, plan) && isOutOfPlan(x + xMove2, y + yMove2, plan)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isOutOfPlan(int x, int y, int[][] plan) {
        try {
            int a = plan[x][y];
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    private static int getUsableCard(int[] inventory) {
        if (inventory[1] != -1) {
            return inventory[1];
        }
        return inventory[0];
    }
}
