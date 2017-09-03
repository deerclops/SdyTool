package com.iwuliu.sdy.core.app;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by DarkSouls on 2017/9/3.
 */

public class Configurator {

    private static final HashMap<String, Object> SDY_CONFIGS = new HashMap<>();
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();

    private Configurator() {
        SDY_CONFIGS.put(ConfigType.CONFIG_READY.name(), false);
    }

    /**
     * 线程安全的懒汉模式
     */
    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }

    public static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    public final HashMap<String, Object> getConfigs() {
        return SDY_CONFIGS;
    }

    public final void configure() {
        initIcons();
        SDY_CONFIGS.put(ConfigType.CONFIG_READY.name(), true);
    }

    public final Configurator withApiHost(String apiHost) {
        SDY_CONFIGS.put(ConfigType.API_HOST.name(), apiHost);
        return this;
    }

    private void checkConfiguration() {
        final boolean isReady = (boolean) SDY_CONFIGS.get(ConfigType.CONFIG_READY.name());
        if (!isReady) {
            throw new RuntimeException("Configuration hasn't been ready yet. Call method configure first.");
        }
    }

    @SuppressWarnings("unchecked")
    public <T> T getConfigurationValue(Enum<ConfigType> key) {
        checkConfiguration();
        return (T) SDY_CONFIGS.get(key.name());
    }

    private void initIcons() {
        if (ICONS.size() > 0) {
            Iconify.IconifyInitializer iconifyInitializer = Iconify.with(ICONS.get(0));
            for (int i = 1; i < ICONS.size(); i++)
                iconifyInitializer.with(ICONS.get(i));
        }
    }

    public final Configurator addIcon(IconFontDescriptor descriptor){
        ICONS.add(descriptor);
        return this;
    }
}
