# M/M/1 Queue Simulator

## Description
**M/M/1** is a Java-based simulator for an M/M/1 queue system. The program models a queue with a single server where arrivals and service times follow an exponential distribution.

## Compilation

To compile the program, run:

```sh
make compile
```

This will compile all Java files and store the class files in the `out/` directory.

## Usage

Run the program with default parameters:

```sh
java -cp out MM1 5 6 1000 1
```

Where the parameters represent:
- **5**: Arrival rate (lambda)
- **6**: Service rate (mu)
- **1000**: Number of events
- **1**: Mode of execution

You can also generate a graphical representation of the results:

```sh
make graph
```

### Running with Different Data Sizes

- **Small Data**:
  ```sh
  make small
  ```
  Runs with 100 events.

- **Medium Data**:
  ```sh
  make medium
  ```
  Runs with 10,000 events.

- **Big Data**:
  ```sh
  make big
  ```
  Runs with 1,000 events.

- **Unstable System** (arrival rate > service rate):
  ```sh
  make unstable
  ```
  Runs with an overloaded system.

### Running with Graph Generation

- **Small Data with Graph**:
  ```sh
  make smallG
  ```
- **Medium Data with Graph**:
  ```sh
  make mediumG
  ```
- **Big Data with Graph**:
  ```sh
  make bigG
  ```
- **Unstable System with Graph**:
  ```sh
  make unstableG
  ```

## Generating Graphs

To generate a graph using Gnuplot:

```sh
make plot
```

## Cleaning Up

To remove compiled files and generated graphs:

```sh
make clean
```

## Help

To display available `make` commands:

```sh
make help
```

## Dependencies

- Java Development Kit (JDK)
- Gnuplot (for graph generation)

On Debian/Ubuntu, install Gnuplot with:

```sh
sudo apt-get install gnuplot
```

On macOS, use:

```sh
brew install gnuplot
```

## Project Structure

- **src/** : Java source files.
- **out/** : Compiled `.class` files.
- **graph/** : Gnuplot scripts and generated graphs.
- **data/** : Output data files for analysis.

## Author
Théo Ischia

