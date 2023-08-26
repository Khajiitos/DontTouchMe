package me.khajiitos.donttouchme.common;

import me.khajiitos.donttouchme.common.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DontTouchMe {
    public static final String MOD_ID = "donttouchme";
    public static final Logger LOGGER = LoggerFactory.getLogger("Don't Touch Me");

    public static void init() {
        Config.load();
    }
}
