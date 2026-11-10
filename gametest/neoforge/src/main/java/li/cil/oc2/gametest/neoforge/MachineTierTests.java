/* SPDX-License-Identifier: MIT */

package li.cil.oc2.gametest.neoforge;

import li.cil.oc2.api.bus.device.DeviceType;
import li.cil.oc2.api.bus.device.DeviceTypes;
import li.cil.oc2.common.inventory.ItemHandler;
import li.cil.oc2.common.item.Items;
import li.cil.oc2.common.vm.MachineTier;
import li.cil.oc2.gametest.fixture.ComputerFixture;
import li.cil.oc2.gametest.fixture.MachineFixture;
import li.cil.oc2.gametest.fixture.RobotFixture;
import li.cil.oc2.gametest.util.Drops;
import net.minecraft.gametest.framework.GameTest;
import net.minecraft.gametest.framework.GameTestAssertException;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.gametest.GameTestHolder;
import net.neoforged.neoforge.gametest.PrefixGameTestTemplate;

import static li.cil.oc2.gametest.util.TestSupport.*;

@GameTestHolder(MOD_ID)
@PrefixGameTestTemplate(false)
public final class MachineTierTests {
    @GameTest(template = TEMPLATE, timeoutTicks = 100)
    public static void tier1ComputerHasFewerSlotsAndSmallerLimits(final GameTestHelper helper) {
        final ComputerFixture computer = ComputerFixture.place(helper, fakePlayer(helper), COMPUTER_POS,
            new ItemStack(Items.COMPUTER_TIER_1.get()));
        helper.startSequence()
            .thenExecuteAfter(5, () -> {
                assertSlots(computer, DeviceTypes.MEMORY.get(), 2);
                assertSlots(computer, DeviceTypes.HARD_DRIVE.get(), 2);
                assertSlots(computer, DeviceTypes.CARD.get(), 2);
                assertAccepts(computer, DeviceTypes.MEMORY.get(), Items.MEMORY_MEDIUM.get(), true);
                assertAccepts(computer, DeviceTypes.MEMORY.get(), Items.MEMORY_LARGE.get(), false);
                assertAccepts(computer, DeviceTypes.HARD_DRIVE.get(), Items.HARD_DRIVE_MEDIUM.get(), true);
                assertAccepts(computer, DeviceTypes.HARD_DRIVE.get(), Items.HARD_DRIVE_LARGE.get(), false);
            })
            .thenSucceed();
    }

    @GameTest(template = TEMPLATE, timeoutTicks = 100)
    public static void tier2ComputerKeepsOriginalLayout(final GameTestHelper helper) {
        final ComputerFixture computer = ComputerFixture.place(helper);
        helper.startSequence()
            .thenExecuteAfter(5, () -> {
                assertSlots(computer, DeviceTypes.MEMORY.get(), 4);
                assertSlots(computer, DeviceTypes.HARD_DRIVE.get(), 4);
                assertSlots(computer, DeviceTypes.CARD.get(), 4);
                assertAccepts(computer, DeviceTypes.MEMORY.get(), Items.MEMORY_LARGE.get(), true);
                assertAccepts(computer, DeviceTypes.MEMORY.get(), Items.MEMORY_HUGE.get(), false);
                assertAccepts(computer, DeviceTypes.HARD_DRIVE.get(), Items.HARD_DRIVE_HUGE.get(), false);
            })
            .thenSucceed();
    }

    @GameTest(template = TEMPLATE, timeoutTicks = 100)
    public static void tier4ComputerHasSixSlotsAndTakesTheLargestParts(final GameTestHelper helper) {
        final ComputerFixture computer = ComputerFixture.place(helper, fakePlayer(helper), COMPUTER_POS,
            new ItemStack(Items.COMPUTER_TIER_4.get()));
        helper.startSequence()
            .thenExecuteAfter(5, () -> {
                assertSlots(computer, DeviceTypes.MEMORY.get(), 6);
                assertSlots(computer, DeviceTypes.HARD_DRIVE.get(), 6);
                assertSlots(computer, DeviceTypes.CARD.get(), 6);
                assertAccepts(computer, DeviceTypes.MEMORY.get(), Items.MEMORY_COLOSSAL.get(), true);
                assertAccepts(computer, DeviceTypes.HARD_DRIVE.get(), Items.HARD_DRIVE_GARGANTUAN.get(), true);
                helper.assertTrue(computer.blockEntity().getTier() == MachineTier.TIER_4, "expected a tier 4 computer");
            })
            .thenSucceed();
    }

