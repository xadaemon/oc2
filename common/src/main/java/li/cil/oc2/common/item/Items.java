/* SPDX-License-Identifier: MIT */

package li.cil.oc2.common.item;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import li.cil.oc2.api.bus.device.vm.ArchitectureType;
import li.cil.oc2.common.Config;
import li.cil.oc2.common.Constants;
import li.cil.oc2.common.block.Blocks;
import li.cil.oc2.common.util.RegistryUtils;
import li.cil.oc2.common.vm.MachineTier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public final class Items {
    private static final DeferredRegister<Item> ITEMS = RegistryUtils.getInitializerFor(Registries.ITEM);

    // --------------------------------------------------------------------- //

    public static final RegistrySupplier<Item> BUS_CABLE = register(Blocks.BUS_CABLE, BusCableItem::new);
    public static final RegistrySupplier<BusInterfaceItem> BUS_INTERFACE = register("bus_interface", BusInterfaceItem::new);
    public static final RegistrySupplier<Item> CHARGER = register(Blocks.CHARGER, ChargerItem::new);
    public static final RegistrySupplier<Item> COMPUTER_TIER_1 = register(Blocks.COMPUTER_TIER_1, ComputerItem::new);
    public static final RegistrySupplier<Item> COMPUTER = register(Blocks.COMPUTER, ComputerItem::new);
    public static final RegistrySupplier<Item> COMPUTER_TIER_3 = register(Blocks.COMPUTER_TIER_3, ComputerItem::new);
    public static final RegistrySupplier<Item> COMPUTER_TIER_4 = register(Blocks.COMPUTER_TIER_4, ComputerItem::new);
    public static final RegistrySupplier<Item> CREATIVE_ENERGY = register(Blocks.CREATIVE_ENERGY);
    public static final RegistrySupplier<Item> DISK_DRIVE = register(Blocks.DISK_DRIVE);
    public static final RegistrySupplier<Item> FLASH_DRIVE = register(Blocks.FLASH_DRIVE);
    public static final RegistrySupplier<Item> KEYBOARD = register(Blocks.KEYBOARD);
    public static final RegistrySupplier<Item> NETWORK_CONNECTOR = register(Blocks.NETWORK_CONNECTOR);
    public static final RegistrySupplier<Item> NETWORK_HUB = register(Blocks.NETWORK_HUB);
    public static final RegistrySupplier<Item> INTERNET_GATEWAY = register(Blocks.INTERNET_GATEWAY);
    public static final RegistrySupplier<Item> PROJECTOR = register(Blocks.PROJECTOR);
    public static final RegistrySupplier<Item> REDSTONE_INTERFACE = register(Blocks.REDSTONE_INTERFACE);
    public static final RegistrySupplier<Item> TERMINAL = register(Blocks.TERMINAL);
    public static final RegistrySupplier<Item> TRANSPOSER = register(Blocks.TRANSPOSER);

    // --------------------------------------------------------------------- //

    public static final RegistrySupplier<Item> WRENCH = register("wrench", WrenchItem::new);
    public static final RegistrySupplier<Item> MANUAL = register("manual", ManualItem::new);

    public static final RegistrySupplier<Item> ROBOT_TIER_1 = register("robot_tier_1", () -> new RobotItem(MachineTier.TIER_1));
    public static final RegistrySupplier<Item> ROBOT = register("robot", () -> new RobotItem(MachineTier.TIER_2));
    public static final RegistrySupplier<Item> ROBOT_TIER_3 = register("robot_tier_3", () -> new RobotItem(MachineTier.TIER_3));
    public static final RegistrySupplier<Item> ROBOT_TIER_4 = register("robot_tier_4", () -> new RobotItem(MachineTier.TIER_4));

    /**
     * All robot items, ordered by tier.
     */
    public static final List<RegistrySupplier<Item>> ROBOTS = List.of(ROBOT_TIER_1, ROBOT, ROBOT_TIER_3, ROBOT_TIER_4);
    public static final RegistrySupplier<NetworkCableItem> NETWORK_CABLE = register("network_cable", NetworkCableItem::new);

    public static final RegistrySupplier<CpuItem> CPU_RISCV = register("cpu_riscv", () ->
        new CpuItem(ArchitectureType.RISCV));
    public static final RegistrySupplier<CpuItem> CPU_Z80 = register("cpu_z80", () ->
        new CpuItem(ArchitectureType.Z80));

    public static final RegistrySupplier<MemoryItem> MEMORY_SMALL = register("memory_small", () ->
        new MemoryItem(() -> Config.memorySmallSize));
    public static final RegistrySupplier<MemoryItem> MEMORY_MEDIUM = register("memory_medium", () ->
        new MemoryItem(() -> Config.memoryMediumSize));
    public static final RegistrySupplier<MemoryItem> MEMORY_LARGE = register("memory_large", () ->
        new MemoryItem(() -> Config.memoryLargeSize));
    public static final RegistrySupplier<MemoryItem> MEMORY_HUGE = register("memory_huge", () ->
        new MemoryItem(() -> Config.memoryHugeSize, true));
    public static final RegistrySupplier<MemoryItem> MEMORY_GIANT = register("memory_giant", () ->
        new MemoryItem(() -> Config.memoryGiantSize, true));
    public static final RegistrySupplier<MemoryItem> MEMORY_COLOSSAL = register("memory_colossal", () ->
        new MemoryItem(() -> Config.memoryColossalSize, true));

    public static final RegistrySupplier<HardDriveItem> HARD_DRIVE_SMALL = register("hard_drive_small", () ->
        new HardDriveItem(() -> Config.hardDriveSmallSize, DyeColor.LIGHT_GRAY));
    public static final RegistrySupplier<HardDriveItem> HARD_DRIVE_MEDIUM = register("hard_drive_medium", () ->
        new HardDriveItem(() -> Config.hardDriveMediumSize, DyeColor.GREEN));
    public static final RegistrySupplier<HardDriveItem> HARD_DRIVE_LARGE = register("hard_drive_large", () ->
        new HardDriveItem(() -> Config.hardDriveLargeSize, DyeColor.CYAN));
    public static final RegistrySupplier<HardDriveItem> HARD_DRIVE_HUGE = register("hard_drive_huge", () ->
        new HardDriveItem(() -> Config.hardDriveHugeSize, DyeColor.BLUE, true));
    public static final RegistrySupplier<HardDriveItem> HARD_DRIVE_GIANT = register("hard_drive_giant", () ->
        new HardDriveItem(() -> Config.hardDriveGiantSize, DyeColor.PURPLE, true));
    public static final RegistrySupplier<HardDriveItem> HARD_DRIVE_COLOSSAL = register("hard_drive_colossal", () ->
        new HardDriveItem(() -> Config.hardDriveColossalSize, DyeColor.MAGENTA, true));
    public static final RegistrySupplier<HardDriveItem> HARD_DRIVE_TITANIC = register("hard_drive_titanic", () ->
        new HardDriveItem(() -> Config.hardDriveTitanicSize, DyeColor.ORANGE, true));
    public static final RegistrySupplier<HardDriveItem> HARD_DRIVE_GARGANTUAN = register("hard_drive_gargantuan", () ->
        new HardDriveItem(() -> Config.hardDriveGargantuanSize, DyeColor.RED, true));

    /**
     * All memory, ordered by capacity. The index decides which machine tiers accept it.
     */
    public static final List<RegistrySupplier<MemoryItem>> MEMORIES =
        List.of(MEMORY_SMALL, MEMORY_MEDIUM, MEMORY_LARGE, MEMORY_HUGE, MEMORY_GIANT, MEMORY_COLOSSAL);

    /**
     * All hard drives, ordered by capacity. The index decides which machine tiers accept it.
     */
    public static final List<RegistrySupplier<HardDriveItem>> HARD_DRIVES =
        List.of(HARD_DRIVE_SMALL, HARD_DRIVE_MEDIUM, HARD_DRIVE_LARGE,
            HARD_DRIVE_HUGE, HARD_DRIVE_GIANT, HARD_DRIVE_COLOSSAL, HARD_DRIVE_TITANIC, HARD_DRIVE_GARGANTUAN);

    public static final RegistrySupplier<FlashMemoryItem> FLASH_MEMORY = register("flash_memory", () ->
        new FlashMemoryItem(Constants.FLASH_MEMORY_SIZE));

    public static final RegistrySupplier<FloppyItem> FLOPPY = register("floppy", () ->
        new FloppyItem(() -> Config.floppySize));

    public static final RegistrySupplier<Item> FILE_IMPORT_EXPORT_CARD = register("file_import_export_card");
    public static final RegistrySupplier<Item> NETWORK_INTERFACE_CARD = register("network_interface_card", NetworkInterfaceCardItem::new);
    public static final RegistrySupplier<Item> NETWORK_TUNNEL_CARD = register("network_tunnel_card", NetworkTunnelItem::new);
    public static final RegistrySupplier<Item> REDSTONE_INTERFACE_CARD = register("redstone_interface_card");
    public static final RegistrySupplier<Item> SERIAL_INTERFACE_CARD = register("serial_interface_card", SerialInterfaceCardItem::new);
    public static final RegistrySupplier<Item> SOUND_CARD = register("sound_card");

    public static final RegistrySupplier<Item> BLOCK_OPERATIONS_MODULE = register("block_operations_module", BlockOperationsModule::new);
    public static final RegistrySupplier<Item> CRAFTING_MODULE = register("crafting_module");
    public static final RegistrySupplier<Item> INVENTORY_OPERATIONS_MODULE = register("inventory_operations_module");
    public static final RegistrySupplier<Item> NETWORK_TUNNEL_MODULE = register("network_tunnel_module", NetworkTunnelItem::new);
    public static final RegistrySupplier<Item> SCANNER_MODULE = register("scanner_module");
    public static final RegistrySupplier<Item> TANK_OPERATIONS_MODULE = register("tank_operations_module");

    public static final RegistrySupplier<Item> CIRCUIT_BOARD = register("circuit_board", ModItem::new);
    public static final RegistrySupplier<Item> TRANSISTOR = register("transistor", ModItem::new);
    public static final RegistrySupplier<Item> HARD_DRIVE_PLATTER = register("hard_drive_platter", ModItem::new);
    public static final RegistrySupplier<Item> HARD_DRIVE_PLATTER_HIGH_CAPACITY = register("hard_drive_platter_high_capacity", ModItem::new);
    public static final RegistrySupplier<Item> HARD_DRIVE_PLATTER_ULTRA_CAPACITY = register("hard_drive_platter_ultra_capacity", ModItem::new);
    public static final RegistrySupplier<PlatterStackItem> HARD_DRIVE_PLATTER_STACK_HUGE = register("hard_drive_platter_stack_huge", () ->
        new PlatterStackItem(HARD_DRIVE_HUGE));
    public static final RegistrySupplier<PlatterStackItem> HARD_DRIVE_PLATTER_STACK_GIANT = register("hard_drive_platter_stack_giant", () ->
        new PlatterStackItem(HARD_DRIVE_GIANT));
    public static final RegistrySupplier<PlatterStackItem> HARD_DRIVE_PLATTER_STACK_COLOSSAL = register("hard_drive_platter_stack_colossal", () ->
        new PlatterStackItem(HARD_DRIVE_COLOSSAL));
    public static final RegistrySupplier<PlatterStackItem> HARD_DRIVE_PLATTER_STACK_TITANIC = register("hard_drive_platter_stack_titanic", () ->
        new PlatterStackItem(HARD_DRIVE_TITANIC));
    public static final RegistrySupplier<PlatterStackItem> HARD_DRIVE_PLATTER_STACK_GARGANTUAN = register("hard_drive_platter_stack_gargantuan", () ->
        new PlatterStackItem(HARD_DRIVE_GARGANTUAN));

    // --------------------------------------------------------------------- //

    public static void initialize() {
    }

    // --------------------------------------------------------------------- //

    private static RegistrySupplier<Item> register(final String name) {
        return register(name, ModItem::new);
    }

    private static <T extends Item> RegistrySupplier<T> register(final String name, final Supplier<T> factory) {
        return ITEMS.register(name, factory);
    }

    private static <T extends Block> RegistrySupplier<Item> register(final RegistrySupplier<T> block) {
        return register(block, ModBlockItem::new);
    }

    private static <TBlock extends Block, TItem extends Item> RegistrySupplier<TItem> register(final RegistrySupplier<TBlock> block, final Function<TBlock, TItem> factory) {
        return register(block.getId().getPath(), () -> factory.apply(block.get()));
    }
}
