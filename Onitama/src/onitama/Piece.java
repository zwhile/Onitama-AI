/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onitama;

import java.util.Arrays;

/**
 *
 * @author Zack
 */
public class Piece {
    
    private int[] coords = new int[2];
    private char type;
    private char ind;

    public Piece(int i, int j, char side, char num) {
        coords[0] = i;
        coords[1] = j;
        type = side;
        ind = num;
    }   
    
    public Piece(Piece a) {
        coords = Arrays.copyOf(a.coords, a.coords.length);
        type = a.getType();
        ind = a.getInd();
    }
    
    private int[] getCoordsArray() {
        return coords;
    }
    
    public int getCoords(int i) {
        return coords[i];
    }
    
    public char getType() {
        return type;
    }
    
    public char getInd() {
        return ind;
    }
    
    public String getPiece() {
        return Character.toString(type)+Character.toString(ind);
    }
    
    public char getOwner() {
        return Character.toLowerCase(type);
    }
    
    public void become(String diff) {
        type = diff.charAt(0);
        ind = diff.charAt(1);
    }
    
}