    @GameTest(template = TEMPLATE, timeoutTicks = 300)
    public static void breakingTieredComputerDropsSameTierWithContents(final GameTestHelper helper) {
        final Player player = fakePlayer(helper);
        final ComputerFixture computer = ComputerFixture.place(helper, player, COMPUTER_POS,
            new ItemStack(Items.COMPUTER_TIER_4.get()));
        helper.startSequence()
            .thenExecuteAfter(10, () -> computer.install(DeviceTypes.MEMORY.get(), new ItemStack(Items.MEMORY_COLOSSAL.get())))
            .thenExecuteAfter(10, () -> breakBlockAndDrop(helper, computer.pos()))
            .thenExecuteAfter(10, () -> {
                final ItemStack dropped = Drops.single(helper);
                if (!dropped.is(Items.COMPUTER_TIER_4.get())) {
                    throw new GameTestAssertException("expected a tier 4 computer item, got " + dropped);
                }

                helper.killAllEntities();
                place(helper, player, dropped, computer.pos());
            })
            .thenExecuteAfter(20, () -> helper.assertTrue(
                computer.handler(DeviceTypes.MEMORY.get()).getStackInSlot(0).is(Items.MEMORY_COLOSSAL.get()),
                "memory was not restored after re-placing the tier 4 computer"))
            .thenSucceed();
    }

    @GameTest(template = TEMPLATE, timeoutTicks = 100)
    public static void tier1RobotHasFewerSlotsAndSmallerLimits(final GameTestHelper helper) {
        final RobotFixture robot = RobotFixture.place(helper, fakePlayer(helper), ROBOT_POS,
            new ItemStack(Items.ROBOT_TIER_1.get()));
        helper.startSequence()
            .thenExecuteAfter(5, () -> {
                helper.assertTrue(robot.entity().getTier() == MachineTier.TIER_1, "expected a tier 1 robot");
                assertSlots(robot, DeviceTypes.MEMORY.get(), 2);
                assertSlots(robot, DeviceTypes.HARD_DRIVE.get(), 1);
                assertSlots(robot, DeviceTypes.ROBOT_MODULE.get(), 2);
                assertAccepts(robot, DeviceTypes.MEMORY.get(), Items.MEMORY_LARGE.get(), false);
                assertAccepts(robot, DeviceTypes.FLOPPY.get(), Items.FLOPPY.get(), true);
                assertAccepts(robot, DeviceTypes.FLOPPY.get(), Items.FLOPPY_DOUBLE_SIDED.get(), false);
            })
            .thenSucceed();
    }

    @GameTest(template = TEMPLATE, timeoutTicks = 100)
    public static void tier4RobotHasMoreSlotsAndTakesTheLargestParts(final GameTestHelper helper) {
        final RobotFixture robot = RobotFixture.place(helper, fakePlayer(helper), ROBOT_POS,
            new ItemStack(Items.ROBOT_TIER_4.get()));
        helper.startSequence()
            .thenExecuteAfter(5, () -> {
                helper.assertTrue(robot.entity().getTier() == MachineTier.TIER_4, "expected a tier 4 robot");
                assertSlots(robot, DeviceTypes.MEMORY.get(), 6);
                assertSlots(robot, DeviceTypes.HARD_DRIVE.get(), 3);
                assertSlots(robot, DeviceTypes.ROBOT_MODULE.get(), 6);
                assertAccepts(robot, DeviceTypes.MEMORY.get(), Items.MEMORY_COLOSSAL.get(), true);
                assertAccepts(robot, DeviceTypes.HARD_DRIVE.get(), Items.HARD_DRIVE_GARGANTUAN.get(), true);
                assertAccepts(robot, DeviceTypes.FLOPPY.get(), Items.FLOPPY_HIGH_DENSITY.get(), true);
            })
            .thenSucceed();
    }

    // --------------------------------------------------------------------- //

    private static void assertSlots(final MachineFixture machine, final DeviceType type, final int expected) {
        final int actual = machine.handler(type).getSlots();
        if (actual != expected) {
            throw new GameTestAssertException("expected " + expected + " " + type + " slots, got " + actual);
        }
    }

    private static void assertAccepts(final MachineFixture machine, final DeviceType type, final Item item, final boolean expected) {
        final ItemHandler handler = machine.handler(type);
        final ItemStack stack = new ItemStack(item);
        final boolean accepted = handler.insertItem(0, stack, true).getCount() < stack.getCount();
        if (accepted != expected) {
            throw new GameTestAssertException((expected ? "expected " : "did not expect ") + type + " slot to accept " + item);
        }
    }
}
