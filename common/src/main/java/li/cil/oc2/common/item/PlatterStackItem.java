/* SPDX-License-Identifier: MIT */

package li.cil.oc2.common.item;

import li.cil.oc2.api.API;
import li.cil.oc2.common.util.TextFormatUtils;
import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nullable;
import java.util.function.Supplier;

/**
 * A stack of hard drive platters, used to craft the hard drive it is sized for.
 * <p>
 * Shows the capacity of that hard drive in its name, so it follows the configured drive sizes.
 */
public final class PlatterStackItem extends ModItem {
    private final Supplier<? extends HardDriveItem> hardDrive;
    @Nullable
    private String descriptionId;

    // --------------------------------------------------------------------- //

    public PlatterStackItem(final Supplier<? extends HardDriveItem> hardDrive) {
        this.hardDrive = hardDrive;
    }

    // --------------------------------------------------------------------- //

    @Override
    public Component getName(final ItemStack stack) {
        final HardDriveItem drive = hardDrive.get();
        return Component.literal("")
            .append(super.getName(stack))
            .append(" (")
            .append(TextFormatUtils.formatSize(drive.getCapacity(new ItemStack(drive))))
            .append(")");
    }

    // --------------------------------------------------------------------- //

    @Override
    protected String getOrCreateDescriptionId() {
        if (descriptionId == null) {
            descriptionId = Util.makeDescriptionId("item", ResourceLocation.fromNamespaceAndPath(API.MOD_ID, "hard_drive_platter_stack"));
        }
        return descriptionId;
    }
}
