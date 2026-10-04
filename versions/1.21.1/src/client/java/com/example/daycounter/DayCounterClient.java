package com.example.daycounter;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.fabricmc.fabric.api.client.screen.v1.Screens;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.PauseScreen;
import net.minecraft.network.chat.Component;

@Environment(EnvType.CLIENT)
public class DayCounterClient implements ClientModInitializer {

    public static final String MOD_ID = "day-counter";
    public static DayCounterConfig CONFIG;

    @Override
    public void onInitializeClient() {
        CONFIG = DayCounterConfig.load();

        HudRenderCallback.EVENT.register(DayCounterClient::renderDayCounter);
        ScreenEvents.AFTER_INIT.register((client, screen, width, height) -> {
            if (!(screen instanceof PauseScreen)) return;
            Button quitButton = null;
            for (var widget : Screens.getButtons(screen)) {
                if (widget instanceof Button button
                        && (quitButton == null || button.getY() > quitButton.getY())) {
                    quitButton = button;
                }
            }
            if (quitButton == null) return;
            int y = quitButton.getY();
            quitButton.setY(y + 24);
            Screens.getButtons(screen).add(Button.builder(
                Component.literal("Day Counter Settings"),
                button -> client.setScreen(new DayCounterSettingsScreen(screen, CONFIG))
            ).bounds(quitButton.getX(), y, quitButton.getWidth(), 20).build());
        });
    }

    private static void renderDayCounter(GuiGraphics graphics, DeltaTracker deltaTracker) {
        Minecraft client = Minecraft.getInstance();
        if (client.level == null || client.options.hideGui) return;

        long   day  = client.level.getGameTime() / 24_000L;
        String text = CONFIG.getTextCase().apply(CONFIG.getLanguage().format(day));
        float  s    = CONFIG.scale;

        float  px   = CONFIG.x;
        float  py   = CONFIG.y;
        if (CONFIG.refW > 0 && CONFIG.refH > 0) {
            px = CONFIG.x * (graphics.guiWidth()  / (float) CONFIG.refW);
            py = CONFIG.y * (graphics.guiHeight() / (float) CONFIG.refH);
        }

        var ps = graphics.pose();
        ps.pushPose();
        ps.translate(px, py, 0);
        ps.scale(s, s, 1);
        graphics.drawString(CONFIG.getFont().getFont(client.font, client.fontFilterFishy), text, 0, 0, 0xFFFFFFFF, true);
        ps.popPose();
    }
}
