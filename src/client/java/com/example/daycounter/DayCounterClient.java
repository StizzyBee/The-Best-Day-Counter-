package com.example.daycounter;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.resources.Identifier;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;

@Environment(EnvType.CLIENT)
public class DayCounterClient implements ClientModInitializer {

    public static final String MOD_ID = "day-counter";
    public static DayCounterConfig CONFIG;

    @Override
    public void onInitializeClient() {
        CONFIG = DayCounterConfig.load();

        HudElementRegistry.attachElementAfter(
            VanillaHudElements.HOTBAR,
            Identifier.fromNamespaceAndPath(MOD_ID, "day_counter"),
            DayCounterClient::renderDayCounter
        );
    }

    private static void renderDayCounter(GuiGraphicsExtractor graphics, DeltaTracker deltaTracker) {
        Minecraft client = Minecraft.getInstance();
        if (client.level == null || client.options.hideGui) return;

        long   day  = client.level.getGameTime() / 24_000L;
        String text = CONFIG.getTextCase().apply(CONFIG.getLanguage().format(day));
        float  s    = CONFIG.scale;

        var ps = graphics.pose();
        ps.pushMatrix();
        ps.translate(CONFIG.x, CONFIG.y);
        ps.scale(s, s);
        graphics.text(CONFIG.getFont().getFont(client.font, client.fontFilterFishy), text, 0, 0, 0xFFFFFFFF, true);
        ps.popMatrix();
    }
}
