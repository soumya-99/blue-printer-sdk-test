package com.blueprintsdktest;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import com.basewin.aidl.OnPrinterListener;
import com.basewin.define.FontsType;
import com.basewin.define.GlobalDef;
import com.basewin.models.BitmapPrintLine;
import com.basewin.models.PrintLine;
import com.basewin.models.TextPrintLine;
import com.basewin.services.PrinterBinder;
import com.basewin.services.ServiceManager;
import com.basewin.utils.TimerCountTools;
import com.basewin.zxing.utils.QRUtil;

import com.basewin.log.LogUtil;
import com.basewin.models.TextPrintLine;
import com.basewin.services.ServiceManager;

import org.json.JSONException;

public class MyPrinter extends ReactContextBaseJavaModule {
    public boolean printingstatus = true;
    public PrinterListener printer_callback = new PrinterListener();
    private static ReactApplicationContext mContext;

    public MyPrinter(ReactApplicationContext reactContext) {
        super(reactContext);
        mContext = reactContext;
    }

    @Override
    public String getName() {
        return "MyPrinter";
    }

    @ReactMethod
    public void greet(String name, Promise response) {

        // response.resolve("hello");
        try {
            String res = "hello " + name;
            response.resolve(res);
        } catch (Exception e) {
            response.reject("Error", e);
        }
    }

    void initPrinter(Callback callback) {
        try {
            // set print type
            ServiceManager.getInstence().getPrinter().setPrintTypesettingType(GlobalDef.PRINTERLAYOUT_TYPESETTING);
        } catch (Exception e) {
            e.printStackTrace();
            callback.invoke(e);
        }
    }

