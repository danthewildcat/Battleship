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
        
        List<Integer> validVertical = new ArrayList<Integer>(gridLocations);
        toDelete.clear();
        // Delete any choices that would be too far down - this will be the 
        // bottom n rows where n=shipLength
        Integer maxVal = totalCells - shipLength*gridSize + 1;
        for (Integer i=maxVal;i<=totalCells;i++){
            toDelete.add(i);
        }
        validVertical.removeAll(toDelete);
        for (int i=0;i<shipCount; i++){
            Integer start = 0;
            // true orientation will mean horizontal
            Random rn = new Random();
            boolean orientation = rn.nextInt(2) == 0;
            int orientationCheck = 0;
            List<Integer> choices = new ArrayList<Integer>();
            int counter = 0;
            while (start == 0 && counter < totalCells*2){
                // Just going to keep trying random positions until we
                // find one that works...
                if (choices.size() == 0){
                    orientationCheck += 1;
                    if (orientation){
                        choices = new ArrayList<Integer>(validHorizontal);
                    } else {
                        choices = new ArrayList<Integer>(validVertical);
                    }
                }
                
                int choice = rn.nextInt(choices.size());
                Integer attempt = choices.remove(choice);
                boolean validAttempt = true;
                for (Ship ship:ships){
                    if (ship.onTarget(attempt)){
                        validAttempt = false;
                        break;
                    }
                }
                
                if (validAttempt){
                    start = attempt;
                }
            }
            
            if (start == 0){
                // Throw grid size exception
            } else {
                if (orientation){
                    validHorizontal.remove(start);
                } else {
                    validVertical.remove(start);
                }
                ships.add(new Ship(start, shipLength, orientation, gridSize));
            }
        }
    }
        
    public boolean checkHit(String location){
        // Location coordinate where Y values are letters, starting with A
        // and the x values are numbers starting at 0.
        // Location should be formatted as A0.
        
        return false;
    }
    
    private void renderGrid(){
        List<Integer> shipCells = new ArrayList<Integer>(gridSize*gridSize);
        List<Integer> hitCells = new ArrayList<Integer>(gridSize*gridSize);
        
        for (Ship ship:ships){
            shipCells.addAll(ship.getCells());
            hitCells.addAll(ship.getHits());
        }
        
        List<String> lines = new ArrayList<String>(gridSize);
        for (int i=0; i<gridSize; i++){
            lines.add("");
        }
        
        for (Integer i=1;i<=gridSize*gridSize; i++){
            String cell = " 0 ";
            Integer position = i;
            if (hitCells.remove(position)){
                cell = " H ";
            } else if (shipCells.remove(position)){
                cell = " S ";
            }
            int line = i/gridSize;
            if (i % gridSize == 0){
                line -= 1;
            }
            String oldLine = lines.get(line);
            lines.set(line, oldLine + cell);
        }
        
        for (String ln: lines){
            System.out.println(ln);
        }
    }
    
    public void play(){
        GameHelper helper = new GameHelper();
        renderGrid();
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
