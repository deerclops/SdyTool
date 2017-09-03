package com.iwuliu.sdy.bl.icon;

import com.joanzapata.iconify.Icon;
import com.joanzapata.iconify.IconFontDescriptor;

/**
 * Created by DarkSouls on 2017/9/3.
 */

public class FontModule implements IconFontDescriptor {

    @Override
    public String ttfFileName() {
        return "iconfont.ttf";
    }

    @Override
    public Icon[] characters() {
        return ExIcon.values();
    }
}
