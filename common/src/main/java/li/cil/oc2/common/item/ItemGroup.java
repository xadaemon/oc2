/* SPDX-License-Identifier: MIT */

package li.cil.oc2.common.item;

import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import li.cil.oc2.api.API;
import li.cil.oc2.common.util.RegistryUtils;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public final class ItemGroup {
    private static final DeferredRegister<CreativeModeTab> TABS = RegistryUtils.getInitializerFor(Registries.CREATIVE_MODE_TAB);

    // --------------------------------------------------------------------- //

    public static final RegistrySupplier<CreativeModeTab> COMMON = TABS.register("common", () ->
        CreativeTabRegistry.create(builder -> {
            builder.icon(() -> new ItemStack(Items.COMPUTER.get()));
            builder.title(Component.translatable("itemGroup." + API.MOD_ID + ".common"));
            builder.displayItems((parameters, output) -> getDisplayOrder().forEach(item -> addItem(item, parameters, output)));
        }));

    // --------------------------------------------------------------------- //

    public static void initialize() {
    }

    /**
     * The items of the creative tab, grouped by kind. Within a group, tiers go from smallest to largest.
     * <p>
     * Items missing from these groups still show up, after all groups, sorted by name. Add new items to
     * the group they belong to.
     */
    public static List<List<RegistrySupplier<? extends Item>>> getGroups() {
        return List.of(
            // Tools
            List.of(Items.MANUAL, Items.WRENCH),
            // Machines
            concat(List.of(Items.COMPUTER_TIER_1, Items.COMPUTER, Items.COMPUTER_TIER_3, Items.COMPUTER_TIER_4), Items.ROBOTS),
            // Bus and power
            List.of(Items.BUS_CABLE, Items.BUS_INTERFACE, Items.CHARGER, Items.CREATIVE_ENERGY),
            // Peripherals
            List.of(Items.DISK_DRIVE, Items.DISK_DRIVE_DOUBLE_SIDED, Items.DISK_DRIVE_HIGH_DENSITY, Items.FLASH_DRIVE,
                Items.KEYBOARD, Items.TERMINAL, Items.PROJECTOR, Items.REDSTONE_INTERFACE, Items.TRANSPOSER),
            // Networking
            List.of(Items.NETWORK_CABLE, Items.NETWORK_CONNECTOR, Items.NETWORK_HUB, Items.INTERNET_GATEWAY),
            // Processors and memory
            concat(List.of(Items.CPU_RISCV, Items.CPU_Z80), Items.MEMORIES),
            // Storage
            concat(Items.HARD_DRIVES, List.of(Items.FLASH_MEMORY), Items.FLOPPIES),
            // Cards
            List.of(Items.FILE_IMPORT_EXPORT_CARD, Items.NETWORK_INTERFACE_CARD, Items.NETWORK_TUNNEL_CARD,
                Items.REDSTONE_INTERFACE_CARD, Items.SERIAL_INTERFACE_CARD, Items.SOUND_CARD),
            // Robot modules
            List.of(Items.BLOCK_OPERATIONS_MODULE, Items.CRAFTING_MODULE, Items.INVENTORY_OPERATIONS_MODULE,
                Items.NETWORK_TUNNEL_MODULE, Items.SCANNER_MODULE, Items.TANK_OPERATIONS_MODULE),
            // Crafting materials
            List.of(Items.TRANSISTOR, Items.CIRCUIT_BOARD,
                Items.HARD_DRIVE_PLATTER_FERRITE, Items.HARD_DRIVE_PLATTER,
                Items.HARD_DRIVE_PLATTER_HIGH_CAPACITY, Items.HARD_DRIVE_PLATTER_ULTRA_CAPACITY,
                Items.HARD_DRIVE_PLATTER_STACK_SMALL, Items.HARD_DRIVE_PLATTER_STACK_MEDIUM,
                Items.HARD_DRIVE_PLATTER_STACK_LARGE, Items.HARD_DRIVE_PLATTER_STACK_HUGE,
                Items.HARD_DRIVE_PLATTER_STACK_GIANT, Items.HARD_DRIVE_PLATTER_STACK_COLOSSAL,
                Items.HARD_DRIVE_PLATTER_STACK_TITANIC, Items.HARD_DRIVE_PLATTER_STACK_GARGANTUAN)
        );
    }

    /**
     * Items of this mod that are not in any of the {@link #getGroups() groups}, sorted by name.
     */
    public static List<Item> getUngroupedItems() {
        final Set<Item> grouped = getGroupedItems();
        return BuiltInRegistries.ITEM.stream()
            .filter(item -> API.MOD_ID.equals(BuiltInRegistries.ITEM.getKey(item).getNamespace()))
            .filter(item -> !grouped.contains(item))
            .sorted(Comparator.comparing(item -> item.getDescription().getString(), String.CASE_INSENSITIVE_ORDER))
            .toList();
    }

    // --------------------------------------------------------------------- //

    private static List<Item> getDisplayOrder() {
        final List<Item> result = new ArrayList<>(getGroupedItems());
        result.addAll(getUngroupedItems());
        return result;
    }

    private static Set<Item> getGroupedItems() {
        final Set<Item> result = new LinkedHashSet<>();
        for (final var group : getGroups()) {
            for (final var item : group) {
                result.add(item.get());
            }
        }
        return result;
    }

    @SafeVarargs
    private static List<RegistrySupplier<? extends Item>> concat(final List<? extends RegistrySupplier<? extends Item>>... lists) {
        final List<RegistrySupplier<? extends Item>> result = new ArrayList<>();
        for (final var list : lists) {
            result.addAll(list);
        }
        return result;
    }

    private static void addItem(final Item item, final CreativeModeTab.ItemDisplayParameters parameters, final CreativeModeTab.Output output) {
        if (item instanceof final CreativeTabItemProvider provider) {
            provider.addCreativeTabItems(parameters, output);
        } else {
            output.accept(new ItemStack(item));
        }
    }

    private ItemGroup() {
    }
}
