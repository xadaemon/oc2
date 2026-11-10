/* SPDX-License-Identifier: MIT */

package li.cil.oc2.common.vm;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import dev.architectury.registry.registries.RegistrySupplier;
import li.cil.oc2.common.Config;
import li.cil.oc2.common.item.AbstractStorageItem;
import li.cil.oc2.common.item.FloppyItem;
import li.cil.oc2.common.item.HardDriveItem;
import li.cil.oc2.common.item.Items;
import li.cil.oc2.common.item.MemoryItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.List;

/**
 * Tiers of computers and robots.
 * <p>
 * A tier decides how many memory, hard drive and expansion (card or module) slots a machine has,
 * the largest memory and hard drive it accepts, and how fast its processor runs. Tier 2 matches
 * the original computer and robot, so machines placed before tiers existed keep their layout.
 */
public enum MachineTier {
    TIER_1(1, new Slots(2, 2, 2), new Slots(2, 1, 2), 2, 2, 1, 0xD08A4E),
    TIER_2(2, new Slots(4, 4, 4), new Slots(4, 1, 4), 3, 3, 1, 0x4FC3C3),
    TIER_3(3, new Slots(4, 4, 4), new Slots(4, 2, 4), 5, 6, 2, 0x6F9BF2),
    TIER_4(4, new Slots(6, 6, 6), new Slots(6, 3, 6), 6, 8, 3, 0xC57FF2);

    /**
     * Slot counts of one kind of machine.
     *
     * @param memory    the number of memory slots.
     * @param hardDrive the number of hard drive slots.
     * @param expansion the number of card slots in computers, or module slots in robots.
     */
    public record Slots(int memory, int hardDrive, int expansion) {
    }

    // --------------------------------------------------------------------- //

    public static final Codec<MachineTier> CODEC = Codec.INT.comapFlatMap(
        level -> level >= 1 && level <= values().length
            ? DataResult.success(values()[level - 1])
            : DataResult.error(() -> "Unknown machine tier: " + level),
        MachineTier::getLevel);

    // --------------------------------------------------------------------- //

    private final int level;
    private final Slots computerSlots;
    private final Slots robotSlots;
    private final int maxMemoryLevel;
    private final int maxHardDriveLevel;
    private final int maxFloppyLevel;
    private final int color;

    // --------------------------------------------------------------------- //

    MachineTier(final int level, final Slots computerSlots, final Slots robotSlots, final int maxMemoryLevel, final int maxHardDriveLevel, final int maxFloppyLevel, final int color) {
        this.level = level;
        this.computerSlots = computerSlots;
        this.robotSlots = robotSlots;
        this.maxMemoryLevel = maxMemoryLevel;
        this.maxHardDriveLevel = maxHardDriveLevel;
        this.maxFloppyLevel = maxFloppyLevel;
        this.color = color;
    }

    // --------------------------------------------------------------------- //

    public int getLevel() {
        return level;
    }

    /**
     * The colour identifying this tier, as 0xRRGGBB.
     * <p>
     * Used for item names and tooltips. The machine textures and inventory screens use the same hues:
     * bronze, teal, blue and purple.
     */
    public int getColor() {
        return color;
    }

    public Slots getComputerSlots() {
        return computerSlots;
    }

    public Slots getRobotSlots() {
        return robotSlots;
    }

    /**
     * The largest memory this tier accepts.
     */
    public MemoryItem getLargestMemory() {
        return Items.MEMORIES.get(maxMemoryLevel - 1).get();
    }

    /**
     * The largest hard drive this tier accepts.
     */
    public HardDriveItem getLargestHardDrive() {
        return Items.HARD_DRIVES.get(maxHardDriveLevel - 1).get();
    }

    /**
     * The largest floppy the floppy slot of robots of this tier reads.
     */
    public FloppyItem getLargestFloppy() {
        return Items.FLOPPIES.get(maxFloppyLevel - 1).get();
    }

    public double getClockMultiplier() {
        return switch (this) {
            case TIER_1 -> Config.tier1ClockMultiplier;
            case TIER_2 -> Config.tier2ClockMultiplier;
            case TIER_3 -> Config.tier3ClockMultiplier;
            case TIER_4 -> Config.tier4ClockMultiplier;
        };
    }

    /**
     * Whether machines of this tier accept the specified item in their device slots.
     * <p>
     * Only memory, hard drives and floppies are limited by tier. Items not known to the tier list, e.g.
     * ones added by other mods, are always accepted.
     */
    public boolean accepts(final ItemStack stack) {
        final Item item = stack.getItem();
        if (item instanceof MemoryItem) {
            return isWithin(Items.MEMORIES, item, maxMemoryLevel);
        }
        if (item instanceof HardDriveItem) {
            return isWithin(Items.HARD_DRIVES, item, maxHardDriveLevel);
        }
        if (item instanceof FloppyItem) {
            return isWithin(Items.FLOPPIES, item, maxFloppyLevel);
        }
        return true;
    }

    // --------------------------------------------------------------------- //

    private static boolean isWithin(final List<? extends RegistrySupplier<? extends AbstractStorageItem>> tiers, final Item item, final int maxLevel) {
        for (int i = 0; i < tiers.size(); i++) {
            if (tiers.get(i).get() == item) {
                return i < maxLevel;
            }
        }
        return true;
    }
}
