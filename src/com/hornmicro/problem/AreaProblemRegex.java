package com.hornmicro.problem;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AreaProblemRegex {
    
    static class Range implements Comparable<Range>{
        public int start;
        public int end;
        public Range(int start, int end) {
            this.start = start;
            this.end = end;
        }
        
        public int length() {
            return end - start;
        }
        
        @Override
        public int compareTo(Range o) {
            if (o == null) return 0;
            return o.length() - this.length();
        }
        
        @Override
        public String toString() {
            return start+","+end;
        }
    }
    
    final static String area = ""+
        "00011111100011\n"+
        "11100010111110\n"+
        "11000111111100\n"+
        "00111100001111\n"+
        "00110000110000\n"+
        "11000100000110";
    final static Pattern pattern = Pattern.compile("(0+)");
    
    public static void main(String[] args) {
        int maxArea = 1;
        String[] lines = area.split("\n");
        for (int level = 0; level < lines.length; level++) {
            maxArea = areaSearch(lines, maxRun(lines[level]), level, level, maxArea);
        }
        System.out.println(maxArea);
        System.out.print(maxX+","+maxY);
    }
    
    static Range maxRun(String line) {
        Matcher m = pattern.matcher(line);
        Range maxRange = new Range(0, 0);
        while (m.find()) {
            Range range = new Range(m.start(0), m.end(0));
            if (maxRange.compareTo(range) > 1) {
                maxRange = range;
            }
        }
        return maxRange;
    }
    
    static int maxX = 0;
    static int maxY = 0;
    static int areaSearch(String[] lines, Range range, int startLevel, int level, int maxArea) {
        String line = lines[level].substring(range.start, range.end);
        Matcher m = pattern.matcher(line);
        Range newRange;
        if (m.find()) {
            newRange = new Range(range.start + m.start(0), range.start + m.end(0) );
            if(newRange.length() > 0) {
                if(level < lines.length - 1) {
                    maxArea = areaSearch(lines, newRange, startLevel, level + 1, maxArea);
                }
            }
            int localMax = (level - startLevel + 1) * newRange.length();
            if(localMax > maxArea) {
                maxArea = localMax;
                maxY = startLevel;
                maxX = newRange.start;
            }
        }
        
        return maxArea;
    }

}
