package minenaruto.narutoplugin.iditems;

import java.util.*;
import org.bukkit.*;
import java.lang.reflect.*;
import org.bukkit.command.*;

public abstract class EasyCommand implements CommandExecutor, TabExecutor
{
    protected final String command;
    protected final String description;
    protected final List<String> alias;
    protected final String usage;
    protected final String permMessage;
    protected static CommandMap cmap;
    
    public EasyCommand(final String command) {
        this(command, null, null, null, null);
    }
    
    public EasyCommand(final String command, final String usage) {
        this(command, usage, null, null, null);
    }
    
    public EasyCommand(final String command, final String usage, final String description) {
        this(command, usage, description, null, null);
    }
    
    public EasyCommand(final String command, final String usage, final String description, final String permissionMessage) {
        this(command, usage, description, permissionMessage, null);
    }
    
    public EasyCommand(final String command, final String usage, final String description, final List<String> aliases) {
        this(command, usage, description, null, aliases);
    }
    
    public EasyCommand(final String command, final String usage, final String description, final String permissionMessage, final List<String> aliases) {
        this.command = command.toLowerCase();
        this.usage = usage;
        this.description = description;
        this.permMessage = permissionMessage;
        this.alias = aliases;
    }
    
    public void register() {
        final ReflectCommand cmd = new ReflectCommand(this.command);
        if (this.alias != null) {
            cmd.setAliases((List)this.alias);
        }
        if (this.description != null) {
            cmd.setDescription(this.description);
        }
        if (this.usage != null) {
            cmd.setUsage(this.usage);
        }
        if (this.permMessage != null) {
            cmd.setPermissionMessage(this.permMessage);
        }
        this.getCommandMap().register("", (Command)cmd);
        cmd.setExecutor(this);
    }
    
    final CommandMap getCommandMap() {
        if (EasyCommand.cmap == null) {
            try {
                final Field f = Bukkit.getServer().getClass().getDeclaredField("commandMap");
                f.setAccessible(true);
                EasyCommand.cmap = (CommandMap)f.get(Bukkit.getServer());
                return this.getCommandMap();
            }
            catch (Exception e) {
                e.printStackTrace();
                return this.getCommandMap();
            }
        }
        if (EasyCommand.cmap != null) {
            return EasyCommand.cmap;
        }
        return this.getCommandMap();
    }
    
    public abstract boolean onCommand(final CommandSender p0, final Command p1, final String p2, final String[] p3);
    
    public List<String> onTabComplete(final CommandSender s, final Command cmd, final String label, final String[] args) {
        return null;
    }
    
    private final class ReflectCommand extends Command
    {
        private EasyCommand exe;
        
        protected ReflectCommand(final String command) {
            super(command);
            this.exe = null;
        }
        
        public void setExecutor(final EasyCommand exe) {
            this.exe = exe;
        }
        
        public boolean execute(final CommandSender s, final String commandLabel, final String[] args) {
            return this.exe != null && this.exe.onCommand(s, this, commandLabel, args);
        }
        
        public List<String> tabComplete(final CommandSender s, final String alais, final String[] args) {
            if (this.exe != null) {
                return this.exe.onTabComplete(s, this, alais, args);
            }
            return null;
        }
    }
}
