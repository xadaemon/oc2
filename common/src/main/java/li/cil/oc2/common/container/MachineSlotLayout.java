/* SPDX-License-Identifier: MIT */

package li.cil.oc2.common.container;

import li.cil.oc2.common.vm.MachineTier;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/**
 * Where a machine's device slots sit in its inventory screen, per tier.
 * <p>
 * The inventory screen backgrounds are drawn to match these positions, so the two have to be
 * changed together. Tiers 2 to 4 of the computer, and tier 2 of the robot, keep the original
 * layout. Tiers 3 and 4 of the robot add a second hard drive slot below the first.
 *
 * @param cpu              the processor slot.
 * @param flashMemory      the flash memory slot.
 * @param memory           the memory slots.
 * @param hardDrives       the hard drive slots.
 * @param expansion        the card slots of computers, or module slots of robots.
 * @param floppy           the floppy slot, robots only.
 * @param playerInventoryY the top of the player inventory.
 * @param height           the height of the screen background.
 */
public record MachineSlotLayout(
    Position cpu,
    Position flashMemory,
    List<Position> memory,
    List<Position> hardDrives,
    List<Position> expansion,
    @Nullable Position floppy,
    int playerInventoryY,
    int height
) {
    public record Position(int x, int y) {
    }

    // --------------------------------------------------------------------- //

    private static final int SLOT_SIZE = 18;
    private static final int DEFAULT_PLAYER_INVENTORY_Y = 115;
    private static final int DEFAULT_HEIGHT = 197;

    // --------------------------------------------------------------------- //

    public static MachineSlotLayout computer(final MachineTier tier) {
        final MachineTier.Slots slots = tier.getComputerSlots();
        return new MachineSlotLayout(
            new Position(64, 52),
            new Position(64, 78),
            row(64, 24, slots.memory()),
            slots.hardDrive() <= 2 ? row(100, 60, slots.hardDrive()) : grid(100, 60, 2, slots.hardDrive()),
            column(38, 24, slots.expansion()),
            null,
            DEFAULT_PLAYER_INVENTORY_Y,
            DEFAULT_HEIGHT);
    }

    public static MachineSlotLayout robot(final MachineTier tier) {
        final MachineTier.Slots slots = tier.getRobotSlots();
        return new MachineSlotLayout(
            new Position(34, 52),
            new Position(34, 78),
            row(34, 24, slots.memory()),
            column(70, 60, slots.hardDrive()),
            column(8, 24, slots.expansion()),
            new Position(88, 60),
            DEFAULT_PLAYER_INVENTORY_Y,
            DEFAULT_HEIGHT);
    }

    // --------------------------------------------------------------------- //

    private static List<Position> row(final int x, final int y, final int count) {
        return grid(x, y, Math.max(count, 1), count);
    }

    private static List<Position> column(final int x, final int y, final int count) {
        return grid(x, y, 1, count);
    }

    private static List<Position> grid(final int x, final int y, final int columns, final int count) {
        final List<Position> result = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            result.add(new Position(x + i % columns * SLOT_SIZE, y + i / columns * SLOT_SIZE));
        }
        return List.copyOf(result);
    }
}
