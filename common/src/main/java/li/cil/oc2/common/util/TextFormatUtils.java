/* SPDX-License-Identifier: MIT */

package li.cil.oc2.common.util;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextColor;

import java.text.DecimalFormat;

public final class TextFormatUtils {
    private static final int SIZE_STEP = 1024;
    private static final String[] SIZE_UNIT = {"B", "KB", "MB", "GB", "TB"};
    private static final DecimalFormat SIZE_FORMAT = new DecimalFormat("#.#");
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.##");

    public static String formatSize(final long size) {
        double value = size;
        int index = 0;
        while (value >= SIZE_STEP && index < SIZE_UNIT.length - 1) {
            value /= SIZE_STEP;
            index++;
        }
        return SIZE_FORMAT.format(value) + SIZE_UNIT[index];
    }

    public static String formatNumber(final double value) {
        return DECIMAL_FORMAT.format(value);
    }

    public static MutableComponent withFormat(final String value, final ChatFormatting formatting) {
        return withFormat(Component.literal(value), formatting);
    }

    public static MutableComponent withFormat(final MutableComponent text, final ChatFormatting formatting) {
        return text.withStyle(s -> s.withColor(TextColor.fromLegacyFormat(formatting)));
    }

    public static Component withFormat(final Component text, final ChatFormatting formatting) {
        return Component.literal("").withStyle(formatting).append(text);
    }
}
