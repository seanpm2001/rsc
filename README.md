<!-- Copyright (c) 2017-2018 Twitter, Inc. -->
<!-- Licensed under the Apache License, Version 2.0 (see LICENSE.md). -->
<!-- Autogenerated by https://github.com/twitter/rsc/tree/006753bef1abb938977222768fc5a1033a1cef2e/bin/bench_upload from https://github.com/twitter/rsc/tree/006753bef1abb938977222768fc5a1033a1cef2e/README.tmpl. -->

# Rsc Performance

Rsc is an experimental Scala compiler focused on compilation speed.
This project is developed by Eugene Burmako and his team at Twitter.

In this document, we aim to publish the results of running our benchmark suite
on the most recent commit in our repository. Since running benchmarks takes time,
there may be short periods of time when this document is out of date. If you're
curious about the exact version of Rsc that is benchmarked in this document,
[click here](https://github.com/twitter/rsc/commit/b63d55cd2d0c26d2386e15312728eef465e25bad).

## Hardware

All benchmarks run on a computer with
Intel(R) Core(TM) i7-4770 CPU @ 3.40GHz (4x32KB+32KB L1 cache,
4x256KB L2 cache, 8192KB L3 cache,
configured to run 4 physical cores and
4 logical cores with Turbo Boost disabled),
31GB RAM and Crucial_CT240M50 disk drive.

## Software

In our benchmarks, we use Debian GNU/Linux 9.4 (stretch) and Java(TM) SE Runtime Environment (build 1.8.0_171-b11)
to run [Rsc 0.0.0-137-b63d55cd](https://github.com/twitter/rsc/commit/b63d55cd2d0c26d2386e15312728eef465e25bad) and Scalac 2.11.12.
To benchmark JVM applications, we use sbt-jmh 0.2.27 that runs in sbt 0.13.17.

Our benchmarks run different fragments of compilation pipelines of
different compilers on https://github.com/twitter/rsc/tree/b63d55cd2d0c26d2386e15312728eef465e25bad/examples/core
that contains 96 files totaling 20143 lines of code.

Every benchmark runs in in hot mode, i.e. it computes performance of a steady
state of the JVM achieved by doing a number of warmup runs in JMH.

## Disclaimer

At this point, Rsc only implements a subset of functionality provided by the
Scala compiler. This means that the benchmark results provided below must
be interpreted with utmost care. Concretely:
  * Performance numbers may significantly deteriorate as we will be
    implementing more and more functionality of the Scala compiler.
    For example, adding support for implicit search or type inference
    is very likely to slow down our compiler by a significant factor.
  * Direct comparisons of Rsc and Scalac performance numbers should take
    into account similarities and differences in provided functionality.
    Consult [the summary in the "Compiler" document](https://github.com/twitter/rsc/tree/b63d55cd2d0c26d2386e15312728eef465e25bad/docs/compiler.md#summary)
    for more information.

## Results

To reproduce, run `bin/bench` (this will take a while).

<table>
  <th>
    <td>Individual</td>
    <td>Cumulative</td>
  </th>
  <tr>
    <td width="208px"><a href="https://github.com/twitter/rsc/tree/b63d55cd2d0c26d2386e15312728eef465e25bad/bench/src/main/scala/rsc/bench/RscParse.scala">RscParse</a></td>
    <td width="208px">19.492 ms</td>
    <td width="208px">19.492 ms</td>
  </tr>
  <tr>
    <td><a href="https://github.com/twitter/rsc/tree/b63d55cd2d0c26d2386e15312728eef465e25bad/bench/src/main/scala/rsc/bench/RscLink.scala">RscLink</a></td>
    <td>52.996 ms</td>
    <td>72.488 ms</td>
  </tr>
  <tr>
    <td><a href="https://github.com/twitter/rsc/tree/b63d55cd2d0c26d2386e15312728eef465e25bad/bench/src/main/scala/rsc/bench/RscOutline.scala">RscOutline</a></td>
    <td>40.158 ms</td>
    <td>112.646 ms</td>
  </tr>
  <tr>
    <td><a href="https://github.com/twitter/rsc/tree/b63d55cd2d0c26d2386e15312728eef465e25bad/bench/src/main/scala/rsc/bench/RscSemanticdb.scala">RscSemanticdb</a></td>
    <td>23.277 ms</td>
    <td>135.923 ms</td>
  </tr>
  <tr>
    <td><a href="https://github.com/twitter/rsc/tree/b63d55cd2d0c26d2386e15312728eef465e25bad/bench/src/main/scala/rsc/bench/RscMjar.scala">RscMjar</a></td>
    <td>292.624 ms</td>
    <td>428.547 ms</td>
  </tr>
  <tr>
    <td><a href="https://github.com/twitter/rsc/tree/b63d55cd2d0c26d2386e15312728eef465e25bad/bench/src/main/scala/rsc/bench/ScalacCompile.scala">ScalacCompile</a></td>
    <td>3854.565 ms</td>
    <td>3854.565 ms</td>
  </tr>
</table>

## Summary

At the moment, generating SemanticDB signatures from an automatically rewritten core of [Twitter Util](https://github.com/twitter/util)
is ~28x faster than full Scalac compilation.
Generating ScalaSignatures is ~9x faster.
