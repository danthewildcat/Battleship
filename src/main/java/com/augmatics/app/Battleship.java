package com.augmatics.battleship;
import com.google.common.collect.*;
import java.util.*;

public class Battleship {
    private static int gridSize;
    private int shipCount = 1;
    private List<Ship> ships = new ArrayList<Ship>();
    
    public static void main(String[] args){
        GameHelper helper = new GameHelper();
        Battleship game = new Battleship();
        
        game.shipCount = Integer.parseInt(helper.getUserInput("How many ships?"));
        
        // Generate a grid that will contain all of the ships...
        int defaultShipLength = 3;
        game.arrangeShips(defaultShipLength);
        System.out.println(game.shipCount + " ships arranged on a " + game.gridSize + "x" + game.gridSize + " grid");
        game.play();
    }
    
    public int randInt(int min, int max){
        Random rn = new Random();
        int rand = rn.nextInt((max - min) + 1 ) + min;
        return rand;
    }
    
    private void arrangeShips(int shipLength){
        // Only need one measure for size since the grid will be square        
        gridSize = shipCount > shipLength ? shipCount+1:shipLength+1;
        
        // Make 5x5 the min grid size...
        if (gridSize < 5) gridSize = 5;
        
        int totalCells = gridSize * gridSize;
        
        List<Integer> gridLocations = new ArrayList<Integer>(totalCells+1);
        for (Integer i=1; i<=totalCells; i++){
            gridLocations.add(i);
        }
        
        List<Integer> validHorizontal = new ArrayList<Integer>(gridLocations);
        // Delete any choices that would be too far to the right
        List<Integer> toDelete = new ArrayList<Integer>(totalCells);
        for (Integer i:validHorizontal){
            if (i % gridSize + shipLength - 1 > gridSize){
                 toDelete.add(i);
            }
        }
        toDelete.add((Integer)25);
        validHorizontal.removeAll(toDelete);
        System.out.println(validHorizontal);
        
        List<Integer> validVertical = new ArrayList<Integer>(gridLocations);
        toDelete.clear();
        // Delete any choices that would be too far down - this will be the 
        // bottom n rows where n=shipLength
        Integer maxVal = totalCells - shipLength*gridSize + 1;
        for (Integer i=maxVal;i<=totalCells;i++){
            toDelete.add(i);
        }
        validVertical.removeAll(toDelete);
        System.out.println(validVertical);
        
        
        for (int i=0;i<shipCount; i++){
            // true orientation will mean horizontal
            boolean orientation = randInt(0,1) == 0;
            
            // Just going to keep trying random positions until we
            // find one that works...
            int guesses = 0;
        }
    }
        
    public boolean checkHit(String location){
        // Location coordinate where Y values are letters, starting with A
        // and the x values are numbers starting at 0.
        // Location should be formatted as A0.
        
        return false;
    }
    
    public void play(){
        GameHelper helper = new GameHelper();
        
        /*
        String ready = helper.getUserInput("Ready to play?(yes or no)");
        int count = 0;
        while (ready.equals("yes") == false && count < 5){
            ready = helper.getUserInput("How about now?(yes or no)");
            count += 1;
        }
        
        if (ready.equals("yes") == false){
            System.out.println("Exiting Game");
            return;
        }
        
        System.out.println("Woohoo!");*/
    }
}
