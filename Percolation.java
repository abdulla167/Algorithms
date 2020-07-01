import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	private Site[][] percolationGrid;
	private WeightedQuickUnionUF quickUnion;
	private int openSites = 0; 
	private final Site virtualTop = new Site();
	private final Site virtualBottom = new Site();
	
	private class Site
	{
		public int num = 0;
		public boolean open = false;
	}
	
	public Percolation(int n) 
	{
		if (n > 0)
		{
			percolationGrid = new Site [n][n];
			quickUnion = new WeightedQuickUnionUF(n*n + 2);
			virtualTop.num = 0;
			virtualBottom.num = n*n + 1;
			int key = 1;
			for (int i = 0; i < percolationGrid.length; i++)
			{
				for (int j = 0; j < percolationGrid[0].length; j++)
				{
					percolationGrid[i][j] = new Site();
					percolationGrid[i][j].num = key;
					key++;
				}
			}
		}
		else
		{
			throw new IllegalArgumentException("wrong number for matrix");
		}
	}
	
	// opens the site (row, col) if it is not open already
    public void open(int row, int col)
    {
    	if (!isOpen(row, col)) {
	    	percolationGrid[row-1][col-1].open = true;
	    	openSites++;
	    	if (row == 1)
	    	{
	    		quickUnion.union(percolationGrid[row-1][col-1].num, virtualTop.num);
	    	}
	    	if (row == percolationGrid.length)
	    	{
	    		quickUnion.union(percolationGrid[row-1][col-1].num, virtualBottom.num);
	    	}
	    	if (row-1 > 0 && isOpen(row-1, col))
	    	{
	    		quickUnion.union(percolationGrid[row-1][col-1].num, percolationGrid[row-2][col-1].num);
	    	}
	    	if (col-1 > 0 && isOpen(row, col-1))
	    	{
	    		quickUnion.union(percolationGrid[row-1][col-1].num, percolationGrid[row-1][col-2].num);
	    	}
	    	if (row-1 < percolationGrid.length-1 && isOpen(row+1, col))
	    	{
	    		quickUnion.union(percolationGrid[row-1][col-1].num, percolationGrid[row][col-1].num);
	    	}
	    	if (col-1 < percolationGrid[0].length-1 && isOpen(row, col+1))
	    	{
	    		quickUnion.union(percolationGrid[row-1][col-1].num, percolationGrid[row-1][col].num);
	    	}   	
    	}
    }
    
    // is the site (row, col) open?
    public boolean isOpen(int row, int col)
    {
    	if (row < 1 || row > percolationGrid.length || col < 1 || col > percolationGrid[0].length)
    	{
    		throw new IllegalArgumentException("outOfMatrix");
    	}
    	else {
	    	if (percolationGrid[row-1][col-1].open) { return true; }
	    	return false;
    	}
    }
    
    /* is the site (row, col) full?
     * show if this site connected to the upper site in the grid
     * or not
     */
    public boolean isFull(int row, int col)
    {
    	if (row < 1 || row > percolationGrid.length || col < 1 || col > percolationGrid[0].length)
    	{
    		throw new IllegalArgumentException("outOfMatrix");
    	}
    	else {
	    	
	    		if (quickUnion.find(percolationGrid[row-1][col-1].num) == quickUnion.find(virtualTop.num))
	    		{
	    			return true;	
	    		}
	    		else {
	    			return false;
	    		}
    	}	
    }
    

    /* returns the number of open sites */
    public int numberOfOpenSites()
    {
    	return openSites;
    }
    
    
    /* does the system percolate?
     * return true if percolates and false if not
     */
    public boolean percolates()
    {
		return (quickUnion.find(virtualBottom.num) == quickUnion.find(virtualTop.num));
    }

    
	public static void main(String[] args) {
		/* write your application here */
		int n = 5;
		Percolation percolation = new Percolation(n);
		while (!percolation.percolates())
		{
			int i = StdRandom.uniform(1, n+1);
			int j = StdRandom.uniform(1, n+1);
			System.out.println(i+" "+j);
			percolation.open(i, j);
		}
	}

}
