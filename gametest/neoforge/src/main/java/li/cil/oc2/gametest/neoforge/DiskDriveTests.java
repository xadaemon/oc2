/* SPDX-License-Identifier: MIT */

package li.cil.oc2.gametest.neoforge;

import li.cil.oc2.common.item.Items;
import li.cil.oc2.gametest.fixture.DiskDriveFixture;
import li.cil.oc2.gametest.util.Drops;
import net.minecraft.gametest.framework.GameTest;
import net.minecraft.gametest.framework.GameTestAssertException;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.gametest.GameTestHolder;
import net.neoforged.neoforge.gametest.PrefixGameTestTemplate;

import static li.cil.oc2.gametest.util.TestSupport.*;

@GameTestHolder(MOD_ID)
@PrefixGameTestTemplate(false)
public final class DiskDriveTests {
    @GameTest(template = TEMPLATE, timeoutTicks = 200)
    public static void breakingDiskDriveDropsItsFloppy(final GameTestHelper helper) {
        final DiskDriveFixture drive = DiskDriveFixture.place(helper);

        helper.startSequence()
            .thenExecuteAfter(20, () -> drive.insert(new ItemStack(Items.FLOPPY.get())))
            .thenExecuteAfter(10, () -> breakBlockAndDrop(helper, drive.pos()))
            .thenExecuteAfter(10, () -> {
                Drops.assertDropped(helper, Items.FLOPPY.get(), "the floppy");
                Drops.assertDropped(helper, Items.DISK_DRIVE.get(), "the drive itself");
            })
            .thenSucceed();
    }

    @GameTest(template = TEMPLATE, timeoutTicks = 100)
    public static void diskDriveOnlyReadsSingleSidedFloppies(final GameTestHelper helper) {
        final DiskDriveFixture drive = DiskDriveFixture.place(helper);

        helper.startSequence()
            .thenExecuteAfter(5, () -> {
                assertReads(drive, Items.FLOPPY.get(), true);
                assertReads(drive, Items.FLOPPY_DOUBLE_SIDED.get(), false);
                assertReads(drive, Items.FLOPPY_HIGH_DENSITY.get(), false);
            })
            .thenSucceed();
    }

    @GameTest(template = TEMPLATE, timeoutTicks = 100)
    public static void doubleSidedDiskDriveReadsUpToDoubleSidedFloppies(final GameTestHelper helper) {
        final DiskDriveFixture drive = DiskDriveFixture.place(helper, new ItemStack(Items.DISK_DRIVE_DOUBLE_SIDED.get()));

        helper.startSequence()
            .thenExecuteAfter(5, () -> {
                assertReads(drive, Items.FLOPPY.get(), true);
                assertReads(drive, Items.FLOPPY_DOUBLE_SIDED.get(), true);
                assertReads(drive, Items.FLOPPY_HIGH_DENSITY.get(), false);
            })
            .thenSucceed();
    }

    @GameTest(template = TEMPLATE, timeoutTicks = 200)
    public static void highDensityDiskDriveReadsAllFloppiesAndDropsItself(final GameTestHelper helper) {
        final DiskDriveFixture drive = DiskDriveFixture.place(helper, new ItemStack(Items.DISK_DRIVE_HIGH_DENSITY.get()));

        helper.startSequence()
            .thenExecuteAfter(5, () -> {
                assertReads(drive, Items.FLOPPY.get(), true);
                assertReads(drive, Items.FLOPPY_DOUBLE_SIDED.get(), true);
                assertReads(drive, Items.FLOPPY_HIGH_DENSITY.get(), true);
            })
            .thenExecuteAfter(5, () -> drive.insert(new ItemStack(Items.FLOPPY_HIGH_DENSITY.get())))
            .thenExecuteAfter(10, () -> breakBlockAndDrop(helper, drive.pos()))
            .thenExecuteAfter(10, () -> {
                Drops.assertDropped(helper, Items.FLOPPY_HIGH_DENSITY.get(), "the floppy");
                Drops.assertDropped(helper, Items.DISK_DRIVE_HIGH_DENSITY.get(), "the drive itself");
            })
            .thenSucceed();
    }

    // --------------------------------------------------------------------- //

    private static void assertReads(final DiskDriveFixture drive, final Item floppy, final boolean expected) {
        if (drive.blockEntity().canInsert(new ItemStack(floppy)) != expected) {
            throw new GameTestAssertException((expected ? "expected " : "did not expect ") +
                drive.blockEntity().getBlockState().getBlock() + " to read " + floppy);
        }
    }

    private DiskDriveTests() {
    }
}
