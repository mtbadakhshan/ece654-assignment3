# ece654-assignment3

[![Build Status](https://app.travis-ci.com/mtbadakhshan/ece654-assignment3.svg?branch=main)](https://app.travis-ci.com/mtbadakhshan/ece654-assignment3)

On the src directory run the following command to compile qualifiers:

`../checker-framework-3.16.0/checker/bin/javac src/myqual/verified/PossiblyUnverified.java src/myqual/verified/Verified.java`

On the src directory run the following command to to run type checks:
`../checker-framework-3.16.0/checker/bin/javac -processor org.checkerframework.common.subtyping.SubtypingChecker -Aquals=myqual.verified.PossiblyUnverified,myqual.verified.Verified FullNode.java `
