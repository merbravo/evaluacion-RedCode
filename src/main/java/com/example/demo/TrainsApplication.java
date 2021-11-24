package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TrainsApplication {
	public int[][] map = {
	        //    A   B   C   D   E
	            {-1,  5, -1,  5,  7},
	            {-1, -1,  4, -1, -1},
	            {-1, -1, -1,  8,  2},
	            {-1, -1,  8, -1,  6},
	            {-1,  3, -1, -1, -1},
	    };
	// MÉTODO PREGUNTAS DEL 1 AL 5 

	public int getDistance(String path)
    {
        int distance = 0;
        for (int i=0; i<path.length()-1; i++) {
            // get the node index in map
            int fromNodeIndex = path.charAt(i) - 'A';
            int toNodeIndex = path.charAt(i+1) - 'A';
            // check the distance of two node in map
            if (map[fromNodeIndex][toNodeIndex] == -1) {
                return -1;
            } else {
                distance += map[fromNodeIndex][toNodeIndex];
            }
        }
        return distance;
    }
	//MÉTODO PREGUNTA 6
	
	public int tripCount = 1;
	
    
    public void dfs(String end, String path, int maxLength)
    {
        
        if (path.length() - 1 > maxLength) return;

        if ( path.length() > 1 && path.endsWith(end) ) {
        	if(tripCount > 1) {
        		System.out.println("Salida # 6:" + tripCount);
        	}
            tripCount ++;
        }

        char lastChar = path.charAt(path.length()-1);
        int lastNodeIndex = lastChar - 'A';
        for ( int i=0; i<map[lastNodeIndex].length; i++ )
        {
            char newChar = (char)(i + 'A');
            if ( map[lastNodeIndex][i] > 0) {
                dfs(end, path + newChar, maxLength);
            }
        }
    }
    //MÉTODO PREGUNTA 7
    public void bfs(String start, String end, int hops) {
        String lastRoute = start;
        for (int hop = 0; hop < hops; hop++) {
            String route = "";
            for (int i = 0; i < lastRoute.length(); i++) {
                char c = lastRoute.charAt(i);
                int id = c - 'A';
                for (int j = 0; j < map[id].length; j++) {
                    if (map[id][j] > 0)
                        route = route + (char) (j + 'A');
                }
            }
            lastRoute = route;
        }
        System.out.println("Salida # 7:" + (lastRoute.split(end).length - 1));
    }
	
    //MÉTODO PREGUNTA 8 Y 9
    public void dfss(String end, String path, int cost) {
        if (path.endsWith(end) && cost < bestCost2 && cost > 0) {
            bestPath2 = path;
            bestCost2 = cost;
            return;
        }
        char lastChar = path.charAt(path.length() - 1);
        int lastNodeIndex = lastChar - 'A';

        for (int i = 0; i < map[lastNodeIndex].length; i++) {
            char newChar = (char) (i + 'A');
            int newCost = map[lastNodeIndex][i];
            if (newCost > 0) {
                if (path.indexOf(newChar) > 0)
                    continue;
                dfss(end, path + newChar, cost + newCost);
            }
        }
    }
    
    public String bestPath2 = "";
    public int bestCost2 = Integer.MAX_VALUE;
    
    //MÉTODO PREGUNTA 10
    public int tripCount2 = 0;
    public void dfsss(String end, String path, int cost) {
        if (cost >= 30)
            return;
        if (cost > 0 && path.endsWith(end)) {
        	tripCount2++;
            //System.out.println("Salida # 10:" + cost);
        }
        char lastChar = path.charAt(path.length() - 1);
        int lastNodeIndex = lastChar - 'A';
        for (int i = 0; i < map[lastNodeIndex].length; i++) {
            char newChar = (char) (i + 'A');
            int newCost = map[lastNodeIndex][i];
            if (newCost > 0) {
                dfsss(end, path + newChar, cost + newCost);
            }
        }
    }
    
    
    
	public static void main(String[] args) {
		//SpringApplication.run(TrainsApplication.class, args);
		TrainsApplication g = new TrainsApplication();
		
		// salida 1
		
		int distance = 0;
        distance = g.getDistance("ABC");
        if (distance > 0)
        	System.out.println("Salida # 1:" + distance);
        else
            System.out.println("Salida # 1:NO HAY TAL RUTA");
        
        //salida 2
        distance = 0;
        distance = g.getDistance("AD");
        if (distance > 0)
        	System.out.println("Salida # 2:" + distance);
        else
            System.out.println("Salida # 2:NO HAY TAL RUTA");
        
        //salida 3
        distance = 0;
        distance = g.getDistance("ADC");
        if (distance > 0)
        	System.out.println("Salida # 3:" + distance);
        else
            System.out.println("Salida # 3:NO HAY TAL RUTA");
        
        //salia 4
        distance = 0;
        distance = g.getDistance("AEBCD");
        if (distance > 0)
        	System.out.println("Salida # 4:" + distance);
        else
            System.out.println("Salida # 4:NO HAY TAL RUTA");
        
        //salida 5
        distance = 0;
        distance = g.getDistance("AED");
        if (distance > 0)
        	System.out.println("Salida # 5:" + distance);
        else
            System.out.println("Salida # 5:NO HAY TAL RUTA");
        
	    //salida 6
	     g.dfs("C", "C", 3);
	     
	   //salida 7
	        g.bfs("A", "C", 4);
	     //salida 8
	        g.dfss("C", "A", 0);
	        System.out.println("Salida # 8:" + g.bestCost2);
	        
	     //salida 9
	        g.dfss("B", "B", 0);
	        System.out.println("Salida # 9:" + g.bestCost2);    
	     
	     //salida 10
	        g.dfsss("C", "C", 0);
	        System.out.println("Salida # 10:" + g.tripCount2);   
	        
	        
        	}

}
