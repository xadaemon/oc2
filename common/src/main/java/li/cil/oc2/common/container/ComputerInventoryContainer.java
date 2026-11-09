/* SPDX-License-Identifier: MIT */

package li.cil.oc2.common.container;

import dev.architectury.registry.menu.ExtendedMenuProvider;
import dev.architectury.registry.menu.MenuRegistry;
import li.cil.oc2.api.bus.device.DeviceTypes;
import li.cil.oc2.common.blockentity.ComputerBlockEntity;
import li.cil.oc2.common.bus.CommonDeviceBusController;
import li.cil.oc2.common.energy.EnergyHandler;
import li.cil.oc2.common.vm.VMItemStackHandlers;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.entity.BlockEntity;

public final class ComputerInventoryContainer extends AbstractComputerContainer {
    public static void createServer(final ComputerBlockEntity computer, final EnergyHandler energy, final CommonDeviceBusController busController, final ServerPlayer player) {
        MenuRegistry.openExtendedMenu(player, new ExtendedMenuProvider() {
            @Override
            public Component getDisplayName() {
                return Component.translatable(computer.getBlockState().getBlock().getDescriptionId());
            }

            @Override
            public AbstractContainerMenu createMenu(final int id, final Inventory inventory, final Player player) {
                return new ComputerInventoryContainer(id, computer, player, createEnergyInfo(energy, busController));
            }

            @Override
            public void saveExtraData(final FriendlyByteBuf buffer) {
                buffer.writeBlockPos(computer.getBlockPos());
            }
        });
    }

    public static ComputerInventoryContainer createClient(final int id, final Inventory playerInventory, final FriendlyByteBuf data) {
        final BlockPos pos = data.readBlockPos();
        final BlockEntity blockEntity = playerInventory.player.level().getBlockEntity(pos);
        if (blockEntity instanceof final ComputerBlockEntity computer) {
            return new ComputerInventoryContainer(id, computer, playerInventory.player, createClientEnergyInfo());
        }

        throw new IllegalArgumentException();
    }

    // --------------------------------------------------------------------- //

    private ComputerInventoryContainer(final int id, final ComputerBlockEntity computer, final Player player, final IntPrecisionContainerData energyInfo) {
        super(Containers.COMPUTER.get(), id, player, computer, energyInfo);

        final VMItemStackHandlers handlers = computer.getItemStackHandlers();
        final MachineSlotLayout layout = MachineSlotLayout.computer(computer.getTier());

        addSingleSlot(handlers, DeviceTypes.CPU.get(), layout.cpu());
        addSingleSlot(handlers, DeviceTypes.FLASH_MEMORY.get(), layout.flashMemory());
        addSlots(handlers, DeviceTypes.MEMORY.get(), layout.memory());
        addSlots(handlers, DeviceTypes.HARD_DRIVE.get(), layout.hardDrives());
        addSlots(handlers, DeviceTypes.CARD.get(), layout.expansion());

        createPlayerInventoryAndHotbarSlots(player.getInventory(), 8, layout.playerInventoryY());
    }
}
