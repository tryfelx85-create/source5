package com.tryfx.pvpbot;

import org.bukkit.plugin.java.JavaPlugin;

public final class TryFXTheBot extends JavaPlugin {

    private BotManager botManager;
    private TrainingWorldManager trainingWorldManager;

    @Override
    public void onEnable() {
        if (getServer().getPluginManager().getPlugin("Citizens") == null) {
            getLogger().severe("Citizens is not installed! TryFXTheBot requires the Citizens plugin. Disabling.");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        saveDefaultConfig();

        this.trainingWorldManager = new TrainingWorldManager(this);
        this.trainingWorldManager.setup();

        this.botManager = new BotManager(this);
        this.botManager.start();

        TrainingCommand trainingCmd = new TrainingCommand(this);
        getCommand("training").setExecutor(trainingCmd);

        SpawnBotCommand spawnCmd = new SpawnBotCommand(this);
        getCommand("trainingbot").setExecutor(spawnCmd);
        getCommand("trainingbot").setTabCompleter(spawnCmd);

        getLogger().info("TryFXTheBot enabled.");
    }

    @Override
    public void onDisable() {
        if (botManager != null) {
            botManager.stop();
        }
        getLogger().info("TryFXTheBot disabled.");
    }

    public BotManager getBotManager() {
        return botManager;
    }

    public TrainingWorldManager getTrainingWorldManager() {
        return trainingWorldManager;
    }
}
