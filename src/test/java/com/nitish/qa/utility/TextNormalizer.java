package com.nitish.qa.utility;

import org.apache.commons.text.StringEscapeUtils;

public final class TextNormalizer {

    private TextNormalizer() {}

    public static String normalize(String value) {
        return value == null ? null : StringEscapeUtils.unescapeHtml4(value);
    }
}
