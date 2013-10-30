package com.hornmicro.problem;

public class AreaProblem {
	
	// ref http://blog.csdn.net/arbuckle/article/details/710988
	// http://stackoverflow.com/questions/3806520/finding-maximum-size-sub-matrix-of-all-1s-in-a-matrix-having-1s-and-0s
    static int maxOriginX = 0;
    static int maxOriginY = 0;
    
    public static int maxArea(int[][] area) {
        int maxArea = 0;

        for (int y = 0; y < 6; y++) {
            for (int x = 0; x < 14; x++) {
                 maxArea = traverseRight(x, y, 1, 14, area[x][y], maxArea, area);
            }
        }
        return maxArea;
    }
    
    public static int traverseRight(int startx, int y, int level, int max, int val, 
            int maxArea, int[][] area) {
        int x = startx;
        for(;x < max && area[x][y] == val; x++) {
            // loop
        }
        int localMax = (x - startx) * level;
        if (localMax > maxArea) {
            maxArea = localMax;
            maxOriginX = startx;
            maxOriginY = y - level + 1;
        }

        if (y + 1 < 6) {    
            maxArea = traverseRight(startx, y + 1, level + 1, x, val, maxArea, area);
        }
        return maxArea;
    }
             
    
    public static void main(String[] args) {
        String arrString = ""+
                "00011111100011"+ 
                "11100010111110"+ 
                "11000111111100"+  
                "00111100001111"+
                "00110000110000"+
                "11000100000110";
        char[] tmpArea = arrString.replaceAll("[\\n ]","").toCharArray();
        int[][] area = new int[14][6];
        for(int i = 0; i < tmpArea.length; i++) {
            area[i % 14][i / 14] = Character.digit(tmpArea[i], 10);
        }
        
        for(int y = 0; y < 6; y++) {
            for(int x = 0; x < 14; x++) {
                System.out.print(area[x][y]);
                if(x != 13) System.out.print(",");
            }
            System.out.println("");
        }
        
        System.out.println(maxArea(area));
        System.out.println(maxOriginX+","+maxOriginY);
    }

}
