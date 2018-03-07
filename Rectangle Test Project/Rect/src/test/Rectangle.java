package test;

import java.security.InvalidParameterException;

//import edu.unm.cs583.Rectangle;

public class Rectangle {

	// Instance variable declaration
	private int x, y, width, height;

	// Constructor declaration
	Rectangle(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		// Check for negative values so negative rectangles cannot be made.
		// Mario LoPrinzi
		if (this.width < 0 || this.height < 0) {
			throw new InvalidParameterException("Values must be positive.");
		}
	}

	// Instance variable accessors
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	// Accessors for computed values
	public int area() {
		// This checks for integer overflow from the multiplication.
		// Mario LoPrinzi
		if (width * height < 0) {
			throw new ArithmeticException("Width*Height has caused and integer overflow.");
		}
		return width * height;
	}

	public double diagonal() {
		return Math.sqrt(width * width + height * height);
	}

	// Overriden Object class methods
	public boolean equals(Object obj) {
		if (obj instanceof Rectangle) {
			Rectangle r = (Rectangle) obj;
			return x == r.x && y == r.y && width == r.width && height == r.height;
		}
		return false;
	}

	public String toString() {
		return "(" + x + "," + y + "), width = " + width + ", height = " + height;
	}

	// Methods
	public Rectangle union(Rectangle r) {
		int newX = Math.min(x, r.x);
		int newY = Math.min(y, r.y);
		int newWidth = Math.max(x + width, r.x + r.width) - newX;
		int newHeight = Math.max(y + height, r.y + r.height) - newY;
		return new Rectangle(newX, newY, newWidth, newHeight);
	}

	public boolean contains(int x, int y) {
		if(this.area() !=0){
			return this.x <= x && x <= this.x + width && this.y <= y && y <= this.y + height;
		}
		else
			return false;
	}

	/* The following methods were not written in class */

	public boolean intersects(Rectangle r) {
		/**
		 * Incorrect solution based on in class tests
		 * 
		 * This solution will work for the test cases we had *in class*, but
		 * asymmetrically. Some test cases will pass as a.intersects(b) but not as
		 * b.intersects(a)
		 * 
		 * NOTE: The tests given for this method will fail with this implementation.
		 */
		// UL corner of r is in 'this'
		// return contains(r.x, r.y)
		// // UR corner of r is in 'this'
		// || contains(r.x, r.y + r.height)
		// // LL corner of r is in 'this'
		// || contains(r.x + r.width, r.y)
		// // LR corner of r is in 'this'
		// || contains(r.x + r.width, r.y + r.height)
		// // At least one extreme x value of r is between
		// // the exteme x values of 'this'
		// || ((x <= r.x && r.x <= x + width || x <= r.x + r.width && r.x + r.width <= x
		// + width)
		// // And at least one extreme y value of 'this' is
		// // is between the exteme values of r
		// && (r.y <= y && y <= r.y + r.height || r.y <= y + height && y + height <= r.y
		// + r.height));

		/**
		 * A correct solution
		 * 
		 * Thinking less about testing to see if the coordinate of one rectangle is
		 * between the coordinates of another rectangle, focus on the opposite problem:
		 * when are the rectangles NOT intersecting
		 * 
		 * To test this, make sure you comment out the full return statement above
		 * before uncommenting these statements.
		 */
		// System.out.println("this: "+ this.toString());
		// System.out.println("r:" + r.toString());
		// System.out.println("r.x + r.width <= x: "+ (r.x + r.width <= x));
		// System.out.println("x + width <= r.x: "+ (x + width <= r.x));
		// System.out.println("r.y + r.height <= y: "+ (r.y + r.height <= y));
		// System.out.println("y + height <= r.y: "+ (y + height <= r.y));
		// System.out.println("r.x + r.width: "+ (r.x + r.width));
		// System.out.println("x + width: "+ (x + width));
		// 'this' starts further right than r ends
		return !(r.x + r.width <= x
				// r starts further right than 'this' ends
				|| x + width <= r.x
				// 'this' starts lower than r ends
				|| r.y + r.height <= y
				// r starts lower than r ends
				|| y + height <= r.y);

	}

	public Rectangle intersection(Rectangle r) {
		if (intersects(r)) {
			int newX = Math.max(x, r.x);
			int newY = Math.max(y, r.y);
			// System.out.println("newX = "+ newX+", newY = "+newY+", width = "
			// + Math.min(x + width - newX, r.x + r.width - newX)+", height = "+Math.min(y +
			// height - newY, r.y + r.height - newY));
			return new Rectangle(newX, newY, Math.min(x + width - newX, r.x + r.width - newX),
					Math.min(y + height - newY, r.y + r.height - newY));
		} else {
			// System.out.println("this: "+ this.toString());
			// System.out.println("r:" + r.toString());
			return null;
		}

	}
}