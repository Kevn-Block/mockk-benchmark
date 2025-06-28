# Mockk Benchmark

A repo that contains microbenchmark results that compare the performance of different mockk configurations.

## Benchmark Details

These benchmarks were run on a machine with the following specs:
- Mac M1 Max
- 32GB of RAM

### Results

![Mockk Performance Benchmark](https://github.com/user-attachments/assets/d7feb900-0c10-4693-b646-5ffe90278d31)

![Mockk Memory Benchmark](https://github.com/user-attachments/assets/23f18027-e1e4-4e7b-8918-c0e893fce95c)


## Running the Benchmarks

To run the benchmarks:

1. Clone the repo
2. Run the following command:
```
./gradlew jmh
```
The benchmark results will be available in the terminal and `/build/results/jmh/results.txt`

## Dependencies

This project uses:
- [mockk](https://mockk.io/) for Kotlin mocking
- [JMH](https://openjdk.org/projects/code-tools/jmh/) for benchmarking
