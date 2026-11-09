/* SPDX-License-Identifier: MIT */

package li.cil.oc2.client.gui;

import li.cil.oc2.client.gui.widget.NineSliceSprite;
import li.cil.oc2.client.gui.widget.Sprite;
import li.cil.oc2.common.vm.MachineTier;

import static li.cil.oc2.client.gui.Textures.*;

public final class Sprites {
    private static final int BORDER = 8;

    public static final Sprite COMPUTER_CONTAINER = new Sprite(COMPUTER_CONTAINER_TEXTURE);
    public static final Sprite ROBOT_CONTAINER = new Sprite(ROBOT_CONTAINER_TEXTURE);
    public static final Sprite COMPUTER_CONTAINER_TIER_1 = new Sprite(COMPUTER_CONTAINER_TIER_1_TEXTURE);
    public static final Sprite COMPUTER_CONTAINER_TIER_3 = new Sprite(COMPUTER_CONTAINER_TIER_3_TEXTURE);
    public static final Sprite COMPUTER_CONTAINER_TIER_4 = new Sprite(COMPUTER_CONTAINER_TIER_4_TEXTURE);
    public static final Sprite ROBOT_CONTAINER_TIER_1 = new Sprite(ROBOT_CONTAINER_TIER_1_TEXTURE);
    public static final Sprite ROBOT_CONTAINER_TIER_3 = new Sprite(ROBOT_CONTAINER_TIER_3_TEXTURE);
    public static final Sprite ROBOT_CONTAINER_TIER_4 = new Sprite(ROBOT_CONTAINER_TIER_4_TEXTURE);
    public static final Sprite TERMINAL_SCREEN = new Sprite(TERMINAL_SCREEN_TEXTURE);
    public static final Sprite BUS_INTERFACE_SCREEN = new Sprite(BUS_INTERFACE_SCREEN_TEXTURE);
    public static final Sprite SIDED_DEVICE_SCREEN = new Sprite(SIDED_DEVICE_SCREEN_TEXTURE);
    public static final Sprite NETWORK_TUNNEL_SCREEN = new Sprite(NETWORK_TUNNEL_SCREEN_TEXTURE);

    public static final NineSliceSprite GENERIC_SCREEN = new NineSliceSprite(GENERIC_SCREEN_TEXTURE, 176, 197, 0, 0, BORDER);
    public static final NineSliceSprite GENERIC_BUTTON_BASE = new NineSliceSprite(GENERIC_BUTTON_TEXTURE, 80, 20, 0, 0, BORDER);
    public static final NineSliceSprite GENERIC_BUTTON_PRESSED = new NineSliceSprite(GENERIC_BUTTON_TEXTURE, 80, 20, 0, 20, BORDER);

    public static final Sprite TERMINAL_FOCUSED = new Sprite(TERMINAL_FOCUSED_TEXTURE);
    public static final Sprite SLOT_SELECTION = new Sprite(SLOT_SELECTION_TEXTURE, 18, 18, 0, 0);
    public static final Sprite INFO_ICON = new Sprite(INFO_ICON_TEXTURE);
    public static final Sprite WARN_ICON = new Sprite(WARN_ICON_TEXTURE);

    public static final Sprite HOTBAR = new Sprite(HOTBAR_TEXTURE);
    public static final Sprite SIDEBAR_1 = new Sprite(SIDEBAR_1_TEXTURE);
    public static final Sprite SIDEBAR_2 = new Sprite(SIDEBAR_2_TEXTURE);
    public static final Sprite SIDEBAR_3 = new Sprite(SIDEBAR_3_TEXTURE);

    public static final Sprite ENERGY_BASE = new Sprite(ENERGY_TEXTURE, 12, 26, 0, 0);
    public static final Sprite ENERGY_BAR = new Sprite(ENERGY_TEXTURE, 12, 26, 12, 0);

    public static final Sprite INPUT_BUTTON_ACTIVE = new Sprite(INPUT_BUTTON_TEXTURE, 12, 12, 1, 1);
    public static final Sprite INPUT_BUTTON_BASE = new Sprite(INPUT_BUTTON_TEXTURE, 12, 12, 15, 1);
    public static final Sprite INPUT_BUTTON_PRESSED = new Sprite(INPUT_BUTTON_TEXTURE, 12, 12, 29, 1);

    public static final Sprite POWER_BUTTON_ACTIVE = new Sprite(POWER_BUTTON_TEXTURE, 12, 12, 1, 1);
    public static final Sprite POWER_BUTTON_BASE = new Sprite(POWER_BUTTON_TEXTURE, 12, 12, 15, 1);
    public static final Sprite POWER_BUTTON_PRESSED = new Sprite(POWER_BUTTON_TEXTURE, 12, 12, 29, 1);

    public static final Sprite INVENTORY_BUTTON_INACTIVE = new Sprite(INVENTORY_BUTTON_TEXTURE, 12, 12, 1, 1);
    public static final Sprite INVENTORY_BUTTON_ACTIVE = new Sprite(INVENTORY_BUTTON_TEXTURE, 12, 12, 15, 1);

    public static final Sprite CONFIG_BUTTON_INACTIVE = new Sprite(CONFIG_BUTTON_TEXTURE, 12, 12, 1, 1);
    public static final Sprite CONFIG_BUTTON_ACTIVE = new Sprite(CONFIG_BUTTON_TEXTURE, 12, 12, 15, 1);

    public static final Sprite NETWORK_TUNNEL_LINK_BUTTON_INACTIVE = new Sprite(NETWORK_TUNNEL_LINK_BUTTON_TEXTURE, 80, 20, 0, 0);
    public static final Sprite NETWORK_TUNNEL_LINK_BUTTON_ACTIVE = new Sprite(NETWORK_TUNNEL_LINK_BUTTON_TEXTURE, 80, 20, 0, 20);

    public static final Sprite CONFIRM_PRESSED = new Sprite(CONFIRM_BUTTON_TEXTURE, 12, 12, 14, 1);
    public static final Sprite CONFIRM_BASE = new Sprite(CONFIRM_BUTTON_TEXTURE, 12, 12, 1, 1);
    public static final Sprite CANCEL_PRESSED = new Sprite(CANCEL_BUTTON_TEXTURE, 12, 12, 14, 1);
    public static final Sprite CANCEL_BASE = new Sprite(CANCEL_BUTTON_TEXTURE, 12, 12, 1, 1);

    // --------------------------------------------------------------------- //

    /**
     * The inventory screen background of a computer of the specified tier. Matches
     * {@link li.cil.oc2.common.container.MachineSlotLayout#computer(MachineTier)}.
     */
    public static Sprite computerContainer(final MachineTier tier) {
        return switch (tier) {
            case TIER_1 -> COMPUTER_CONTAINER_TIER_1;
            case TIER_2 -> COMPUTER_CONTAINER;
            case TIER_3 -> COMPUTER_CONTAINER_TIER_3;
            case TIER_4 -> COMPUTER_CONTAINER_TIER_4;
        };
    }

    /**
     * The inventory screen background of a robot of the specified tier. Matches
     * {@link li.cil.oc2.common.container.MachineSlotLayout#robot(MachineTier)}.
     */
    public static Sprite robotContainer(final MachineTier tier) {
        return switch (tier) {
            case TIER_1 -> ROBOT_CONTAINER_TIER_1;
            case TIER_2 -> ROBOT_CONTAINER;
            case TIER_3 -> ROBOT_CONTAINER_TIER_3;
            case TIER_4 -> ROBOT_CONTAINER_TIER_4;
        };
    }
}
