package com.fenghj.android.utilslibrary;

import android.support.annotation.IntDef;
import android.support.annotation.IntRange;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Formatter;

/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 2016/09/21
 *     desc  : Log相关工具类
 * </pre>
 */
public final class JLog {

    public static final int V = Log.VERBOSE;
    public static final int D = Log.DEBUG;
    public static final int I = Log.INFO;
    public static final int W = Log.WARN;
    public static final int E = Log.ERROR;
    public static final int A = Log.ASSERT;

    @IntDef({V, D, I, W, E, A})
    @Retention(RetentionPolicy.SOURCE)
    private @interface TYPE {
    }

    private static final char[] T = new char[]{'V', 'D', 'I', 'W', 'E', 'A'};

    private static final int JSON = 0x10;

    private static boolean sLogSwitch         = true;  // log总开关，默认开
    private static String  sGlobalTag         = null;  // log标签
    private static boolean sTagIsSpace        = true;  // log标签是否为空白
    private static boolean sLogHeadSwitch     = true;  // log头部开关，默认开
    private static boolean sLogBorderSwitch   = true;  // log边框开关，默认开
    //    private static int     sConsoleFilter     = V;     // log控制台过滤器
    private static int     sStackDeep         = 1;     // log栈深度

    private static final String LINE_SEP      = System.getProperty("line.separator");
    private static final String TOP_BORDER    = "╔═══════════════════════════════════════════════════════════════════════════════════════════════════";
    private static final String SPLIT_BORDER  = "╠───────────────────────────────────────────────────────────────────────────────────────────────────";
    private static final String LEFT_BORDER   = "║ ";
    private static final String BOTTOM_BORDER = "╚═══════════════════════════════════════════════════════════════════════════════════════════════════";
    private static final int    MAX_LEN       = 4000;
    private static final String NULL          = "null";
    private static final String ARGS          = "args";

    private JLog() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static void v(final Object... contents) {
        log(V, sGlobalTag, contents);
    }

    public static void vTag(final String tag, final Object... contents) {
        log(V, tag, contents);
    }

    public static void d(final Object... contents) {
        log(D, sGlobalTag, contents);
    }

    public static void dTag(final String tag, final Object... contents) {
        log(D, tag, contents);
    }

    public static void i(final Object... contents) {
        log(I, sGlobalTag, contents);
    }

    public static void iTag(final String tag, final Object... contents) {
        log(I, tag, contents);
    }

    public static void w(final Object... contents) {
        log(W, sGlobalTag, contents);
    }

    public static void wTag(final String tag, final Object... contents) {
        log(W, tag, contents);
    }

    public static void e(final Object... contents) {
        log(E, sGlobalTag, contents);
    }

    public static void eTag(final String tag, final Object... contents) {
        log(E, tag, contents);
    }

    public static void a(final Object... contents) {
        log(A, sGlobalTag, contents);
    }

    public static void aTag(final String tag, final Object... contents) {
        log(A, tag, contents);
    }

    public static void json(final String content) {
        log(JSON | D, sGlobalTag, content);
    }

    public static void json(@TYPE final int type, final String content) {
        log(JSON | type, sGlobalTag, content);
    }

    public static void json(final String tag, final String content) {
        log(JSON | D, tag, content);
    }

    public static void json(@TYPE final int type, final String tag, final String content) {
        log(JSON | type, tag, content);
    }

    private static void log(final int type, final String tag, final Object... contents) {
        if (!sLogSwitch) return;
        int type_low = type & 0x0f, type_high = type & 0xf0;
        final TagHead tagHead = processTagAndHead(tag);
        String body = processBody(type_high, contents);
        print2Console(type_low, tagHead.tag, tagHead.consoleHead, body);
    }

    private static TagHead processTagAndHead(String tag) {
        if (!sTagIsSpace && !sLogHeadSwitch) {
            tag = sGlobalTag;
        } else {
            final StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            StackTraceElement targetElement = stackTrace[3];
            String fileName = targetElement.getFileName();
            String className;
            if (fileName == null) {// 混淆可能会导致获取为空 加-keepattributes SourceFile,LineNumberTable
                className = targetElement.getClassName();
                String[] classNameInfo = className.split("\\.");
                if (classNameInfo.length > 0) {
                    className = classNameInfo[classNameInfo.length - 1];
                }
                int index = className.indexOf('$');
                if (index != -1) {
                    className = className.substring(0, index);
                }
                fileName = className + ".java";
            } else {
                int index = fileName.indexOf('.');// 混淆可能导致文件名被改变从而找不到"."
                className = index == -1 ? fileName : fileName.substring(0, index);
            }
            if (sTagIsSpace) tag = isSpace(tag) ? className : tag;
            if (sLogHeadSwitch) {
                String tName = Thread.currentThread().getName();
                final String head = new Formatter()
                        .format("%s, %s(%s:%d)",
                                tName,
                                targetElement.getMethodName(),
                                fileName,
                                targetElement.getLineNumber())
                        .toString();
                final String fileHead = " [" + head + "]: ";
                if (sStackDeep <= 1) {
                    return new TagHead(tag, new String[]{head}, fileHead);
                } else {
                    final String[] consoleHead = new String[Math.min(sStackDeep, stackTrace.length - 3)];
                    consoleHead[0] = head;
                    int spaceLen = tName.length() + 2;
                    String space = new Formatter().format("%" + spaceLen + "s", "").toString();
                    for (int i = 1, len = consoleHead.length; i < len; ++i) {
                        targetElement = stackTrace[i + 3];
                        consoleHead[i] = new Formatter()
                                .format("%s%s(%s:%d)",
                                        space,
                                        targetElement.getMethodName(),
                                        targetElement.getFileName(),
                                        targetElement.getLineNumber())
                                .toString();
                    }
                    return new TagHead(tag, consoleHead, fileHead);
                }
            }
        }
        return new TagHead(tag, null, ": ");
    }

