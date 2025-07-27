
public class Connections {

    private static final int[][] CONNECTIONS = {
        {1, 0, 3, 2, 5, 4, 7, 6}, // Tile 0
        {3, 6, 5, 0, 7, 2, 1, 4}, // Tile 1
        {7, 4, 5, 6, 1, 2, 3, 0}, // Tile 2
        {5, 4, 3, 2, 1, 0, 7, 6}, // Tile 3
        {5, 4, 7, 6, 1, 0, 3, 2}, // Tile 4
        {7, 6, 5, 4, 3, 2, 1, 0}, // Tile 5
        {3, 2, 1, 0, 7, 6, 5, 4}, // Tile 6
        {1, 0, 3, 2, 5, 4, 7, 6}, // Tile 7 
        {3, 6, 5, 0, 7, 2, 1, 4}, // Tile 8 
        {1, 0, 7, 4, 3, 6, 5, 2}, // Tile 9
        {1, 0, 3, 2, 7, 6, 5, 4}, // Tile 10
        {5, 4, 7, 6, 1, 0, 3, 2}, // Tile 11 
        {5, 6, 7, 4, 3, 0, 1, 2}, // Tile 12
        {7, 4, 5, 6, 1, 2, 3, 0}, // Tile 13 
        {7, 4, 3, 2, 1, 6, 5, 0}, // Tile 14
        {3, 4, 7, 0, 1, 6, 5, 2}, // Tile 15
        {3, 4, 5, 0, 1, 2, 7, 6}, // Tile 16
        {5, 6, 3, 2, 7, 0, 1, 4}, // Tile 17
        {3, 4, 7, 0, 1, 6, 5, 2}, // Tile 18
        {3, 6, 7, 0, 5, 4, 1, 2}, // Tile 19
        {5, 2, 1, 6, 7, 0, 3, 4}, // Tile 20
        {5, 4, 7, 6, 1, 0, 3, 2}, // Tile 21
        {7, 6, 3, 2, 5, 4, 1, 0}, // Tile 22
        {5, 6, 3, 2, 7, 0, 1, 4}, // Tile 23
        {3, 4, 5, 0, 1, 2, 7, 6}, // Tile 24
        {3, 4, 7, 0, 1, 6, 5, 2}, // Tile 25
        {7, 2, 1, 6, 5, 4, 3, 0}, // Tile 26
        {1, 0, 5, 6, 7, 2, 3, 4}, // Tile 27
        {5, 2, 1, 6, 7, 0, 3, 4}, // Tile 28
        {5, 2, 1, 4, 3, 0, 7, 6}, // Tile 29
        {7, 6, 5, 4, 3, 2, 1, 0}, // Tile 30
        {5, 6, 7, 4, 3, 0, 1, 2}, // Tile 31
        {5, 2, 1, 4, 3, 0, 7, 6}, // Tile 32
        {3, 6, 7, 0, 5, 4, 1, 2}, // Tile 33
        {7, 6, 3, 2, 5, 4, 1, 0}, // Tile 34
        {1, 0, 7, 4, 3, 6, 5, 2}, // Tile 35
        {7, 2, 1, 4, 3, 6, 5, 0}, // Tile 36
        {3, 2, 1, 0, 5, 4, 7, 6}, // Tile 37
        {1, 0, 5, 4, 3, 2, 7, 6}, // Tile 38
        {1, 0, 7, 6, 5, 4, 3, 2}, // Tile 39
        {5, 4, 7, 6, 1, 0, 3, 2}, // Tile 40 
        {3, 2, 1, 0, 7, 6, 5, 4}, // Tile 41
        {1, 0, 5, 6, 7, 2, 3, 4}, // Tile 42
        {5, 6, 7, 4, 3, 0, 1, 2}, // Tile 43
        {3, 2, 1, 0, 7, 6, 5, 4}, // Tile 44
        {5, 2, 1, 6, 7, 0, 3, 4}, // Tile 45
        {5, 4, 3, 2, 1, 0, 7, 6}, // Tile 46
        {5, 6, 7, 4, 3, 0, 1, 2}, // Tile 47
        {1, 0, 7, 6, 5, 4, 3, 2}, // Tile 48
        {7, 4, 3, 2, 1, 6, 5, 0}, // Tile 49
        {3, 2, 1, 0, 5, 4, 7, 6}, // Tile 50
        {3, 4, 7, 0, 1, 6, 5, 2}, // Tile 51
        {5, 2, 1, 6, 7, 0, 3, 4}, // Tile 52
        {7, 4, 5, 6, 1, 2, 3, 0}, // Tile 53 
        {1, 0, 5, 4, 3, 2, 7, 6}, // Tile 54
        {7, 6, 5, 4, 3, 2, 1, 0}, // Tile 55 
        {7, 2, 1, 6, 5, 4, 3, 0}, // Tile 56
        {7, 2, 1, 4, 3, 6, 5, 0}, // Tile 57
        {1, 0, 3, 2, 7, 6, 5, 4}, // Tile 58
        {7, 4, 5, 6, 1, 2, 3, 0}, // Tile 59 
    };

    public static int getConnection(int tile, int point) {
        if (tile < 0 || tile > 59) {
            throw new RuntimeException("Incorrect tile index");
        }
        if (point < 0 || point > 7) {
            throw new RuntimeException("Incorrect point index");
        }
        return CONNECTIONS[tile][point];
    }

    public static int getXMovement(int point) {
        if (point < 0 || point > 7) {
            throw new RuntimeException("Incorrect point index");
        }
        if (point == 2 || point == 3) {
            return 1;
        }
        if (point == 6 || point == 7) {
            return -1;
        }
        return 0;
    }

    public static int getYMovement(int point) {
        if (point < 0 || point > 7) {
            throw new RuntimeException("Incorrect point index");
        }
        if (point == 4 || point == 5) {
            return 1;
        }
        if (point == 0 || point == 1) {
            return -1;
        }
        return 0;
    }
}
