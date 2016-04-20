package com.augmatics.battleship;
import java.util.*;

public class Ship {
    private boolean orientation; // true is horizontal
    private boolean sunk = false;
    private int shipLength;
    private ArrayList<Integer> positions;
    private ArrayList<Integer> hits;
    
    public Ship(int start, int len, boolean orientation, int gridSize){
        shipLength = len;
        orientation = orientation;
        
        positions = new ArrayList<Integer>(len);
        if (orientation) {
            // Horizontal
            for (int i=0;i<len;i++){
                positions.add(start + i);
            }
        } else {
            // Vertical
            for (int i=0;i<len;i++){
                positions.add(start + gridSize*i);
            }      
        }
        
        // Clone the positions into the hits list
        hits = new ArrayList<Integer>(shipLength);
        
        System.out.println(orientation ? "horizontal":"vertical");
        System.out.println(positions);
    }
    
    public boolean onTarget(Integer location){
        return positions.contains(location);
    }
    
    public boolean applyHit(Integer location){
        if (hits.contains(location)){
            return false;
        } else {
            hits.add(location);
            return true;
        }
    }
    
    public ArrayList<Integer> getCells(){
        return positions;
    }
    
    public ArrayList<Integer> getHits(){
        return hits;
    }
    
    public boolean isSunk(){
        if (sunk) return true;
        
        if (hits.size() == shipLength){
            sunk = true;
        }
        
        return sunk;
    }
    
    public int getLength(){
        return shipLength;
    }
    
    public boolean isHorizontal(){
        return orientation;
    }
    
    public boolean isVertical(){
        return orientation == false;
    }
}
