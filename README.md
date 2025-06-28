# Mockk Benchmark

A repo that contains microbenchmark results that compare the performance of different mockk configurations.

## Benchmark Details

These benchmarks were run on a machine with the following specs:
- Mac M1 Max
- 32GB of RAM

### Performance (ms/op)

| Benchmark                        | isRelaxed | Score\_ms | Error\_ms |
| :------------------------------- | :-------- | --------: | --------: |
| mockWithDIAndCall                | true      |     0.015 |     0.003 |
| mockWithDIAndCall                | false     |     0.017 |     0.006 |
| setupWithInterface               | true      |     0.021 |     0.005 |
| setupWithInterface               | false     |     0.021 |     0.002 |
| setupWithAnswers                 | false     |     0.022 |     0.003 |
| setupWithAnswers                 | true      |     0.023 |     0.006 |
| setupWithClassUsingInterface     | false     |     0.027 |     0.004 |
| setupWithClassUsingInterface     | true      |     0.028 |     0.005 |
| setupOnly                        | true      |     0.084 |     0.004 |
| setupOnly                        | false     |     0.086 |     0.011 |
| setupWithCalls                   | true      |     0.101 |     0.007 |
| setupWithCalls                   | false     |     0.105 |     0.013 |
| setupWithFirstArgCall            | false     |     0.165 |     0.014 |
| setupWithFirstArgCall            | true      |     0.167 |     0.014 |
| setupWithLoopCalls               | false     |     0.668 |     0.072 |
| setupWithLoopCalls               | true      |     0.692 |     0.145 |
| setupWithCallsAndVerify          | false     |     4.446 |     1.439 |
| setupWithCallsAndVerify          | true      |     4.506 |     1.417 |
| setupWithStaticInterface         | false     |     7.584 |     0.385 |
| setupWithStaticInterface         | true      |     8.052 |     0.957 |
| setupWithStaticAndCallsAndVerify | true      |    12.504 |     1.327 |
| setupWithStaticAndCallsAndVerify | false     |    12.593 |     1.437 |
| mockObjectAndCall                | false     |    28.539 |     6.304 |
| mockObjectAndCall                | true      |    30.008 |     6.701 |


### Memory Allocated (KB/op)

| Benchmark                        | isRelaxed | Score\_kb | Error\_kb |
| :------------------------------- | :-------- | --------: | --------: |
| mockWithDIAndCall                | false     |        16 |       0.1 |
| mockWithDIAndCall                | true      |        16 |       0.2 |
| setupWithAnswers                 | true      |        25 |       0.4 |
| setupWithAnswers                 | false     |        26 |       0.3 |
| setupWithInterface               | false     |        26 |       0.3 |
| setupWithInterface               | true      |        26 |       0.3 |
| setupWithClassUsingInterface     | false     |        30 |       0.2 |
| setupWithClassUsingInterface     | true      |        31 |       0.2 |
| setupOnly                        | false     |       117 |       0.4 |
| setupOnly                        | true      |       118 |       0.4 |
| setupWithCalls                   | true      |       131 |       0.8 |
| setupWithCalls                   | false     |       132 |       0.8 |
| setupWithFirstArgCall            | true      |       205 |       3.9 |
| setupWithFirstArgCall            | false     |       209 |       3.7 |
| setupWithStaticInterface         | false     |       446 |       0.4 |
| setupWithStaticInterface         | true      |       446 |       0.6 |
| setupWithLoopCalls               | true      |      1004 |       8.2 |
| setupWithLoopCalls               | false     |      1009 |       8.8 |
| mockObjectAndCall                | false     |      1142 |       2.3 |
| mockObjectAndCall                | true      |      1143 |       1.5 |
| setupWithStaticAndCallsAndVerify | false     |      1745 |     548.1 |
| setupWithStaticAndCallsAndVerify | true      |      1767 |     558.5 |
| setupWithCallsAndVerify          | true      |      6396 |    2047.1 |
| setupWithCallsAndVerify          | false     |      6543 |    1911.3 |


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
