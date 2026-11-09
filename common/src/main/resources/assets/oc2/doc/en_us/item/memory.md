# Memory
![Random Access Memories](item:oc2:memory_large)

The random access memory (RAM) device provides working memory to [computers](../block/computer.md) and [robots](robot.md).

As opposed to [hard drives](hard_drive.md), this memory is volatile and will be cleared when a computer powers down. It is theoretically possible for computers to function without RAM, by software only using the stack for data storage. However, typically software requires RAM to function. In particular, the [firmware](flash_memory.md) contained in newly obtained computers requires a decent amount to function.

How much memory is needed depends on the operating system. OpenOS II runs with a single 2M module, Linux needs at least 8M, and CP/M works with any module. Also see the [getting started](../systems/index.md) manual entry.

Memory modules come in 2M, 4M, 8M, 16M, 32M and 64M sizes. The 16M, 32M and 64M modules are high capacity memory, which the server can disable with the `highMemoryMachines` setting. Which modules a machine accepts depends on its tier: tier 1 [computers](../block/computer.md) and [robots](robot.md) take up to 4M modules, tier 2 up to 8M, tier 3 up to 32M and tier 4 up to 64M. A tier 4 machine with all six slots filled with 64M modules has 384M of RAM.

Computers *have to be shut down* before installing or removing this component. Installing it while the computer is running will have no effect, removing it may lead to system errors.
