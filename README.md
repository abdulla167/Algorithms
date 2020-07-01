# Algorithms
# What is percolation ?
It is a model for many physical systems, it is (nxn) grid of squares that we calls sites and each site is open with probability P or blocked with probability 1-P. We say the system is percolated if the top and bottom of this grid are connected by open sites, we can consider it as electric circuit, the open sites are conductor and blocked sites are insulator.
Our program role to determine the threshold which the system should exceed to percolate by using union-find algorithm, and this threshold is the number of open sites at which the system percolates over the number of blocked sites
we should make this experiment more than one time and get average or mean to this result to get threshold for the system to percolate, then we get the interval where the system will percolate with confidence 95 %.

![](wkwKd.png)
# What is the role of the application ?
I have implemented two classes
#### Percolation.java: 
1) initializing the grid with nxn blocked sites by constructor.
2) open(int row, int col) : This method used to open exact site in the grid.
3) isOpen(int row, int col) : This method to check exact site is open or not.
4) isFull(int row, int col) : This method chech exact node if connected with open-upper site.
5) getNumberOfOpen() : This method for get number of open sites in the grid.
6) percolate() : This method check if the system percolates or not ?.
#### PercolationStats.java: 
1) Looping many times to form many percolation system to get more than one result.
2) mean() : This method used to get the mean of the thresholds.
3) stddev() : This method used to get standard deviation for the thresholds.
4) confidenceLo() : This method used to get the lower confidence interval for the threshold.
5) confidenceHi() : This method used to get the higher confidence interval for the threshold.

