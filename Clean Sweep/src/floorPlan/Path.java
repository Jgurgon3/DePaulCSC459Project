package floorPlan;

import java.util.ArrayList;

public class Path
{
// Graph adjacency matrix (unweighted graph)
   static private int[][] graph = { {0, 1, 0, 1, 0, 0, 0, 0, 0},
   {1, 0, 1, 0, 1, 0, 0, 0, 0},
   {0, 1, 0, 0, 0, 1, 0, 0, 0},
   {1, 0, 0, 0, 1, 0, 1, 0, 0},
   {0, 1, 0, 1, 0, 1, 0, 1, 0},
   {0, 0, 1, 0, 1, 0, 0, 0, 1} ,
   {0, 0, 0, 1, 0, 0, 0, 1, 0},
   {0, 0, 0, 0, 1, 0, 1, 0, 1},
   {0, 0, 0, 0, 0, 1, 0, 1, 0}


                                  };
/*                                  
// Graph adjacency matrix (weighted graph)
   static private int[][] graph = { {0,50, 0,80, 0},  // 0->1  0->3
                                    {0, 0,60,90, 0},  // 1->2, 1->3
                                    {0, 0, 0, 0,40},  // 2->4
                                    {0, 0,20, 0,70},  // 3->2, 3->4
                                    {0,50, 0, 0, 0}   // 4->1 
                                  };
*/
//--------------------------------------------------------------                                  
    public static void main (String[] args)
    {
        ArrayList<Integer> Start = new ArrayList();
        Start.add(0);    // The start node
        ArrayList<ArrayList> Queue = new ArrayList();
        Queue.add(Start); // inserted in the initial queue of paths as [[Start]]
// Use one search at a time (comment the others)  
        ArrayList Path = depth_first(Queue,8);   // Depth first search 
//        ArrayList Path = breadth_first(Queue,8); // Breadth first search
//        ArrayList Path = uni_cost(Queue,8); // Uniform cost search
        System.out.println(Path);
//        System.out.println(path_cost(Path));
    }
//--------------------------------------------------------------
// Adds to the queue paths extending the first path in the queue       
    public static ArrayList<ArrayList> extend (ArrayList<Integer> Path)
    {
        ArrayList<ArrayList> NewPaths = new ArrayList();
        for (int i=0;i<graph.length;i++)
            if (graph[Path.get(0)][i]>0 && !Path.contains(i))
            {
                ArrayList Path1 = (ArrayList)Path.clone();
                Path1.add(0,i);
                NewPaths.add(Path1);
            }
        return NewPaths;
    }
//--------------------------------------------------------------
// Appends y to x and returns the result in a new ArrayList z 
    public static ArrayList append (ArrayList x, ArrayList y) 
    {
        ArrayList z = (ArrayList)x.clone();;
        for (int i=0;i<y.size();i++) z.add(y.get(i));
        return z;
    }
//--------------------------------------------------------------
// Depth first search for a path from Start to Goal. 
// The Start node must be in the initial queue of paths as [[Start]]   
    public static ArrayList<ArrayList> depth_first(ArrayList<ArrayList> Queue, int Goal) 
    {
        if (Queue.size()==0) return Queue;         // path not found
        if ((Integer)Queue.get(0).get(0) == Goal)  // if Goal is the first node in the first path
            return Queue.get(0);                   // return path
        else // replace the first path with all its extensions and put them in front of the queue            
        {
            ArrayList<ArrayList> NewPaths = extend(Queue.get(0));
            Queue.remove(0); 
            return depth_first(append(NewPaths,Queue),Goal);
        }
    }
//--------------------------------------------------------------
// Breadth first search for a path from Start to Goal. 
// The Start node must be in the initial queue of paths as [[Start]]   
    public static ArrayList<ArrayList> breadth_first(ArrayList<ArrayList> Queue, int Goal) 
    {
        if (Queue.size()==0) return Queue;          // path not found
        if ((Integer)Queue.get(0).get(0) == Goal)   // if Goal is the first node in the first path
            return Queue.get(0);                           // return path
        else // replace the first path with all its extensions and put them at the end of the queue
        { 
            ArrayList<ArrayList> NewPaths = extend(Queue.get(0));
            Queue.remove(0);
            return breadth_first(append(Queue,NewPaths),Goal);
        }
    }
//--------------------------------------------------------------
    public static int path_cost (ArrayList<Integer> Path)
    {
        int cost = 0;
        for (int i=0;i<Path.size()-1;i++)
            cost = cost + graph[Path.get(i+1)][Path.get(i)];
        return cost;
    }
//--------------------------------------------------------------    
// Uniform cost search for a path from Start to Goal. 
// The Start node must be in the initial queue of paths as [[Start]]   
    public static ArrayList<ArrayList> uni_cost(ArrayList<ArrayList> Queue, int Goal) 
    {
        if (Queue.size()==0) return Queue;          // path not found
        if ((Integer)Queue.get(0).get(0) == Goal)   // if Goal is the first node in the first path
            return Queue.get(0);                           // return path
        else // replace the first path with all its extensions and put them at the end of the queue
        { 
            ArrayList<ArrayList> NewPaths = extend(Queue.get(0));
            Queue.remove(0);
            ArrayList<ArrayList> Queue1 = append(Queue,NewPaths);
            sort(Queue1);   // Put the path with the lowest cost first in the queue
            return uni_cost(Queue1,Goal);
        }
    }
//--------------------------------------------------------------
    public static void sort(ArrayList<ArrayList> Queue)
    {
        int out, in;
        for(out=Queue.size()-1; out>=1; out--)
         for(in=0; in<out; in++)
            if( path_cost(Queue.get(in)) > path_cost(Queue.get(in+1)) )
               swap(Queue, in, in+1);
    }
//--------------------------------------------------------------
    private static void swap(ArrayList<ArrayList> a, int one, int two)
    {
        ArrayList<Integer> temp = a.get(one);
        a.set(one,a.get(two));
        a.set(two,temp);
    }

}