    private static String processBody(final int type, final Object... contents) {
        String body = NULL;
        if (contents != null) {
            if (contents.length == 1) {
                Object object = contents[0];
                if (object != null) body = object.toString();
                if (type == JSON) {
                    body = formatJson(body);
                }
            } else {
                StringBuilder sb = new StringBuilder();
                for (int i = 0, len = contents.length; i < len; ++i) {
                    Object content = contents[i];
                    sb.append(ARGS)
                            .append("[")
                            .append(i)
                            .append("]")
                            .append(" = ")
                            .append(content == null ? NULL : content.toString())
                            .append(LINE_SEP);
                }
                body = sb.toString();
            }
        }
        return body;
    }

    private static String formatJson(String json) {
        try {
            if (json.startsWith("{")) {
                json = new JSONObject(json).toString(4);
            } else if (json.startsWith("[")) {
                json = new JSONArray(json).toString(4);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }

    private static void print2Console(final int type, final String tag, final String[] head, final String msg) {
        printBorder(type, tag, true);
        printHead(type, tag, head);
        printMsg(type, tag, msg);
        printBorder(type, tag, false);
    }

    private static void printBorder(final int type, final String tag, boolean isTop) {
        if (sLogBorderSwitch) {
            Log.println(type, tag, isTop ? TOP_BORDER : BOTTOM_BORDER);
        }
    }

    private static void printHead(final int type, final String tag, final String[] head) {
        if (head != null) {
            for (String aHead : head) {
                Log.println(type, tag, sLogBorderSwitch ? LEFT_BORDER + aHead : aHead);
            }
            if (sLogBorderSwitch) Log.println(type, tag, SPLIT_BORDER);
        }
    }

    private static void printMsg(final int type, final String tag, final String msg) {
        int len = msg.length();
        int countOfSub = len / MAX_LEN;
        if (countOfSub > 0) {
            int index = 0;
            for (int i = 0; i < countOfSub; i++) {
                printSubMsg(type, tag, msg.substring(index, index + MAX_LEN));
                index += MAX_LEN;
            }
            if (index != len) {
                printSubMsg(type, tag, msg.substring(index, len));
            }
        } else {
            printSubMsg(type, tag, msg);
        }
    }

    private static void printSubMsg(final int type, final String tag, final String msg) {
        if (!sLogBorderSwitch) {
            Log.println(type, tag, msg);
            return;
        }
        StringBuilder sb = new StringBuilder();
        String[] lines = msg.split(LINE_SEP);
        for (String line : lines) {
            Log.println(type, tag, LEFT_BORDER + line);
        }
    }

    private static boolean isSpace(final String s) {
        if (s == null) return true;
        for (int i = 0, len = s.length(); i < len; ++i) {
            if (!Character.isWhitespace(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static class Builder {

        public Builder setLogSwitch(boolean logSwitch) {
            sLogSwitch = logSwitch;
            return this;
        }

        public Builder setGlobalTag(final String tag) {
            if (isSpace(tag)) {
                sGlobalTag = "";
                sTagIsSpace = true;
            } else {
                sGlobalTag = tag;
                sTagIsSpace = false;
            }
            return this;
        }

        public Builder setLogHeadSwitch(boolean logHeadSwitch) {
            sLogHeadSwitch = logHeadSwitch;
            return this;
        }

        public Builder setBorderSwitch(boolean borderSwitch) {
            sLogBorderSwitch = borderSwitch;
            return this;
        }

//        public Builder setConsoleFilter(@TYPE final int consoleFilter) {
//            sConsoleFilter = consoleFilter;
//            return this;
//        }

        public Builder setStackDeep(@IntRange(from = 1) final int stackDeep) {
            sStackDeep = stackDeep;
            return this;
        }
    }

    private static class TagHead {
        String tag;
        String[] consoleHead;
        String fileHead;

        TagHead(String tag, String[] consoleHead, String fileHead) {
            this.tag = tag;
            this.consoleHead = consoleHead;
            this.fileHead = fileHead;
        }
    }
}
