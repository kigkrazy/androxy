package com.reizx.asf.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.blankj.utilcode.constant.TimeConstants;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.elvishew.xlog.Logger;
import com.elvishew.xlog.printer.AndroidPrinter;
import com.elvishew.xlog.printer.Printer;
import com.elvishew.xlog.printer.file.naming.FileNameGenerator;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class XLogUtilExt {
    private static Logger logger;

    static {
        Printer androidPrinter = new AndroidPrinter();
        logger = new Logger.Builder()
                .nt()
                .tag("xlog")
                .nb()
                .nst()
                .printers(androidPrinter)
                .build();
    }


    public static void d(@NonNull String message, @Nullable Object... args) {
        logger.d(message, args);
    }


    public static void i(@NonNull String message, @Nullable Object... args) {
        logger.i(message, args);
    }

    public static void v(@NonNull String message, @Nullable Object... args) {
        logger.v(message, args);
    }

    public static void w(@NonNull String message, @Nullable Object... args) {
        logger.w(message, args);
    }

    public static void e(@NonNull String message, @Nullable Object... args) {
        logger.e(null, message, args);
    }

    /**
     * Formats the given json content and print it
     */
    public static void json(@Nullable String json) {
        logger.json(json);
    }

    /**
     * Formats the given xml content and print it
     */
    public static void xml(@Nullable String xml) {
        logger.xml(xml);
    }


    public static class Setter {
        String tag;
        Printer[] printers;

        private Setter() {
        }

        public static Setter newSetter() {
            return new Setter();
        }

        public Setter tag(String tag) {
            this.tag = tag;
            return this;
        }

        public Setter printers(Printer... printers) {
            this.printers = printers;
            return this;
        }

        public void set() {
            XLogUtilExt.logger = new Logger.Builder()
                    .nt()
                    .tag(tag)
                    .nb()
                    .nst()
                    .printers(printers)
                    .build();
        }
    }

    public static class HistoryDateFileNameGenerator implements FileNameGenerator {
        int history;
        String logdir;

        ThreadLocal<SimpleDateFormat> localDateFormat = new ThreadLocal<SimpleDateFormat>() {
            @Override
            protected SimpleDateFormat initialValue() {
                return new SimpleDateFormat("yyyy-MM-dd", Locale.US);
            }
        };

        /**
         * 设置保存历史
         *
         * @param history
         */
        public HistoryDateFileNameGenerator(int history, String logdir) {
            this.logdir = logdir;

            if (history <= 0) {
                this.history = 3;
            } else {
                this.history = history;
            }
        }

        @Override
        public boolean isFileNameChangeable() {
            return true;
        }

        /**
         * Generate a file name which represent a specific date.
         * 此处设置保留几天的任务
         */
        @Override
        public String generateFileName(int logLevel, long timestamp) {
            SimpleDateFormat sdf = localDateFormat.get();
            sdf.setTimeZone(TimeZone.getDefault());
            String now = sdf.format(new Date(timestamp));
            deleteHistory(sdf, now);
            return now;
        }

        /**
         * 删除7天前的数据
         */
        public void deleteHistory(SimpleDateFormat sdf, String now) {
            List<File> files = FileUtils.listFilesInDir(logdir);
            if (files.size() <= history)
                return;

            for (File file : files) {
                String name = file.getName();
                long span = TimeUtils.getTimeSpan(name, now, sdf, TimeConstants.DAY);

                if (span < 3){
                    FileUtils.deleteFile(file);
                }
            }
        }
    }
}
