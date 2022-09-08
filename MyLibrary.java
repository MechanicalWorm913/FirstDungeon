import java.util.*;

public  interface MyLibrary {
    Random generator = new Random();
    /**
     *
     */


    int mapWidth = 100; //generator.nextInt(20,50);
    int mapHeight = 100;//generator.nextInt(20, 50);



    int[][] squareType = new int[mapHeight][mapWidth];
    int[][] squarePriority = new int[mapHeight][mapWidth];

}
