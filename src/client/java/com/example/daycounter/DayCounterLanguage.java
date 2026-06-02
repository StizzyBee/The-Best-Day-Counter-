package com.example.daycounter;

public enum DayCounterLanguage {
   ENGLISH("english", "English", "Day"),
   SPANISH("spanish", "Español", "Día"),
   PORTUGUESE("portuguese", "Português", "Dia"),
   FRENCH("french", "Français", "Jour"),
   RUSSIAN("russian", "Русский", "День"),
   CHINESE("chinese", "中文", "天");

   public final String id;
   public final String displayName;
   public final String dayWord;

   DayCounterLanguage(String id, String displayName, String dayWord) {
      this.id = id;
      this.displayName = displayName;
      this.dayWord = dayWord;
   }

   public String format(long day) {
      return this.dayWord + ": " + day;
   }

   public static DayCounterLanguage fromId(String id) {
      for (DayCounterLanguage l : values()) {
         if (l.id.equalsIgnoreCase(id)) {
            return l;
         }
      }

      return ENGLISH;
   }

   public DayCounterLanguage next() {
      DayCounterLanguage[] v = values();
      return v[(this.ordinal() + 1) % v.length];
   }

   public DayCounterLanguage prev() {
      DayCounterLanguage[] v = values();
      return v[(this.ordinal() + v.length - 1) % v.length];
   }
}
