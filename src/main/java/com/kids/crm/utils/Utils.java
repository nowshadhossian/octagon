package com.kids.crm.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Objects;
import java.util.TreeSet;

public class Utils {

    public static boolean answerMatched(String selectedOptionCommaSeparated, String answersCommaSeparated) {
        return !StringUtils.isBlank(selectedOptionCommaSeparated) && !StringUtils.isBlank(answersCommaSeparated)
                && Objects.equals(new TreeSet<>(Arrays.asList(selectedOptionCommaSeparated.trim().toUpperCase().split("\\s*,\\s*"))).toString(),
                new TreeSet<>(Arrays.asList(answersCommaSeparated.trim().toUpperCase().split("\\s*,\\s*"))).toString());

    }
}
