
import processing.core.*;
import processing.event.MouseEvent;
import java.util.*;

public class Dungeon1 extends PApplet implements MyLibrary {
    static gameState currentState = gameState.Running;
    static cameraState currentCameraState = cameraState.playerCentered;
    combatState currentCombatState = combatState.OutCombat;
    ArrayList<EnemyCreation> enemies = new ArrayList<EnemyCreation>();
    enum gameState {
        Over, Running
    }
    enum combatState {
        InCombat, OutCombat
    }
    enum cameraState {
        freeRoam, playerCentered
    }
    int cameraScaling = 20;
    CreateStructures createStructures = new CreateStructures();
    EnemyCreation enemyCreation = new EnemyCreation();
    PImage testBoots;
    PImage clown;
    PImage testSquare;
    PImage stoneTile1;
    PImage stoneTile2;
    PImage[] resizedTile = new PImage[200];
    PImage warrior1;
    PImage attackIcon1;
    PImage defenseIcon1;
    float redSquare;
    public int moveToken = 0;
    int currentXPos = 10;
    int currentYPos = 10;
    int tileType;
    boolean movingUp, movingDown, movingLeft, movingRight;
    //boolean combatStarted = false;
    int squareX;
    int squareY;
    boolean Test = false;
    int cameraX = currentXPos;
    int cameraY = currentYPos;
    boolean centerCamera = false;
    int pastMouseXPosition;
    int pastMouseYPosition;
    int test = 0;
    boolean mouseDragging = true, freeRoam, playerCentered;

    public static void main(String[] args) {
        PApplet.main("Dungeon1");
    }

    public void settings() {
        //fullScreen();
        size(1920, 1080);
    }

    public void setup() {
        //enemyCreation.squarePriorityInitializer();
        playerStartPos();
        createMap();
        currentState = gameState.Running;
        testBoots = loadImage("Images/TestBoots.png");
        clown = loadImage("Images/clown.png");
        testSquare = loadImage("Images/testSquare.png");
        stoneTile1 = loadImage("Images/stoneTile1.png");
        warrior1 = loadImage("Images/warrior1.png");
        defenseIcon1 = loadImage("Images/defenseIcon1.png");
        attackIcon1 = loadImage("Images/attackIcon.png");
        for (int r = 15; r < 50; r++) {

            resizedTile[r] = loadImage("Images/resizedTile.png");
        }
    }
    public void draw() {
        background(0, 0, 0);
        drawMap();
        if (currentState == gameState.Running) {
            if (currentCombatState == combatState.InCombat) {

                drawPlayer();
                drawEnemy();
            } else if (currentCombatState == combatState.OutCombat) {
                //Attack button position
                if (mouseX > (1790) && mouseX < (1890) && mouseY > (45) && mouseY < (120)) {
                }
                //Defend button position
                else if (mouseX > (1790) && mouseX < (1890) && mouseY > (140) && mouseY < (220)) {
                }
                isMoving();
                moveEnemy();
                drawEnemy();
                drawAction();
            }
            // if (Test) {
            //      createStructures.createStructures();
            //  }
            //System.out.println(cameraScaling);
        }
    }
    public void imageSetup() {
    }

    public void keyPressed() {
        if (key == 'w') {
            movingUp = true;
        }
        if (key == 's') {
            movingDown = true;
        }
        if (key == 'a') {
            movingLeft = true;
        }
        if (key == 'd') {
            movingRight = true;
        }
        if (key == 'k') {
            Test = true;
        }
        if (key == 'c') {
            currentCameraState = cameraState.playerCentered;
            centerCamera = true;
        }
        if (key == 'v') {
                currentCameraState = cameraState.freeRoam;

        }
        if (key == 'b') {
                currentCameraState = cameraState.playerCentered;
        }
        if (key == 't') {
            // Wait function
            moveToken = 1;
        }
    }

