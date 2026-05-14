import express from "express";
import path from "path";

const app = express();
const PORT = 3000;

// This is a placeholder server for the AI Studio preview.
// Since this is a native Android project, the preview shows instructions.

app.get("/", (req, res) => {
  res.send(`
    <style>
      body { background: #121212; color: #e0e0e0; font-family: sans-serif; display: flex; flex-direction: column; align-items: center; justify-content: center; height: 100vh; margin: 0; text-align: center; }
      .container { padding: 2rem; border-radius: 1rem; background: rgba(255,255,255,0.05); backdrop-filter: blur(10px); border: 1px solid rgba(255,255,255,0.1); max-width: 500px; }
      h1 { color: #fff; }
      p { line-height: 1.6; }
      .badge { background: #4caf50; color: #000; padding: 0.2rem 0.5rem; border-radius: 0.2rem; font-weight: bold; font-size: 0.8rem; }
    </style>
    <div class="container">
      <h1>com.void.repo</h1>
      <p><span class="badge">NATIVE APP</span> This project is built using <b>Java & XML</b> to be compatible with <b>AIDE</b> and Android Studio.</p>
      <p><b>Name:</b> Void Repo</p>
      <p><b>Branding:</b> 🦋⃟ᴠͥɪͣᴘͫ•𝐒𝐑𝟕 𝐌𝐨𝐝𝐬</p>
      <p>The web preview is limited for native mobile projects. Use the <b>Export</b> feature to download and build the APK.</p>
      <hr style="border: 0; border-top: 1px solid rgba(255,255,255,0.1); margin: 2rem 0;">
      <p style="font-size: 0.9rem; color: #999;">Core Logic: AES-256 local-only file obfuscation by SR7 Mods.</p>
    </div>
  `);
});

app.listen(PORT, "0.0.0.0", () => {
  console.log(`Instructions server running on port ${PORT}`);
});
