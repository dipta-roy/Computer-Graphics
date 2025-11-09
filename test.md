# Computer Graphics Algorithms in Java

This repository contains simple Java implementations of foundational computer graphics algorithms, including:

- **DDA Line Drawing Algorithm**
- **Midpoint Circle Drawing Algorithm**
- **Cohen-Sutherland Line Clipping Algorithm**

Each program runs via terminal input and is organized for clarity and educational use.

---

## 🧠 Algorithms Overview

1. DDA Line Drawing
	Defination: DDA Line Drawing Algorithm that can generate return the coordinates through which a line can be drawn from one point to another.
	```Usage:
	a. Takes two endpoints.
	b. Computes intermediate points using floating-point increments.
	c. Rounds and prints the pixel locations.
	```

2. Midpoint Circle Drawing
	Defination: Mid-Point is an algorithm used to determine the points needed for rasterizing a circle. 
	```Usage:
	a. Takes radius and center as input.
	b. Uses midpoint decision parameter to draw a full circle using 8-way symmetry.
	```

3. Cohen-Sutherland Line Clipping
	Defination: The Cohen–Sutherland algorithm is a computer-graphics algorithm used for line clipping. The algorithm divides a two-dimensional space into 9 regions and then efficiently determines the lines and portions of lines that are visible in the central region 
	```Usage
	a. Takes a clipping window and a line segment.
	b. Uses region codes and logical operations to determine whether to accept, reject, or clip the line.
	```

---

## 📁 Files

| File                              | Description |
|-----------------------------------|-------------|
| `Home.java`                       | Main menu to access all algorithms. |
| `DDA_Line_Algorithm.java`         | Implements the DDA (Digital Differential Analyzer) Line Drawing Algorithm. |
| `MidPointDrawing.java`            | Implements the Midpoint Circle Drawing Algorithm using symmetry. |
| `CohenSutherlandLineClipping.java`| Implements the Cohen-Sutherland Line Clipping Algorithm using region codes. |

---

## 🚀 How to Run

Make sure you have **Java 8+** installed.

1. **Clone the repository**
	```bash
	git clone https://github.com/dipta-roy/computer-graphics-java.git
	cd computer-graphics-java
	```

2. Compile all programs

	```bash
	javac *.java
	```

3. Run the main menu

	```bash
	java Home
	```
	
4. Follow the prompts to choose an algorithm and input parameters.

