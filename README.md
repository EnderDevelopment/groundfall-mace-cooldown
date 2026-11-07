# Minecraft Mace Cooldown Plugin

Groundfall Mace Cooldown is a Minecraft plugin for Paper servers that applies a 60-second mace cooldown when a player lands after becoming airborne following a successful mace hit.

## Install

1. Use Java 21 or newer.
2. Build the plugin with Maven:
   ```bash
   mvn package
   ```
3. Copy `target/plugin.jar` into your server's `plugins` folder.
4. Start or restart the Paper server.
5. Confirm that the plugin loads successfully.

## Use

1. Hold a mace in the player's main hand.
2. Hit an entity with the mace.
3. Become airborne after the hit.
4. Touch the ground again.
5. The mace receives a 60-second item cooldown.
6. The cooldown prevents the player from using the mace until it expires.

The plugin does not require a configuration file or commands.

---

More Minecraft plugin projects by EnderDevelopment: https://github.com/EnderDevelopment

Project page: https://dash.enderdevelopment.com/community/14d19fdc-210d-4608-8a1f-70d98b66a884?utm_source=github&utm_medium=readme&utm_campaign=auto_publish&utm_content=14d19fdc-210d-4608-8a1f-70d98b66a884
