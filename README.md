# Day Counter

Displays the current world day count on your HUD. Everything is configurable from a settings screen inside the game — no config files to edit.

---

## Summary

A Fabric mod for Minecraft 26.1 that shows how many in-game days have passed (calculated from the world's total tick time). The counter can be freely positioned anywhere on screen, resized, and styled with different languages, fonts, and text cases. All settings persist between sessions.

---

## How to use

### Opening the settings

1. Open the pause menu (Escape key).
2. Click the **"Day Counter Settings"** button — it appears just above "Save and Quit / Disconnect".
3. A screen opens with all the controls and a live preview of the counter.

### Positioning the counter

- **Drag**: Click and drag the blue-outlined preview box to move it anywhere.
- **Presets**: The five preset buttons (Top-Left, Top-Center, etc.) snap the counter to common positions.
- **Center H / Center V**: Align the counter exactly to the horizontal or vertical center of the screen.
- **Arrow keys**: Nudge the counter 1 pixel at a time. Hold Shift while pressing an arrow key to move 5 pixels at once.

### Resizing

- **+ / − buttons**: Increase or decrease the scale in 0.25× steps (range: 0.5× to 5.0×).
- **Mouse scroll**: Hover over the preview box and scroll to resize quickly.

### Language

Cycle through six display languages with the ‹ and › buttons: English, Español, Português, Français, Русский, 中文. The counter label (e.g. "Day", "Día", "День") updates immediately.

### Font

Switch between **Default** (the standard Minecraft GUI font) and **Filtered** (uses the alternate font renderer, which can look different depending on your Unicode font settings).

### Text case

Toggle between:
- **Normal** — "Day: 0"
- **ALL CAPS** — "DAY: 0"
- **lowercase** — "day: 0"

### Saving

- Click **Done** (or press Escape) to save all changes and return to the pause menu.
- Settings are saved to `config/day-counter.json` and persist across restarts.
