# Mockk Benchmark

A repo that contains microbenchmark results that compare the performance of different mockk configurations.

## Benchmark Details

These benchmarks were run on a machine with the following specs:
- Mac M1 Max
- 32GB of RAM

### Benchmark Results - Performance in ms
```csv
Benchmark,isRelaxed,Score_ms,Error_ms
mockWithDIAndCall,True,0.015,0.003
mockWithDIAndCall,False,0.017,0.006
setupWithInterface,False,0.021,0.002
setupWithInterface,True,0.021,0.005
setupWithAnswers,False,0.022,0.003
setupWithAnswers,True,0.023,0.006
setupWithClassUsingInterface,False,0.027,0.004
setupWithClassUsingInterface,True,0.028,0.005
setupOnly,True,0.084,0.004
setupOnly,False,0.086,0.011
setupWithCalls,True,0.101,0.007
setupWithCalls,False,0.105,0.013
setupWithFirstArgCall,False,0.165,0.014
setupWithFirstArgCall,True,0.167,0.014
setupWithLoopCalls,False,0.668,0.072
setupWithLoopCalls,True,0.692,0.145
setupWithCallsAndVerify,False,4.446,1.439
setupWithCallsAndVerify,True,4.506,1.417
setupWithStaticInterface,False,7.584,0.385
setupWithStaticInterface,True,8.052,0.957
setupWithStaticAndCallsAndVerify,True,12.504,1.327
setupWithStaticAndCallsAndVerify,False,12.593,1.437
mockObjectAndCall,False,28.539,6.304
mockObjectAndCall,True,30.008,6.701
```

### Benchmark Results - Memory Allocated in KB
```csv
Benchmark,isRelaxed,Score_kb,Error_kb
mockWithDIAndCall,False,15.866,0.119
mockWithDIAndCall,True,16.227,0.221
setupWithAnswers,True,25.441,0.381
setupWithAnswers,False,25.505,0.303
setupWithInterface,False,26.123,0.275
setupWithInterface,True,26.286,0.335
setupWithClassUsingInterface,False,30.437,0.192
setupWithClassUsingInterface,True,30.626,0.24
setupOnly,False,117.407,0.412
setupOnly,True,117.653,0.387
setupWithCalls,True,130.696,0.764
setupWithCalls,False,131.696,0.792
setupWithFirstArgCall,True,204.724,3.866
setupWithFirstArgCall,False,208.535,3.672
setupWithStaticInterface,False,445.543,0.357
setupWithStaticInterface,True,445.573,0.555
setupWithLoopCalls,True,1002.987,8.197
setupWithLoopCalls,False,1008.077,8.789
mockObjectAndCall,False,1141.574,2.345
mockObjectAndCall,True,1143.294,1.522
setupWithStaticAndCallsAndVerify,False,1744.438,548.886
setupWithStaticAndCallsAndVerify,True,1765.751,558.7
setupWithCallsAndVerify,True,6395.848,2048.256
setupWithCallsAndVerify,False,6543.169,1910.533
```

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