    public void leftAlignedPrintText(String msg, int size) {
        try {
            // timeTools = new TimerCountTools();
            // timeTools.start();
            // clean print cache.
            ServiceManager.getInstence().getPrinter().cleanCache();
            // set Gray
            ServiceManager.getInstence().getPrinter().setPrintGray(Integer.valueOf(2000));
            // set lineSpace
            ServiceManager.getInstence().getPrinter().setLineSpace(Integer.valueOf(1));
            // set font
            ServiceManager.getInstence().getPrinter().setPrintFont(FontsType.simsun);
            TextPrintLine textPrintLine = new TextPrintLine();
            textPrintLine.setType(PrintLine.TEXT);
            textPrintLine.setPosition(PrintLine.LEFT);
            textPrintLine.setSize(size);

            textPrintLine.setContent(msg);
            ServiceManager.getInstence().getPrinter().addPrintLine(textPrintLine);
            ServiceManager.getInstence().getPrinter().beginPrint(printer_callback);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void rightAlignedPrintText(String msg, int size) {
        try {
            // timeTools = new TimerCountTools();
            // timeTools.start();
            // clean print cache.
            ServiceManager.getInstence().getPrinter().cleanCache();
            // set Gray
            ServiceManager.getInstence().getPrinter().setPrintGray(Integer.valueOf(2000));
            // set lineSpace
            ServiceManager.getInstence().getPrinter().setLineSpace(Integer.valueOf(1));
            // set font
            ServiceManager.getInstence().getPrinter().setPrintFont(FontsType.simsun);
            TextPrintLine textPrintLine = new TextPrintLine();
            textPrintLine.setType(PrintLine.TEXT);
            textPrintLine.setPosition(PrintLine.RIGHT);
            textPrintLine.setSize(size);
            textPrintLine.setContent(msg);
            ServiceManager.getInstence().getPrinter().addPrintLine(textPrintLine);
            ServiceManager.getInstence().getPrinter().beginPrint(printer_callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void centerAlignedPrintText(String msg, int size) {
        try {
            // timeTools = new TimerCountTools();
            // timeTools.start();
            // clean print cache.
            ServiceManager.getInstence().getPrinter().cleanCache();
            // set Gray
            ServiceManager.getInstence().getPrinter().setPrintGray(Integer.valueOf(2000));
            // set lineSpace
            ServiceManager.getInstence().getPrinter().setLineSpace(Integer.valueOf(1));
            // set font
            ServiceManager.getInstence().getPrinter().setPrintFont(FontsType.simsun);
            TextPrintLine textPrintLine = new TextPrintLine();
            textPrintLine.setType(PrintLine.TEXT);
            textPrintLine.setPosition(PrintLine.CENTER);
            textPrintLine.setSize(size);
            textPrintLine.setContent(msg);
            ServiceManager.getInstence().getPrinter().addPrintLine(textPrintLine);
            ServiceManager.getInstence().getPrinter().beginPrint(printer_callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void PrintText(String header1, String header2, String header3, String content1, String content2,
            String footer1, String footer2, int size1, int size2, int size3, int size4, int size5, int size6,
            int size7) {
        try {
            // timeTools = new TimerCountTools();
            // timeTools.start();
            // clean print cache.
            ServiceManager.getInstence().getPrinter().cleanCache();
            // set Gray
            ServiceManager.getInstence().getPrinter().setPrintGray(Integer.valueOf(2000));
            // set lineSpace
            ServiceManager.getInstence().getPrinter().setLineSpace(Integer.valueOf(1));
            // set font
            ServiceManager.getInstence().getPrinter().setPrintFont(FontsType.simsun);
            TextPrintLine textPrintLine1 = new TextPrintLine();
            textPrintLine1.setType(PrintLine.TEXT);
            textPrintLine1.setPosition(PrintLine.CENTER);
            textPrintLine1.setSize(size1);
            textPrintLine1.setContent(header1);
            TextPrintLine textPrintLine2 = new TextPrintLine();
            textPrintLine2.setType(PrintLine.TEXT);
            textPrintLine2.setPosition(PrintLine.CENTER);
            textPrintLine2.setSize(size2);
            textPrintLine2.setContent(header2);
            TextPrintLine textPrintLine3 = new TextPrintLine();
            textPrintLine3.setType(PrintLine.TEXT);
            textPrintLine3.setPosition(PrintLine.CENTER);
            textPrintLine3.setSize(size3);
            textPrintLine3.setContent(header3);
            TextPrintLine textPrintLine4 = new TextPrintLine();
            textPrintLine4.setType(PrintLine.TEXT);
            textPrintLine4.setPosition(PrintLine.CENTER);
            textPrintLine4.setSize(size4);
            textPrintLine4.setContent(content1);
            TextPrintLine textPrintLine5 = new TextPrintLine();
            textPrintLine5.setType(PrintLine.TEXT);
            textPrintLine5.setPosition(PrintLine.CENTER);
            textPrintLine5.setSize(size5);
            textPrintLine5.setContent(content2);
            TextPrintLine textPrintLine6 = new TextPrintLine();
            textPrintLine6.setType(PrintLine.TEXT);
            textPrintLine6.setPosition(PrintLine.CENTER);
            textPrintLine6.setSize(size6);
            textPrintLine6.setContent(footer1);
            TextPrintLine textPrintLine7 = new TextPrintLine();
            textPrintLine7.setType(PrintLine.TEXT);
            textPrintLine7.setPosition(PrintLine.CENTER);
            textPrintLine7.setSize(size7);
            textPrintLine7.setContent(footer2);
            ServiceManager.getInstence().getPrinter().addPrintLine(textPrintLine1);
            ServiceManager.getInstence().getPrinter().addPrintLine(textPrintLine2);
            ServiceManager.getInstence().getPrinter().addPrintLine(textPrintLine3);
            ServiceManager.getInstence().getPrinter().addPrintLine(textPrintLine4);
            ServiceManager.getInstence().getPrinter().addPrintLine(textPrintLine5);
            ServiceManager.getInstence().getPrinter().addPrintLine(textPrintLine6);
            ServiceManager.getInstence().getPrinter().addPrintLine(textPrintLine7);
            ServiceManager.getInstence().getPrinter().beginPrint(printer_callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @ReactMethod
    public void printText(Promise res, Callback callback) {
        try {
            res.resolve(callback);
            this.initPrinter(callback);
            this.centerAlignedPrintText("TAX INVOICE", TextPrintLine.FONT_LARGE); // 20 Chars
            this.centerAlignedPrintText("APPETALS SOLUTIONS PVT LTD", TextPrintLine.FONT_NORMAL);// 32 Chars
            this.leftAlignedPrintText("--------------------------------", TextPrintLine.FONT_NORMAL);
            this.leftAlignedPrintText("PRODUCT NAME               RATE ", TextPrintLine.FONT_NORMAL);
            this.leftAlignedPrintText("QTY      UNIT               AMT ", TextPrintLine.FONT_NORMAL);
            this.leftAlignedPrintText("--------------------------------", TextPrintLine.FONT_NORMAL);
            this.leftAlignedPrintText("XXXXXXXXXXXXXX     XXXXXXXXX.XX ", TextPrintLine.FONT_NORMAL);
            this.leftAlignedPrintText("XXXX.XX  XXX       XXXXXXXXX.XX ", TextPrintLine.FONT_NORMAL);
            this.leftAlignedPrintText("--------------------------------", TextPrintLine.FONT_NORMAL);
            this.leftAlignedPrintText("XXXXXXXXXXXXXX     XXXXXXXXX.XX ", TextPrintLine.FONT_NORMAL);
            this.leftAlignedPrintText("XXXX.XX  XXX       XXXXXXXXX.XX ", TextPrintLine.FONT_NORMAL);
            this.leftAlignedPrintText("--------------------------------", TextPrintLine.FONT_NORMAL);
            this.leftAlignedPrintText("XXXXXXXXXXXXXX     XXXXXXXXX.XX ", TextPrintLine.FONT_NORMAL);
            this.leftAlignedPrintText("XXXX.XX  XXX       XXXXXXXXX.XX ", TextPrintLine.FONT_NORMAL);
            this.leftAlignedPrintText("--------------------------------", TextPrintLine.FONT_NORMAL);
            this.leftAlignedPrintText("TOTAL            XXXXXXXXXXX.XX ", TextPrintLine.FONT_NORMAL);
            this.paperFeed(1);
            this.centerAlignedPrintText("THANKYOU", TextPrintLine.FONT_SMALL);// 48 Chars
            this.paperFeed(3);
        } catch (Exception e) {
            callback.invoke(e);
            res.reject(e);
        }

    }

    public void setTextPrintLine(String printContent, int printAlignment, int printFontSize, boolean isBold) {
        try {
            // timeTools = new TimerCountTools();
            // timeTools.start();
            // ServiceManager.getInstence().getPrinter().setPrintFont(FontsType.simsun);
            TextPrintLine textPrintLine = new TextPrintLine();
            textPrintLine.setType(PrintLine.TEXT);
            textPrintLine.setPosition(printAlignment);
            textPrintLine.setSize(printFontSize);
            textPrintLine.setContent(printContent);
            textPrintLine.setBold(isBold);
            ServiceManager.getInstence().getPrinter().addPrintLine(textPrintLine);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void paperFeed(int lines) {
        try {
            // timeTools = new TimerCountTools();
            // timeTools.start();
            ServiceManager.getInstence().getPrinter().cleanCache();
            TextPrintLine textPrintLine = new TextPrintLine();
            textPrintLine.setType(PrintLine.TEXT);
            textPrintLine.setPosition(PrintLine.CENTER);
            textPrintLine.setSize(TextPrintLine.FONT_NORMAL);
            for (int loop = 0; loop < lines; loop++) {
                textPrintLine.setContent("                               ");
                ServiceManager.getInstence().getPrinter().addPrintLine(textPrintLine);
            }
            ServiceManager.getInstence().getPrinter().beginPrint(printer_callback);

/// checked paper         } catch (JSONException e) {
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public class PrinterListener implements OnPrinterListener {
        private final String TAG = "Print";

        @Override
        public void onStart() {
            // Print start
            Log.d("PrinterListener", "start print");
            printingstatus = false;
        }

        @Override
        public void onFinish() {
            // End of the print
            Log.d("PrinterListener", "Print success");
            // timeTools.stop();
            // Log.d("PrinterListener", "time cost：" + timeTools.getProcessTime());
            printingstatus = true;

        }

        @Override
        public void onError(int errorCode, String detail) {
            // print error
            Log.d("PrinterListener", "print error" + " errorcode = " + errorCode + " detail = " + detail);
            printingstatus = true;
            if (errorCode == PrinterBinder.PRINTER_ERROR_NO_PAPER) {
                Toast.makeText(mContext, "Insufficient Paper", Toast.LENGTH_LONG).show();
            }
            if (errorCode == PrinterBinder.PRINTER_ERROR_OVER_HEAT) {
                Toast.makeText(mContext, "Device Over heated", Toast.LENGTH_LONG).show();
            }
            if (errorCode == PrinterBinder.PRINTER_ERROR_OTHER) {
                Toast.makeText(mContext, "Insufficient Paper", Toast.LENGTH_LONG).show();
                // Toast.makeText(mContext, "other error happen during printing",
                // Toast.LENGTH_SHORT).show();
            }
        }
    }
}
