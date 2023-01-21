import com.sun.source.util.DocTreePathScanner;

import java.sql.Array;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.*;

public class Version2{
    public static void main(String[] main) {
        DotComsGame obj = new DotComsGame();
        obj.setGrid();
        obj.startGame();
    }
}
class pair{
    public int x;
    public int y;
    public pair(int x, int y){
        this.x = x;
        this.y = y;
    }
}
class dotCom{
    private String domain;
    private pair loc;
    private int noVisited;

    private boolean[] isVis;

    public dotCom(){
        Scanner sc = new Scanner(System.in);
        System.out.println("\nPlease Enter Domain names : ");
        this.domain = sc.nextLine();
        int s = DotComsGame.len;
        Random r = new Random();
        this.loc = new pair(r.nextInt(0,s), r.nextInt(0,s-2));
        this.setNoVisited(3);
        this.isVis = new boolean[3];
    }

    public void setNoVisited(int t){
        this.noVisited = t;
    }

    public String getDomain(){
        return this.domain;
    }

    public boolean[] getIsVis(){
        return this.isVis;
    }
    public pair getLoc(){
        return this.loc;
    }
    public int getNoVisited(){
        return this.noVisited;
    }
}

class DotComsGame{
     private int[][] grid;
     public static final int len;
     private static float noGuess;
     private ArrayList<dotCom> dotComs;

     static{
         Scanner sc = new Scanner(System.in);
         int s = 0;
         while(s<3) {
             System.out.println("Please Enter length of Grid (Minimum length should be 3): ");
             s = sc.nextInt();
         }
         len = s;
     }

     public DotComsGame(){
         this.grid = new int[len][len];
     }

     public int getLen(){
         return this.len;
     }
     public int[][] getGrid(){
         return this.grid;
     }
     public void setLocations(pair loc) {
         int x = loc.x;
         int y = loc.y;
         this.grid[x][y]++;
         this.grid[x][y+1]++;
         this.grid[x][y+2]++;
     }

     public boolean checkGuess(String inp) {
         int x = (int)inp.charAt(0)-(int)'A';
         int y = (int)inp.charAt(1) -(int)'0';
         if(inp.length()==2 && x<len && x>=0 && y<len && y>=0){
             if(this.grid[x][y]>0) {
                 this.grid[x][y]=0;
                 return true;
             }
         }
         return false;
     }

     public void setGrid(){
         Scanner sc = new Scanner(System.in);
         System.out.println("\nEnter Total Number of dotcoms you want to put in grid: ");
         int n = sc.nextInt();
         this.dotComs = new ArrayList<dotCom>(n);
         for(int i=0; i<n; i++){
             dotCom obj = new dotCom();
             this.setLocations(obj.getLoc());
             this.dotComs.add(obj);
         }
     }

     public void checkDotCom(String inp){

         int x = (int)inp.charAt(0)-(int)'A';
         int y = (int)inp.charAt(1)-(int)'0';
         for(dotCom temp: this.dotComs){
             int x_ = temp.getLoc().x;
             int y_ = temp.getLoc().y;
             if(x_==x && y>=y_ && y<=(y_+2)) {
                 if(!temp.getIsVis()[y-y_]){
                     temp.getIsVis()[y-y_]=true;
                     temp.setNoVisited(temp.getNoVisited()-1);
                 }
             }
         }

         Iterator<dotCom> itr = this.dotComs.iterator();
         while(itr.hasNext()){
             dotCom temp = itr.next();
             if (temp.getNoVisited() == 0) {
                 System.out.println("Kill," + temp.getDomain() + ".com");
                 itr.remove();
             }
         }
     }
     public void startGame() {
         System.out.println("\nLet's Start the game.....");
         float noHits = 0;
         while(true)
         {
             noGuess++;
             Scanner sc = new Scanner(System.in);
             int s = DotComsGame.len;
             char RowLim = (char)('A'+(s-1));
             char ColLim = (char)('0'+(s-3));
             System.out.println("\nEnter Your Guess: (Note: Format([A-" + RowLim+ "][0-" + ColLim + "])");
             String loc = sc.nextLine();
             System.out.println("The Guess is " + loc);
             String result = "Miss";

             if(this.checkGuess(loc)){
                result = "Hit";
                noHits++;
                this.checkDotCom(loc);
             }

             System.out.println(result);

             if(this.dotComs.isEmpty()){
                 System.out.println("Congratulations!!  You Finished the game.");
                 System.out.println("Your rating is "+ (noHits/noGuess));
                 return;
             }
         }
     }
}
