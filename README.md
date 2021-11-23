# ServerJumpSK - The Skript Addon
![](https://img.shields.io/github/v/release/Zettovec/ServerJumpSK?display_name=tag&include_prereleases)
![](https://img.shields.io/github/downloads/Zettovec/ServerJumpSK/total)

ServerJumpSK is a successor of my previously developed and no longer maintenained Spigot plugin ServerJump. It allows you to send players across your BungeeCord network easily, via Skript on your **Spigot server**.

### What do you need?
- A working BungeeCord network (Tutorial <a href="https://www.spigotmc.org/wiki/bungeecord-configuration-guide/">here</a>)
- Option `bungeecord: true` set in your spigot.yml
- Skript

### How to install?
1. Put the latest release, <a href="https://github.com/Zettovec/ServerJumpSK/releases/">.jar file</a>, into the your Spigot server's `plugins/` folder. (**This is a Spigot plugin** - don't put it into the BungeeCord's plugins folder.)
2. **Restart your Spigot server!** (Skript and Skript Addons are generaly not advised to be reloaded via Plugman or /reload - don't do it then.)
3. Use it in your scripts!

### Documentation
The addon only contains one effect:

```skript
jump %player% to [server] %string%
```   

<details> 
  <summary>Examples</summary>

1. `jump player to "lobby"` - sends player to the lobby server
2. `jump loop-player to server "game"` - sends loop-player to the game server

</details>
