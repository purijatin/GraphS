package Graph.Algo.ShortestPath;

/**
 *
 * @author Jatin
 */
public interface Adder<E>
{
    /**
     * Adds add1 and add2 and returns the sum. 
     * Infinity needs to be defined for an adder.
     * if add1 or add2 is infinite, then it returns infinite.
     * @param add1
     * @param add2
     * @return 
     */
    public E add(E add1,E add2);
}
