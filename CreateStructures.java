import java.util.*;
import processing.core.*;
public class CreateStructures implements MyLibrary {

    public void createStructures() {
        int failedStructureGeneration = 0;
        boolean firstStructure = true;

        for (int structureAmount = 0; structureAmount < 20; structureAmount++) {
            int startStructureWidth;
            int startStructureHeight;
            int testStartStructureWidth;
            int testStartStructureHeight;
            boolean canGenerateStructure, canGenerateDoorsLeft = true, canGenerateDoorsRight = true, canGenerateDoorsUp = true, canGenerateDoorsDown = true;


            if (failedStructureGeneration < 100) {

                int chance = generator.nextInt(0,1);
                int structureWidth = generator.nextInt(3, 10);
                int structureHeight = generator.nextInt(3, 10);
                int doorAmount = generator.nextInt(15,30);
                int doorsGenerated = 0;
                int doorGenerationChance = 50;
                startStructureWidth = generator.nextInt(10, mapWidth - 10);
                startStructureHeight = generator.nextInt(10, mapHeight - 10);
                testStartStructureHeight = startStructureHeight;
                testStartStructureWidth = startStructureWidth;
                canGenerateStructure = true;
                int maxDoorXGeneration;
                int maxDoorYGeneration;

                for (int r = 0; r < structureWidth; r++) {
                    testStartStructureWidth++;
                    if (testStartStructureHeight + structureHeight < mapHeight && testStartStructureWidth + structureWidth < mapWidth) {
                        //Top and Bottom Structure Walls
                        if (squareType[testStartStructureWidth][testStartStructureHeight] == 3 || squareType[testStartStructureWidth][testStartStructureHeight - structureHeight] == 3 || testStartStructureHeight - structureHeight < 0 || testStartStructureWidth - structureWidth < 0 || squareType[testStartStructureWidth][testStartStructureHeight] == 5 || squareType[testStartStructureWidth][testStartStructureHeight] == 4 || squareType[testStartStructureWidth][testStartStructureHeight - structureHeight] == 4 || squareType[testStartStructureWidth][testStartStructureHeight] == 6 || squareType[testStartStructureWidth][testStartStructureHeight - structureHeight] == 6) {
                            canGenerateStructure = false;
                        }
                    }
                }

                for (int c = 0; c < structureHeight; c++) {
                    testStartStructureHeight--;
                    if (testStartStructureHeight + structureHeight < mapHeight && testStartStructureWidth + structureWidth < mapWidth) {
                        //Left and Right Structure Walls
                        if (squareType[testStartStructureWidth][testStartStructureHeight] == 3 || squareType[testStartStructureWidth - structureWidth][testStartStructureHeight] == 3 || testStartStructureWidth - structureWidth < 0 || testStartStructureHeight - structureHeight < 0 || squareType[testStartStructureWidth][testStartStructureHeight] == 5 || squareType[testStartStructureWidth][testStartStructureHeight] == 4 || squareType[testStartStructureWidth - structureWidth][testStartStructureHeight] == 4 || squareType[testStartStructureWidth][testStartStructureHeight] == 6 || squareType[testStartStructureWidth - structureWidth][testStartStructureHeight] == 6) {
                            canGenerateStructure = false;
                        }
                    }
                }
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------//
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------//

                //Start of actual Generation of non-clipping walls
                if (canGenerateStructure) {
                    testStartStructureHeight = startStructureHeight;
                    testStartStructureWidth = startStructureWidth;
                    //Makes white insides, walls overwrite the outside
                    for (int r = startStructureWidth + 1; r < startStructureWidth + structureWidth; r++) {
                        for (int c = startStructureHeight - structureHeight; c < startStructureHeight; c++) {
                            squareType[r][c] = 5;
                        }
                    }

                    //canGenerateDoorsLeft = true; canGenerateDoorsRight = true; canGenerateDoorsUp = true; canGenerateDoorsDown = true;

                    //width-80 = left, width-20 = right, height-20 = bottom, height - 80 = top

                    if (testStartStructureWidth > mapWidth - 20) {
                        canGenerateDoorsRight = false;
                    }
                    if (testStartStructureWidth < mapWidth - 80) {
                        canGenerateDoorsLeft = false;
                    }
                    if (testStartStructureHeight > mapHeight - 20) {
                        canGenerateDoorsDown = false;
                    }
                    if (testStartStructureHeight < mapHeight - 80) {
                        canGenerateDoorsUp = false;
                    }
                    if (firstStructure) {
                        firstStructure = false;
                        maxDoorXGeneration = testStartStructureWidth;
                        maxDoorYGeneration = testStartStructureHeight;
                    }

                    //Corner cuz it never draws the bottom left corner for some reason
                    squareType[testStartStructureWidth][testStartStructureHeight] = 4;

                    //Top and Bottom Structure Walls
                    for (int r = 0; r < structureWidth; r++) {
                        startStructureWidth++;
                        //offSet++;

                        squareType[startStructureWidth][startStructureHeight] = 3;
                        squareType[startStructureWidth][startStructureHeight - structureHeight] = 3;
                        doorGenerationChance = generator.nextInt(0,100);
                        if (doorGenerationChance > 40 && doorsGenerated <= doorAmount && squareType[startStructureWidth][startStructureHeight] == 3 && squareType[startStructureWidth][startStructureHeight - structureHeight] == 3 && squareType[startStructureWidth-1][startStructureHeight] != 6 && squareType[startStructureWidth+1][startStructureHeight] != 6 && squareType[startStructureWidth-1][startStructureHeight-structureHeight] != 6 && squareType[startStructureWidth+1][startStructureHeight-structureHeight] != 6) {
                            //Bottom Doors
                            //if (chance > 0 && canGenerateDoorsDown) {
                            if (canGenerateDoorsDown && chance == 0) {
                                squareType[startStructureWidth][startStructureHeight] = 6;
                                //Top Doors
                            }
                            chance = generator.nextInt(0,2);
                            if (canGenerateDoorsUp && chance == 0){
                                squareType[startStructureWidth][startStructureHeight - structureHeight] = 6;
                            }
                            doorsGenerated++;
                            chance = generator.nextInt(0,2);
                        }

                    }
                    //Corner stuff so doors don't spawn there
                    squareType[startStructureWidth][startStructureHeight] = 4;
                    squareType[startStructureWidth][startStructureHeight - structureHeight] = 6;

                    for (int c = 0; c < structureHeight; c++) {
                        startStructureHeight--;
                        //offSet++;
                        //Left and Right Structure Walls
                        squareType[startStructureWidth][startStructureHeight] = 3;
                        squareType[startStructureWidth - structureWidth][startStructureHeight] = 3;
                        doorGenerationChance = generator.nextInt(0,100);
                        if (doorGenerationChance > 40 && doorsGenerated <= doorAmount && squareType[startStructureWidth][startStructureHeight] == 3 && squareType[startStructureWidth - structureWidth][startStructureHeight] == 3 && squareType[startStructureWidth][startStructureHeight-1] != 6 && squareType[startStructureWidth][startStructureHeight+1] != 6 && squareType[startStructureWidth-structureWidth][startStructureHeight-1] != 6 && squareType[startStructureWidth-structureWidth][startStructureHeight+1] != 6) {
                            //Right Doors
                            if (canGenerateDoorsRight && chance == 0) {
                                squareType[startStructureWidth][startStructureHeight] = 6;
                            }
                            //Left Doors
                            chance = generator.nextInt(0,2);
                            if (canGenerateDoorsLeft && chance == 0) {
                                squareType[startStructureWidth - structureWidth][startStructureHeight] = 6;
                            }
                            doorsGenerated++;
                            chance = generator.nextInt(0,2);
                        }
                    }
                    drawCornerCoordinates(testStartStructureWidth, testStartStructureHeight, structureWidth, structureHeight);
                    //squareType[startStructureWidth][startStructureHeight] = 4;
                    //squareType[startStructureWidth - structureWidth][startStructureHeight] = 4;

                    System.out.println(chance);
                } else {
                    structureAmount--;
                    failedStructureGeneration++;
                }
            } else {
                break;
            }
        }
    }
    public void drawCornerCoordinates(int testStartStructureWidth, int testStartStructureHeight, int structureWidth, int structureHeight) {
        //Top Left Corner
        if (mapWidth/2 > testStartStructureWidth && mapHeight/2 > testStartStructureHeight) {
            squareType[testStartStructureWidth][testStartStructureHeight-structureHeight] = 7;
            //canGenerateDoorsLeft = false;
            // canGenerateDoorsUp = false;
        }
        //Bottom Right Corner
        if (mapWidth/2 < testStartStructureWidth && mapHeight/2 < testStartStructureHeight) {
            squareType[testStartStructureWidth+structureWidth][testStartStructureHeight] = 7;
            // canGenerateDoorsDown = false;
            //canGenerateDoorsRight = false;
        }
        //Bottom Left Corner
        if (mapWidth/2 > testStartStructureWidth && mapHeight/2 < testStartStructureHeight) {
            squareType[testStartStructureWidth][testStartStructureHeight] = 7;
            //canGenerateDoorsDown = false;
            //canGenerateDoorsLeft = false;
        }
        //Top Right Corner
        if (mapWidth/2 < testStartStructureWidth && mapHeight/2 > testStartStructureHeight) {
            squareType[testStartStructureWidth+structureWidth][testStartStructureHeight-structureHeight] = 7;
            //canGenerateDoorsUp = false;
            //canGenerateDoorsRight = false;
        }
    }
    }

