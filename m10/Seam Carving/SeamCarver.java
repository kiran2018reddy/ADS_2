import java.awt.Color;
public class SeamCarver {
	private Picture inputpic;
	// create a seam carver object based on the given picture
	// public SeamCarver(Picture picture) throws Exception {
	// 	if (picture == null) {
	// 		throw new Exception("picture is null");
	// 	}
	public SeamCarver(Picture picture) {
		if (picture == null){
			System.out.println("picture is null");
			return;
		}
		this.inputpic = picture;
	}
	// current picture
	public Picture picture() {
		return this.inputpic;
	}
	// width of current picture
	public int width() {
		return this.inputpic.width();
	}

	// height of current picture
	public int height() {
		return this.inputpic.height();
	}

	// energy of pixel at column x and row y
	public double energy(int x, int y) {
		if (x == 0 || x == width() - 1 || y == 0 || y == height() - 1) {
			return 1000;
		}
		Color pixelnextcol = this.inputpic.get(x + 1 , y);
		Color pixelprevcol = this.inputpic.get(x - 1 , y);
		Color pixeltopcol = this.inputpic.get(x, y - 1);
		Color pixelbottomcol = this.inputpic.get(x, y + 1);
		double rxval = Math.pow(pixelnextcol.getRed() - pixelprevcol.getRed(), 2);
		double bxval = Math.pow(pixelnextcol.getBlue() - pixelprevcol.getBlue(), 2);
		double gxval = Math.pow(pixelnextcol.getGreen() - pixelprevcol.getGreen(), 2);
		double totalxval = rxval + bxval + gxval;
		double ryval = Math.pow(pixelbottomcol.getRed() - pixeltopcol.getRed(), 2);
		double byval = Math.pow(pixelbottomcol.getBlue() - pixeltopcol.getBlue(), 2);
		double gyval = Math.pow(pixelbottomcol.getGreen() - pixeltopcol.getGreen(), 2);
		double totalyval = ryval + byval + gyval;
		return Math.sqrt(totalxval + totalyval);
	}
	// 
	public double min(int x, int y) {
		double a = energy(x + 1, y - 1);
		int ay = y - 1;
		double b = energy(x + 1, y);
		int by = y; 
		double c = energy(x + 1, y + 1);
		int cy = y + 1;
		if (y - 1 == 0) {
			double z = Math.min(b, c);
			if (z == b) {
				return by;
			} else {
				return cy;
			}

		} else if (y + 1 == height() - 1) {
			double e = Math.min(a, b);
			if (b == e) {
				return by;
			} else {
				return ay;
			}

		} else if (x + 1 == width() - 1) {
			return y;

		}else {
			double d = Math.min(Math.min(a, b), Math.min(b, c));
			if (d == b) {
				return by;
			} else if (d == c) {
				return cy;
			} else {
				return ay;
			}
		}
	}
	// // sequence of indices for horizontal seam
	// double min(double a,double b){

