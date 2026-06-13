package com.example.daycounter.mixin;

import com.example.daycounter.DayCounterClient;
import com.example.daycounter.DayCounterSettingsScreen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.PauseScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Injects a "Day Counter Settings" button into the pause screen,
 * right above the disconnect/save-and-quit button.
 */
@Mixin(PauseScreen.class)
public class PauseScreenMixin {

    @Shadow private Button disconnectButton;

    @Inject(method = "init", at = @At("TAIL"))
    private void addDayCounterButton(CallbackInfo ci) {
        Screen self = (Screen) (Object) this;
        int btnW = 204, btnH = 20;
        int btnX = (self.width - btnW) / 2;
        int origDisconnectY = disconnectButton.getY();

        // Shift the disconnect button down one row
        disconnectButton.setY(origDisconnectY + 24);

        // Insert our button right above it
        ((ScreenInvoker) self).invokeAddRenderableWidget(Button.builder(
            Component.literal("Day Counter Settings"),
            btn -> Minecraft.getInstance().setScreen(
                new DayCounterSettingsScreen(self, DayCounterClient.CONFIG)
            )
        ).bounds(btnX, origDisconnectY, btnW, btnH).build());
    }
}
