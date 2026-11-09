/* SPDX-License-Identifier: MIT */

package li.cil.oc2.common.container;

import li.cil.oc2.api.bus.device.DeviceType;
import li.cil.oc2.common.vm.PowerAction;
import li.cil.oc2.common.vm.VMItemStackHandlers;
import li.cil.oc2.common.vm.VirtualMachine;
import net.minecraft.world.inventory.MenuType;

import java.util.List;

public abstract class AbstractMachineContainer extends AbstractContainer {
    protected static final int ENERGY_INFO_SIZE = 3;

    public static final int ENERGY_STORED_INDEX = 0;
    public static final int ENERGY_CAPACITY_INDEX = 1;
    public static final int ENERGY_CONSUMPTION_INDEX = 2;

    // --------------------------------------------------------------------- //

    private final IntPrecisionContainerData energyInfo;

    // --------------------------------------------------------------------- //

    protected AbstractMachineContainer(final MenuType<?> type, final int id, final IntPrecisionContainerData energyInfo) {
        super(type, id);
        this.energyInfo = energyInfo;

        checkContainerDataCount(energyInfo, ENERGY_INFO_SIZE);
        addDataSlots(energyInfo);
    }

    /**
     * Adds a slot for the first item of a device type, if the machine has one.
     */
    protected void addSingleSlot(final VMItemStackHandlers handlers, final DeviceType deviceType, final MachineSlotLayout.Position position) {
        handlers.getItemHandler(deviceType).ifPresent(itemHandler -> {
            if (itemHandler.getSlots() > 0) {
                addSlot(new DeviceTypeSlotItemHandler(itemHandler, deviceType, 0, position.x(), position.y()));
            }
        });
    }

    /**
     * Adds slots for the items of a device type, one per position, up to the number the machine has.
     */
    protected void addSlots(final VMItemStackHandlers handlers, final DeviceType deviceType, final List<MachineSlotLayout.Position> positions) {
        handlers.getItemHandler(deviceType).ifPresent(itemHandler -> {
            final int count = Math.min(itemHandler.getSlots(), positions.size());
            for (int slot = 0; slot < count; slot++) {
                final MachineSlotLayout.Position position = positions.get(slot);
                addSlot(new DeviceTypeSlotItemHandler(itemHandler, deviceType, slot, position.x(), position.y()));
            }
        });
    }

    // --------------------------------------------------------------------- //

    public abstract void switchToInventory();

    public abstract VirtualMachine getVirtualMachine();

    public abstract void sendPowerActionToServer(final PowerAction action);

    public int getEnergy() {
        return energyInfo.getInt(ENERGY_STORED_INDEX);
    }

    public int getEnergyCapacity() {
        return energyInfo.getInt(ENERGY_CAPACITY_INDEX);
    }

    public int getEnergyConsumption() {
        return energyInfo.getInt(ENERGY_CONSUMPTION_INDEX);
    }

    // --------------------------------------------------------------------- //

    protected static IntPrecisionContainerData createClientEnergyInfo() {
        return new IntPrecisionContainerData.Client(ENERGY_INFO_SIZE);
    }
}
