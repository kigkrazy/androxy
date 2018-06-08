package com.reizx.asf.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.blankj.utilcode.util.FileIOUtils;
import com.blankj.utilcode.util.FileUtils;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.LogAdapter;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.orhanobut.logger.Printer;
import com.reizx.andrutil.joor.Reflect;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 基于：
 * https://github.com/orhanobut/logger
 * 项目进行开发的模块化日志输出工具类。
 * Created by kig on 2017/12/25.
 * <p>
 * mod by kigkrazy
 */

public class AsfMgrLog {
    private static Printer printer = Reflect.on("com.orhanobut.logger.LoggerPrinter").create().get();
    /**
     * 初始化Log
     * @param tag 全局标签
     * @param path 文件保存路径
     * @param logToAdnroid 是否打印到安卓
     * @param logToFile 是否打印到文件
     * @param logFilePrefix log的文件名前缀
     * @param logFileMaxSize log文件的最大大小
     */
    public static void initLog(String tag, boolean logToAdnroid, boolean logToFile, String path, String logFilePrefix, long logFileMaxSize) {
        if (logToAdnroid) {
            FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                    .showThreadInfo(false)      // 是否显示线程信息。默认为true
                    .methodCount(0)             // 显示多少个方法行。默认值为2
                    .methodOffset(0)            // 跳过堆栈跟踪中的一些方法调用。默认值为5
                    .tag(tag)
                    .build();
            printer.addAdapter(new AndroidLogAdapter(formatStrategy));
        }

        if (logToFile) {
            FileLogAdapter.setLogDir(path, logFilePrefix, logFileMaxSize);       // Log保存文件夹地址
            printer.addAdapter(new FileLogAdapter(tag));
        }
    }

    /**
     * 添加自定义LogAdapter，使得log输出更多样。
     *
     * @param logAdapter
     */
    public void addLogAdapter(LogAdapter logAdapter) {
        printer.addAdapter(logAdapter);
    }

//    public static void addTag(String tag) {      // 增加市场标签
//        printer.t(tag);
//    }

    public static void v(String msg) {
        printer.v(msg);
    }

    public static void v(String tag, String msg) {
        printer.v(tag + " -- " + msg);
    }

    public static void d(String msg) {
        printer.d(msg);
    }

    public static void d(String tag, String msg) {
        printer.d(tag + " -- " + msg);
    }

    public static void i(String msg) {
        printer.i(msg);
    }

    public static void i(String tag, String msg) {
        printer.i(tag + " -- " + msg);
    }

    public static void w(String msg) {
        printer.w(msg);
    }

    public static void w(String tag, String msg) {
        printer.w(tag + " -- " + msg);
    }

    public static void e(String msg) {
        printer.e(msg);
    }

    public static void e(String tag, String msg) {
        printer.e(tag + " -- " + msg);
    }

    public static void json(String msg) {
        printer.json(msg);
    }

    public static void json(String tag, String msg) {
        printer.json(tag + " -- " + msg);
    }

    public static void xml(String msg) {
        printer.xml(msg);
    }

    public static void xml(String tag, String msg) {
        printer.xml(tag + " -- " + msg);
    }

    /**
     * 日志文件输出类
     */
    public static class FileLogAdapter implements LogAdapter {

        private static final SimpleDateFormat LOG_DATE_TIME_FORMAT = new SimpleDateFormat("MM-dd HH:mm:ss.SSS");
        private static ExecutorService sLogExecutor = Executors.newSingleThreadExecutor();
        private static LogFileManager sLogFileManager;
        private static String mTag;

        public FileLogAdapter(String tag) {
            mTag = tag;
        }

        @Override
        public boolean isLoggable(int priority, @Nullable String tag) {
            return true;
        }

        @Override
        public void log(int priority, @Nullable String tag, @NonNull String message) {
            writeToFileIfNeeded(mTag, message);
        }

