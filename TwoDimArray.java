
/**
 * Tim Rainey 
 * 10/12/2020
 * CICS122 
 * Programming Project #1 
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JOptionPane;


public class TwoDimArray {
	// Create the 2d array with the double variable type
	private double[][] twoDimArr;

	/**
	 * default constructors loads the array by calling the loadArray() method
	 */
	public TwoDimArray() {
		loadArray();
	}

	/**
	 * method for getting the array max
	 * 
	 * @return
	 */
	public double getArrayMaxValue() {
		double maximum = twoDimArr[0][0];

		for (int i = 0; i < twoDimArr.length; i++) {
			
			for (int j = 0; j < twoDimArr[i].length; j++) {
			
				if (twoDimArr[i][j] > maximum) {
					maximum = twoDimArr[i][j];
				}
			}
		}

		return maximum;
	}

	/**
	 * method for getting the array min
	 * 
	 * @return
	 */
	public double getArrayMinValue() {
		
		double minimum = twoDimArr[0][0];

		for (int i = 0; i < twoDimArr.length; i++) {
			
			for (int j = 0; j < twoDimArr[i].length; j++) {
			
				if (twoDimArr[i][j] < minimum) {
					minimum = twoDimArr[i][j];
				}
			}
		}

		return minimum;
	}

	/**
	 * method for calculating the standard deviation
	 * 
	 * @return
	 */
	public double calcStdDev() {
		
		double stdDev = twoDimArr[0][0];

		double sum = 0;
		
		int count = 0;
		
		for (int rows = 0; rows < twoDimArr.length; rows++) {
			for (int cols = 0; cols < twoDimArr[rows].length; cols++) {
				sum = sum + twoDimArr[rows][cols];
				count++;
			}
		}

		stdDev = Math.sqrt(sum / count - 1);

		return stdDev;

	}

	/**
	 * method for finding the the value at a specific index
	 * 
	 * @return
	 */
	public String findValue() {
		String found = "";

		for (int i = 0; i < twoDimArr.length; ++i) {
			for (int j = 0; j < twoDimArr[i].length; ++j) {
				if (twoDimArr[i][j] == 14) {

					found = (i + "," + j);
				}

			}
		}
		return found;

	}

	/**
	 * method for replacing a specific index
	 * 
	 * @return
	 */
	public double replace() {
		twoDimArr[1][1] = 10;

		return twoDimArr[1][1];

	}

	/**
	 * method to read the data from a file into the two dimensional array
	 */

	public void loadArray() {
		String fileName = "";

		try {
			// prompt the user for an input file name
			fileName = JOptionPane.showInputDialog("Enter the file name: ");

			// open the input file
			Scanner infile = new Scanner(new File("2dArray.txt"));

			if (!infile.hasNextLine()) {
				// if the file is empty, display an error message using a JOptionPane and quit
				JOptionPane.showMessageDialog(null, "The input file " + fileName + " is empty!");
				System.exit(1);
			} else {
				infile.reset();

				String strRows = infile.next();
				String strCols = infile.next();

				int rows = Integer.parseInt(strRows);
				int cols = Integer.parseInt(strCols);

				twoDimArr = new double[rows][cols];

				for (int i = 0; i < rows; i++) {
					for (int j = 0; j < cols; j++) {
						String data = infile.next();
						twoDimArr[i][j] = Double.parseDouble(data);
					}
				}

				// close the input file
				infile.close();
			}
		} catch (NullPointerException npe) {
			// display the NullPointerException
			JOptionPane.showMessageDialog(null, npe.getMessage());
			System.exit(1);
		} catch (NumberFormatException nfe) {
			// display the NumberFormatException
			JOptionPane.showMessageDialog(null, "cannot convert input to integer");
			System.exit(1);
		} catch (FileNotFoundException fnfe) {
			// if the file doesn't exist, display an error message using a JOptionPane and
			// quit
			JOptionPane.showMessageDialog(null, "The input file " + fileName + " doesn't exist!");
			System.exit(1);
		}
	}

	/**
	 * start main method
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// it's default constructor calls the loadArray() method and executes all other
		// methods called
		TwoDimArray tda = new TwoDimArray();

		// call the getArrayMaxValue method
		System.out.println("Maximum value: " + tda.getArrayMaxValue());

		// call the getArrayMinValue method
		System.out.println("Minimum value: " + tda.getArrayMinValue());

		// call the calcStdDev method
		System.out.println("The standard deviation is " + tda.calcStdDev());

		// call the findValue method
		System.out.println("The value of 14 is at location " + tda.findValue() + " of the array");

		// call the replace method
		System.out.println("The following value of  0.1234 has been replaced with " + tda.replace());
	}

}
