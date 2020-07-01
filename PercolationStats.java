import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
	private static final double CONFIDENCE_95 = 1.96;
	private final double[] thresholds;
	
	 // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials)
    {
    	if (trials <= 0)
    	{
    		throw new IllegalArgumentException("unmber of trials should exceed 0 trial");
    	}
    	double threshold = 0;
    	thresholds = new double[trials];
    	for (int trial = 1; trial <= trials; trial++)
    	{
    		Percolation percolation = new Percolation(n);
    		while (!percolation.percolates())
    		{
    			int i = StdRandom.uniform(1, n+1);
    			int j = StdRandom.uniform(1, n+1);
    			percolation.open(i, j);
    		}
    		threshold = ((double) percolation.numberOfOpenSites()/(double) (n*n));
    		thresholds[trial-1] = threshold;
    	}
    }

    // sample mean of percolation threshold
    public double mean()
    {
    	return StdStats.mean(thresholds);
    }

    // sample standard deviation of percolation threshold
    public double stddev()
    {
    	return StdStats.stddev(thresholds);
    }
    
    
    // low endpoint of 95% confidence interval
    public double confidenceLo()
    {
    	double mean = mean();
    	double stddev = stddev();
    	double confidenceLow =  mean - ((CONFIDENCE_95 * stddev)/ Math.sqrt(thresholds.length));
    	return confidenceLow;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi()
    {
    	double mean = mean();
    	double stddev = stddev();
    	double confidenceHigh =  mean + ((CONFIDENCE_95 * stddev)/ Math.sqrt(thresholds.length));
    	return confidenceHigh;
    }

   // test client (see below)
   public static void main(String[] args)
   {
		/* write your application here */
	   PercolationStats percolation = new PercolationStats(100, 50);
	   System.out.println(percolation.mean());

   }

}