        /**
         * 设置写入log的文件夹
         *
         * @param dirPath 文件夹地址
         */
        public static void setLogDir(String dirPath, String logFilePrefix, long logFileMaxSize) {
//            File file = new File(dirPath);
//            if (!file.exists() || !file.isDirectory()) {
//                throw new InvalidParameterException();
//            }
            sLogFileManager = new LogFileManager(dirPath, logFilePrefix, logFileMaxSize);
        }

        public static void writeToFileIfNeeded(final String tag, final String msg) {
            if (sLogFileManager == null) {
                return;
            }
            sLogExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    flushLogToFile(tag, msg);
                }
            });
        }

        private static void flushLogToFile(String tag, String msg) {
            String logMsg = formatLog(tag, msg);
            sLogFileManager.writeLogToFile(logMsg);
        }

        private static String formatLog(String tag, String msg) {
            return String.format("%s pid=%d %s: %s\n", LOG_DATE_TIME_FORMAT.format(new Date()), android.os.Process.myPid(), tag, msg);
        }

        public static class LogFileManager {

            private final SimpleDateFormat LOG_FILE_DATE_FORMAT = new SimpleDateFormat("MM-dd HH:mm:ss");//日期

            private File mCurrentLogFile;//当前日志文件对象
            private String mLogFileDir;//日志文件夹
            private String mLogFilePrefix;//文件前缀
            private long mLogFileMaxSize = 100 * 1024 * 1024; //默认文件最大大小为100M
            private int mLogFilesMaxNum = 5; //默认文件个数最多有5个

            LogFileManager(String logFileDir, String logFilePrefix, long logFileMaxSize) {
                mLogFileDir = logFileDir;
                mLogFilePrefix = logFilePrefix;
                mLogFileMaxSize = logFileMaxSize;
            }

            LogFileManager(String logFileDir, String logFilePrefix, long logFileMaxSize, int logFilesMaxNum) {
                mLogFileDir = logFileDir;
                mLogFilePrefix = logFilePrefix;
                mLogFileMaxSize = logFileMaxSize;
                mLogFilesMaxNum = logFilesMaxNum;
            }

            public void writeLogToFile(String logMessage) {
                if (mCurrentLogFile == null || mCurrentLogFile.length() >= mLogFileMaxSize) {
                    mCurrentLogFile = getNewLogFile();
                }
                FileIOUtils.writeFileFromString(mCurrentLogFile.getPath(), logMessage, true);
            }

            private File getNewLogFile() {
                List<File> files = FileUtils.listFilesInDir(mLogFileDir);
                if (files == null || files.size() == 0) {
                    // 创建新文件
                    return createNewLogFile();
                }
                if (files.size() > mLogFilesMaxNum) {
                    // 删掉最老的文件
                    FileUtils.deleteFile(files.get(0));
                }
                // 取最新的文件，看写没写满
                File lastLogFile = files.get(files.size() - 1);
                if (lastLogFile.length() < mLogFileMaxSize) {
                    return lastLogFile;
                } else {
                    // 创建新文件
                    return createNewLogFile();
                }
            }

            private File createNewLogFile() {
                return createFile(mLogFileDir + mLogFilePrefix + LOG_FILE_DATE_FORMAT.format(new Date()) + ".txt");
            }

            /**
             * 创建文件， 如果不存在则创建，否则返回原文件的File对象
             *
             * @param path 文件路径
             * @return 创建好的文件对象, 返回为空表示失败
             */
            synchronized public File createFile(String path) {
                if (TextUtils.isEmpty(path)) {
                    return null;
                }

                File file = new File(path);
                if (file.isFile()) {
                    return file;
                }

                File parentFile = file.getParentFile();
                if (parentFile != null && (parentFile.isDirectory() || parentFile.mkdirs())) {
                    try {
                        if (file.createNewFile()) {
                            return file;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                return null;
            }
        }
    }
}
