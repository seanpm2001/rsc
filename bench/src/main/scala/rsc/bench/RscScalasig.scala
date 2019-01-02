// Copyright (c) 2017-2019 Twitter, Inc.
// Licensed under the Apache License, Version 2.0 (see LICENSE.md).
package rsc.bench

import java.nio.file._
import java.util.concurrent.TimeUnit
import org.openjdk.jmh.annotations._
import org.openjdk.jmh.annotations.Mode._

@BenchmarkMode(Array(SampleTime))
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 3, time = 10, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 10, timeUnit = TimeUnit.SECONDS)
@Fork(value = 1, jvmArgs = Array("-Xms4G", "-Xmx4G"))
class RscScalasig extends RscBenchmark {
  @Benchmark
  def run(bs: BenchmarkState): Unit = {
    val rscOut = Files.createTempFile("rsc_", ".jar")
    runCompiler("-cp", bs.rscDeps, "-d", rscOut, "-artifacts", "semanticdb,scalasig", bs.files)
  }
}
