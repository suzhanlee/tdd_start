package com.example.tdd_start.chapter2;

public class PasswordStrengthMeter {

    public PasswordStrength meter(String s) {
        if (s == null || s.isEmpty()) {
            return PasswordStrength.INVALID;
        }

        int metCounts = getMetCriteriaCounts(s);

        if (metCounts <= 1) {
            return PasswordStrength.WEAK;
        }

//        if (lengthEnough && !containsNum && !containsUpp) {
//            return PasswordStrength.WEAK;
//        }
//
//        if (!lengthEnough && containsNum && !containsUpp) {
//            return PasswordStrength.WEAK;
//        }
//
//        if (!lengthEnough && !containsNum && containsUpp) {
//            return PasswordStrength.WEAK;
//        }

        if (metCounts == 2) {
            return PasswordStrength.NORMAL;
        }
//        if (!lengthEnough) {
//            return PasswordStrength.NORMAL;
//        }
//
//        if (!containsNum) {
//            return PasswordStrength.NORMAL;
//        }
//
//        if (!containsUpp) {
//            return PasswordStrength.NORMAL;
//        }

        return PasswordStrength.STRONG;
    }

    private static int getMetCriteriaCounts(String s) {
        int metCounts = 0;
        boolean lengthEnough = s.length() >= 8;
        if (lengthEnough) {
            metCounts++;
        }
        if (meetsContainingNumberCriteria(s)) {
            metCounts++;
        }
        if (meetsContainingUppercaseCriteria(s)) {
            metCounts++;
        }
        return metCounts;
    }

    private static boolean meetsContainingUppercaseCriteria(String s) {
        boolean containsUpp = false;
        for (char ch : s.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                containsUpp = true;
                break;
            }
        }
        return containsUpp;
    }

    private static boolean meetsContainingNumberCriteria(String s) {
        boolean containsNum = false;
        for (char ch : s.toCharArray()) {
            if (ch >= '0' && ch <= '9') {
                containsNum = true;
                break;
            }
        }
        return containsNum;
    }
}
