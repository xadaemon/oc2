# Hard Drive
![Going in circles](item:oc2:hard_drive_large)

Hard drives are the main storage device used for data storage in [computers](../block/computer.md) and [robots](robot.md).

Usually computers use a firmware that requires some hard drive to complete the boot process. See the [flash memory](flash_memory.md) entry for more details. Crafting a hard drive together with a [RISC-V processor](cpu_riscv.md) writes an operating system to it:
- 2M hard drive: [OpenOS II](../systems/openos2.md).
- 8M hard drive: [Linux](../systems/linux.md).

Crafting either of these drives with a [wrench](wrench.md) wipes it back to an empty drive.

Every hard drive is built around a platter stack of its size, made by stacking platters around an iron spindle:
- Ferrite platters, made from a layer of redstone over a layer of iron ingots, stack into 2M (2 platters), 4M (4) and 8M (8) stacks.
- Platters, made from a layer of gold ingots, a layer of redstone and a layer of iron ingots, stack into 16M (2 platters), 32M (4) and 64M (8) stacks.
- High capacity platters add netherite scrap and stack into 128M stacks (4 platters).
- Ultra capacity platters add a netherite ingot and stack into 256M stacks (4 platters).

Larger drives need higher tier machines: tier 1 [computers](../block/computer.md) and [robots](robot.md) take up to 4M drives, tier 2 up to 8M, tier 3 up to 64M and tier 4 up to 256M. The server can disable the 16M and larger drives, along with their platters and stacks, with the `highMemoryMachines` setting. Wipe any of these drives by crafting it with a [wrench](wrench.md).

OpenOS II and Linux show additional hard drives as `/dev/vdb`, `/dev/vdc` and so on. See the guides on how to format and mount them. CP/M does not support hard drives.

Computers *have to be shut down* before installing or removing this component. Installing it while the computer is running will have no effect, removing it may lead to system errors.

Storage media are not perfect, and a drive left unused for a long time may suffer from bit rot and lose its contents. Format corrupted drives using the [wrench](wrench.md). Interestingly, this only seems to happen when there are a lot of storage devices in the world.
