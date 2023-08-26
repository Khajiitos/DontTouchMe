package me.khajiitos.donttouchme.common.mixin;

import me.khajiitos.donttouchme.common.config.Config;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(Entity.class)
public class EntityMixin {
    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;isVehicle()Z", ordinal = 0, shift = At.Shift.BEFORE), method = "push(Lnet/minecraft/world/entity/Entity;)V", locals = LocalCapture.CAPTURE_FAILHARD, cancellable = true)
    public void beforePushing(Entity entity, CallbackInfo ci, double x, double z, double idk1, double idk2) {
        Entity thisEntity = (Entity)(Object)this;
        LocalPlayer localPlayer = Minecraft.getInstance().player;

        if (thisEntity != localPlayer || (!(entity instanceof Player) && Config.cfg.playersOnly)) {
            if (!thisEntity.isVehicle() && thisEntity.isPushable()) {
                thisEntity.push(-x, 0.0, -z);
            }
        }

        if (entity != localPlayer || (!(thisEntity instanceof Player) && Config.cfg.playersOnly)) {
            if (!entity.isVehicle() && entity.isPushable()) {
                entity.push(x, 0.0, z);
            }
        }

        ci.cancel();
    }
}
