package dev.crystell.fallforyou.mixin;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ServerPlayer.class)
public class ServerPlayerMixin {
    @ModifyVariable(method = "die", at = @At("STORE"), ordinal = 0)
    private Component modifyDeathMessage(Component originalMessage) {
        System.out.println(originalMessage.getString());
        if (originalMessage.getString().endsWith("fell from a high place")) {
            ServerPlayer thisObject = (ServerPlayer)(Object)this;
            if (thisObject.getName().getString().equals("ecrystell")) {
                Integer idx = originalMessage.getString().indexOf("from");
                return Component.literal(
                        String.format(
                                "%s for Rbrine",
                                originalMessage.getString().substring(0,idx-1)
                        )
                );
            } else {
                Integer idx = originalMessage.getString().indexOf("from");
                return Component.literal(
                        String.format(
                                "%s for ecrystell",
                                originalMessage.getString().substring(0,idx-1)
                        )
                );
            }

        }
        return Component.literal(
                String.format(
                        "%s meow",
                originalMessage
                )
        );
    }
}
