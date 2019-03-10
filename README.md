##Caching System

## Input file format

The first line in the input file must contain the cache policy type (FIFO, LRU,
TIME) and a parameter that can be either the capacity of the cache (for FIFO/LRU
cache) or the availability time for keys (for TimeAwareCache).

The rest of the lines are commands in the format `instruction argument` where:
   * delay -> adds a delay of `argument` miliseconds. You can use this to
	test `TimeAwareCache`.
   * get -> displays the `argument` file content. If the file is not in the
	cache, it will be first loaded, generating a miss and a hit.
   * top_hits / top_misses / top_updates -> outputs the first `argument` values
     for hits / misses / updates.
   * key_hits / key_misses / key_updates -> outputs hits / misses / updates
     for `argument` key. Uses KeyStatsListener.
   * total_hits / total_misses / total_updates -> outputs hist / misses / updates
     for the whole cache. Uses StatsListener.

You can find input files examples in the "tests" folder.

## Usage

Compile from terminal :

	make -f CachingMakefile

Run from terminal :

	java -cp src Main inputFile

