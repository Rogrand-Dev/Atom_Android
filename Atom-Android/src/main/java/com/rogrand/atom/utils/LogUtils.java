package com.rogrand.atom.utils;

import com.orhanobut.logger.Logger;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Log 管理类
 */
public class LogUtils {

    private static boolean isDebug = true;

    private LogUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    // ---------- 传入默认 TAG 的函数 ----------
    public static void i(String msg) {
        if (!isDebug)
            return;
        Logger.i(msg);
    }

    public static void d(String msg) {
        if (!isDebug)
            return;
        Logger.d(msg);
    }

    /**
     * 输出异常
     */
    public static void e(String msg) {
        if (!isDebug)
            return;
        Logger.e(msg);
    }

    /**
     * 输出 List
     */
    public static void d(List<String> msg) {
        if (!isDebug)
            return;
        Logger.d(msg);
    }

    /**
     * 输出 Set
     */
    public static void d(Set<String> msg) {
        if (!isDebug)
            return;
        Logger.d(msg);
    }

    /**
     * 输出 Map
     */
    public static void d(Map<String, String> msg) {
        if (!isDebug)
            return;
        Logger.d(msg);
    }

    /**
     * 输出 Json
     */
    public static void json(String msg) {
        if (!isDebug)
            return;
        Logger.json(msg);
    }

    /**
     * 输出 XML
     */
    public static void xml(String msg) {
        if (!isDebug)
            return;
        Logger.xml(msg);
    }

    // ---------- 传入自定义 TAG 的函数 ----------
    public static void i(String tag, String msg) {
        if (!isDebug)
            return;
        Logger.t(tag).i(msg);
    }

    public static void d(String tag, String msg) {
        if (!isDebug)
            return;
        Logger.t(tag).d(msg);
    }

    public static void e(String tag, String msg) {
        if (!isDebug)
            return;
        Logger.t(tag).e(msg);
    }

    /**
     * 输出 List
     */
    public static void d(String tag, List<String> msg) {
        if (!isDebug)
            return;
        Logger.t(tag).d(msg);
    }

    /**
     * 输出 Set
     */
    public static void d(String tag, Set<String> msg) {
        if (!isDebug)
            return;
        Logger.t(tag).d(msg);
    }

    /**
     * 输出 Map
     */
    public static void d(String tag, Map<String, String> msg) {
        if (!isDebug)
            return;
        Logger.t(tag).d(msg);
    }

    /**
     * 输出 Json
     */
    public static void json(String tag, String msg) {
        if (!isDebug)
            return;
        Logger.t(tag).json(msg);
    }

    /**
     * 输出 XML
     */
    public static void xml(String tag, String msg) {
        if (!isDebug)
            return;
        Logger.t(tag).xml(msg);
    }
}