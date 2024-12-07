# GenomeIndexing

## Overview

The **GenomeIndexing** is a Java tool for extracting indices and seeds from genome sequences using **Minimizer** and **Genome-on-Diet** algorithms.
This project apply the concepts explained in this [lecture](https://www.youtube.com/watch?v=1WlJrmrlQtc), which is part
of the `Introduction to Bioinformatics` course taught at the Islamic University of Gaza (IUG).

## Input file structure

* The first line of the input contains one integer `n` — the number of test cases.

* Each test case consists of three lines:

    * The first line of each test case contains one integer `t` — the type of the algorithm to be used on the following
      genome sequence. This project covers only two types:
        1. Minimizer, `t` must be `1`.
        2. Genome-on-Diet, `t` must be  `2`.

    * The second line contains the genome sequence.
    * The third line contains the integers `k`, `w`, and `p` (if applicable) respectively.

Example: [sample-input.txt](sample-input.txt)

## Prerequisites

To run this project, you need:

* Java Development Kit (JDK) 18 or later installed.
* An IDE (e.g., IntelliJ IDEA, Eclipse, or VS Code) or the command line for running Java applications.

## Project Setup

You can install To run this project, follow these steps:

1. Clone the repository:

    ```bash
    git clone https://github.com/MohammedOH/GenomeIndexing.git
    cd GenomeIndexing
    ```

2. Create the input file, and follow the [Input file structure](#Input-file-structure):

   ```bash
    touch input.txt
    ```
   or

   ```bash
    echo "" > input.txt
    ```
   For more help see [sample-input.txt](sample-input.txt) file

3. Compile the Project:

    ```bash
    javac -source 18 -target 18 -d out src/*.java
    ```

4. Run the Project:

    ```bash
    java -cp out Main
    ```

5. Open the output file `output.txt`.

## License

This project is licensed under the [MIT License](https://opensource.org/licenses/MIT).

## Created By

**Mohammed O. AlHabbash**,  
[mohammad.o.a1999@gmail.com](mailto:mohammad.o.a1999@gmail.com)
