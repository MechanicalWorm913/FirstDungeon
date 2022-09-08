import static processing.core.PApplet.*;

public class EnemyCreation implements MyLibrary {

    public int enemyX = 10;
    public int enemyY = 10;
    int moveToken;



int timeOffset = 2;
private boolean canMoveUp,canMoveDown,canMoveLeft,canMoveRight,movingUp,movingDown,movingLeft,movingRight, movedUp, movedDown, movedLeft, movedRight;
private int failedMoveAttempt = 0,moveUpPriority = 0, moveDownPriority = 0, moveLeftPriority = 0, moveRightPriority = 0;

    public void squarePriorityInitializer() {
        for (int r = 0; r < mapWidth; r++) {
            for (int c = 0; c < mapHeight; c++) {
                squarePriority[r][c] = 0;
            }
        }
    }


    public int enemyMove(int currentXPos, int currentYPos, int movementToken) {

        //float timePassed = second();


            while (movementToken == 1) {
                if (currentXPos > enemyX) {
                    movingRight = true;
                }
                if (currentXPos < enemyX) {
                    movingLeft = true;
                }
                if (currentYPos > enemyY) {
                    movingDown = true;
                }
                if (currentYPos < enemyY) {
                    movingUp = true;
                }

                if (movementToken == 1 && movingUp && squareType[enemyX][enemyY-1] != 2 && (enemyY-1 != currentYPos || enemyX != currentXPos)) {
                    enemyY--;
                    movementToken = 0;
                    movingUp = false;
                }
                if (movementToken == 1 && movingDown && squareType[enemyX][enemyY+1] != 2 && (enemyY+1 != currentYPos || enemyX != currentXPos)) {
                    enemyY++;
                    movementToken = 0;
                    movingDown = false;
                }
                if (movementToken == 1 && movingRight && squareType[enemyX+1][enemyY] != 2 && (enemyX+1 != currentXPos || enemyY != currentYPos)) {
                    enemyX++;
                    movementToken = 0;
                    movingRight = false;
                }
                if (movementToken == 1 && movingLeft && squareType[enemyX-1][enemyY] != 2 && (enemyX-1 != currentXPos || enemyY != currentYPos)) {
                    enemyX--;
                    movementToken = 0;
                    movingLeft = false;
                }
                if (movementToken == 1) {
                    failedMoveAttempt++;
                }
                if (failedMoveAttempt > 3) {
                    movementToken = 0;
                }
            }
            return movementToken;
    }
        /* else while (failedMoveAttempt > 0) {

            if (movingUp) {
                if (moveLeftPriority > 0) {
                    enemyX--;
                    failedMoveAttempt--;
                    if (squareType[enemyX][enemyY] == 2) {
                        enemyX++;
                        moveLeftPriority--;
                        failedMoveAttempt++;
                    }
                }
                if (moveRightPriority > moveLeftPriority) {
                    enemyX++;
                    failedMoveAttempt--;
                    if (squareType[enemyX][enemyY] == 2) {
                        enemyX--;
                        moveRightPriority--;
                        failedMoveAttempt++;
                    }
                }
            }
            if (movingDown) {
                if (moveRightPriority > 0) {
                    enemyX++;
                    failedMoveAttempt--;
                    if (squareType[enemyX][enemyY] == 2) {
                        enemyX--;
                        moveRightPriority--;
                        failedMoveAttempt++;
                    }
                }
                if (moveLeftPriority > moveRightPriority) {
                    enemyX--;
                    failedMoveAttempt--;
                    if (squareType[enemyX][enemyY] == 2) {
                        enemyX++;
                        moveLeftPriority--;
                        failedMoveAttempt++;
                    }
                }
            }
            if (movingRight) {
                if (moveDownPriority > 0) {
                    enemyY++;
                    failedMoveAttempt--;
                    movedDown = true;
                    if (squareType[enemyX][enemyY] == 2) {
                        enemyY--;
                        failedMoveAttempt++;
                        moveDownPriority--;
                        movedDown = false;
                    }
                    if (movedDown) {
                        squarePriority[enemyX][enemyY++] = 1;
                    }
                }
                if (moveUpPriority > moveDownPriority) {
                    enemyY--;
                    failedMoveAttempt--;
                    movedUp = true;
                    if (squareType[enemyX][enemyY] == 2) {
                        enemyY++;
                        failedMoveAttempt++;
                        moveUpPriority--;
                        movedUp = false;
                    }
                    if (movedUp) {
                        squarePriority[enemyX][enemyY--] = 1;
                    }
                }
            }
            if (movingLeft) {
                if (moveUpPriority > 0) {
                    enemyY--;
                    failedMoveAttempt--;
                    if (squareType[enemyX][enemyY] == 2) {
                        enemyY++;
                        failedMoveAttempt++;
                        moveUpPriority--;
                    }
                }
                if (moveDownPriority > moveUpPriority) {
                    enemyY++;
                    failedMoveAttempt--;
                    if (squareType[enemyX][enemyY] == 2) {
                        enemyY--;
                        moveDownPriority--;
                        failedMoveAttempt++;
                    }
                }
            }
            movedUp = false;
            movedDown = false;
            movedRight = false;
            movedLeft = false;
        }*/
    }


