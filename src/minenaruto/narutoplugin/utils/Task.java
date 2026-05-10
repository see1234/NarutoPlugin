package minenaruto.narutoplugin.utils;

import java.util.HashMap;

import minenaruto.narutoplugin.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;




public abstract class Task
extends BukkitRunnable {
    public static final HashMap<String, Task> tasks = new HashMap<String, Task>();
    private int periods;
    private int delay;
    private int period;
    private final String name;
    private final JavaPlugin plugin;

    public Task(JavaPlugin plugin, String name, int periods, int delayInMilliseconds, int periodInMilliseconds) {
        if (delayInMilliseconds != 0 && delayInMilliseconds < 50) {
            throw new IllegalArgumentException("Delay time must be 0 or not less than 50ms!");
        }
        if (periodInMilliseconds < 50) {
            throw new IllegalArgumentException("Period time must be not less than 50ms!");
        }
        this.name = name;
        this.plugin = plugin;
        if (periods == 0) {
            this.periods = -1;
        }
        this.periods = periods;
        this.delay = delayInMilliseconds / 50;
        this.period = periodInMilliseconds / 50;
        this.runTaskTimer((Plugin)plugin, (long)this.delay, (long)this.period);
        tasks.put(name, this);
    }

    public abstract void onTick();

    public void run() {
        if (this.periods > 0) {
            --this.periods;
        }
        this.onTick();
        if (this.periods == 0) {
            this.cancel();
        }
    }

    public int getPeriods() {
        return this.periods;
    }

    public int getDelayInTicks() {
        return this.delay;
    }

    public int getPeriodInTicks() {
        return this.period;
    }

    public String getName() {
        return this.name;
    }

    public JavaPlugin getPlugin() {
        return this.plugin;
    }

    public void setPeriods(int periods) {
        this.periods = periods;
    }
    public boolean cancel2() {
        tasks.remove(this.getName());
		return false;
    }
    public void cancel() {
        super.cancel();
        tasks.remove(this.getName());
    }

    public static Task getTask(String name) {
        return tasks.get(name);
    }
    @SuppressWarnings("unlikely-arg-type")
	public static Task getTask1(boolean b) {
        return tasks.get(b);
    }
    public static void schedule(Runnable r) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), r);
    }

    public static void schedule(Runnable r, long delay) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), r, delay);
    }

    public static void schedule(Runnable r, long delay, long period) {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), r, delay, period);
    }

    public static void runAsync(Runnable r) {
        Bukkit.getScheduler().runTaskAsynchronously(Main.getInstance(), r);
    }
}