    public void keyReleased() {
        if (key == 'w') {
            movingUp = false;
        }
        if (key == 's') {
            movingDown = false;
        }
        if (key == 'a') {
            movingLeft = false;
        }
        if (key == 'd') {
            movingRight = false;
        }
        if (key == 'k') {
            Test = false;
        }
    }


    public void mouseDragged(MouseEvent e) {

        if (pastMouseXPosition < mouseX) {
          cameraX--;
        }
        if (pastMouseXPosition > mouseX) {
          cameraX++;
        }
        if (pastMouseYPosition < mouseY) {
          cameraY--;
        }
        if (pastMouseYPosition > mouseY) {
          cameraY++;
        }
            pastMouseXPosition = mouseX;
            pastMouseYPosition = mouseY;
    }
    public void mousePressed() {
        currentCameraState = cameraState.freeRoam;
        mouseDragging = true;
            pastMouseXPosition = mouseX;
            pastMouseYPosition = mouseY;
    }
    public void mouseReleased() {
        mouseDragging = false;
    }

    public void mouseWheel(MouseEvent e) {
        if (e.getAmount() < 0) {

            cameraScaling++;
        } else {

            cameraScaling--;
        }
        if (cameraScaling > 50) {
            if (e.getAmount() < 0) {

                cameraScaling++;
            } else {

                cameraScaling--;
            }
        }
    }
    public void isMoving() {
        if (currentCameraState == cameraState.playerCentered) {
            if (movingUp && (squareType[currentXPos][currentYPos - 1] != 2 && squareType[currentXPos][currentYPos - 1] != 3) && moveToken == 0 && (currentYPos - 1 != enemyCreation.enemyY || currentXPos != enemyCreation.enemyX)) {
                currentYPos--;
                cameraY--;
                moveToken = 1;
            }
            if (movingDown && (squareType[currentXPos][currentYPos + 1] != 2 && squareType[currentXPos][currentYPos + 1] != 3) && moveToken == 0 && (currentYPos + 1 != enemyCreation.enemyY || currentXPos != enemyCreation.enemyX)) {
                currentYPos++;
                cameraY++;
                moveToken = 1;
            }
            if (movingRight && (squareType[currentXPos + 1][currentYPos] != 2 && squareType[currentXPos + 1][currentYPos] != 3) && moveToken == 0 && (currentXPos + 1 != enemyCreation.enemyX || currentYPos != enemyCreation.enemyY)) {
                currentXPos++;
                cameraX++;
                moveToken = 1;
            }
            if (movingLeft && (squareType[currentXPos - 1][currentYPos] != 2 && squareType[currentXPos - 1][currentYPos] != 3) && moveToken == 0 && (currentXPos - 1 != enemyCreation.enemyX || currentYPos != enemyCreation.enemyY)) {
                currentXPos--;
                cameraX--;
                moveToken = 1;
            }
        } else if (currentCameraState == cameraState.freeRoam) {
            if (movingUp && (squareType[currentXPos][currentYPos - 1] != 2 && squareType[currentXPos][currentYPos - 1] != 3) && moveToken == 0 && (currentYPos - 1 != enemyCreation.enemyY || currentXPos != enemyCreation.enemyX)) {
                currentYPos--;
                moveToken = 1;
            }
            if (movingDown && (squareType[currentXPos][currentYPos + 1] != 2 && squareType[currentXPos][currentYPos + 1] != 3) && moveToken == 0 && (currentYPos + 1 != enemyCreation.enemyY || currentXPos != enemyCreation.enemyX)) {
                currentYPos++;
                moveToken = 1;
            }
            if (movingRight && (squareType[currentXPos + 1][currentYPos] != 2 && squareType[currentXPos + 1][currentYPos] != 3) && moveToken == 0 && (currentXPos + 1 != enemyCreation.enemyX || currentYPos != enemyCreation.enemyY)) {
                currentXPos++;
                moveToken = 1;
            }
            if (movingLeft && (squareType[currentXPos - 1][currentYPos] != 2 && squareType[currentXPos - 1][currentYPos] != 3) && moveToken == 0 && (currentXPos - 1 != enemyCreation.enemyX || currentYPos != enemyCreation.enemyY)) {
                currentXPos--;
                moveToken = 1;
            }
        }
        drawPlayer();
    }
    public void playerStartPos() {
        currentXPos = 96/2;
        currentYPos = 54/2;
    }
    public void drawPlayer() {
            image(testSquare, (currentXPos * cameraScaling - cameraX * cameraScaling), (currentYPos * cameraScaling - cameraY * cameraScaling), cameraScaling, cameraScaling);
    }
    public void drawEnemy() {
            image(testSquare, (enemyCreation.enemyX * cameraScaling - cameraX * cameraScaling), (enemyCreation.enemyY * cameraScaling - cameraY * cameraScaling), cameraScaling, cameraScaling);
    }
    public void moveEnemy() {
        if (enemyCreation.enemyMove(currentXPos, currentYPos, moveToken) == 0) {
            moveToken = 0;
        }
    }
    public void createMap() {
        //First creates the plane to navigate
            for (int r = 0; r < mapHeight; r++) {
                for (int c = 0; c < mapWidth; c++) {
                    redSquare = generator.nextInt(100);

                    if (redSquare > 90) {
                        tileType = 2;
                    } else {
                        tileType = 0;
                    }
                    squareType[r][c] = tileType;
                }
            }
            //then create stuff after, or it gets overwritten
        createStructures.createStructures();
    }
    public void drawMap() {

        if (cameraScaling < 30) {
            cameraScaling = 30;
        }
        if (cameraScaling == 32) {
            cameraScaling++;
        }
        if (cameraScaling > 75) {
            cameraScaling = 75;
        }
        stoneTile1 = loadImage("Images/stoneTile1.png");
        stoneTile2 = loadImage("Images/stone_Tile2.png");
        if (centerCamera) {
            cameraX = currentXPos - 47*20/ cameraScaling;
            cameraY = currentYPos - 23*20/ cameraScaling;
        }
        centerCamera = false;

        for (int r = cameraX; r < cameraX +  96*20/cameraScaling + 4; r++) {
            for (int c = cameraY; c < cameraY + 54*20/ cameraScaling + 4; c++) {
                squareX = r * cameraScaling - cameraX * cameraScaling;
                squareY = c * cameraScaling - cameraY * cameraScaling;
                if (r > 0 && c > 0 && r < mapWidth && c < mapHeight) {

                    if (squareType[r][c] == 0) {
                        stoneTile2.resize(cameraScaling,cameraScaling);
                        image(stoneTile2, squareX, squareY);
                    } else {
                        colorTiles(r, c);
                        rect(squareX, squareY, cameraScaling, cameraScaling);
                    }
                }
            }
        }
    }


    public void colorTiles(int r, int c) {
        if (squareType[r][c] == 2) {
            fill(255, 0, 0);
        }
        if (squareType[r][c] == 3 || squareType[r][c] == 4) {
            fill(255, 255, 0);
        }
        if (squareType[r][c] == 5) {
            fill(255, 255, 255);
        }
        if (squareType[r][c] == 6) {
            fill(0, 255, 255);
        }
        if (squareType[r][c] == 7) {
            fill(255, 0, 255);
        }
    }

    public void drawAction() {

        if ((currentYPos - 1 == enemyCreation.enemyY && currentXPos == enemyCreation.enemyX) || (currentYPos + 1 == enemyCreation.enemyY && currentXPos == enemyCreation.enemyX) || (currentXPos - 1 == enemyCreation.enemyX && currentYPos == enemyCreation.enemyY) || (currentXPos + 1 == enemyCreation.enemyX && currentYPos == enemyCreation.enemyY)) {
            image(attackIcon1, 1770, 20, 128, 128);
            image(defenseIcon1, 1770, 120, 128, 128);
        }

    }

}