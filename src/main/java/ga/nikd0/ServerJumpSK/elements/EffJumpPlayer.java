package ga.nikd0.ServerJumpSK.elements;

import javax.annotation.Nullable;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import ga.nikd0.ServerJumpSK.PlayerJumper;
import ga.nikd0.ServerJumpSK.ServerJumpSK;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class EffJumpPlayer extends Effect {

    static {
        Skript.registerEffect(EffJumpPlayer.class, "jump %player% to [server] %string%");
    }

    @SuppressWarnings("null")
    private Expression<Player> player;
    @SuppressWarnings("null")
    private Expression<String> server;

    @SuppressWarnings({"unchecked", "null"})
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, ParseResult parser) {
        this.player = (Expression<Player>) expressions[0];
        this.server = (Expression<String>) expressions[1];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "Jump player " + player.toString(event, debug) + " to server " + server.toString(event, debug);
    }

    @Override
    protected void execute(Event event) {
        if (player == null)  return;
        if (server == null)  return;

        Player target = Bukkit.getPlayer(player.getSingle(event).getDisplayName());
        PlayerJumper pj = new PlayerJumper();
        pj.jumpPlayer(target, server.getSingle(event));
    }

}
