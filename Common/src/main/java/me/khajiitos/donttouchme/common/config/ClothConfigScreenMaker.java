package me.khajiitos.donttouchme.common.config;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;

public class ClothConfigScreenMaker {

    public static Screen create(Minecraft minecraft, Screen parent) {
        return create(parent);
    }

    public static Screen create(Screen parent) {
        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(new TranslatableComponent("donttouchme.name"))
                .setSavingRunnable(Config::save);

        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        ConfigCategory category = builder.getOrCreateCategory(new TranslatableComponent("donttouchme.category.general"));

        category.addEntry(
                entryBuilder.startBooleanToggle(new TranslatableComponent("donttouchme.config.players_only"), Config.cfg.playersOnly)
                        .setDefaultValue(Config.DEFAULT.playersOnly)
                        .setTooltip(new TranslatableComponent("donttouchme.config.players_only.description"))
                        .setSaveConsumer(bool -> Config.cfg.playersOnly = bool)
                        .build()
        );

        return builder.build();
    }
}