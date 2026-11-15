# sfpro fonts (TV only)

The TV reskin uses one neutral family, four weights:

```
res/font/chora_display_regular.ttf    -> SF Pro Display Regular   (400)
res/font/chora_display_medium.ttf     -> SF Pro Display Medium    (500)
res/font/chora_display_semibold.ttf   -> SF Pro Display Semibold  (600)
res/font/chora_display_bold.ttf       -> SF Pro Display Bold      (700)
```

Drop the licensed SF Pro Display files here with **exactly those base names** (`.otf` or
`.ttf` is fine; the resource name is the file name without the extension). The `sfpro`
flavor's resources override the open fallback in `app/src/main/res/font/` by name, so no
Kotlin changes and no XML font family are needed — `TvFonts.Display` picks them up.

Rules:

- Only the four weights above. No italic, no Text optical size (Display-only decision,
  spec §4.3).
- Do not rename the files: code references `R.font.chora_display_*` from
  `ui/theme/tv/TvFonts.kt` and must not learn flavor-specific names.
- Do not put anything except font files in `res/font/` — a README or `.gitkeep` there is an
  invalid Android resource and fails the resource merge.
- Record the source and SHA-256 of each file in `NOTICE` before shipping.

Until these files exist, the `sfpro` build silently uses the Inter fallback bundled in
`main`, so `assembleSfproDebug` succeeding does **not** prove SF Pro is being used.
Verify on device (spec §4.3 exit check) that the type looks like SF Pro, not Inter.
