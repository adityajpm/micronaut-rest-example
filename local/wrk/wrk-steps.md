### wrk github
```
https://github.com/wg/wrk
```

### Installation
```
brew install wrk
```

#### Pre-steps
 ```
 <project.dir> ./gradlew run
 ```

#### Notes
```
wrk -t12 -c400 -d30s --latency http://127.0.0.1:8080/index.html
```
This runs a benchmark for 30 seconds, using 12 threads, and keeping 400 HTTP connections open.

#### Warm up - first run
```
wrk -t6 -c40 -d5s -s ./service.lua --latency http://localhost:8080/transfer x 3
```

#### Actual test - second run
```
wrk -t20 -c200 -d30s -s ./service.lua --latency http://localhost:8080/transfer
```

### small test
```
wrk -t1 -c1 -d1s -s ./service.lua --latency http://localhost:50165/transfer
```