	// }
	public int[] findHorizontalSeam() {
        int[][] edgeTo = new int[height()][width()];
        double[][] distTo = new double[height()][width()];
        reset(distTo);
        for (int row = 0; row < height(); row++) {
            distTo[row][0] = 1000;
        }
		// this is for relaxation.
        for (int col = 0; col < width() - 1; col++) {
            for (int row = 0; row < height(); row++) {
                relaxH(row, col, edgeTo, distTo);
            }
        }
        double minDist = Double.MAX_VALUE;
        int minRow = 0;
        for (int row = 0; row < height(); row++) {
            if (minDist > distTo[row][width() - 1]) {
                minDist = distTo[row][width() - 1];
                minRow = row;
            }
        }
        int[] indices = new int[width()];
        //to find the horizontal seam.
        for (int col = width() - 1, row = minRow; col >= 0; col--) {
            indices[col] = row;
            row -= edgeTo[row][col];
        }
        return indices;
    }
    private void relaxH(int row, int col, int[][] edgeTo, double[][] distTo) {
        int nextCol = col + 1;
        for (int i = -1; i <= 1; i++) {
            int nextRow = row + i;
            if (nextRow < 0 || nextRow >= height()) continue;
            if(i == 0) {
            	if(distTo[nextRow][nextCol] >= distTo[row][col]  + energy(nextCol, nextRow)) {
	                distTo[nextRow][nextCol] = distTo[row][col]  + energy(nextCol, nextRow);
	                edgeTo[nextRow][nextCol] = i;
            	}
            }
            if (distTo[nextRow][nextCol] > distTo[row][col]  + energy(nextCol, nextRow)) {
                distTo[nextRow][nextCol] = distTo[row][col]  + energy(nextCol, nextRow);
                edgeTo[nextRow][nextCol] = i;
            }
        }
    }
	/**
	 *this method is to find the vertical seam.
	 *first of all find the shortest path from top to.
	 *bottom.
	 *time complexity is O(w*h)
	 *w is the width and h is the height
	 * @return sequence of indices for vertical seam.
	 */
	public int[] findVerticalSeam() {
		double[][] energy = new double[height()][width()];
		int[][] edgeTo = new int[height()][width()];
		double[][] distTo = new double[height()][width()];
		reset(distTo);
		int[] indices = new int[height()];
		if(width() == 1 || height() == 1) {
			return indices;
		}
		for(int i = 0; i < width(); i++) {
			distTo[0][i] = 1000.0;
		}
		// this is for relaxation.
		for (int i = 0; i < height() - 1; i++) {
			for(int j = 0; j < width(); j++) {
				relaxV(i, j, edgeTo, distTo);
			}
		}
		// calculating from last row
		// column wise
        double minDist = Double.MAX_VALUE;
        int minCol = 0;
        for (int col = 0; col < width(); col++) {
            if (minDist > distTo[height() - 1][col]) {
                minDist = distTo[height() - 1][col];
                minCol = col;
            }
        }
        //indices values of shortest path.
        for (int row = height() -1, col = minCol; row >= 0; row--) {
            indices[row] = col;
            col -= edgeTo[row][col];
        }
        indices[0] = indices[1];
        return indices;
    }
    /**
     *time complexity is O(W * H)
     *W is the width of image
     *H is the height of image
     * @param      distTo  The distance to
     */
	private void reset(double[][] distTo) {
		/**
		 *reset all the values to maxvalue.
		 */
		for(int i = 0; i < distTo.length; i++) {
			for(int j = 0; j < distTo[i].length; j++) {
				distTo[i][j] = Double.MAX_VALUE;
			}
		}
	}
	private void relaxV(int row, int col, int[][] edgeTo, double[][] distTo) {
		int nextRow = row + 1;
        for (int i = -1; i <= 1; i++) {
            int nextCol = col + i;
            if (nextCol < 0 || nextCol >= width()) {
            	continue;
            }
            //spl case for bottom element.
            if(i == 0) {
            	if(distTo[nextRow][nextCol] >= distTo[row][col] + energy(nextCol, nextRow)) {
            	distTo[nextRow][nextCol] = distTo[row][col] + energy(nextCol, nextRow);
                edgeTo[nextRow][nextCol] = i;
            	}
            }
            if (distTo[nextRow][nextCol] > distTo[row][col] + energy(nextCol, nextRow)) {
                distTo[nextRow][nextCol] = distTo[row][col] + energy(nextCol, nextRow);
                edgeTo[nextRow][nextCol] = i;
            }
    	}
	}
	// remove horizontal seam from current picture
	//time complexity is O(width * height)
	public void removeHorizontalSeam(int[] seam) {
		//handle exceptions
	for(int col = 0; col < width(); col++) {
		for(int row = seam[col]; row < height() - 1; row++) {
			this.inputpic.set(col, row, this.inputpic.get(col, row + 1));
		}
	}
	// height()--;
	}
	// remove vertical seam from current picture
	//time complexity is O(width * height)
	public void removeVerticalSeam(int[] seam) {
	for(int row = 0; row < height(); row++) {
		for(int col = seam[row]; col < width() - 1; col++) {
		this.inputpic.set(col, row, this.inputpic.get(col + 1, row));
		}
	}
	// width()--;
	}
}
