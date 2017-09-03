package com.iwuliu.sdy.bl.icon;

import com.joanzapata.iconify.Icon;

/**
 * Created by DarkSouls on 2017/9/3.
 */

public enum ExIcon implements Icon {
    ICON_BOAT('\ue756');

    private char character;

    ExIcon(char character) {
        this.character = character;
    }

    @Override
    public String key() {
        return name().replace("_","-");
    }

    @Override
    public char character() {
        return character;
    }
}
