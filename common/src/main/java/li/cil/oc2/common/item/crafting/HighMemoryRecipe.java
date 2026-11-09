/* SPDX-License-Identifier: MIT */

package li.cil.oc2.common.item.crafting;

import com.mojang.serialization.MapCodec;
import li.cil.oc2.common.Config;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.level.Level;

import java.util.function.Function;

/**
 * A shaped recipe that only matches while {@link Config#highMemoryMachines} is enabled.
 */
public final class HighMemoryRecipe extends ShapedRecipe {
    public HighMemoryRecipe(final ShapedRecipe recipe) {
        super(recipe.getGroup(), recipe.category(), recipe.pattern, recipe.result, recipe.showNotification());
    }

    // --------------------------------------------------------------------- //

    @Override
    public boolean matches(final CraftingInput input, final Level level) {
        return Config.highMemoryMachines && super.matches(input, level);
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return RecipeSerializers.HIGH_MEMORY.get();
    }

    // --------------------------------------------------------------------- //

    public static final class Serializer implements RecipeSerializer<HighMemoryRecipe> {
        private static final MapCodec<HighMemoryRecipe> CODEC = RecipeSerializer.SHAPED_RECIPE.codec()
            .xmap(HighMemoryRecipe::new, Function.identity());
        private static final StreamCodec<RegistryFriendlyByteBuf, HighMemoryRecipe> STREAM_CODEC = RecipeSerializer.SHAPED_RECIPE.streamCodec()
            .map(HighMemoryRecipe::new, Function.identity());

        @Override
        public MapCodec<HighMemoryRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, HighMemoryRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}
