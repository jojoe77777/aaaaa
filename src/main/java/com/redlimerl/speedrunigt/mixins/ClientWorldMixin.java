package com.redlimerl.speedrunigt.mixins;

import com.redlimerl.speedrunigt.timer.InGameTimer;
import com.redlimerl.speedrunigt.timer.TimerStatus;
import net.minecraft.client.world.ClientWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientWorld.class)
public class ClientWorldMixin {

    @Inject(at = @At("HEAD"), method = "method_16327")
    public void onTick(CallbackInfo ci) {
        InGameTimer.getInstance().tick();
    }

    @Inject(at = @At("HEAD"), method = "disconnect")
    public void disconnect(CallbackInfo ci) {
        InGameTimer timer = InGameTimer.getInstance();
        if (timer.getStatus() != TimerStatus.NONE) {
            InGameTimer.leave();
        }
    }
}
