# Floppy
![It's not the floppy that bends](item:oc2:floppy)

Floppy disks are removable media. This means they can be inserted and removed from [disk drives](../block/disk_drive.md) without the [computer](../block/computer.md) using the disk drive having to reboot to use them.

Floppies come in three kinds, each needing a [disk drive](../block/disk_drive.md) that can read it. Better drives read all the smaller kinds, too:
- Floppy Disk: single-sided, 512K. Read by every disk drive.
- Double-Sided Floppy Disk: 1M, marked with a blue stripe. Needs a double-sided or high density disk drive.
- High Density Floppy Disk: the classic 1.4M, marked with red stripes and the high density hole. Needs a high density disk drive.

CP/M only uses the first 512K of a floppy, so larger floppies give a Z80 machine no extra room.

Crafting a blank floppy together with a [Z80 processor](cpu_z80.md) writes the CP/M system to it. That is the disk a Z80 machine boots from. Crafting a written floppy with a [wrench](wrench.md) erases it back to a blank one.

[Robots](robot.md) have a floppy slot, as they cannot use disk drives. Tier 1 and 2 robots read single-sided floppies, tier 3 robots read up to double-sided ones and tier 4 robots read all floppies.

Please see the [disk drive](../block/disk_drive.md) manual entry for some useful commands when working with floppies.

Storage media are not perfect, and a floppy left unused for a long time may suffer from bit rot and lose its contents. Format corrupted floppies using the [wrench](wrench.md). Interestingly, this only seems to happen when there are a lot of storage devices in the world.
