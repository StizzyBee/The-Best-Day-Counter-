package com.example.daycounter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import net.fabricmc.loader.api.FabricLoader;

public class DayCounterConfig {
   private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
   private static final Path CONFIG_FILE = FabricLoader.getInstance().getConfigDir().resolve("day-counter.json");
   public static final float SCALE_MIN = 0.5F;
   public static final float SCALE_MAX = 5.0F;
   public static final float SCALE_STEP = 0.25F;
   public int x = 2;
   public int y = 2;
   public float scale = 1.0F;
   public String language = DayCounterLanguage.ENGLISH.id;
   public String font = DayCounterFont.DEFAULT.id;
   public String textCase = DayCounterTextCase.NORMAL.id;
   public int r = 255;
   public int g = 255;
   public int b = 255;

   public DayCounterLanguage getLanguage() {
      return DayCounterLanguage.fromId(this.language);
   }

   public void setLanguage(DayCounterLanguage lang) {
      this.language = lang.id;
   }

   public DayCounterFont getFont() {
      return DayCounterFont.fromId(this.font);
   }

   public void setFont(DayCounterFont f) {
      this.font = f.id;
   }

   public DayCounterTextCase getTextCase() {
      return DayCounterTextCase.fromId(this.textCase);
   }

   public void setTextCase(DayCounterTextCase c) {
      this.textCase = c.id;
   }

   public static DayCounterConfig load() {
      if (Files.exists(CONFIG_FILE)) {
         try (Reader r = Files.newBufferedReader(CONFIG_FILE)) {
            DayCounterConfig cfg = (DayCounterConfig)GSON.fromJson(r, DayCounterConfig.class);
            if (cfg != null) {
               cfg.scale = Math.max(0.5F, Math.min(5.0F, cfg.scale));
               if (cfg.language == null) {
                  cfg.language = DayCounterLanguage.ENGLISH.id;
               }

               if (cfg.font == null) {
                  cfg.font = DayCounterFont.DEFAULT.id;
               }

               if (cfg.textCase == null) {
                  cfg.textCase = DayCounterTextCase.NORMAL.id;
               }

               cfg.r = Math.max(0, Math.min(255, cfg.r));
               cfg.g = Math.max(0, Math.min(255, cfg.g));
               cfg.b = Math.max(0, Math.min(255, cfg.b));

               return cfg;
            }
         } catch (Exception var5) {
         }
      }

      return new DayCounterConfig();
   }

   public void save() {
      try (Writer w = Files.newBufferedWriter(CONFIG_FILE)) {
         GSON.toJson(this, w);
      } catch (Exception var6) {
      }
   }
}
