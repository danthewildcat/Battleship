package com.augmatics.battleship;
import java.util.*;

public class Ship {
    private boolean orientation; // true is horizontal
    private boolean sunk = false;
    private int shipLength;
    private List<Integer> positions;
    private List<Integer> hits;
    
    public Ship(int start, int len, int gridSize, boolean orientation){
        this.shipLength = len;
        this.orientation = orientation;
        
        positions = new ArrayList<Integer>(len);
        if (orientation) {
            // Horizontal
            for (int i=0;i<len;i++){
                this.positions.add(start + i);
            }
        } else {
            // Vertical
            for (int i=0;i<len;i++){
                this.positions.add(start + gridSize);
            }      
        }
        
        // Clone the positions into the hits list
        hits = new ArrayList<Integer>(positions.size());
        Collections.copy(hits, positions);
    }
    
    public boolean onTarget(Integer location){
        return positions.contains(location);
    }
    
    public boolean applyHit(Integer location){
        return hits.remove(location);
    }
    
    public boolean isSunk(){
        if (sunk) return true;
        
        if (hits.isEmpty()){
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
