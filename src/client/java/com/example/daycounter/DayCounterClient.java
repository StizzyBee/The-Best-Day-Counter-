package com.example.daycounter;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.resources.Identifier;
import org.joml.Matrix3x2fStack;

@Environment(EnvType.CLIENT)
public class DayCounterClient implements ClientModInitializer {
   public static final String MOD_ID = "day-counter";
   public static DayCounterConfig CONFIG;

   public void onInitializeClient() {
      CONFIG = DayCounterConfig.load();
      HudElementRegistry.attachElementAfter(
         VanillaHudElements.HOTBAR, Identifier.fromNamespaceAndPath("day-counter", "day_counter"), DayCounterClient::renderDayCounter
      );
   }

   private static void renderDayCounter(GuiGraphicsExtractor graphics, DeltaTracker deltaTracker) {
      Minecraft client = Minecraft.getInstance();
      if (client.level != null && !client.options.hideGui) {
         long day = client.level.getGameTime() / 24000L;
         String text = CONFIG.getTextCase().apply(CONFIG.getLanguage().format(day));
         float s = CONFIG.scale;
         Matrix3x2fStack ps = graphics.pose();
         ps.pushMatrix();
         ps.translate(CONFIG.x, CONFIG.y);
         ps.scale(s, s);
         int color = 0xFF000000 | (CONFIG.r << 16) | (CONFIG.g << 8) | CONFIG.b;
         graphics.text(CONFIG.getFont().getFont(client.font, client.fontFilterFishy), text, 0, 0, color, true);
         ps.popMatrix();
      }
   }
}
