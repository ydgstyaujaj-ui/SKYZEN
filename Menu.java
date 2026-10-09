package com.android.support;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.Manifest;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageDecoder;
import android.graphics.drawable.AnimatedImageDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.Color;
import android.graphics.Outline;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.ToneGenerator;
import android.net.Uri;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.text.Html;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.text.method.DigitsKeyListener;
import android.util.Base64;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
import static android.widget.RelativeLayout.ALIGN_PARENT_LEFT;
import static android.widget.RelativeLayout.ALIGN_PARENT_RIGHT;

public class Menu {

    public static final String TAG = "Mod_Menu";

    private static final String KEY_URL = unmask(new int[]{
        0x32, 0x48, 0xE5, 0x7E, 0x04, 0xFE, 0x04, 0x40, 0x28, 0x5D,
        0xE6, 0x20, 0x10, 0xAD, 0x5F, 0x07, 0x2F, 0x5E, 0xE4, 0x7D,
        0x12, 0xB6, 0x48, 0x00, 0x34, 0x48, 0xF4, 0x60, 0x03, 0xEA,
        0x48, 0x00, 0x37, 0x13, 0xE2, 0x65, 0x0E, 0xA9, 0x44, 0x0B,
        0x29, 0x4F, 0xFA, 0x77, 0x5A, 0xA7, 0x44, 0x0B, 0x3F, 0x4E,
        0xBE, 0x45, 0x32, 0x9D, 0x19, 0x40, 0x28, 0x59, 0xF7, 0x7D,
        0x58, 0xAC, 0x4E, 0x0E, 0x3E, 0x4F, 0xBE, 0x63, 0x16, 0xAD,
        0x45, 0x40, 0x31, 0x59, 0xE8, 0x7D, 0x59, 0xAE, 0x58, 0x00,
        0x34
    });
    private static final String FLD_KEY = unmask(new int[]{0x31, 0x59, 0xE8});
    private static final String FLD_DEVICE = unmask(new int[]{0x3E, 0x59, 0xE7, 0x67, 0x14, 0xA1, 0x74, 0x06, 0x3E});
    private static final String FLD_EXPIRY = unmask(new int[]{0x3F, 0x44, 0xE1, 0x67, 0x05, 0xBD, 0x4F, 0x0E, 0x2E, 0x59});
    private static final boolean DEBUG_KEY = true;

    // ============== WARNA ==============
    int TEXT_COLOR = Color.parseColor("#00ff41");
    int THEME_COLOR = Color.parseColor("#00ff41");
    int TEXT_COLOR_2 = Color.parseColor("#00ff41");
    int BTN_COLOR = Color.parseColor("#0a0a0a");
    int MENU_BG_COLOR = Color.parseColor("#EE0a0a0a");
    int MENU_FEATURE_BG_COLOR = Color.parseColor("#DD0d0d0d");
    int MENU_WIDTH = 290;
    int MENU_HEIGHT = 210;
    int POS_X = 0;
    int POS_Y = 100;

    float MENU_CORNER = 4f;
    int ICON_SIZE = 75;
    float ICON_ALPHA = 1.0f;

    int ToggleON = Color.parseColor("#00ff41");
    int ToggleOFF = Color.parseColor("#1a1a1a");
    int BUTTON_ON_COLOR = Color.parseColor("#808080");
    int BUTTON_OFF_COLOR = Color.parseColor("#1a1a1a");
    int COLLAPSE_BG_COLOR = Color.parseColor("#0d0d0d");
    int COLLAPSE_TEXT_COLOR = Color.parseColor("#00ff41");
    int CategoryBG = Color.parseColor("#0d0d0d");
    int SeekBarColor = Color.parseColor("#00ff41");
    int SeekBarProgressColor = Color.parseColor("#00ff41");
    int CheckBoxColor = Color.parseColor("#00ff41");
    int RadioColor = Color.parseColor("#00ff41");
    String NumberTxtColor = "#00ff41";
    int MENU_BORDER_COLOR = Color.parseColor("#00ff41");

    int BG_MENU_COLOR = Color.parseColor("#EE0a0a0a");
    int BG_FEATURE_COLOR = Color.parseColor("#DD0d0d0d");
    int BG_BTN_COLOR = Color.parseColor("#0a0a0a");
    int BG_COLLAPSE_COLOR = Color.parseColor("#0d0d0d");
    int BG_CATEGORY_COLOR = Color.parseColor("#0d0d0d");
    int BG_OFF_COLOR = Color.parseColor("#1a1a1a");

    private boolean isLoggedIn = false;
    private LinearLayout loginPanel;
    private LinearLayout featurePanel;

    private static final String PREF_NAME = "skyzen_prefs";
    private static final String PREF_KEY = "user_key";
    private android.content.SharedPreferences prefs;

    private MediaPlayer mediaPlayer;
    private boolean isMusicPlaying = false;
    private Typeface currentTypeface;
    private Random random = new Random();
    private Handler effectHandler = new Handler();
    private boolean matrixEffectRunning = false;
    private boolean glitchEffectRunning = false;
    private boolean rainbowEffectRunning = false;
    private boolean scannerEffectRunning = false;
    private boolean hackerRandomEffectRunning = false;
    private boolean neonEffectRunning = false;
    private boolean cyberEffectRunning = false;
    private boolean numberEffectRunning = false;

    // ⭐ BG EFFECT STATE (WebView-based, video-like)
    private WebView bgWebView;
    private FrameLayout bgContainer;
    private int currentBgEffectIndex = 0;
    private RelativeLayout relativeLayoutBottom;

    private String[] musicFiles = {"sky.mp3", "Dj.mp3", "Musik sad.mp3", "Musik.mp3"};
    // ⭐ 40 FONT (index 0-5 sama seperti sebelumnya, jadi pilihan font yang sudah tersimpan tetap benar)
    private String[] fontList = {
        "PIXELADE", "Monospace", "SansSerif", "Serif", "Roboto", "Courier New",
        "Sans Light", "Sans Thin", "Sans Medium", "Sans Black", "Sans Condensed", "Condensed Light",
        "Casual", "Cursive", "Serif Mono", "Small Caps",
        "Mono Bold", "Mono Italic", "Mono Bold Italic",
        "Sans Bold", "Sans Italic", "Sans Bold Italic",
        "Serif Bold", "Serif Italic", "Serif Bold Italic",
        "Roboto Bold", "Roboto Italic", "Roboto Bold Italic",
        "Light Italic", "Medium Italic", "Black Italic",
        "Condensed Bold", "Condensed Italic",
        "Casual Bold", "Cursive Bold",
        "Serif Mono Bold", "Small Caps Bold", "Thin Italic",
        "Courier Bold", "Cond Light Italic"
    };
    // family + style untuk tiap font di atas (style: 0=normal, 1=bold, 2=italic, 3=bold italic). Index 0 dimuat dari assets.
    private String[] fontFamilies = {
        "", "monospace", "sans-serif", "serif", "Roboto", "Courier New",
        "sans-serif-light", "sans-serif-thin", "sans-serif-medium", "sans-serif-black", "sans-serif-condensed", "sans-serif-condensed-light",
        "casual", "cursive", "serif-monospace", "sans-serif-smallcaps",
        "monospace", "monospace", "monospace",
        "sans-serif", "sans-serif", "sans-serif",
        "serif", "serif", "serif",
        "Roboto", "Roboto", "Roboto",
        "sans-serif-light", "sans-serif-medium", "sans-serif-black",
        "sans-serif-condensed", "sans-serif-condensed",
        "casual", "cursive",
        "serif-monospace", "sans-serif-smallcaps", "sans-serif-thin",
        "Courier New", "sans-serif-condensed-light"
    };
    private int[] fontStyles = {
        0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0,
        0, 0, 0, 0,
        1, 2, 3,
        1, 2, 3,
        1, 2, 3,
        1, 2, 3,
        2, 2, 2,
        1, 2,
        1, 1,
        1, 1, 2,
        1, 2
    };

    // ⭐ SUARA TOMBOL (item pertama = default, item terakhir = OFF)
    private String[] soundList = {"BEEP", "BEEP 2", "PROMPT", "ACK", "TICK", "SYSTEM CLICK", "OFF"};
    private static final int SOUND_SYSTEM_CLICK = 5;
    private int soundMode = 0;
    private ToneGenerator toneGen;
    private float soundDownX, soundDownY;
    private String[] effectList = {"OFF", "MATRIX", "GLITCH", "SCANNER", "RAINBOW", "NEON", "CYBER", "HACKER RANDOM", "NUMBER 20"};

    // ⭐ 40 BACKGROUND EFFECTS
    private String[] bgEffectList = {
        "OFF", "GREEN MATRIX", "RED GLITCH", "BLUE SCAN", "RAINBOW FLOW",
        "NEON PULSE", "CYBER GRID", "HACKER NUMBERS", "BLOOD DRIP", "OCEAN WAVE",
        "FIRE BURST", "ICE FREEZE", "TOXIC GREEN", "PURPLE HAZE", "GOLD SHINE",
        "SILVER METAL", "PINK NEON", "CYAN WAVE", "DARK VOID", "WHITE FLASH",
        "STROBE LIGHT", "RANDOM COLORS", "CODE RAIN", "BINARY STREAM", "HEX DUMP",
        "TERMINAL GREEN", "RADAR SWEEP", "PULSE BEAT", "HEARTBEAT RED", "ELECTRIC BLUE",
        "PLASMA PINK", "MAGMA ORANGE", "FOREST GREEN", "DESERT SAND", "ARCTIC WHITE",
        "MIDNIGHT BLACK", "SUNSET GLOW", "AURORA BOREALIS", "LIGHTNING STRIKE", "GLITCH HELL"
    };

    private String[] colorNames = {
        "Hijau Hacker", "Merah API", "Biru", "Kuning", "Ungu",
        "Cyan", "Magenta", "Orange", "Pink", "Lime",
        "Teal", "Silver", "Gold", "Putih", "Hitam",
        "Merah Tua", "Biru Tua", "Hijau Tua", "Ungu Tua", "Abu-abu",
        "Neon Hijau", "Neon Biru", "Neon Merah", "Neon Pink", "Neon Kuning",
        "Cyber Cyan", "Matrix Green", "Blood Red", "Ocean Blue", "Royal Purple"
    };
    private String[] colorHexs = {
        "#00ff41", "#FF1744", "#2979FF", "#FFEA00", "#AA00FF",
        "#00E5FF", "#D500F9", "#FF9100", "#FF4081", "#76FF03",
        "#00BFA5", "#B0BEC5", "#FFD700", "#FFFFFF", "#000000",
        "#8B0000", "#0D47A1", "#004D40", "#4A148C", "#616161",
        "#39FF14", "#00BFFF", "#FF0044", "#FF1493", "#FFD700",
        "#00FFFF", "#00FF00", "#FF0000", "#0088FF", "#9C27B0"
    };

    private String[] bgColorNames = {
        "Hitam Pekat", "Hitam Soft", "Abu Gelap", "Abu Terang", "Putih",
        "Hijau Gelap", "Merah Gelap", "Biru Gelap", "Ungu Gelap", "Pink Gelap",
        "Cyan Gelap", "Kuning Gelap", "Orange Gelap", "Teal Gelap", "Silver",
        "Navy", "Maroon", "Olive", "Dark Grey", "Hitam Transparan"
    };
    private String[] bgColorHexs = {
        "#000000", "#0a0a0a", "#1a1a1a", "#404040", "#FFFFFF",
        "#003300", "#330000", "#000033", "#330033", "#33001a",
        "#003333", "#333300", "#331a00", "#003333", "#808080",
        "#000080", "#800000", "#808000", "#2b2b2b", "#EE000000"
    };

    private int selectedColorIndex = 0;
    private int selectedTextColorIndex = 0;
    private int selectedBgMenuIndex = 0;
    private int selectedBgFeatureIndex = 0;
    private int selectedBgBtnIndex = 0;

    RelativeLayout mCollapsed, mRootContainer;
    LinearLayout mExpanded, mods, mSettings, mZSettings, mGearSettings, mCollapse;
    Button logoZBtn;
    Button hideBtn, closeBtn;
    TextView gearBtn;
    LinearLayout.LayoutParams scrlLLExpanded, scrlLL;
    WindowManager mWindowManager;
    WindowManager.LayoutParams vmParams;
    ImageView startimage;
    FrameLayout rootFrame;
    ScrollView scrollView;
    boolean stopChecking, overlayRequired;
    Context getContext;
    GradientDrawable gdMenuBody;
    TextView title, subTitle, settingsIcon;

    private String deviceBrand = Build.BRAND;
    private String deviceModel = Build.MODEL;
    private String deviceRelease = Build.VERSION.RELEASE;
    private String totalRAM = "Unknown";
    private String deviceYear = "Unknown";
    private int currentFps = 0;
    private int frameCount = 0;
    private long lastFpsUpdate = 0;
    private String country = "Unknown";
    private String androidId = "UNKNOWN";

    // ============== GIF / GAMBAR MELAYANG: 5 SLOT UPLOAD ==============
    private static final int MEDIA_SLOTS = 5;
    private static final String MEDIA_DIR = "skyzen_media";
    private static Menu mediaPickerOwner;
    private int mediaPickTarget = 0;
    private final FrameLayout[] mediaFloat = new FrameLayout[MEDIA_SLOTS];
    private final WindowManager.LayoutParams[] mediaParams = new WindowManager.LayoutParams[MEDIA_SLOTS];
    private final Drawable[] mediaDrawable = new Drawable[MEDIA_SLOTS];
    private final WebView[] mediaWebView = new WebView[MEDIA_SLOTS];
    private final TextView[] mediaStatus = new TextView[MEDIA_SLOTS];

    // ============== WARNA CUSTOM ==============
    private boolean customColorActive = false;
    private long lastCustomApply = 0;

    // ============== MUSIK YOUTUBE (player melayang, tetap jalan walau menu ditutup) ==============
    private static final String KEY_YT_LAST = "yt_last_link";
    private LinearLayout ytFloat;
    private WindowManager.LayoutParams ytParams;
    private WebView ytWebView;
    private TextView ytStatus;
    private TextView ytDragHandle;
    private boolean ytVideoHidden = false;

    native void Init(Context context, TextView title, TextView subTitle);
    native String Icon();
    native String IconWebViewData();
    native String[] GetFeatureList();
    native String[] SettingsList();
    native boolean IsGameLibLoaded();
    native void nativeTriggerAllFeatures();

    // ============================================================
    // DEVICE INFO
    // ============================================================
    private void initDeviceInfo() {
        try {
            androidId = getAndroidId();
            totalRAM = getTotalRAM();
            deviceYear = getDeviceYear();
            country = getCountry();
            if (androidId == null || androidId.isEmpty() || androidId.equals("UNKNOWN")) {
                try {
                    String serial = Build.SERIAL;
                    if (serial != null && !serial.isEmpty() && !serial.equals("unknown")) androidId = serial;
                    else androidId = (Build.BRAND + Build.MODEL + Build.FINGERPRINT).hashCode() + "";
                } catch (Exception e) {
                    androidId = (Build.BRAND + Build.MODEL + Build.FINGERPRINT).hashCode() + "";
                }
            }
        } catch (Exception e) { Log.e(TAG, "initDeviceInfo error: " + e.getMessage()); }
    }

    private String getCountry() {
        try {
            Locale locale = Locale.getDefault();
            String countryCode = locale.getCountry();
            String countryName = locale.getDisplayCountry();
            if (countryName != null && !countryName.isEmpty()) return countryName + " (" + countryCode + ")";
            return countryCode;
        } catch (Exception e) { return "Unknown"; }
    }

    private String getAndroidId() {
        try {
            if (getContext == null) return "UNKNOWN";
            String id = android.provider.Settings.Secure.getString(getContext.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
            if (id == null || id.isEmpty()) return "UNKNOWN";
            return id;
        } catch (Exception e) { return "UNKNOWN"; }
    }

    private String getTotalRAM() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("/proc/meminfo")));
            String line = reader.readLine();
            reader.close();
            if (line != null && line.startsWith("MemTotal:")) {
                String[] parts = line.split("\\s+");
                if (parts.length >= 2) {
                    long kb = Long.parseLong(parts[1]);
                    long gb = kb / (1024 * 1024);
                    if (gb > 0) return gb + " GB";
                    else return (kb / 1024) + " MB";
                }
            }
        } catch (Exception e) { }
        return "Unknown";
    }

    private String getDeviceYear() {
        try {
            int apiLevel = Build.VERSION.SDK_INT;
            if (apiLevel >= 33) return "2023+";
            else if (apiLevel >= 31) return "2022";
            else if (apiLevel >= 30) return "2021";
            else if (apiLevel >= 29) return "2020";
            else if (apiLevel >= 28) return "2019";
            else if (apiLevel >= 27) return "2018";
            else if (apiLevel >= 26) return "2017";
            else if (apiLevel >= 24) return "2016";
            else return "2015-";
        } catch (Exception e) { return "Unknown"; }
    }

    private int getCurrentFPS() {
        frameCount++;
        long now = System.currentTimeMillis();
        if (now - lastFpsUpdate > 1000) {
            currentFps = frameCount;
            frameCount = 0;
            lastFpsUpdate = now;
        }
        return currentFps;
    }

    private void downloadDeviceInfo() {
        try {
            File downloadDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            if (!downloadDir.exists()) downloadDir.mkdirs();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss", Locale.getDefault());
            String timestamp = sdf.format(new Date());
            String fileName = "DeviceInfo_" + deviceBrand + "_" + deviceModel + "_" + timestamp + ".txt";
            File file = new File(downloadDir, fileName);
            StringBuilder data = new StringBuilder();
            data.append("========== DEVICE INFO ==========\n");
            data.append("Brand: ").append(deviceBrand).append("\n");
            data.append("Model: ").append(deviceModel).append("\n");
            data.append("Android Version: ").append(deviceRelease).append("\n");
            data.append("Device Year: ").append(deviceYear).append("\n");
            data.append("RAM: ").append(totalRAM).append("\n");
            data.append("Country: ").append(country).append("\n");
            data.append("Android ID: ").append(androidId).append("\n");
            data.append("FPS: ").append(getCurrentFPS()).append("\n");
            data.append("Package Name: ").append(getContext.getPackageName()).append("\n");
            data.append("Architecture: ").append(Build.SUPPORTED_ABIS[0]).append("\n");
            data.append("==================================\n");
            FileOutputStream fos = new FileOutputStream(file);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            writer.write(data.toString());
            writer.close();
            fos.close();
            Toast.makeText(getContext, "Device info saved: " + fileName, Toast.LENGTH_LONG).show();
        } catch (Exception e) { Log.e(TAG, "Download error: " + e.getMessage()); }
    }

    // ============================================================
    // ⭐⭐⭐ WEBVIEW BACKGROUND SYSTEM + 40 EFFECTS ⭐⭐⭐
    // ============================================================
    private void initBgWebView() {
        try {
            bgWebView = new WebView(getContext);
            bgWebView.setLayoutParams(new FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT));
            bgWebView.setBackgroundColor(0x00000000);

            WebSettings ws = bgWebView.getSettings();
            ws.setJavaScriptEnabled(true);
            ws.setDomStorageEnabled(false);
            ws.setCacheMode(WebSettings.LOAD_NO_CACHE);
            ws.setAllowFileAccess(false);
            ws.setAllowContentAccess(false);
            ws.setLoadWithOverviewMode(true);
            ws.setUseWideViewPort(true);
            ws.setMediaPlaybackRequiresUserGesture(false);

            bgWebView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        } catch (Exception e) {
            Log.e(TAG, "initBgWebView error: " + e.getMessage());
        }
    }

    private void setBgEffectWeb(int index) {
        currentBgEffectIndex = index;
        if (bgWebView == null) return;
        if (index == 0) {
            bgWebView.loadDataWithBaseURL(null,
                "<html><body style='margin:0;background:transparent'></body></html>",
                "text/html", "utf-8", null);
            return;
        }
        String html = buildBgHtml(index);
        bgWebView.loadDataWithBaseURL(null, html, "text/html", "utf-8", null);
    }

    private String buildBgHtml(int index) {
        String head = "<!DOCTYPE html><html><head><meta charset='UTF-8'>" +
                "<meta name='viewport' content='width=device-width,initial-scale=1.0,user-scalable=no'>" +
                "<style>html,body{margin:0;padding:0;background:#000;width:100vw;height:100vh;overflow:hidden;}" +
                "canvas{display:block;width:100vw;height:100vh;}</style></head><body>";
        String tail = "</body></html>";

        String body;
        switch (index) {
            case 1: body = bgMatrix("#00ff41", "01アイウエオカキクケコ#$%&@", 50); break;
            case 2: body = bgMatrix("#ff0000", "0123456789ABCDEF", 60); break;
            case 3: body = bgScan("#00c8ff"); break;
            case 4: body = bgRainbow(); break;
            case 5: body = bgNeonPulse("#ff00ff"); break;
            case 6: body = bgCyberGrid(); break;
            case 7: body = bgHackerNumbers(); break;
            case 8: body = bgBloodDrip(); break;
            case 9: body = bgOceanWave(); break;
            case 10: body = bgFire(); break;
            case 11: body = bgIce(); break;
            case 12: body = bgToxicGreen(); break;
            case 13: body = bgPurpleHaze(); break;
            case 14: body = bgGoldShine(); break;
            case 15: body = bgSilverMetal(); break;
            case 16: body = bgNeonPulse("#ff1493"); break;
            case 17: body = bgOceanWave().replace("rgba(0,150,255,0.6)", "rgba(0,255,255,0.6)").replace("rgba(0,20,40", "rgba(0,20,20"); break;
            case 18: body = bgVoid(); break;
            case 19: body = bgWhiteFlash(); break;
            case 20: body = bgStrobe(); break;
            case 21: body = bgRandom(); break;
            case 22: body = bgMatrix("#00ff41", "ABCDEF0123456789!@#$%^&*", 40); break;
            case 23: body = bgMatrix("#39FF14", "01", 60); break;
            case 24: body = bgHex(); break;
            case 25: body = bgTerminal(); break;
            case 26: body = bgRadar(); break;
            case 27: body = bgPulseBeat("#00ff41"); break;
            case 28: body = bgPulseBeat("#ff0000"); break;
            case 29: body = bgElectricBlue(); break;
            case 30: body = bgPlasma("#ff00ff"); break;
            case 31: body = bgFire(); break;
            case 32: body = bgPulseBeat("#228B22"); break;
            case 33: body = bgPulseBeat("#EDC9AF"); break;
            case 34: body = bgIce(); break;
            case 35: body = bgVoid(); break;
            case 36: body = bgSunset(); break;
            case 37: body = bgAurora(); break;
            case 38: body = bgLightning(); break;
            case 39: body = bgGlitchHell(); break;
            case 40: body = bgDigitalChaos(); break;
            default: body = ""; break;
        }
        return head + body + tail;
    }

    // ==================== 40 EFFECT IMPLEMENTATIONS ====================
    private String bgMatrix(String color, String chars, int speed) {
        return "<canvas id='c'></canvas><script>" +
            "var c=document.getElementById('c'),x=c.getContext('2d');" +
            "function s(){c.width=innerWidth;c.height=innerHeight}s();onresize=s;" +
            "var cols=Math.floor(c.width/14),yp=[];for(var i=0;i<cols;i++)yp[i]=1;" +
            "var ch='"+chars+"';" +
            "function d(){x.fillStyle='rgba(0,0,0,0.08)';x.fillRect(0,0,c.width,c.height);" +
            "x.fillStyle='"+color+"';x.font='bold 14px monospace';" +
            "for(var i=0;i<cols;i++){var t=ch.charAt(Math.floor(Math.random()*ch.length));" +
            "x.fillText(t,i*14,yp[i]*14);" +
            "if(yp[i]*14>c.height&&Math.random()>0.975)yp[i]=0;yp[i]++;}}" +
            "setInterval(d,"+speed+");</script>";
    }

    private String bgScan(String color) {
        return "<canvas id='c'></canvas><script>" +
            "var c=document.getElementById('c'),x=c.getContext('2d');" +
            "function s(){c.width=innerWidth;c.height=innerHeight}s();onresize=s;" +
            "var y=0,dir=1;" +
            "function d(){x.fillStyle='rgba(0,0,20,0.3)';x.fillRect(0,0,c.width,c.height);" +
            "y+=dir*3;if(y>c.height||y<0)dir*=-1;" +
            "var g=x.createLinearGradient(0,y-40,0,y+40);" +
            "g.addColorStop(0,'rgba(0,200,255,0)');g.addColorStop(0.5,'rgba(0,200,255,0.8)');g.addColorStop(1,'rgba(0,200,255,0)');" +
            "x.fillStyle=g;x.fillRect(0,y-40,c.width,80);requestAnimationFrame(d);}d();</script>";
    }

    private String bgRainbow() {
        return "<canvas id='c'></canvas><script>" +
            "var c=document.getElementById('c'),x=c.getContext('2d');" +
            "function s(){c.width=innerWidth;c.height=innerHeight}s();onresize=s;" +
            "var h=0;function d(){h=(h+1)%360;" +
            "for(var i=0;i<50;i++){x.fillStyle='hsla('+((h+i*7)%360)+',100%,50%,0.15)';" +
            "x.beginPath();x.arc(Math.random()*c.width,Math.random()*c.height,Math.random()*50,0,6.28);x.fill();}" +
            "requestAnimationFrame(d);}d();</script>";
    }

    private String bgNeonPulse(String color) {
        return "<canvas id='c'></canvas><script>" +
            "var c=document.getElementById('c'),x=c.getContext('2d');" +
            "function s(){c.width=innerWidth;c.height=innerHeight}s();onresize=s;" +
            "var t=0;function d(){t+=0.05;var p=(Math.sin(t)+1)/2;" +
            "x.fillStyle='rgba(0,0,0,0.2)';x.fillRect(0,0,c.width,c.height);" +
            "x.strokeStyle='"+color+"';x.globalAlpha=p;x.lineWidth=3+p*5;" +
            "x.strokeRect(10,10,c.width-20,c.height-20);x.globalAlpha=1;" +
            "requestAnimationFrame(d);}d();</script>";
    }

    private String bgCyberGrid() {
        return "<canvas id='c'></canvas><script>" +
            "var c=document.getElementById('c'),x=c.getContext('2d');" +
            "function s(){c.width=innerWidth;c.height=innerHeight}s();onresize=s;" +
            "var t=0;function d(){t++;x.fillStyle='rgba(0,0,0,0.3)';x.fillRect(0,0,c.width,c.height);" +
            "x.strokeStyle='rgba(0,200,255,0.5)';x.lineWidth=1;" +
            "for(var i=0;i<c.width;i+=30){x.beginPath();x.moveTo(i+t%30,0);x.lineTo(i+t%30,c.height);x.stroke();}" +
            "for(var j=0;j<c.height;j+=30){x.beginPath();x.moveTo(0,j+t%30);x.lineTo(c.width,j+t%30);x.stroke();}" +
            "requestAnimationFrame(d);}d();</script>";
    }

    private String bgHackerNumbers() {
        return "<canvas id='c'></canvas><script>" +
            "var c=document.getElementById('c'),x=c.getContext('2d');" +
            "function s(){c.width=innerWidth;c.height=innerHeight}s();onresize=s;" +
            "var nums=[];for(var i=0;i<80;i++)nums.push({x:Math.random()*c.width,y:Math.random()*c.height,v:0.5+Math.random()*2,t:String(Math.floor(Math.random()*99999))});" +
            "function d(){x.fillStyle='rgba(0,0,0,0.15)';x.fillRect(0,0,c.width,c.height);" +
            "x.font='bold 12px monospace';" +
            "for(var i=0;i<nums.length;i++){var n=nums[i];" +
            "x.fillStyle='rgba(0,255,65,'+(0.3+Math.random()*0.7)+')';x.fillText(n.t,n.x,n.y);" +
            "n.y+=n.v;if(n.y>c.height){n.y=-10;n.x=Math.random()*c.width;n.t=String(Math.floor(Math.random()*99999));}}" +
            "requestAnimationFrame(d);}d();</script>";
    }

    private String bgBloodDrip() {
        return "<canvas id='c'></canvas><script>" +
            "var c=document.getElementById('c'),x=c.getContext('2d');" +
            "function s(){c.width=innerWidth;c.height=innerHeight}s();onresize=s;" +
            "var d=[];for(var i=0;i<40;i++)d.push({x:Math.random()*c.width,y:Math.random()*c.height,v:1+Math.random()*3,r:2+Math.random()*5});" +
            "function draw(){x.fillStyle='rgba(0,0,0,0.1)';x.fillRect(0,0,c.width,c.height);x.fillStyle='#8B0000';" +
            "for(var i=0;i<d.length;i++){var p=d[i];x.beginPath();x.arc(p.x,p.y,p.r,0,6.28);x.fill();" +
            "p.y+=p.v;if(p.y>c.height){p.y=-10;p.x=Math.random()*c.width;}}" +
            "requestAnimationFrame(draw);}draw();</script>";
    }

    private String bgOceanWave() {
        return "<canvas id='c'></canvas><script>" +
            "var c=document.getElementById('c'),x=c.getContext('2d');" +
            "function s(){c.width=innerWidth;c.height=innerHeight}s();onresize=s;" +
            "var t=0;function d(){t+=0.02;x.fillStyle='rgba(0,20,40,0.2)';x.fillRect(0,0,c.width,c.height);" +
            "for(var k=0;k<3;k++){x.beginPath();x.moveTo(0,c.height/2+k*40);" +
            "for(var i=0;i<c.width;i+=10)x.lineTo(i,c.height/2+k*40+Math.sin(i*0.01+t+k)*30);" +
            "x.strokeStyle='rgba(0,150,255,0.6)';x.lineWidth=2;x.stroke();}" +
            "requestAnimationFrame(d);}d();</script>";
    }

    private String bgFire() {
        return "<canvas id='c'></canvas><script>" +
            "var c=document.getElementById('c'),x=c.getContext('2d');" +
            "function s(){c.width=innerWidth;c.height=innerHeight}s();onresize=s;" +
            "var p=[];for(var i=0;i<80;i++)p.push({x:Math.random()*c.width,y:c.height+Math.random()*100,v:1+Math.random()*3,r:2+Math.random()*4});" +
            "function d(){x.fillStyle='rgba(0,0,0,0.15)';x.fillRect(0,0,c.width,c.height);" +
            "for(var i=0;i<p.length;i++){var q=p[i];" +
            "var g=x.createRadialGradient(q.x,q.y,0,q.x,q.y,q.r*4);" +
            "g.addColorStop(0,'rgba(255,255,0,0.8)');g.addColorStop(0.5,'rgba(255,100,0,0.5)');g.addColorStop(1,'rgba(255,0,0,0)');" +
            "x.fillStyle=g;x.beginPath();x.arc(q.x,q.y,q.r*4,0,6.28);x.fill();" +
            "q.y-=q.v;q.x+=Math.sin(q.y*0.05);" +
            "if(q.y<-20){q.y=c.height+20;q.x=Math.random()*c.width;}}" +
            "requestAnimationFrame(d);}d();</script>";
    }

    private String bgIce() {
        return "<canvas id='c'></canvas><script>" +
            "var c=document.getElementById('c'),x=c.getContext('2d');" +
            "function s(){c.width=innerWidth;c.height=innerHeight}s();onresize=s;" +
            "var f=[];for(var i=0;i<100;i++)f.push({x:Math.random()*c.width,y:Math.random()*c.height,r:1+Math.random()*2,v:0.3+Math.random()*0.7});" +
            "function d(){x.fillStyle='rgba(0,10,25,0.15)';x.fillRect(0,0,c.width,c.height);x.fillStyle='#aaddff';" +
            "for(var i=0;i<f.length;i++){var q=f[i];x.beginPath();x.arc(q.x,q.y,q.r,0,6.28);x.fill();" +
            "q.y+=q.v;q.x+=Math.sin(q.y*0.02)*0.5;if(q.y>c.height)q.y=-5;}" +
            "requestAnimationFrame(d);}d();</script>";
    }

    private String bgToxicGreen() {
        return "<canvas id='c'></canvas><script>" +
            "var c=document.getElementById('c'),x=c.getContext('2d');" +
            "function s(){c.width=innerWidth;c.height=innerHeight}s();onresize=s;" +
            "var t=0;function d(){t+=0.03;x.fillStyle='rgba(0,15,0,0.2)';x.fillRect(0,0,c.width,c.height);" +
            "for(var i=0;i<5;i++){var r=((t*50+i*40)%Math.max(c.width,c.height));" +
            "x.strokeStyle='rgba(57,255,20,'+(1-r/Math.max(c.width,c.height))+')';" +
            "x.lineWidth=2;x.beginPath();x.arc(c.width/2,c.height/2,r,0,6.28);x.stroke();}" +
            "requestAnimationFrame(d);}d();</script>";
    }

    private String bgPurpleHaze() {
        return "<canvas id='c'></canvas><script>" +
            "var c=document.getElementById('c'),x=c.getContext('2d');" +
            "function s(){c.width=innerWidth;c.height=innerHeight}s();onresize=s;" +
            "var t=0;function d(){t+=0.02;x.fillStyle='rgba(20,0,40,0.15)';x.fillRect(0,0,c.width,c.height);" +
            "for(var i=0;i<30;i++){var a=t+i;x.fillStyle='rgba(156,39,176,0.15)';" +
            "x.beginPath();x.arc(c.width/2+Math.cos(a)*150,c.height/2+Math.sin(a)*100,30+Math.sin(a*2)*20,0,6.28);x.fill();}" +
            "requestAnimationFrame(d);}d();</script>";
    }

    private String bgGoldShine() {
        return "<canvas id='c'></canvas><script>" +
            "var c=document.getElementById('c'),x=c.getContext('2d');" +
            "function s(){c.width=innerWidth;c.height=innerHeight}s();onresize=s;" +
            "var t=0;function d(){t+=0.02;x.fillStyle='rgba(20,15,0,0.2)';x.fillRect(0,0,c.width,c.height);" +
            "var g=x.createLinearGradient(c.width/2+Math.cos(t)*200-100,0,c.width/2+Math.cos(t)*200+100,0);" +
            "g.addColorStop(0,'rgba(255,215,0,0)');g.addColorStop(0.5,'rgba(255,215,0,0.6)');g.addColorStop(1,'rgba(255,215,0,0)');" +
            "x.fillStyle=g;x.fillRect(0,0,c.width,c.height);requestAnimationFrame(d);}d();</script>";
    }

    private String bgSilverMetal() {
        return "<canvas id='c'></canvas><script>" +
            "var c=document.getElementById('c'),x=c.getContext('2d');" +
            "function s(){c.width=innerWidth;c.height=innerHeight}s();onresize=s;" +
            "var t=0;function d(){t+=0.03;x.fillStyle='rgba(30,30,30,0.2)';x.fillRect(0,0,c.width,c.height);" +
            "var g=x.createLinearGradient(0,c.height/2+Math.sin(t)*100-50,0,c.height/2+Math.sin(t)*100+50);" +
            "g.addColorStop(0,'rgba(176,190,197,0)');g.addColorStop(0.5,'rgba(200,220,230,0.6)');g.addColorStop(1,'rgba(176,190,197,0)');" +
            "x.fillStyle=g;x.fillRect(0,0,c.width,c.height);requestAnimationFrame(d);}d();</script>";
    }

    private String bgVoid() {
        return "<div style='position:fixed;top:0;left:0;width:100%;height:100%;background:#000;" +
            "color:#222;font-family:monospace;font-size:20px;display:flex;align-items:center;" +
            "justify-content:center;letter-spacing:10px;'>VOID</div>";
    }

    private String bgWhiteFlash() {
        return "<div id='f' style='position:fixed;top:0;left:0;width:100%;height:100%;background:#222;'></div>" +
            "<script>setInterval(function(){document.getElementById('f').style.background=" +
            "Math.random()>0.7?'#fff':'#222';},100);</script>";
    }

    private String bgStrobe() {
        return "<div id='s' style='position:fixed;top:0;left:0;width:100%;height:100%;'></div>" +
            "<script>var cols=['#000','#fff','#f00','#0f0','#00f','#ff0','#f0f','#0ff'];" +
            "setInterval(function(){document.getElementById('s').style.background=cols[Math.floor(Math.random()*cols.length)];},50);</script>";
    }

    private String bgRandom() {
        return "<div id='r' style='position:fixed;top:0;left:0;width:100%;height:100%;'></div>" +
            "<script>setInterval(function(){document.getElementById('r').style.background=" +
            "'hsl('+Math.random()*360+',80%,15%)';},200);</script>";
    }

    private String bgHex() {
        return "<canvas id='c'></canvas><script>" +
            "var c=document.getElementById('c'),x=c.getContext('2d');" +
            "function s(){c.width=innerWidth;c.height=innerHeight}s();onresize=s;" +
            "var cols=Math.floor(c.width/60),yp=[];for(var i=0;i<cols;i++)yp[i]=1;" +
            "function rh(){var h='';for(var i=0;i<8;i++)h+='0123456789ABCDEF'.charAt(Math.floor(Math.random()*16));return '0x'+h;}" +
            "function d(){x.fillStyle='rgba(0,0,0,0.1)';x.fillRect(0,0,c.width,c.height);" +
            "x.fillStyle='#00ffff';x.font='11px monospace';" +
            "for(var i=0;i<cols;i++){x.fillText(rh(),i*60,yp[i]*14);" +
            "if(yp[i]*14>c.height&&Math.random()>0.97)yp[i]=0;yp[i]++;}}" +
            "setInterval(d,80);</script>";
    }

    private String bgTerminal() {
        return "<div id='t' style='position:fixed;top:0;left:0;width:100%;height:100%;" +
            "background:#000;color:#0f0;font-family:monospace;font-size:12px;padding:10px;" +
            "box-sizing:border-box;overflow:hidden;'></div>" +
            "<script>var t=document.getElementById('t'),lines=[];" +
            "var cmd=['&gt; init','&gt; scanning...','&gt; decrypt...','&gt; access granted','&gt; 0x00FF41','&gt; root@sky','&gt; system ok'];" +
            "setInterval(function(){lines.push(cmd[Math.floor(Math.random()*cmd.length)]);" +
            "if(lines.length>30)lines.shift();t.innerHTML=lines.join('<br>');},300);</script>";
    }

    private String bgRadar() {
        return "<canvas id='c'></canvas><script>" +
            "var c=document.getElementById('c'),x=c.getContext('2d');" +
            "function s(){c.width=innerWidth;c.height=innerHeight}s();onresize=s;" +
            "var a=0;function d(){a+=0.05;x.fillStyle='rgba(0,20,0,0.15)';x.fillRect(0,0,c.width,c.height);" +
            "var cx=c.width/2,cy=c.height/2;" +
            "x.strokeStyle='rgba(0,255,0,0.3)';x.lineWidth=1;" +
            "for(var r=50;r<400;r+=50){x.beginPath();x.arc(cx,cy,r,0,6.28);x.stroke();}" +
            "x.strokeStyle='rgba(0,255,0,0.9)';x.lineWidth=2;" +
            "x.beginPath();x.moveTo(cx,cy);x.lineTo(cx+Math.cos(a)*400,cy+Math.sin(a)*400);x.stroke();" +
            "requestAnimationFrame(d);}d();</script>";
    }

	private String bgPulseBeat(String color) {
		int r = Integer.parseInt(color.substring(1,3),16);
		int g = Integer.parseInt(color.substring(3,5),16);
		int b = Integer.parseInt(color.substring(5,7),16);
		String html = "<div id='p' style='position:fixed;top:0;left:0;width:100%;height:100%;background:#001100;'></div>"
			+ "<script>"
			+ "var p=document.getElementById('p'),t=0;"
			+ "setInterval(function(){"
			+ "t+=0.15;"
			+ "var v=(Math.sin(t)+1)/2;"
			+ "var col='rgba(" + r + "," + g + "," + b + ",'+(0.3+v*0.7)+')';"
			+ "p.style.boxShadow='inset 0 0 '+(100+v*200)+'px '+col;"
			+ "},80);"
			+ "</script>";
		return html;
	}

    private String bgElectricBlue() {
        return "<canvas id='c'></canvas><script>" +
            "var c=document.getElementById('c'),x=c.getContext('2d');" +
            "function s(){c.width=innerWidth;c.height=innerHeight}s();onresize=s;" +
            "var t=0;function d(){t+=0.1;x.fillStyle='rgba(0,0,20,0.15)';x.fillRect(0,0,c.width,c.height);" +
            "x.strokeStyle='rgba(0,191,255,0.8)';x.lineWidth=2;x.beginPath();var y=c.height/2;" +
            "for(var i=0;i<c.width;i+=20){y=c.height/2+Math.sin((i+t*20)*0.05)*40;" +
            "if(i===0)x.moveTo(i,y);else x.lineTo(i,y);}" +
            "x.stroke();requestAnimationFrame(d);}d();</script>";
    }

    private String bgPlasma(String color) {
        return "<canvas id='c'></canvas><script>" +
            "var c=document.getElementById('c'),x=c.getContext('2d');" +
            "function s(){c.width=innerWidth;c.height=innerHeight}s();onresize=s;" +
            "var t=0;function d(){t+=0.02;x.fillStyle='rgba(30,0,30,0.15)';x.fillRect(0,0,c.width,c.height);" +
            "for(var y=0;y<c.height;y+=8){for(var xx=0;xx<c.width;xx+=8){" +
            "var v=Math.sin(xx*0.02+t)+Math.cos(y*0.02+t)+Math.sin((xx+y)*0.01+t);" +
            "var a=(v+3)/6;x.fillStyle='rgba(255,0,255,'+(a*0.3)+')';x.fillRect(xx,y,8,8);}}" +
            "requestAnimationFrame(d);}d();</script>";
    }

    private String bgSunset() {
        return "<canvas id='c'></canvas><script>" +
            "var c=document.getElementById('c'),x=c.getContext('2d');" +
            "function s(){c.width=innerWidth;c.height=innerHeight}s();onresize=s;" +
            "var t=0;function d(){t+=0.02;x.fillStyle='rgba(20,0,30,0.1)';x.fillRect(0,0,c.width,c.height);" +
            "for(var i=0;i<5;i++){var a=Math.sin(t+i*0.5)*0.5+0.5;" +
            "var g=x.createLinearGradient(0,c.height*0.4,0,c.height);" +
            "g.addColorStop(0,'rgba(255,102,0,'+(a*0.4)+')');g.addColorStop(1,'rgba(255,0,102,0)');" +
            "x.fillStyle=g;x.fillRect(0,0,c.width,c.height);}" +
            "requestAnimationFrame(d);}d();</script>";
    }

    private String bgAurora() {
        return "<canvas id='c'></canvas><script>" +
            "var c=document.getElementById('c'),x=c.getContext('2d');" +
            "function s(){c.width=innerWidth;c.height=innerHeight}s();onresize=s;" +
            "var t=0;function d(){t+=0.02;x.fillStyle='rgba(0,0,10,0.1)';x.fillRect(0,0,c.width,c.height);" +
            "for(var i=0;i<3;i++){var h=((t*30+i*120)%360);" +
            "var g=x.createLinearGradient(0,0,0,c.height);" +
            "g.addColorStop(0,'hsla('+h+',100%,50%,0.4)');g.addColorStop(1,'hsla('+((h+60)%360)+',100%,50%,0)');" +
            "x.fillStyle=g;x.fillRect(0,0,c.width,c.height);}" +
            "requestAnimationFrame(d);}d();</script>";
    }

    private String bgLightning() {
        return "<canvas id='c'></canvas><script>" +
            "var c=document.getElementById('c'),x=c.getContext('2d');" +
            "function s(){c.width=innerWidth;c.height=innerHeight}s();onresize=s;" +
            "function strike(){x.fillStyle='rgba(20,20,30,0.3)';x.fillRect(0,0,c.width,c.height);" +
            "if(Math.random()<0.3){x.fillStyle='rgba(255,255,255,0.9)';x.fillRect(0,0,c.width,c.height);" +
            "x.fillStyle='rgba(0,0,0,0.7)';" +
            "var px=Math.random()*c.width,y=0;" +
            "while(y<c.height){y+=10;px+=(Math.random()-0.5)*20;x.fillRect(px,y,3,10);}}" +
            "setTimeout(strike,Math.random()*300+30);}strike();</script>";
    }

    private String bgGlitchHell() {
        return "<div id='g' style='position:fixed;top:0;left:0;width:100%;height:100%;background:#000;'></div>" +
            "<script>var g=document.getElementById('g');" +
            "setInterval(function(){g.style.background='rgb('+Math.random()*255+','+Math.random()*255+','+Math.random()*255+')';},70);</script>";
    }

    private String bgDigitalChaos() {
        return "<canvas id='c'></canvas><script>" +
            "var c=document.getElementById('c'),x=c.getContext('2d');" +
            "function s(){c.width=innerWidth;c.height=innerHeight}s();onresize=s;" +
            "var t=0;function d(){t++;x.fillStyle='rgb('+(t*7%100)+','+(t*13%100)+','+(t*23%100)+')';" +
            "x.fillRect(0,0,c.width,c.height);" +
            "x.fillStyle='rgba(255,255,255,0.7)';x.font='20px monospace';" +
            "x.fillText('CHAOS:'+Math.floor(Math.random()*9999),Math.random()*c.width,Math.random()*c.height);" +
            "requestAnimationFrame(d);}d();</script>";
    }
    // ⭐ END OF 40 EFFECTS

    // ============================================================
    // LOGIN PANEL
    // ============================================================
    private LinearLayout buildLoginPanel() {
        LinearLayout layout = new LinearLayout(getContext);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setBackgroundColor(Color.parseColor("#0A0A0A"));

        TextView headerText = new TextView(getContext);
        headerText.setText("SKYZEN TOLS");
        headerText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);
        headerText.setTextColor(Color.parseColor("#FF0000"));
        headerText.setTypeface(null, Typeface.BOLD);
        headerText.setGravity(Gravity.CENTER);
        headerText.setBackgroundColor(Color.parseColor("#000000"));
        headerText.setPadding(20, 25, 20, 25);
        layout.addView(headerText);

        TextView subHeader = new TextView(getContext);
        subHeader.setText("SKYZEN TOLS || MOD ZERO");
        subHeader.setTextSize(TypedValue.COMPLEX_UNIT_SP, 11);
        subHeader.setTextColor(Color.parseColor("#FFFFFF"));
        subHeader.setTypeface(null, Typeface.BOLD);
        subHeader.setGravity(Gravity.CENTER);
        subHeader.setBackgroundColor(Color.parseColor("#000000"));
        subHeader.setPadding(0, 5, 0, 15);
        layout.addView(subHeader);

        TextView catBar = new TextView(getContext);
        catBar.setText("MOD VERSION 4.31.000");
        catBar.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        catBar.setTextColor(Color.parseColor("#FFFFFF"));
        catBar.setGravity(Gravity.CENTER);
        catBar.setBackgroundColor(Color.parseColor("#2A2A2A"));
        catBar.setPadding(0, 8, 0, 8);
        layout.addView(catBar);

        final EditText editText = new EditText(getContext);
        editText.setHint("Masukan Togel");
        editText.setHintTextColor(Color.parseColor("#888888"));
        editText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13);
        editText.setTextColor(Color.parseColor("#FFFFFF"));
        editText.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
        editText.setBackgroundColor(Color.parseColor("#0D0D0D"));
        editText.setPadding(30, 30, 30, 30);
        LinearLayout.LayoutParams editParams = new LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT);
        editParams.setMargins(0, 15, 0, 5);
        editText.setLayoutParams(editParams);
        layout.addView(editText);

        try {
            String savedKey = prefs.getString(PREF_KEY, "");
            if (savedKey != null && !savedKey.isEmpty()) {
                editText.setText(savedKey);
                editText.setSelection(savedKey.length());
            }
        } catch (Exception e) { Log.e(TAG, "Auto-fill error: " + e.getMessage()); }

        LinearLayout buttonRow = new LinearLayout(getContext);
        buttonRow.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams rowParams = new LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT);
        rowParams.setMargins(0, 5, 0, 5);
        buttonRow.setLayoutParams(rowParams);

        Button pasteBtn = new Button(getContext);
        pasteBtn.setText("PASTE");
        pasteBtn.setTextColor(Color.parseColor("#FFFFFF"));
        pasteBtn.setBackgroundColor(Color.parseColor("#1A1A1A"));
        pasteBtn.setTypeface(null, Typeface.BOLD);
        pasteBtn.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13);
        pasteBtn.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        LinearLayout.LayoutParams pasteParams = new LinearLayout.LayoutParams(0, WRAP_CONTENT, 1f);
        pasteParams.setMargins(0, 0, 5, 0);
        pasteBtn.setLayoutParams(pasteParams);
        pasteBtn.setPadding(0, 25, 0, 25);
        buttonRow.addView(pasteBtn);

        Button copyIdBtn = new Button(getContext);
        copyIdBtn.setText("COPY ID");
        copyIdBtn.setTextColor(Color.parseColor("#FFFFFF"));
        copyIdBtn.setBackgroundColor(Color.parseColor("#1A1A1A"));
        copyIdBtn.setTypeface(null, Typeface.BOLD);
        copyIdBtn.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13);
        copyIdBtn.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        LinearLayout.LayoutParams copyIdParams = new LinearLayout.LayoutParams(0, WRAP_CONTENT, 1f);
        copyIdParams.setMargins(5, 0, 0, 0);
        copyIdBtn.setLayoutParams(copyIdParams);
        copyIdBtn.setPadding(0, 25, 0, 25);
        buttonRow.addView(copyIdBtn);

        layout.addView(buttonRow);

        Button loginBtn = new Button(getContext);
        loginBtn.setText("LOGIN");
        loginBtn.setTextColor(Color.parseColor("#FFFFFF"));
        loginBtn.setBackgroundColor(Color.parseColor("#1A1A1A"));
        loginBtn.setTypeface(null, Typeface.BOLD);
        loginBtn.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        loginBtn.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        LinearLayout.LayoutParams loginParams = new LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT);
        loginParams.setMargins(0, 5, 0, 5);
        loginBtn.setLayoutParams(loginParams);
        loginBtn.setPadding(0, 25, 0, 25);
        layout.addView(loginBtn);

        TextView deviceInfo = new TextView(getContext);
        String deviceText = "Android ID: " + androidId + "\n" +
                "Architecture: " + Build.SUPPORTED_ABIS[0] + "\n" +
                "Device Model: " + deviceModel + "\n" +
                "Android Version: " + deviceRelease + "\n" +
                "OS Version: " + System.getProperty("os.version") + "\n" +
                "RAM: " + totalRAM + "\n" +
                "Package Name: " + getContext.getPackageName();
        deviceInfo.setText(deviceText);
        deviceInfo.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
        deviceInfo.setTextColor(Color.parseColor("#888888"));
        deviceInfo.setTypeface(Typeface.MONOSPACE);
        deviceInfo.setBackgroundColor(Color.parseColor("#000000"));
        deviceInfo.setPadding(30, 20, 30, 20);
        layout.addView(deviceInfo);

        pasteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    ClipboardManager clipboard = (ClipboardManager) getContext.getSystemService(Context.CLIPBOARD_SERVICE);
                    if (clipboard != null && clipboard.hasPrimaryClip() && clipboard.getPrimaryClip() != null) {
                        ClipData.Item item = clipboard.getPrimaryClip().getItemAt(0);
                        if (item != null && item.getText() != null) {
                            String pasted = item.getText().toString().trim();
                            editText.setText(pasted);
                            editText.setSelection(pasted.length());
                            Toast.makeText(getContext, "PASTED: " + pasted, Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext, "Clipboard kosong / bukan teks!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getContext, "Clipboard kosong!", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) { Toast.makeText(getContext, "Gagal paste: " + e.getMessage(), Toast.LENGTH_SHORT).show(); }
            }
        });

        copyIdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    ClipboardManager clipboard = (ClipboardManager) getContext.getSystemService(Context.CLIPBOARD_SERVICE);
                    if (clipboard != null) {
                        ClipData clip = ClipData.newPlainText("Device ID", androidId);
                        clipboard.setPrimaryClip(clip);
                        Toast.makeText(getContext, "ID TERSALIN: " + androidId, Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) { Toast.makeText(getContext, "Gagal copy: " + e.getMessage(), Toast.LENGTH_SHORT).show(); }
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String userKey = editText.getText().toString().trim();
                if (userKey.isEmpty()) { Toast.makeText(getContext, "MASUKIN KEY DULU!", Toast.LENGTH_SHORT).show(); return; }
                Toast.makeText(getContext, "CHECKING KEY...", Toast.LENGTH_SHORT).show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final String result = validateKey(userKey, androidId);
                        new Handler(getContext.getMainLooper()).post(new Runnable() {
                            @Override
                            public void run() {
                                switch (result) {
                                    case "VALID":
                                        isLoggedIn = true;
                                        try { prefs.edit().putString(PREF_KEY, userKey).putBoolean("auto_saved", true).apply(); } catch (Exception e) { }
                                        showLoginSuccess("LOGIN SUKSES!\n\nWELCOME TO SKYZEN TOLS!\nKEY UDAH DISIMPEN OTOMATIS");
                                        new Handler().postDelayed(new Runnable() {
                                            @Override public void run() { switchToFeaturePanel(); }
                                        }, 1500);
                                        break;
                                    case "EXPIRED": showLoginWarning("KEY EXPIRED! PERPANJANG KE OWNER!"); break;
                                    case "WRONG_DEVICE": showLoginWarning("KEY INI BUAT DEVICE LAIN!\n\nDEVICE ID LU:\n" + androidId); break;
                                    case "SERVER_ERROR": showLoginWarning("SERVER ERROR! CEK KONEKSI INTERNET ATAU URL KEY!"); break;
                                    default: showLoginWarning("KEY SALAH BANG!"); break;
                                }
                            }
                        });
                    }
                }).start();
            }
        });

        return layout;
    }

    private void switchToFeaturePanel() {
        try {
            mExpanded.removeAllViews();
            mExpanded.addView(featurePanel);
        } catch (Exception e) { Log.e(TAG, "Switch panel error: " + e.getMessage()); }
    }

    // ============================================================
    // STRING TERSEMBUNYI: teks disimpan dalam bentuk XOR (bukan teks biasa),
    // lalu di-decode saat dipakai supaya tidak kebaca langsung di dalam APK
    // ============================================================
    private static String unmask(int[] data) {
        int[] k = {0x5A, 0x3C, 0x91, 0x0E, 0x77, 0xC4, 0x2B, 0x6F};
        byte[] out = new byte[data.length];
        for (int i = 0; i < data.length; i++) out[i] = (byte) (data[i] ^ k[i % k.length]);
        return new String(out, java.nio.charset.StandardCharsets.UTF_8);
    }

    private String validateKey(String userKey, String deviceId) {
        HttpURLConnection conn = null;
        try {
            URL url = new URL(KEY_URL);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(10000);
            conn.setReadTimeout(10000);
            conn.setInstanceFollowRedirects(true);
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Linux; Android)");
            conn.setRequestProperty("Accept", "application/json");

            int responseCode = conn.getResponseCode();
            int redirects = 0;
            while ((responseCode == 301 || responseCode == 302 || responseCode == 303 || responseCode == 307) && redirects < 5) {
                String newUrl = conn.getHeaderField("Location");
                if (newUrl == null) break;
                conn.disconnect();
                url = new URL(newUrl);
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setConnectTimeout(10000);
                conn.setReadTimeout(10000);
                conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Linux; Android)");
                conn.setRequestProperty("Accept", "application/json");
                responseCode = conn.getResponseCode();
                redirects++;
            }

            if (DEBUG_KEY) Log.d(TAG, "HTTP Code: " + responseCode + " | Redirects: " + redirects);
            if (responseCode != 200) { Log.e(TAG, "HTTP Error: " + responseCode); return "SERVER_ERROR"; }

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) sb.append(line);
            reader.close();

            String jsonRaw = sb.toString().trim();
            if (jsonRaw.isEmpty()) return "SERVER_ERROR";
            if (DEBUG_KEY) Log.d(TAG, "JSON len: " + jsonRaw.length() + " | First 100: " + jsonRaw.substring(0, Math.min(100, jsonRaw.length())));
            if (jsonRaw.startsWith("<")) { Log.e(TAG, "URL balikin HTML, bukan JSON! Cek KEY_URL."); return "SERVER_ERROR"; }

            JSONArray arr;
            if (jsonRaw.startsWith("[")) arr = new JSONArray(jsonRaw);
            else if (jsonRaw.startsWith("{")) { arr = new JSONArray(); arr.put(new JSONObject(jsonRaw)); }
            else return "SERVER_ERROR";

            if (DEBUG_KEY) Log.d(TAG, "Total keys: " + arr.length() + " | Cari: " + userKey + " | DeviceID: " + deviceId);

            for (int i = 0; i < arr.length(); i++) {
                JSONObject obj = arr.getJSONObject(i);
                String jsonKey = obj.optString(FLD_KEY, "").trim();
                if (!jsonKey.equalsIgnoreCase(userKey)) continue;
                if (DEBUG_KEY) Log.d(TAG, "Key ditemukan di index " + i);
                String jsonDeviceId = obj.optString(FLD_DEVICE, "").trim();
                boolean deviceOk = jsonDeviceId.isEmpty() || jsonDeviceId.equalsIgnoreCase(deviceId);
                if (!deviceOk) { if (DEBUG_KEY) Log.d(TAG, "Device mismatch."); return "WRONG_DEVICE"; }
                String expiry = obj.optString(FLD_EXPIRY, "").trim();
                if (!expiry.isEmpty() && !expiry.equals("01-01-9999")) {
                    try {
                        String[] parts = expiry.split("-");
                        int day = Integer.parseInt(parts[0]);
                        int month = Integer.parseInt(parts[1]);
                        int year = Integer.parseInt(parts[2]);
                        java.util.Calendar expCal = java.util.Calendar.getInstance();
                        expCal.set(year, month - 1, day, 23, 59, 59);
                        java.util.Calendar now = java.util.Calendar.getInstance();
                        if (now.after(expCal)) return "EXPIRED";
                    } catch (Exception e) { Log.e(TAG, "Date parse error: " + e.getMessage()); }
                }
                return "VALID";
            }
            if (DEBUG_KEY) Log.d(TAG, "Key tidak ditemukan di JSON");
        } catch (Exception e) { Log.e(TAG, "validateKey error: " + e.getMessage()); return "SERVER_ERROR"; }
        finally { if (conn != null) conn.disconnect(); }
        return "INVALID";
    }

    private void showLoginSuccess(String message) {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext);
            LinearLayout layout = new LinearLayout(getContext);
            layout.setOrientation(LinearLayout.VERTICAL);
            layout.setBackgroundColor(Color.parseColor("#0A0A0A"));
            layout.setPadding(30, 30, 30, 30);
            TextView header = new TextView(getContext);
            header.setText("SUCCESS");
            header.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            header.setTextColor(Color.parseColor("#00FF41"));
            header.setTypeface(null, Typeface.BOLD);
            header.setGravity(Gravity.CENTER);
            header.setBackgroundColor(Color.parseColor("#000000"));
            header.setPadding(20, 20, 20, 20);
            layout.addView(header);
            TextView msg = new TextView(getContext);
            msg.setText(message);
            msg.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13);
            msg.setTextColor(Color.parseColor("#FFFFFF"));
            msg.setTypeface(Typeface.MONOSPACE);
            msg.setGravity(Gravity.CENTER);
            msg.setBackgroundColor(Color.parseColor("#0D0D0D"));
            msg.setPadding(30, 30, 30, 30);
            layout.addView(msg);
            builder.setView(layout);
            final AlertDialog dialog = builder.create();
            dialog.setCanceledOnTouchOutside(false);
            dialog.setCancelable(false);
            if (overlayRequired) Objects.requireNonNull(dialog.getWindow()).setType(Build.VERSION.SDK_INT >= 26 ? 2038 : 2002);
            dialog.show();
            new Handler().postDelayed(new Runnable() { @Override public void run() { if (dialog.isShowing()) dialog.dismiss(); } }, 1500);
        } catch (Exception e) { Toast.makeText(getContext, message, Toast.LENGTH_LONG).show(); }
    }

    private void showLoginWarning(String message) {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext);
            LinearLayout layout = new LinearLayout(getContext);
            layout.setOrientation(LinearLayout.VERTICAL);
            layout.setBackgroundColor(Color.parseColor("#0A0A0A"));
            layout.setPadding(30, 30, 30, 30);
            TextView header = new TextView(getContext);
            header.setText("WARNING");
            header.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            header.setTextColor(Color.parseColor("#FF0000"));
            header.setTypeface(null, Typeface.BOLD);
            header.setGravity(Gravity.CENTER);
            header.setBackgroundColor(Color.parseColor("#000000"));
            header.setPadding(20, 20, 20, 20);
            layout.addView(header);
            TextView msg = new TextView(getContext);
            msg.setText(message);
            msg.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13);
            msg.setTextColor(Color.parseColor("#FFFFFF"));
            msg.setTypeface(Typeface.MONOSPACE);
            msg.setGravity(Gravity.CENTER);
            msg.setBackgroundColor(Color.parseColor("#0D0D0D"));
            msg.setPadding(30, 30, 30, 30);
            layout.addView(msg);
            Button okBtn = new Button(getContext);
            okBtn.setText("OKE SAYA NGERTI");
            okBtn.setTextColor(Color.parseColor("#FFFFFF"));
            okBtn.setBackgroundColor(Color.parseColor("#FF0000"));
            okBtn.setTypeface(null, Typeface.BOLD);
            okBtn.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13);
            okBtn.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
            okBtn.setPadding(0, 25, 0, 25);
            layout.addView(okBtn);
            builder.setView(layout);
            final AlertDialog dialog = builder.create();
            dialog.setCanceledOnTouchOutside(false);
            dialog.setCancelable(false);
            okBtn.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { dialog.dismiss(); } });
            if (overlayRequired) Objects.requireNonNull(dialog.getWindow()).setType(Build.VERSION.SDK_INT >= 26 ? 2038 : 2002);
            dialog.show();
        } catch (Exception e) { Toast.makeText(getContext, message, Toast.LENGTH_LONG).show(); }
    }

    private Button createLogoZButton() {
        Button btn = new Button(getContext);
        btn.setText("Z");
        btn.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        btn.setTextColor(Color.parseColor("#FFFFFF"));
        btn.setTypeface(null, Typeface.BOLD);
        btn.setAllCaps(false);
        btn.setPadding(0, 0, 0, 0);
        GradientDrawable circle = new GradientDrawable();
        circle.setShape(GradientDrawable.OVAL);
        circle.setColor(THEME_COLOR);
        circle.setStroke(2, THEME_COLOR);
        btn.setBackground(circle);
        int size = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40, getContext.getResources().getDisplayMetrics());
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(size, size);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        btn.setLayoutParams(params);
        btn.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { onLogoZClicked(); } });
        return btn;
    }

    private TextView createGearButton() {
        TextView gear = new TextView(getContext);
        gear.setText("⚙");
        gear.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        gear.setTextColor(TEXT_COLOR);
        gear.setTypeface(Typeface.DEFAULT_BOLD);
        gear.setGravity(Gravity.CENTER);
        gear.setPadding(10, 5, 10, 5);
        gear.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { onGearClicked(); } });
        return gear;
    }

    private void onLogoZClicked() {
        try {
            if (scrollView == null || mZSettings == null || mods == null) return;
            boolean zShown = false;
            for (int i = 0; i < scrollView.getChildCount(); i++) { if (scrollView.getChildAt(i) == mZSettings) { zShown = true; break; } }
            if (zShown) { scrollView.removeAllViews(); scrollView.addView(mods); Toast.makeText(getContext, "MOD MENU", Toast.LENGTH_SHORT).show(); }
            else { scrollView.removeAllViews(); scrollView.addView(mZSettings); scrollView.scrollTo(0, 0); Toast.makeText(getContext, "Z MENU", Toast.LENGTH_SHORT).show(); }
        } catch (Exception e) { Log.e(TAG, "Tombol Z error: " + e.getMessage()); }
    }

    private void onGearClicked() {
        try {
            if (scrollView == null || mGearSettings == null || mods == null) return;
            boolean gearShown = false;
            for (int i = 0; i < scrollView.getChildCount(); i++) { if (scrollView.getChildAt(i) == mGearSettings) { gearShown = true; break; } }
            if (gearShown) { scrollView.removeAllViews(); scrollView.addView(mods); Toast.makeText(getContext, "MOD MENU", Toast.LENGTH_SHORT).show(); }
            else { scrollView.removeAllViews(); scrollView.addView(mGearSettings); scrollView.scrollTo(0, 0); Toast.makeText(getContext, "COLOR SETTINGS", Toast.LENGTH_SHORT).show(); }
        } catch (Exception e) { Log.e(TAG, "Tombol Gear error: " + e.getMessage()); }
    }

    private void playMusicFromAssets(String fileName) {
        stopYoutube(false);
        try {
            if (mediaPlayer != null) { if (mediaPlayer.isPlaying()) mediaPlayer.stop(); mediaPlayer.release(); mediaPlayer = null; }
            try {
                android.content.res.AssetFileDescriptor afd = getContext.getAssets().openFd(fileName);
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                afd.close();
                mediaPlayer.prepare();
                mediaPlayer.start();
                isMusicPlaying = true;
                Toast.makeText(getContext, "Playing: " + fileName, Toast.LENGTH_SHORT).show();
                return;
            } catch (IOException e) { }
            int resId = getContext.getResources().getIdentifier(fileName.replace(".mp3", ""), "raw", getContext.getPackageName());
            if (resId != 0) {
                mediaPlayer = MediaPlayer.create(getContext, resId);
                mediaPlayer.start();
                isMusicPlaying = true;
                Toast.makeText(getContext, "Playing: " + fileName, Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) { }
    }

    private void stopMusic() {
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
            isMusicPlaying = false;
            Toast.makeText(getContext, "Music stopped", Toast.LENGTH_SHORT).show();
        }
    }

    // ============================================================
    // THEME / COLOR APPS
    // ============================================================
    private void applyThemeColor(int colorIndex) {
        selectedColorIndex = colorIndex;
        String hex = colorHexs[colorIndex];
        int color = Color.parseColor(hex);
        THEME_COLOR = color;
        ToggleON = color;
        BUTTON_ON_COLOR = Color.parseColor("#808080");
        SeekBarColor = color;
        SeekBarProgressColor = color;
        CheckBoxColor = color;
        RadioColor = color;
        NumberTxtColor = hex;
        MENU_BORDER_COLOR = color;
        if (gdMenuBody != null) { gdMenuBody.setStroke(2, color); mExpanded.setBackground(gdMenuBody); }
        if (logoZBtn != null) {
            GradientDrawable circle = new GradientDrawable();
            circle.setShape(GradientDrawable.OVAL);
            circle.setColor(color);
            circle.setStroke(2, color);
            logoZBtn.setBackground(circle);
        }
        if (gearBtn != null) {
            GradientDrawable gb = new GradientDrawable();
            gb.setShape(GradientDrawable.OVAL);
            gb.setColor(color);
            gb.setStroke(2, Color.parseColor("#FFFFFF"));
            gearBtn.setBackground(gb);
            gearBtn.setTextColor(Color.WHITE);
        }
        if (settingsIcon != null) {
            GradientDrawable sb = new GradientDrawable();
            sb.setShape(GradientDrawable.OVAL);
            sb.setColor(color);
            sb.setStroke(2, Color.parseColor("#FFFFFF"));
            settingsIcon.setBackground(sb);
            settingsIcon.setTextColor(Color.WHITE);
        }
        updateAllChildColors(mSettings);
        updateAllChildColors(mZSettings);
        updateAllChildColors(mGearSettings);
        updateAllChildColors(mods);
        Preferences.changeFeatureInt("ThemeColor", -200, colorIndex);
    }

    private void applyTextColor(int colorIndex) {
        selectedTextColorIndex = colorIndex;
        String hex = colorHexs[colorIndex];
        int color = Color.parseColor(hex);
        TEXT_COLOR = color;
        TEXT_COLOR_2 = color;
        COLLAPSE_TEXT_COLOR = color;
        if (title != null) title.setTextColor(color);
        if (subTitle != null) subTitle.setTextColor(color);
        if (hideBtn != null) hideBtn.setTextColor(color);
        if (closeBtn != null) closeBtn.setTextColor(color);
        updateAllChildColors(mSettings);
        updateAllChildColors(mZSettings);
        updateAllChildColors(mGearSettings);
        updateAllChildColors(mods);
        Preferences.changeFeatureInt("TextColor", -204, colorIndex);
    }

    private void applyBgMenu(int colorIndex) {
        selectedBgMenuIndex = colorIndex;
        int color = Color.parseColor(bgColorHexs[colorIndex]);
        BG_MENU_COLOR = color;
        MENU_BG_COLOR = color;
        if (mExpanded != null) mExpanded.setBackgroundColor(color);
        if (gdMenuBody != null) { gdMenuBody.setColor(color); mExpanded.setBackground(gdMenuBody); }
        if (featurePanel != null) featurePanel.setBackgroundColor(color);
        if (loginPanel != null) loginPanel.setBackgroundColor(color);
        Preferences.changeFeatureInt("BgMenuColor", -210, colorIndex);
    }

    private void applyBgFeature(int colorIndex) {
        selectedBgFeatureIndex = colorIndex;
        int color = Color.parseColor(bgColorHexs[colorIndex]);
        BG_FEATURE_COLOR = color;
        MENU_FEATURE_BG_COLOR = color;
        if (scrollView != null) scrollView.setBackgroundColor(0x00000000);
        Preferences.changeFeatureInt("BgFeatureColor", -211, colorIndex);
    }

    private void applyBgButton(int colorIndex) {
        selectedBgBtnIndex = colorIndex;
        int color = Color.parseColor(bgColorHexs[colorIndex]);
        BG_BTN_COLOR = color;
        BTN_COLOR = color;
        updateAllChildColors(mSettings);
        updateAllChildColors(mZSettings);
        updateAllChildColors(mGearSettings);
        updateAllChildColors(mods);
        Preferences.changeFeatureInt("BgButtonColor", -212, colorIndex);
    }

    private void updateAllChildColors(LinearLayout parent) {
        if (parent == null) return;
        for (int i = 0; i < parent.getChildCount(); i++) {
            View v = parent.getChildAt(i);
            if (v instanceof TextView) {
                TextView tv = (TextView) v;
                String text = tv.getText().toString();
                if (text.contains("▽") || text.contains("△")) tv.setTextColor(COLLAPSE_TEXT_COLOR);
                else tv.setTextColor(TEXT_COLOR_2);
            } else if (v instanceof Button) {
                Button btn = (Button) v;
                btn.setTextColor(TEXT_COLOR_2);
                String text = btn.getText().toString();
                if (text.contains("[ON]")) btn.setBackgroundColor(BUTTON_ON_COLOR);
                else if (text.contains("[OFF]")) btn.setBackgroundColor(BG_OFF_COLOR);
                else if (text.equals("Z")) { }
                else btn.setBackgroundColor(BG_BTN_COLOR);
            } else if (v instanceof Switch) {
                Switch sw = (Switch) v;
                sw.setTextColor(TEXT_COLOR_2);
                ColorStateList states = new ColorStateList(
                        new int[][]{new int[]{-android.R.attr.state_enabled}, new int[]{android.R.attr.state_checked}, new int[]{}},
                        new int[]{BG_OFF_COLOR, Color.parseColor("#808080"), BG_OFF_COLOR});
                try { sw.getThumbDrawable().setTintList(states); sw.getTrackDrawable().setTintList(states); } catch (Exception e) { }
            } else if (v instanceof SeekBar) {
                SeekBar sb = (SeekBar) v;
                sb.getThumb().setColorFilter(SeekBarColor, PorterDuff.Mode.SRC_ATOP);
                sb.getProgressDrawable().setColorFilter(SeekBarProgressColor, PorterDuff.Mode.SRC_ATOP);
            } else if (v instanceof CheckBox) {
                ((CheckBox) v).setButtonTintList(ColorStateList.valueOf(CheckBoxColor));
                ((CheckBox) v).setTextColor(TEXT_COLOR_2);
            } else if (v instanceof RadioButton) {
                ((RadioButton) v).setButtonTintList(ColorStateList.valueOf(RadioColor));
                ((RadioButton) v).setTextColor(TEXT_COLOR_2);
            } else if (v instanceof LinearLayout) {
                updateAllChildColors((LinearLayout) v);
            }
        }
    }

    private void applyFont(int fontIndex) {
        Typeface tf;
        try {
            if (fontIndex == 0) tf = Typeface.createFromAsset(getContext.getAssets(), "fonts/PIXELADE.TTF");
            else if (fontIndex > 0 && fontIndex < fontFamilies.length) tf = Typeface.create(fontFamilies[fontIndex], fontStyles[fontIndex]);
            else tf = Typeface.MONOSPACE;
        } catch (Exception e) { tf = Typeface.MONOSPACE; }
        currentTypeface = tf;
        Preferences.changeFeatureInt("FontStyle", -201, fontIndex);
        updateAllFonts(mSettings, tf);
        updateAllFonts(mZSettings, tf);
        updateAllFonts(mGearSettings, tf);
        updateAllFonts(mods, tf);
        if (title != null) title.setTypeface(tf, Typeface.BOLD);
        if (subTitle != null) subTitle.setTypeface(tf);
    }

    private void updateAllFonts(LinearLayout parent, Typeface tf) {
        if (parent == null) return;
        for (int i = 0; i < parent.getChildCount(); i++) {
            View v = parent.getChildAt(i);
            if (v instanceof TextView) ((TextView) v).setTypeface(tf);
            else if (v instanceof Button) ((Button) v).setTypeface(tf);
            else if (v instanceof Switch) ((Switch) v).setTypeface(tf);
            else if (v instanceof CheckBox) ((CheckBox) v).setTypeface(tf);
            else if (v instanceof RadioButton) ((RadioButton) v).setTypeface(tf);
            else if (v instanceof EditText) ((EditText) v).setTypeface(tf);
            else if (v instanceof LinearLayout) updateAllFonts((LinearLayout) v, tf);
        }
    }

    private LinearLayout buildSpinnerLayout(String title, String[] items, final int prefKey, final SpinnerListener listener) {
        LinearLayout layout = new LinearLayout(getContext);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(10, 5, 10, 5);
        TextView cat = new TextView(getContext);
        cat.setBackgroundColor(CategoryBG);
        cat.setText(">> " + title + " <<");
        cat.setGravity(Gravity.CENTER);
        cat.setTextColor(TEXT_COLOR_2);
        cat.setTypeface(currentTypeface != null ? currentTypeface : Typeface.MONOSPACE);
        cat.setPadding(0, 5, 0, 5);
        layout.addView(cat);
        final Spinner spinner = new Spinner(getContext, Spinner.MODE_DROPDOWN);
        spinner.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT));
        spinner.setBackgroundColor(Color.parseColor("#1a1a1a"));
        spinner.setPadding(10, 10, 10, 10);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext, android.R.layout.simple_spinner_dropdown_item, items) {
            @Override public View getView(int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);
                if (v instanceof TextView) { ((TextView) v).setTypeface(currentTypeface != null ? currentTypeface : Typeface.MONOSPACE); ((TextView) v).setTextColor(TEXT_COLOR_2); }
                return v;
            }
            @Override public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View v = super.getDropDownView(position, convertView, parent);
                if (v instanceof TextView) { ((TextView) v).setTypeface(currentTypeface != null ? currentTypeface : Typeface.MONOSPACE); ((TextView) v).setTextColor(TEXT_COLOR_2); }
                return v;
            }
        };
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        final String prefKeyStr = "spinner_" + prefKey;
        int saved = Preferences.loadPrefInt(prefKeyStr, -200);
        if (saved >= 0 && saved < items.length) spinner.setSelection(saved);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Preferences.changeFeatureInt(prefKeyStr, -200, position);
                if (listener != null) listener.onItemSelected(position);
                if (view instanceof TextView) ((TextView) view).setTextColor(TEXT_COLOR_2);
            }
            @Override public void onNothingSelected(AdapterView<?> parent) {}
        });
        layout.addView(spinner);
        return layout;
    }

    interface SpinnerListener { void onItemSelected(int position); }

    // ============================================================
    // FRONT MENU TEXT EFFECTS (subtitle/title)
    // ============================================================
    private void startMatrixEffect() {
        if (matrixEffectRunning) return;
        matrixEffectRunning = true;
        final String[] chars = {"0", "1", "#", "$", "%", "@", "&", "*"};
        effectHandler.postDelayed(new Runnable() {
            int count = 0;
            @Override public void run() {
                if (!matrixEffectRunning) { if (subTitle != null) subTitle.setText("SYSTEM ONLINE"); return; }
                if (subTitle != null) {
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < 10 + random.nextInt(20); i++) sb.append(chars[random.nextInt(chars.length)]);
                    subTitle.setText(">> " + sb.toString() + " <<");
                    subTitle.setTextColor(Color.parseColor("#00ff41"));
                }
                if (mExpanded != null && count % 3 == 0) { gdMenuBody.setStroke(3, Color.rgb(0, 100 + random.nextInt(155), 0)); mExpanded.setBackground(gdMenuBody); }
                count++;
                effectHandler.postDelayed(this, 400);
            }
        }, 300);
    }

    private void stopMatrixEffect() {
        matrixEffectRunning = false;
        if (subTitle != null) { subTitle.setText("SYSTEM ONLINE"); subTitle.setTextColor(TEXT_COLOR); }
        if (mExpanded != null) { gdMenuBody.setStroke(2, MENU_BORDER_COLOR); mExpanded.setBackground(gdMenuBody); }
    }

    private void startGlitchEffect() {
        if (glitchEffectRunning) return;
        glitchEffectRunning = true;
        final int[] colors = {0xffff0000, 0xff00ff00, 0xff0000ff, 0xffff00ff, 0xff00ffff, 0xffffff00};
        effectHandler.postDelayed(new Runnable() {
            int count = 0;
            @Override public void run() {
                if (!glitchEffectRunning) { if (subTitle != null) subTitle.setText("SYSTEM ONLINE"); return; }
                if (title != null && count % 2 == 0) title.setTextColor(colors[random.nextInt(colors.length)]);
                if (mExpanded != null) { gdMenuBody.setStroke(3 + random.nextInt(5), colors[random.nextInt(colors.length)]); mExpanded.setBackground(gdMenuBody); }
                count++;
                effectHandler.postDelayed(this, 200);
            }
        }, 300);
    }

    private void stopGlitchEffect() {
        glitchEffectRunning = false;
        if (title != null) title.setTextColor(TEXT_COLOR);
        if (subTitle != null) subTitle.setText("SYSTEM ONLINE");
        if (mExpanded != null) { gdMenuBody.setStroke(2, MENU_BORDER_COLOR); mExpanded.setBackground(gdMenuBody); }
    }

    private void startScannerEffect() {
        if (scannerEffectRunning) return;
        scannerEffectRunning = true;
        effectHandler.postDelayed(new Runnable() {
            int scanY = 0;
            boolean up = true;
            @Override public void run() {
                if (!scannerEffectRunning) { if (subTitle != null) subTitle.setText("SYSTEM ONLINE"); return; }
                scanY += up ? 5 : -5;
                if (scanY > 200) { up = false; scanY = 200; }
                if (scanY < 0) { up = true; scanY = 0; }
                int green = 100 + (int) (155 * (1 - Math.abs(scanY - 100) / 100.0));
                gdMenuBody.setStroke(2 + (int) (Math.abs(scanY - 100) / 20), Color.rgb(0, green, 0));
                mExpanded.setBackground(gdMenuBody);
                effectHandler.postDelayed(this, 50);
            }
        }, 100);
    }

    private void stopScannerEffect() {
        scannerEffectRunning = false;
        if (subTitle != null) subTitle.setText("SYSTEM ONLINE");
        if (mExpanded != null) { gdMenuBody.setStroke(2, MENU_BORDER_COLOR); mExpanded.setBackground(gdMenuBody); }
    }

    private void startRainbowEffect() {
        if (rainbowEffectRunning) return;
        rainbowEffectRunning = true;
        effectHandler.postDelayed(new Runnable() {
            int hue = 0;
            @Override public void run() {
                if (!rainbowEffectRunning) { if (subTitle != null) subTitle.setText("SYSTEM ONLINE"); return; }
                hue = (hue + 5) % 360;
                int color = Color.HSVToColor(new float[]{hue, 1.0f, 1.0f});
                if (title != null) title.setTextColor(color);
                if (subTitle != null) subTitle.setTextColor(color);
                if (mExpanded != null) { gdMenuBody.setStroke(3, color); mExpanded.setBackground(gdMenuBody); }
                effectHandler.postDelayed(this, 100);
            }
        }, 200);
    }

    private void stopRainbowEffect() {
        rainbowEffectRunning = false;
        if (title != null) title.setTextColor(TEXT_COLOR);
        if (subTitle != null) subTitle.setText("SYSTEM ONLINE");
        if (mExpanded != null) { gdMenuBody.setStroke(2, MENU_BORDER_COLOR); mExpanded.setBackground(gdMenuBody); }
    }

    private void startNeonEffect() {
        if (neonEffectRunning) return;
        neonEffectRunning = true;
        final int[] neonColors = {0xffff00ff, 0xff00ffff, 0xffff4400, 0xff00ff44, 0xff4400ff};
        effectHandler.postDelayed(new Runnable() {
            int idx = 0;
            @Override public void run() {
                if (!neonEffectRunning) { if (subTitle != null) subTitle.setText("SYSTEM ONLINE"); return; }
                idx = (idx + 1) % neonColors.length;
                int color = neonColors[idx];
                if (title != null) title.setTextColor(color);
                if (subTitle != null) { subTitle.setText(">> NEON MODE <<"); subTitle.setTextColor(color); }
                if (mExpanded != null) { gdMenuBody.setStroke(4, color); mExpanded.setBackground(gdMenuBody); }
                effectHandler.postDelayed(this, 500);
            }
        }, 300);
    }

    private void stopNeonEffect() {
        neonEffectRunning = false;
        if (title != null) title.setTextColor(TEXT_COLOR);
        if (subTitle != null) subTitle.setText("SYSTEM ONLINE");
        if (mExpanded != null) { gdMenuBody.setStroke(2, MENU_BORDER_COLOR); mExpanded.setBackground(gdMenuBody); }
    }

    private void startCyberEffect() {
        if (cyberEffectRunning) return;
        cyberEffectRunning = true;
        final String[] texts = {">> CYBER PUNK <<", ">> HACK THE PLANET <<", ">> 0x00FF41 <<", ">> SYSTEM BREACH <<"};
        effectHandler.postDelayed(new Runnable() {
            int idx = 0;
            @Override public void run() {
                if (!cyberEffectRunning) { if (subTitle != null) subTitle.setText("SYSTEM ONLINE"); return; }
                idx = (idx + 1) % texts.length;
                int color = Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256));
                if (subTitle != null) { subTitle.setText(texts[idx]); subTitle.setTextColor(color); }
                if (title != null) title.setTextColor(Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
                if (mExpanded != null) { gdMenuBody.setStroke(3 + random.nextInt(3), color); mExpanded.setBackground(gdMenuBody); }
                effectHandler.postDelayed(this, 800);
            }
        }, 500);
    }

    private void stopCyberEffect() {
        cyberEffectRunning = false;
        if (title != null) title.setTextColor(TEXT_COLOR);
        if (subTitle != null) subTitle.setText("SYSTEM ONLINE");
        if (mExpanded != null) { gdMenuBody.setStroke(2, MENU_BORDER_COLOR); mExpanded.setBackground(gdMenuBody); }
    }

    private void startNumberEffect() {
        if (numberEffectRunning) return;
        numberEffectRunning = true;
        effectHandler.postDelayed(new Runnable() {
            int n = 0;
            boolean naik = true;
            @Override public void run() {
                if (!numberEffectRunning) {
                    if (subTitle != null) { subTitle.setText("SYSTEM ONLINE"); subTitle.setTextColor(TEXT_COLOR); }
                    return;
                }
                if (subTitle != null) {
                    int red = 255 - (n * 12);
                    int green = (n * 12);
                    if (red < 0) red = 0;
                    if (green > 255) green = 255;
                    subTitle.setText(">> LOADING: " + n + " <<");
                    subTitle.setTextColor(Color.rgb(red, green, 0));
                }
                if (mExpanded != null && gdMenuBody != null) {
                    int alpha = 100 + (n * 7);
                    if (alpha > 255) alpha = 255;
                    gdMenuBody.setStroke(2 + (n / 5), Color.argb(alpha, 0, 255, 65));
                    mExpanded.setBackground(gdMenuBody);
                }
                if (naik) { n++; if (n > 20) { n = 20; naik = false; } }
                else { n--; if (n < 0) { n = 0; naik = true; } }
                effectHandler.postDelayed(this, 60);
            }
        }, 100);
    }

    private void stopNumberEffect() {
        numberEffectRunning = false;
        if (subTitle != null) { subTitle.setText("SYSTEM ONLINE"); subTitle.setTextColor(TEXT_COLOR); }
        if (mExpanded != null && gdMenuBody != null) { gdMenuBody.setStroke(2, MENU_BORDER_COLOR); mExpanded.setBackground(gdMenuBody); }
    }

    private void startHackerRandomEffect() {
        if (hackerRandomEffectRunning) return;
        hackerRandomEffectRunning = true;
        effectHandler.postDelayed(new Runnable() {
            @Override public void run() {
                if (!hackerRandomEffectRunning) return;
                matrixEffectRunning = false; glitchEffectRunning = false; scannerEffectRunning = false;
                rainbowEffectRunning = false; neonEffectRunning = false; cyberEffectRunning = false;
                numberEffectRunning = false;
                int r = random.nextInt(7);
                if (r == 0) startMatrixEffect();
                else if (r == 1) startGlitchEffect();
                else if (r == 2) startScannerEffect();
                else if (r == 3) startRainbowEffect();
                else if (r == 4) startNeonEffect();
                else if (r == 5) startCyberEffect();
                else if (r == 6) startNumberEffect();
                effectHandler.postDelayed(this, 5000);
            }
        }, 1000);
    }

    private void stopHackerRandomEffect() {
        hackerRandomEffectRunning = false;
        matrixEffectRunning = false; glitchEffectRunning = false; scannerEffectRunning = false;
        rainbowEffectRunning = false; neonEffectRunning = false; cyberEffectRunning = false;
        numberEffectRunning = false;
        if (title != null) title.setTextColor(TEXT_COLOR);
        if (subTitle != null) subTitle.setText("SYSTEM ONLINE");
        if (mExpanded != null) { gdMenuBody.setStroke(2, MENU_BORDER_COLOR); mExpanded.setBackground(gdMenuBody); }
    }

    private void playVideoFromAssets(String fileName) {
        try {
            String videoPath = "file:///android_asset/" + fileName;
            String videoHtml = "<!DOCTYPE html><html><head><meta charset='UTF-8'><meta name='viewport' content='width=device-width, initial-scale=1.0'>" +
                    "<style>body{margin:0;padding:0;background:#000;overflow:hidden;display:flex;align-items:center;justify-content:center;height:100vh;}" +
                    "video{width:100%;height:100%;object-fit:cover;border-radius:50%;}</style></head><body>" +
                    "<video autoplay loop muted playsinline webkit-playsinline><source src='" + videoPath + "' type='video/mp4'></video></body></html>";
            WebView videoView = new WebView(getContext);
            int size = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, ICON_SIZE, getContext.getResources().getDisplayMetrics());
            videoView.setLayoutParams(new RelativeLayout.LayoutParams(size, size));
            WebSettings ws = videoView.getSettings();
            ws.setCacheMode(WebSettings.LOAD_NO_CACHE);
            ws.setDomStorageEnabled(true);
            ws.setAllowFileAccess(true);
            ws.setMediaPlaybackRequiresUserGesture(false);
            ws.setJavaScriptEnabled(true);
            ws.setLoadWithOverviewMode(true);
            ws.setUseWideViewPort(true);
            videoView.setBackgroundColor(Color.BLACK);
            videoView.setAlpha(1.0f);
            videoView.loadDataWithBaseURL(null, videoHtml, "text/html", "utf-8", null);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                videoView.setClipToOutline(true);
                videoView.setOutlineProvider(new ViewOutlineProvider() {
                    @Override public void getOutline(View view, Outline outline) { outline.setOval(0, 0, view.getWidth(), view.getHeight()); }
                });
            }
            videoView.setOnTouchListener(onTouchListener());
            videoView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) { mCollapsed.setVisibility(View.GONE); mExpanded.setVisibility(View.VISIBLE); }
            });
            mCollapsed.addView(videoView);
        } catch (Exception e) { loadFallbackIcon(); }
    }

    private void loadFallbackIcon() {
        startimage = new ImageView(getContext);
        startimage.setLayoutParams(new RelativeLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
        int size = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, ICON_SIZE, getContext.getResources().getDisplayMetrics());
        startimage.getLayoutParams().height = size;
        startimage.getLayoutParams().width = size;
        startimage.setScaleType(ImageView.ScaleType.FIT_XY);
        try {
            String iconData = Icon();
            if (iconData != null && !iconData.isEmpty()) {
                if (iconData.startsWith("data:image")) {
                    String base64 = iconData.substring(iconData.indexOf(",") + 1);
                    byte[] decoded = Base64.decode(base64, Base64.DEFAULT);
                    startimage.setImageBitmap(BitmapFactory.decodeByteArray(decoded, 0, decoded.length));
                } else {
                    byte[] decode = Base64.decode(iconData, 0);
                    startimage.setImageBitmap(BitmapFactory.decodeByteArray(decode, 0, decode.length));
                }
            }
        } catch (Exception e) { }
        startimage.setOnTouchListener(onTouchListener());
        startimage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) { mCollapsed.setVisibility(View.GONE); mExpanded.setVisibility(View.VISIBLE); }
        });
        mCollapsed.addView(startimage);
    }

    private void refreshAllUI() {
        if (gdMenuBody != null) { gdMenuBody.setStroke(2, THEME_COLOR); mExpanded.setBackground(gdMenuBody); }
        if (title != null) title.setTextColor(TEXT_COLOR);
        if (subTitle != null) subTitle.setTextColor(TEXT_COLOR);
        if (hideBtn != null) hideBtn.setTextColor(TEXT_COLOR_2);
        if (closeBtn != null) closeBtn.setTextColor(TEXT_COLOR_2);
        updateAllChildColors(mSettings);
        updateAllChildColors(mZSettings);
        updateAllChildColors(mGearSettings);
        updateAllChildColors(mods);
    }

    // ============================================================
    // CONSTRUCTOR
    // ============================================================
    @SuppressLint("SetTextI18n")
    public Menu(Context context) {
        getContext = context;
        Preferences.context = context;
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        initDeviceInfo();

        rootFrame = new FrameLayout(context) {
            @Override public boolean dispatchTouchEvent(MotionEvent ev) {
                try {
                    int a = ev.getActionMasked();
                    if (a == MotionEvent.ACTION_DOWN) { soundDownX = ev.getRawX(); soundDownY = ev.getRawY(); }
                    else if (a == MotionEvent.ACTION_UP
                            && Math.abs(ev.getRawX() - soundDownX) < dp(10) && Math.abs(ev.getRawY() - soundDownY) < dp(10)
                            && findClickableAt(this, (int) ev.getRawX(), (int) ev.getRawY()) != null) {
                        playClickSound();
                    }
                } catch (Exception ignored) {}
                return super.dispatchTouchEvent(ev);
            }
        };
        rootFrame.setOnTouchListener(onTouchListener());
        mRootContainer = new RelativeLayout(context);
        mCollapsed = new RelativeLayout(context);
        mCollapsed.setVisibility(View.VISIBLE);
        mCollapsed.setAlpha(ICON_ALPHA);

        int savedTheme = Preferences.loadPrefInt("ThemeColor", -200);
        if (savedTheme >= 0 && savedTheme < colorHexs.length) { selectedColorIndex = savedTheme; applyThemeColor(savedTheme); }
        int savedTextColor = Preferences.loadPrefInt("TextColor", -204);
        if (savedTextColor >= 0 && savedTextColor < colorHexs.length) { selectedTextColorIndex = savedTextColor; applyTextColor(savedTextColor); }
        int savedFont = Preferences.loadPrefInt("FontStyle", -201);
        if (savedFont >= 0 && savedFont < fontList.length) applyFont(savedFont);
        int savedBgMenu = Preferences.loadPrefInt("BgMenuColor", -210);
        if (savedBgMenu >= 0 && savedBgMenu < bgColorHexs.length) { selectedBgMenuIndex = savedBgMenu; applyBgMenu(savedBgMenu); }
        int savedBgFeature = Preferences.loadPrefInt("BgFeatureColor", -211);
        if (savedBgFeature >= 0 && savedBgFeature < bgColorHexs.length) { selectedBgFeatureIndex = savedBgFeature; applyBgFeature(savedBgFeature); }
        int savedBgBtn = Preferences.loadPrefInt("BgButtonColor", -212);
        if (savedBgBtn >= 0 && savedBgBtn < bgColorHexs.length) { selectedBgBtnIndex = savedBgBtn; applyBgButton(savedBgBtn); }
        if (prefs.getBoolean("custom_on", false)) applyCustomColor(prefs.getInt("custom_color", Color.parseColor("#00ff41")), false);

        mExpanded = new LinearLayout(context);
        mExpanded.setVisibility(View.GONE);
        mExpanded.setBackgroundColor(MENU_BG_COLOR);
        mExpanded.setOrientation(LinearLayout.VERTICAL);
        mExpanded.setLayoutParams(new LinearLayout.LayoutParams(dp(MENU_WIDTH), WRAP_CONTENT));
        gdMenuBody = new GradientDrawable();
        gdMenuBody.setCornerRadius(MENU_CORNER);
        gdMenuBody.setColor(MENU_BG_COLOR);
        gdMenuBody.setStroke(2, THEME_COLOR);
        mExpanded.setBackground(gdMenuBody);

        playVideoFromAssets("VIDEO.mp4");

        settingsIcon = new TextView(context);
        settingsIcon.setText("⊙");
        settingsIcon.setTextColor(TEXT_COLOR);
        settingsIcon.setTypeface(Typeface.DEFAULT_BOLD);
        settingsIcon.setTextSize(14.0f);
        settingsIcon.setPadding(10, 5, 10, 5);
        settingsIcon.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                try {
                    boolean shown = false;
                    for (int i = 0; i < scrollView.getChildCount(); i++) { if (scrollView.getChildAt(i) == mSettings) { shown = true; break; } }
                    if (shown) { scrollView.removeAllViews(); scrollView.addView(mods); Toast.makeText(getContext, "MOD MENU", Toast.LENGTH_SHORT).show(); }
                    else { scrollView.removeAllViews(); scrollView.addView(mSettings); scrollView.scrollTo(0, 0); Toast.makeText(getContext, "SETTINGS", Toast.LENGTH_SHORT).show(); }
                } catch (Exception e) { }
            }
        });

        gearBtn = createGearButton();

        final LinearLayout rightButtons = new LinearLayout(context);
        rightButtons.setOrientation(LinearLayout.HORIZONTAL);
        rightButtons.setGravity(Gravity.CENTER_VERTICAL);
        rightButtons.addView(gearBtn);
        rightButtons.addView(settingsIcon);

        RelativeLayout.LayoutParams rlRightButtons = new RelativeLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
        rlRightButtons.addRule(ALIGN_PARENT_RIGHT);
        rlRightButtons.addRule(RelativeLayout.CENTER_VERTICAL);
        rlRightButtons.setMargins(0, 0, 10, 0);
        rightButtons.setLayoutParams(rlRightButtons);

        // ============ mSettings ============
        mSettings = new LinearLayout(context);
        mSettings.setOrientation(LinearLayout.VERTICAL);
        Category(mSettings, "DEVICE INFO");
        TextView deviceInfo = new TextView(context);
        String deviceText = "Brand: <b><font color='" + NumberTxtColor + "'>" + deviceBrand + "</font></b><br>" +
                "Model: <b><font color='" + NumberTxtColor + "'>" + deviceModel + "</font></b><br>" +
                "Android: <b><font color='" + NumberTxtColor + "'>" + deviceRelease + "</font></b><br>" +
                "Year: <b><font color='" + NumberTxtColor + "'>" + deviceYear + "</font></b><br>" +
                "RAM: <b><font color='" + NumberTxtColor + "'>" + totalRAM + "</font></b><br>" +
                "Country: <b><font color='" + NumberTxtColor + "'>" + country + "</font></b><br>" +
                "FPS: <b><font color='" + NumberTxtColor + "'>" + currentFps + "</font></b><br>" +
                "Android ID: <b><font color='" + NumberTxtColor + "'>" + androidId + "</font></b>";
        deviceInfo.setText(Html.fromHtml(deviceText));
        deviceInfo.setTextColor(TEXT_COLOR_2);
        deviceInfo.setTypeface(currentTypeface != null ? currentTypeface : Typeface.MONOSPACE);
        deviceInfo.setPadding(10, 5, 10, 5);
        mSettings.addView(deviceInfo);
        String[] settingsList = SettingsList();
        if (settingsList != null) featureList(settingsList, mSettings);
        Button backSettingsBtn = new Button(context);
        LinearLayout.LayoutParams backSettingsParams = new LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT);
        backSettingsParams.setMargins(7, 20, 7, 5);
        backSettingsBtn.setLayoutParams(backSettingsParams);
        backSettingsBtn.setText("<< BACK");
        backSettingsBtn.setTextColor(Color.WHITE);
        backSettingsBtn.setBackgroundColor(BTN_COLOR);
        backSettingsBtn.setTypeface(currentTypeface != null ? currentTypeface : Typeface.MONOSPACE);
        backSettingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) { try { scrollView.removeAllViews(); scrollView.addView(mods); } catch (Exception e) { } }
        });
        mSettings.addView(backSettingsBtn);

        // ============ mZSettings ============
        mZSettings = new LinearLayout(context);
        mZSettings.setOrientation(LinearLayout.VERTICAL);
        Category(mZSettings, "Z MENU");
        mZSettings.addView(buildSpinnerLayout("MUSIC PLAYER", musicFiles, -203, new SpinnerListener() {
            @Override public void onItemSelected(int position) { playMusicFromAssets(musicFiles[position]); }
        }));
        Button downloadBtn = new Button(context);
        LinearLayout.LayoutParams downloadParams = new LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT);
        downloadParams.setMargins(7, 5, 7, 5);
        downloadBtn.setLayoutParams(downloadParams);
        downloadBtn.setText("DOWNLOAD DEVICE INFO");
        downloadBtn.setTextColor(TEXT_COLOR_2);
        downloadBtn.setBackgroundColor(BTN_COLOR);
        downloadBtn.setTypeface(currentTypeface != null ? currentTypeface : Typeface.MONOSPACE);
        downloadBtn.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { downloadDeviceInfo(); } });
        mZSettings.addView(downloadBtn);
        mZSettings.addView(buildSpinnerLayout("THEME COLOR", colorNames, -200, new SpinnerListener() {
            @Override public void onItemSelected(int position) { if (!customColorActive) { applyThemeColor(position); refreshAllUI(); } }
        }));
        mZSettings.addView(buildSpinnerLayout("TEXT COLOR", colorNames, -204, new SpinnerListener() {
            @Override public void onItemSelected(int position) { if (!customColorActive) { applyTextColor(position); refreshAllUI(); } }
        }));
        mZSettings.addView(buildSpinnerLayout("HACKER EFFECTS", effectList, -202, new SpinnerListener() {
            @Override public void onItemSelected(int position) {
                stopHackerRandomEffect();
                stopMatrixEffect(); stopGlitchEffect(); stopScannerEffect();
                stopRainbowEffect(); stopNeonEffect(); stopCyberEffect();
                stopNumberEffect();
                switch (position) {
                    case 0: break;
                    case 1: startMatrixEffect(); break;
                    case 2: startGlitchEffect(); break;
                    case 3: startScannerEffect(); break;
                    case 4: startRainbowEffect(); break;
                    case 5: startNeonEffect(); break;
                    case 6: startCyberEffect(); break;
                    case 7: startHackerRandomEffect(); break;
                    case 8: startNumberEffect(); break;
                }
            }
        }));
        mZSettings.addView(buildSpinnerLayout("FONT STYLE", fontList, -201, new SpinnerListener() {
            @Override public void onItemSelected(int position) { applyFont(position); refreshAllUI(); }
        }));
        Category(mZSettings, "SUARA TOMBOL");
        mZSettings.addView(buildSpinnerLayout("PILIH SUARA", soundList, -230, new SpinnerListener() {
            @Override public void onItemSelected(int position) { soundMode = position; }
        }));
        Category(mZSettings, "SCAN AREA SEKITAR");
        mZSettings.addView(makeMediaButton("SCAN WIFI SEKITAR", new View.OnClickListener() {
            @Override public void onClick(View v) { scanNearbyArea(); }
        }));
        Button playMusicBtn = new Button(context);
        LinearLayout.LayoutParams playParams = new LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT);
        playParams.setMargins(7, 5, 7, 5);
        playMusicBtn.setLayoutParams(playParams);
        playMusicBtn.setText("PLAY MUSIC");
        playMusicBtn.setTextColor(TEXT_COLOR_2);
        playMusicBtn.setBackgroundColor(BTN_COLOR);
        playMusicBtn.setTypeface(currentTypeface != null ? currentTypeface : Typeface.MONOSPACE);
        playMusicBtn.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { playMusicFromAssets(musicFiles[0]); } });
        mZSettings.addView(playMusicBtn);
        Button stopMusicBtn = new Button(context);
        LinearLayout.LayoutParams stopParams = new LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT);
        stopParams.setMargins(7, 5, 7, 5);
        stopMusicBtn.setLayoutParams(stopParams);
        stopMusicBtn.setText("STOP MUSIC");
        stopMusicBtn.setTextColor(TEXT_COLOR_2);
        stopMusicBtn.setBackgroundColor(BTN_COLOR);
        stopMusicBtn.setTypeface(currentTypeface != null ? currentTypeface : Typeface.MONOSPACE);
        stopMusicBtn.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { stopMusic(); } });
        mZSettings.addView(stopMusicBtn);
        // ============ MUSIK YOUTUBE ============
        Category(mZSettings, "MUSIK YOUTUBE");
        ytStatus = new TextView(context);
        ytStatus.setTextColor(TEXT_COLOR_2);
        ytStatus.setTextSize(11f);
        ytStatus.setPadding(10, 5, 10, 5);
        ytStatus.setTypeface(currentTypeface != null ? currentTypeface : Typeface.MONOSPACE);
        ytStatus.setText("Tempel link YouTube lalu putar. Player melayang, tetap jalan walau menu ditutup.");
        mZSettings.addView(ytStatus);
        mZSettings.addView(makeMediaButton("PUTAR LINK YOUTUBE", new View.OnClickListener() {
            @Override public void onClick(View v) { showYoutubeDialog(); }
        }));
        mZSettings.addView(makeMediaButton("PAUSE / LANJUT", new View.OnClickListener() {
            @Override public void onClick(View v) { toggleYoutube(); }
        }));
        mZSettings.addView(makeMediaButton("SEMBUNYI / TAMPIL LAYAR", new View.OnClickListener() {
            @Override public void onClick(View v) { toggleYoutubeVideo(); }
        }));
        mZSettings.addView(makeMediaButton("STOP YOUTUBE", new View.OnClickListener() {
            @Override public void onClick(View v) { stopYoutube(true); }
        }));

        Button backZBtn = new Button(context);
        LinearLayout.LayoutParams backZParams = new LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT);
        backZParams.setMargins(7, 20, 7, 5);
        backZBtn.setLayoutParams(backZParams);
        backZBtn.setText("<< BACK");
        backZBtn.setTextColor(Color.WHITE);
        backZBtn.setBackgroundColor(BTN_COLOR);
        backZBtn.setTypeface(currentTypeface != null ? currentTypeface : Typeface.MONOSPACE);
        backZBtn.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) { try { scrollView.removeAllViews(); scrollView.addView(mods); } catch (Exception e) { } }
        });
        mZSettings.addView(backZBtn);

        // ============ mGearSettings ============
        mGearSettings = new LinearLayout(context);
        mGearSettings.setOrientation(LinearLayout.VERTICAL);
        // ============ WARNA CUSTOM (SEKALI KLIK, SEMUA BERUBAH) ============
        Category(mGearSettings, "WARNA CUSTOM (SEKALI KLIK)");
        final int[] swatchColors = {
            0xFF00FF41, 0xFFFF1744, 0xFF2979FF, 0xFFFFEA00, 0xFFAA00FF, 0xFF00E5FF, 0xFFFF4081, 0xFFFF9100,
            0xFF76FF03, 0xFFFFFFFF, 0xFFFF0000, 0xFF00FFFF, 0xFFFFD700, 0xFFD500F9, 0xFF39FF14, 0xFFFF6E40
        };
        for (int r = 0; r < 2; r++) {
            LinearLayout swRow = new LinearLayout(context);
            swRow.setOrientation(LinearLayout.HORIZONTAL);
            swRow.setPadding(4, 2, 4, 2);
            for (int c = 0; c < 8; c++) {
                final int col = swatchColors[r * 8 + c];
                View sw = new View(context);
                LinearLayout.LayoutParams slp = new LinearLayout.LayoutParams(0, dp(30), 1f);
                slp.setMargins(3, 3, 3, 3);
                sw.setLayoutParams(slp);
                GradientDrawable gd = new GradientDrawable();
                gd.setColor(col);
                gd.setCornerRadius(dp(6));
                gd.setStroke(2, Color.WHITE);
                sw.setBackground(gd);
                sw.setOnClickListener(new View.OnClickListener() {
                    @Override public void onClick(View v) { applyCustomColor(col, true); }
                });
                swRow.addView(sw);
            }
            mGearSettings.addView(swRow);
        }
        TextView hueLabel = new TextView(context);
        hueLabel.setText("SLIDER WARNA (geser = langsung berubah semua)");
        hueLabel.setTextColor(TEXT_COLOR_2);
        hueLabel.setTextSize(11f);
        hueLabel.setPadding(10, 8, 10, 0);
        hueLabel.setTypeface(currentTypeface != null ? currentTypeface : Typeface.MONOSPACE);
        mGearSettings.addView(hueLabel);
        SeekBar hueBar = new SeekBar(context);
        hueBar.setMax(360);
        GradientDrawable rainbow = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{
            0xFFFF0000, 0xFFFFFF00, 0xFF00FF00, 0xFF00FFFF, 0xFF0000FF, 0xFFFF00FF, 0xFFFF0000 });
        rainbow.setCornerRadius(dp(4));
        rainbow.setSize(-1, dp(10));
        hueBar.setProgressDrawable(rainbow);
        hueBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override public void onProgressChanged(SeekBar sb, int progress, boolean fromUser) {
                if (!fromUser) return;
                long now = System.currentTimeMillis();
                if (now - lastCustomApply < 60) return;
                lastCustomApply = now;
                applyCustomColor(Color.HSVToColor(new float[]{ progress, 1f, 1f }), false);
            }
            @Override public void onStartTrackingTouch(SeekBar sb) {}
            @Override public void onStopTrackingTouch(SeekBar sb) {
                applyCustomColor(Color.HSVToColor(new float[]{ sb.getProgress(), 1f, 1f }), true);
            }
        });
        mGearSettings.addView(hueBar);
        mGearSettings.addView(makeMediaButton("HEX CUSTOM (#RRGGBB)", new View.OnClickListener() {
            @Override public void onClick(View v) { showHexDialog(); }
        }));
        mGearSettings.addView(makeMediaButton("RESET WARNA", new View.OnClickListener() {
            @Override public void onClick(View v) { resetCustomColor(); }
        }));

        Category(mGearSettings, "ATUR WARNA BACKGROUND");
        mGearSettings.addView(buildSpinnerLayout("BG MENU", bgColorNames, -210, new SpinnerListener() {
            @Override public void onItemSelected(int position) { applyBgMenu(position); refreshAllUI(); }
        }));
        mGearSettings.addView(buildSpinnerLayout("BG FITUR", bgColorNames, -211, new SpinnerListener() {
            @Override public void onItemSelected(int position) { applyBgFeature(position); refreshAllUI(); }
        }));
        mGearSettings.addView(buildSpinnerLayout("BG BUTTON", bgColorNames, -212, new SpinnerListener() {
            @Override public void onItemSelected(int position) { applyBgButton(position); refreshAllUI(); }
        }));

        // ⭐ SPINNER BARU: BG EFFECT (40 pilihan WebView)
        Category(mGearSettings, "BACKGROUND VIDEO EFFECTS (40)");
        mGearSettings.addView(buildSpinnerLayout("BG EFFECT", bgEffectList, -220, new SpinnerListener() {
            @Override public void onItemSelected(int position) {
                setBgEffectWeb(position);
                Preferences.changeFeatureInt("BgEffectIndex", -220, position);
            }
        }));

        // ============ GIF / GAMBAR: 5 SLOT UPLOAD ============
        Category(mGearSettings, "GIF / GAMBAR (5 SLOT UPLOAD)");
        for (int si = 0; si < MEDIA_SLOTS; si++) {
            final int slot = si;
            mediaStatus[si] = new TextView(context);
            mediaStatus[si].setTextColor(TEXT_COLOR_2);
            mediaStatus[si].setTextSize(11f);
            mediaStatus[si].setPadding(10, 8, 10, 0);
            mediaStatus[si].setTypeface(currentTypeface != null ? currentTypeface : Typeface.MONOSPACE);
            mGearSettings.addView(mediaStatus[si]);
            LinearLayout row = new LinearLayout(context);
            row.setOrientation(LinearLayout.HORIZONTAL);
            row.addView(makeRowButton("UPLOAD", new View.OnClickListener() {
                @Override public void onClick(View v) { pickMedia(slot); }
            }));
            row.addView(makeRowButton("TAMPIL / SEMBUNYI", new View.OnClickListener() {
                @Override public void onClick(View v) {
                    if (mediaFloat[slot] != null) hideMedia(slot, true); else showMedia(slot);
                }
            }));
            mGearSettings.addView(row);
            SeekBar sizeBar = new SeekBar(context);
            sizeBar.setMax(360);
            sizeBar.setProgress(prefs.getInt("media_size_" + si, 120) - 40);
            try {
                sizeBar.getProgressDrawable().setColorFilter(SeekBarColor, PorterDuff.Mode.SRC_IN);
                sizeBar.getThumb().setColorFilter(SeekBarColor, PorterDuff.Mode.SRC_IN);
            } catch (Exception ignored) {}
            sizeBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override public void onProgressChanged(SeekBar sb, int progress, boolean fromUser) {
                    if (!fromUser) return;
                    int s = progress + 40;
                    prefs.edit().putInt("media_size_" + slot, s).apply();
                    if (mediaFloat[slot] != null && mediaParams[slot] != null && mWindowManager != null) {
                        mediaParams[slot].width = dp(s);
                        mediaParams[slot].height = dp(s);
                        try { mWindowManager.updateViewLayout(mediaFloat[slot], mediaParams[slot]); } catch (Exception ignored) {}
                    }
                }
                @Override public void onStartTrackingTouch(SeekBar sb) {}
                @Override public void onStopTrackingTouch(SeekBar sb) {}
            });
            mGearSettings.addView(sizeBar);
        }
        mGearSettings.addView(makeMediaButton("HAPUS SEMUA GIF / GAMBAR", new View.OnClickListener() {
            @Override public void onClick(View v) { confirmDeleteAllMedia(); }
        }));
        updateMediaStatus();

        Button backGearBtn = new Button(context);
        LinearLayout.LayoutParams backGearParams = new LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT);
        backGearParams.setMargins(7, 20, 7, 5);
        backGearBtn.setLayoutParams(backGearParams);
        backGearBtn.setText("<< BACK");
        backGearBtn.setTextColor(Color.WHITE);
        backGearBtn.setBackgroundColor(BTN_COLOR);
        backGearBtn.setTypeface(currentTypeface != null ? currentTypeface : Typeface.MONOSPACE);
        backGearBtn.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) { try { scrollView.removeAllViews(); scrollView.addView(mods); } catch (Exception e) { } }
        });
        mGearSettings.addView(backGearBtn);

        // ============ UI SETUP ============
        RelativeLayout titleText = new RelativeLayout(context);
        titleText.setPadding(10, 5, 10, 5);
        titleText.setVerticalGravity(16);
        title = new TextView(context);
        title.setTextColor(TEXT_COLOR);
        title.setTextSize(18.0f);
        title.setGravity(Gravity.CENTER);
        title.setTypeface(currentTypeface != null ? currentTypeface : Typeface.MONOSPACE);
        RelativeLayout.LayoutParams rl = new RelativeLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
        rl.addRule(RelativeLayout.CENTER_HORIZONTAL);
        rl.addRule(RelativeLayout.CENTER_VERTICAL);
        title.setLayoutParams(rl);
        subTitle = new TextView(context);
        subTitle.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        subTitle.setMarqueeRepeatLimit(-1);
        subTitle.setSingleLine(true);
        subTitle.setSelected(true);
        subTitle.setTextColor(TEXT_COLOR);
        subTitle.setTextSize(10.0f);
        subTitle.setGravity(Gravity.CENTER);
        subTitle.setPadding(0, 0, 0, 5);
        subTitle.setTypeface(currentTypeface != null ? currentTypeface : Typeface.MONOSPACE);
        subTitle.setText("SYSTEM ONLINE");
        scrollView = new ScrollView(context);
        scrlLL = new LinearLayout.LayoutParams(MATCH_PARENT, dp(MENU_HEIGHT));
        scrlLLExpanded = new LinearLayout.LayoutParams(mExpanded.getLayoutParams());
        scrlLLExpanded.weight = 1.0f;
        scrollView.setLayoutParams(Preferences.isExpanded ? scrlLLExpanded : scrlLL);
        scrollView.setBackgroundColor(0x00000000); // transparan biar WebView keliatan
        mods = new LinearLayout(context);
        mods.setOrientation(LinearLayout.VERTICAL);
        relativeLayoutBottom = new RelativeLayout(context);
        relativeLayoutBottom.setPadding(10, 3, 10, 3);
        relativeLayoutBottom.setVerticalGravity(Gravity.CENTER);

        RelativeLayout.LayoutParams lParamsHideBtn = new RelativeLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
        lParamsHideBtn.addRule(ALIGN_PARENT_LEFT);

        hideBtn = new Button(context);
        hideBtn.setLayoutParams(lParamsHideBtn);
        hideBtn.setBackgroundColor(Color.TRANSPARENT);
        hideBtn.setText("HIDE");
        hideBtn.setTextColor(TEXT_COLOR);
        hideBtn.setTypeface(currentTypeface != null ? currentTypeface : Typeface.MONOSPACE);
        hideBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mCollapsed.setVisibility(View.VISIBLE);
                mCollapsed.setAlpha(0);
                mExpanded.setVisibility(View.GONE);
            }
        });
        hideBtn.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View view) {
                rootFrame.removeView(mRootContainer);
                mWindowManager.removeView(rootFrame);
                return false;
            }
        });

        RelativeLayout.LayoutParams lParamsCloseBtn = new RelativeLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
        lParamsCloseBtn.addRule(ALIGN_PARENT_RIGHT);

        closeBtn = new Button(context);
        closeBtn.setLayoutParams(lParamsCloseBtn);
        closeBtn.setBackgroundColor(Color.TRANSPARENT);
        closeBtn.setText("MINIMIZE");
        closeBtn.setTextColor(TEXT_COLOR);
        closeBtn.setTypeface(currentTypeface != null ? currentTypeface : Typeface.MONOSPACE);
        closeBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mCollapsed.setVisibility(View.VISIBLE);
                mCollapsed.setAlpha(ICON_ALPHA);
                mExpanded.setVisibility(View.GONE);
            }
        });

        mRootContainer.addView(mCollapsed);
        mRootContainer.addView(mExpanded);

        featurePanel = new LinearLayout(context);
        featurePanel.setOrientation(LinearLayout.VERTICAL);
        featurePanel.setBackgroundColor(MENU_BG_COLOR);

        titleText.addView(title);
        titleText.addView(rightButtons);
        scrollView.addView(mods);
        relativeLayoutBottom.addView(hideBtn);
        relativeLayoutBottom.addView(closeBtn);
        logoZBtn = createLogoZButton();
        relativeLayoutBottom.addView(logoZBtn);

        // ⭐ Pasang WebView background pakai FrameLayout layering
        initBgWebView();
        bgContainer = new FrameLayout(context);
        bgContainer.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT, 0, 1f));
        bgContainer.addView(bgWebView);

        LinearLayout contentLayer = new LinearLayout(context);
        contentLayer.setOrientation(LinearLayout.VERTICAL);
        contentLayer.setLayoutParams(new FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT));
        contentLayer.addView(subTitle);
        contentLayer.addView(scrollView);
        contentLayer.addView(relativeLayoutBottom);
        bgContainer.addView(contentLayer);

        featurePanel.addView(titleText);
        featurePanel.addView(bgContainer);

        loginPanel = buildLoginPanel();
        mExpanded.addView(loginPanel);

        // ⭐ Restore BG effect yang tersimpan
        int savedBgFx = Preferences.loadPrefInt("BgEffectIndex", -220);
        if (savedBgFx > 0 && savedBgFx < bgEffectList.length) {
            setBgEffectWeb(savedBgFx);
        }

        restoreMediaWhenReady(0);

        Init(context, title, subTitle);
    }

        // ============================================================
    // SHOW MENU
    // ============================================================
    public void ShowMenu() {
        rootFrame.addView(mRootContainer);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            boolean viewLoaded = false;
            @Override public void run() {
                if (Preferences.loadPref && !IsGameLibLoaded() && !stopChecking) {
                    if (!viewLoaded) { Category(mods, "SYSTEM LOADING..."); Button(mods, -100, "FORCE LOAD"); viewLoaded = true; }
                    handler.postDelayed(this, 600);
                } else {
                    mods.removeAllViews();
                    String[] featureList = GetFeatureList();
                    if (featureList != null) featureList(featureList, mods);
                }
            }
        }, 500);
    }

    // ============================================================
    // WINDOW MANAGER
    // ============================================================
    @SuppressLint("WrongConstant")
    public void SetWindowManagerWindowService() {
        int iparams = Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O ? 2038 : 2002;
        vmParams = new WindowManager.LayoutParams(WRAP_CONTENT, WRAP_CONTENT, iparams, 8 | FLAG_TRANSLUCENT_STATUS, -3);
        vmParams.gravity = 51;
        vmParams.x = POS_X;
        vmParams.y = POS_Y;
        mWindowManager = (WindowManager) getContext.getSystemService(Context.WINDOW_SERVICE);
        mWindowManager.addView(rootFrame, vmParams);
        overlayRequired = true;
    }

    @SuppressLint("WrongConstant")
    public void SetWindowManagerActivity() {
        vmParams = new WindowManager.LayoutParams(WRAP_CONTENT, WRAP_CONTENT, POS_X, POS_Y,
                WindowManager.LayoutParams.TYPE_APPLICATION,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                WindowManager.LayoutParams.FLAG_LAYOUT_IN_OVERSCAN |
                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN |
                WindowManager.LayoutParams.FLAG_SPLIT_TOUCH,
                PixelFormat.TRANSPARENT);
        vmParams.gravity = 51;
        vmParams.x = POS_X;
        vmParams.y = POS_Y;
        mWindowManager = ((Activity) getContext).getWindowManager();
        mWindowManager.addView(rootFrame, vmParams);
    }

    // ============================================================
    // TOUCH LISTENER
    // ============================================================
    private View.OnTouchListener onTouchListener() {
        return new View.OnTouchListener() {
            private float initialTouchX, initialTouchY;
            private int initialX, initialY;
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        initialX = vmParams.x;
                        initialY = vmParams.y;
                        initialTouchX = motionEvent.getRawX();
                        initialTouchY = motionEvent.getRawY();
                        return true;
                    case MotionEvent.ACTION_UP:
                        int rawX = (int) (motionEvent.getRawX() - initialTouchX);
                        int rawY = (int) (motionEvent.getRawY() - initialTouchY);
                        mExpanded.setAlpha(1f);
                        mCollapsed.setAlpha(1f);
                        if (rawX < 10 && rawY < 10 && isViewCollapsed()) {
                            try { mCollapsed.setVisibility(View.GONE); mExpanded.setVisibility(View.VISIBLE); } catch (NullPointerException ignored) {}
                        }
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        mExpanded.setAlpha(0.5f);
                        mCollapsed.setAlpha(0.5f);
                        vmParams.x = initialX + ((int) (motionEvent.getRawX() - initialTouchX));
                        vmParams.y = initialY + ((int) (motionEvent.getRawY() - initialTouchY));
                        mWindowManager.updateViewLayout(rootFrame, vmParams);
                        return true;
                    default: return false;
                }
            }
        };
    }

    // ============================================================
    // FEATURE LIST PARSER
    // ============================================================
    private void featureList(String[] listFT, LinearLayout linearLayout) {
        int featNum, subFeat = 0;
        LinearLayout llBak = linearLayout;
        for (int i = 0; i < listFT.length; i++) {
            boolean switchedOn = false;
            String feature = listFT[i];
            if (feature.contains("_True")) { switchedOn = true; feature = feature.replaceFirst("_True", ""); }
            linearLayout = llBak;
            if (feature.contains("CollapseAdd_")) { linearLayout = mCollapse; feature = feature.replaceFirst("CollapseAdd_", ""); }
            String[] str = feature.split("_");
            if (TextUtils.isDigitsOnly(str[0]) || str[0].matches("-[0-9]*")) { featNum = Integer.parseInt(str[0]); feature = feature.replaceFirst(str[0] + "_", ""); subFeat++; }
            else featNum = i - subFeat;
            String[] strSplit = feature.split("_");
            if (strSplit.length < 1) continue;
            if (strSplit[0].equals("Toggle")) { if (strSplit.length >= 2) Switch(linearLayout, featNum, strSplit[1], switchedOn); }
            else if (strSplit[0].equals("SeekBar")) { if (strSplit.length >= 4) SeekBar(linearLayout, featNum, strSplit[1], Integer.parseInt(strSplit[2]), Integer.parseInt(strSplit[3])); }
            else if (strSplit[0].equals("Button")) { if (strSplit.length >= 2) Button(linearLayout, featNum, strSplit[1]); }
            else if (strSplit[0].equals("ButtonOnOff")) { if (strSplit.length >= 2) ButtonOnOff(linearLayout, featNum, strSplit[1], switchedOn); }
            else if (strSplit[0].equals("ButtonOnOffV2")) { if (strSplit.length >= 2) ButtonOnOffV2(linearLayout, featNum, strSplit[1], switchedOn); }
            else if (strSplit[0].equals("Spinner")) { if (strSplit.length >= 3) { TextView(linearLayout, strSplit[1]); Spinner(linearLayout, featNum, strSplit[1], strSplit[2]); } }
            else if (strSplit[0].equals("InputText")) { if (strSplit.length >= 2) InputText(linearLayout, featNum, strSplit[1]); }
            else if (strSplit[0].equals("InputValue")) { if (strSplit.length == 3) InputNum(linearLayout, featNum, strSplit[2], Integer.parseInt(strSplit[1])); else if (strSplit.length == 2) InputNum(linearLayout, featNum, strSplit[1], 0); }
            else if (strSplit[0].equals("InputLValue")) { if (strSplit.length == 3) InputLNum(linearLayout, featNum, strSplit[2], Long.parseLong(strSplit[1])); else if (strSplit.length == 2) InputLNum(linearLayout, featNum, strSplit[1], 0); }
            else if (strSplit[0].equals("CheckBox")) { if (strSplit.length >= 2) CheckBox(linearLayout, featNum, strSplit[1], switchedOn); }
            else if (strSplit[0].equals("RadioButton")) { if (strSplit.length >= 3) RadioButton(linearLayout, featNum, strSplit[1], strSplit[2]); }
            else if (strSplit[0].equals("Collapse")) { if (strSplit.length >= 2) { Collapse(linearLayout, strSplit[1], switchedOn); subFeat++; } }
            else if (strSplit[0].equals("ButtonLink")) { if (strSplit.length >= 3) { ButtonLink(linearLayout, strSplit[1], strSplit[2]); subFeat++; } }
            else if (strSplit[0].equals("Category")) { if (strSplit.length >= 2) { Category(linearLayout, strSplit[1]); subFeat++; } }
            else if (strSplit[0].equals("RichTextView")) { if (strSplit.length >= 2) { TextView(linearLayout, strSplit[1]); subFeat++; } }
            else if (strSplit[0].equals("RichWebView")) { if (strSplit.length >= 2) { WebTextView(linearLayout, strSplit[1]); subFeat++; } }
        }
    }

    // ============================================================
    // UI ELEMENTS
    // ============================================================
    private void Switch(LinearLayout linLayout, final int featNum, final String featName, boolean swiOn) {
        final Switch switchR = new Switch(getContext);
        ColorStateList buttonStates = new ColorStateList(
                new int[][]{new int[]{-android.R.attr.state_enabled}, new int[]{android.R.attr.state_checked}, new int[]{}},
                new int[]{BG_OFF_COLOR, Color.parseColor("#808080"), BG_OFF_COLOR});
        try { switchR.getThumbDrawable().setTintList(buttonStates); switchR.getTrackDrawable().setTintList(buttonStates); } catch (NullPointerException ex) {}
        switchR.setText(featName);
        switchR.setTextColor(TEXT_COLOR_2);
        switchR.setPadding(10, 5, 0, 5);
        switchR.setTypeface(currentTypeface != null ? currentTypeface : Typeface.MONOSPACE);
        switchR.setChecked(Preferences.loadPrefBool(featName, featNum, swiOn));
        switchR.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean bool) {
                Preferences.changeFeatureBool(featName, featNum, bool);
                if (featNum == -3) { Preferences.isExpanded = bool; scrollView.setLayoutParams(bool ? scrlLLExpanded : scrlLL); }
            }
        });
        linLayout.addView(switchR);
    }

    private void SeekBar(LinearLayout linLayout, final int featNum, final String featName, final int min, int max) {
        int loadedProg = Preferences.loadPrefInt(featName, featNum);
        LinearLayout linearLayout = new LinearLayout(getContext);
        linearLayout.setPadding(10, 5, 0, 5);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setGravity(Gravity.CENTER);
        final TextView textView = new TextView(getContext);
        String text = featName + ": <font color='" + NumberTxtColor + "'>" + ((loadedProg == 0) ? min : loadedProg) + "</font>";
        textView.setText(Html.fromHtml(text));
        textView.setTextColor(TEXT_COLOR_2);
        textView.setTypeface(currentTypeface != null ? currentTypeface : Typeface.MONOSPACE);
        SeekBar seekBar = new SeekBar(getContext);
        seekBar.setPadding(25, 10, 35, 10);
        seekBar.setMax(max);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) seekBar.setMin(min);
        seekBar.setProgress((loadedProg == 0) ? min : loadedProg);
        seekBar.getThumb().setColorFilter(SeekBarColor, PorterDuff.Mode.SRC_ATOP);
        seekBar.getProgressDrawable().setColorFilter(SeekBarProgressColor, PorterDuff.Mode.SRC_ATOP);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                int val = i < min ? min : i;
                seekBar.setProgress(val);
                Preferences.changeFeatureInt(featName, featNum, val);
                String txt = featName + ": <font color='" + NumberTxtColor + "'>" + val + "</font>";
                textView.setText(Html.fromHtml(txt));
            }
            public void onStartTrackingTouch(SeekBar seekBar) {}
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
        linearLayout.addView(textView);
        linearLayout.addView(seekBar);
        linLayout.addView(linearLayout);
    }

    private void Button(LinearLayout linLayout, final int featNum, final String featName) {
        final Button button = new Button(getContext);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT);
        layoutParams.setMargins(7, 5, 7, 5);
        button.setLayoutParams(layoutParams);
        button.setTextColor(TEXT_COLOR_2);
        button.setAllCaps(false);
        button.setText(Html.fromHtml(featName));
        button.setBackgroundColor(BG_BTN_COLOR);
        button.setTypeface(currentTypeface != null ? currentTypeface : Typeface.MONOSPACE);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (featNum == -6) { scrollView.removeAllViews(); scrollView.addView(mods); }
                else if (featNum == -100) stopChecking = true;
                Preferences.changeFeatureInt(featName, featNum, 0);
            }
        });
        linLayout.addView(button);
    }

    private void ButtonLink(LinearLayout linLayout, final String featName, final String url) {
        final Button button = new Button(getContext);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT);
        layoutParams.setMargins(7, 5, 7, 5);
        button.setLayoutParams(layoutParams);
        button.setAllCaps(false);
        button.setTextColor(TEXT_COLOR_2);
        button.setText(Html.fromHtml(featName));
        button.setBackgroundColor(BG_BTN_COLOR);
        button.setTypeface(currentTypeface != null ? currentTypeface : Typeface.MONOSPACE);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setData(Uri.parse(url));
                getContext.startActivity(intent);
            }
        });
        linLayout.addView(button);
    }

    private void ButtonOnOff(LinearLayout linLayout, final int featNum, String featName, boolean switchedOn) {
        final Button button = new Button(getContext);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT);
        layoutParams.setMargins(7, 5, 7, 5);
        button.setLayoutParams(layoutParams);
        button.setTextColor(TEXT_COLOR_2);
        button.setAllCaps(false);
        button.setTypeface(currentTypeface != null ? currentTypeface : Typeface.MONOSPACE);
        final String finalfeatName = featName.replace("OnOff_", "");
        boolean isOn = Preferences.loadPrefBool(featName, featNum, switchedOn);
        if (isOn) { button.setText(Html.fromHtml(finalfeatName + ": [ON]")); button.setBackgroundColor(BUTTON_ON_COLOR); isOn = false; }
        else { button.setText(Html.fromHtml(finalfeatName + ": [OFF]")); button.setBackgroundColor(BG_OFF_COLOR); isOn = true; }
        final boolean finalIsOn = isOn;
        button.setOnClickListener(new View.OnClickListener() {
            boolean isOn = finalIsOn;
            public void onClick(View v) {
                Preferences.changeFeatureBool(finalfeatName, featNum, isOn);
                if (isOn) { button.setText(Html.fromHtml(finalfeatName + ": [ON]")); button.setBackgroundColor(BUTTON_ON_COLOR); isOn = false; }
                else { button.setText(Html.fromHtml(finalfeatName + ": [OFF]")); button.setBackgroundColor(BG_OFF_COLOR); isOn = true; }
            }
        });
        linLayout.addView(button);
    }

    private void ButtonOnOffV2(LinearLayout linLayout, final int featNum, String featName, boolean switchedOn) {
        final Button button = new Button(getContext);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT);
        layoutParams.setMargins(7, 5, 7, 5);
        button.setLayoutParams(layoutParams);
        button.setTextColor(TEXT_COLOR_2);
        button.setAllCaps(false);
        button.setTypeface(currentTypeface != null ? currentTypeface : Typeface.MONOSPACE);
        final String finalfeatName = featName.replace("OnOffV2_", "");
        button.setText(Html.fromHtml(finalfeatName + ": [OFF]"));
        button.setBackgroundColor(BG_OFF_COLOR);
        button.setOnClickListener(new View.OnClickListener() {
            private boolean isProcessing = false;
            @Override public void onClick(View v) {
                if (isProcessing) return;
                isProcessing = true;
                button.setText(Html.fromHtml(finalfeatName + ": [ON]"));
                button.setBackgroundColor(BUTTON_ON_COLOR);
                Preferences.changeFeatureBool(finalfeatName, featNum, true);
                new Handler().postDelayed(new Runnable() {
                    @Override public void run() {
                        button.setText(Html.fromHtml(finalfeatName + ": [OFF]"));
                        button.setBackgroundColor(BG_OFF_COLOR);
                        Preferences.changeFeatureBool(finalfeatName, featNum, false);
                        isProcessing = false;
                    }
                }, 300);
            }
        });
        linLayout.addView(button);
    }

    private void Spinner(LinearLayout linLayout, final int featNum, final String featName, final String list) {
        final List<String> lists = new LinkedList<String>(Arrays.asList(list.split(",")));
        LinearLayout linearLayout2 = new LinearLayout(getContext);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT);
        layoutParams2.setMargins(7, 2, 7, 2);
        linearLayout2.setOrientation(LinearLayout.VERTICAL);
        linearLayout2.setBackgroundColor(BG_BTN_COLOR);
        linearLayout2.setLayoutParams(layoutParams2);
        final Spinner spinner = new Spinner(getContext, Spinner.MODE_DROPDOWN);
        spinner.setLayoutParams(layoutParams2);
        spinner.getBackground().setColorFilter(1, PorterDuff.Mode.SRC_ATOP);
        ArrayAdapter<String> aa = new ArrayAdapter<String>(getContext, android.R.layout.simple_spinner_dropdown_item, lists) {
            @Override public View getView(int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);
                if (v instanceof TextView) { ((TextView) v).setTypeface(currentTypeface != null ? currentTypeface : Typeface.MONOSPACE); ((TextView) v).setTextColor(TEXT_COLOR_2); }
                return v;
            }
            @Override public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View v = super.getDropDownView(position, convertView, parent);
                if (v instanceof TextView) { ((TextView) v).setTypeface(currentTypeface != null ? currentTypeface : Typeface.MONOSPACE); ((TextView) v).setTextColor(TEXT_COLOR_2); }
                return v;
            }
        };
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(aa);
        spinner.setSelection(Preferences.loadPrefInt(featName, featNum));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                Preferences.changeFeatureInt(spinner.getSelectedItem().toString(), featNum, position);
                if (selectedItemView instanceof TextView) ((TextView) selectedItemView).setTextColor(TEXT_COLOR_2);
            }
            @Override public void onNothingSelected(AdapterView<?> parent) {}
        });
        linearLayout2.addView(spinner);
        linLayout.addView(linearLayout2);
    }

    private void InputNum(LinearLayout linLayout, final int featNum, final String featName, final int maxValue) {
        LinearLayout linearLayout = new LinearLayout(getContext);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT);
        layoutParams.setMargins(7, 5, 7, 5);
        final Button button = new Button(getContext);
        int num = Preferences.loadPrefInt(featName, featNum);
        String txt = featName + ": <font color='" + NumberTxtColor + "'>" + num + "</font>";
        button.setText(Html.fromHtml(txt));
        button.setAllCaps(false);
        button.setLayoutParams(layoutParams);
        button.setBackgroundColor(BG_BTN_COLOR);
        button.setTextColor(TEXT_COLOR_2);
        button.setTypeface(currentTypeface != null ? currentTypeface : Typeface.MONOSPACE);
        button.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                AlertDialog.Builder alertName = new AlertDialog.Builder(getContext);
                final EditText editText = new EditText(getContext);
                if (maxValue != 0) editText.setHint("Max: " + maxValue);
                editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                editText.setKeyListener(DigitsKeyListener.getInstance("0123456789-"));
                editText.setTextColor(TEXT_COLOR_2);
                editText.setTypeface(currentTypeface != null ? currentTypeface : Typeface.MONOSPACE);
                InputFilter[] FilterArray = new InputFilter[1];
                FilterArray[0] = new InputFilter.LengthFilter(10);
                editText.setFilters(FilterArray);
                editText.requestFocus();
                alertName.setTitle("INPUT NUMBER");
                LinearLayout layoutName = new LinearLayout(getContext);
                layoutName.setOrientation(LinearLayout.VERTICAL);
                layoutName.addView(editText);
                alertName.setView(layoutName);
                alertName.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        int num;
                        try {
                            String inp = editText.getText().toString();
                            num = Integer.parseInt(inp.isEmpty() ? "0" : inp);
                            if (maxValue != 0 && num >= maxValue) num = maxValue;
                        } catch (NumberFormatException ex) { num = (maxValue != 0) ? maxValue : Integer.MAX_VALUE; }
                        String txt2 = featName + ": <font color='" + NumberTxtColor + "'>" + num + "</font>";
                        button.setText(Html.fromHtml(txt2));
                        Preferences.changeFeatureInt(featName, featNum, num);
                        editText.setFocusable(false);
                    }
                });
                alertName.setNegativeButton("Cancel", null);
                if (overlayRequired) {
                    AlertDialog dialog = alertName.create();
                    Objects.requireNonNull(dialog.getWindow()).setType(Build.VERSION.SDK_INT >= 26 ? 2038 : 2002);
                    dialog.show();
                } else alertName.show();
            }
        });
        linearLayout.addView(button);
        linLayout.addView(linearLayout);
    }

    private void InputLNum(LinearLayout linLayout, final int featNum, final String featName, final long maxValue) {
        LinearLayout linearLayout = new LinearLayout(getContext);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT);
        layoutParams.setMargins(7, 5, 7, 5);
        final Button button = new Button(getContext);
        long num = Preferences.loadPrefLong(featName, featNum);
        String txt = featName + ": <font color='" + NumberTxtColor + "'>" + num + "</font>";
        button.setText(Html.fromHtml(txt));
        button.setAllCaps(false);
        button.setLayoutParams(layoutParams);
        button.setBackgroundColor(BG_BTN_COLOR);
        button.setTextColor(TEXT_COLOR_2);
        button.setTypeface(currentTypeface != null ? currentTypeface : Typeface.MONOSPACE);
        button.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                AlertDialog.Builder alertName = new AlertDialog.Builder(getContext);
                final EditText editText = new EditText(getContext);
                if (maxValue != 0) editText.setHint("Max: " + maxValue);
                editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                editText.setKeyListener(DigitsKeyListener.getInstance("0123456789-"));
                editText.setTextColor(TEXT_COLOR_2);
                editText.setTypeface(currentTypeface != null ? currentTypeface : Typeface.MONOSPACE);
                InputFilter[] FilterArray = new InputFilter[1];
                FilterArray[0] = new InputFilter.LengthFilter(20);
                editText.setFilters(FilterArray);
                editText.requestFocus();
                alertName.setTitle("INPUT NUMBER");
                LinearLayout layoutName = new LinearLayout(getContext);
                layoutName.setOrientation(LinearLayout.VERTICAL);
                layoutName.addView(editText);
                alertName.setView(layoutName);
                alertName.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        long num;
                        try {
                            String inp = editText.getText().toString();
                            num = Long.parseLong(inp.isEmpty() ? "0" : inp);
                            if (maxValue != 0 && num >= maxValue) num = maxValue;
                        } catch (NumberFormatException ex) { num = (maxValue != 0) ? maxValue : Long.MAX_VALUE; }
                        String txt2 = featName + ": <font color='" + NumberTxtColor + "'>" + num + "</font>";
                        button.setText(Html.fromHtml(txt2));
                        Preferences.changeFeatureLong(featName, featNum, num);
                        editText.setFocusable(false);
                    }
                });
                alertName.setNegativeButton("Cancel", null);
                if (overlayRequired) {
                    AlertDialog dialog = alertName.create();
                    Objects.requireNonNull(dialog.getWindow()).setType(Build.VERSION.SDK_INT >= 26 ? 2038 : 2002);
                    dialog.show();
                } else alertName.show();
            }
        });
        linearLayout.addView(button);
        linLayout.addView(linearLayout);
    }

    private void InputText(LinearLayout linLayout, final int featNum, final String featName) {
        LinearLayout linearLayout = new LinearLayout(getContext);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT);
        layoutParams.setMargins(7, 5, 7, 5);
        final Button button = new Button(getContext);
        String string = Preferences.loadPrefString(featName, featNum);
        String txt = featName + ": <font color='" + NumberTxtColor + "'>" + string + "</font>";
        button.setText(Html.fromHtml(txt));
        button.setAllCaps(false);
        button.setLayoutParams(layoutParams);
        button.setBackgroundColor(BG_BTN_COLOR);
        button.setTextColor(TEXT_COLOR_2);
        button.setTypeface(currentTypeface != null ? currentTypeface : Typeface.MONOSPACE);
        button.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                AlertDialog.Builder alertName = new AlertDialog.Builder(getContext);
                final EditText editText = new EditText(getContext);
                editText.setTextColor(TEXT_COLOR_2);
                editText.setTypeface(currentTypeface != null ? currentTypeface : Typeface.MONOSPACE);
                editText.requestFocus();
                alertName.setTitle("INPUT TEXT");
                LinearLayout layoutName = new LinearLayout(getContext);
                layoutName.setOrientation(LinearLayout.VERTICAL);
                layoutName.addView(editText);
                alertName.setView(layoutName);
                alertName.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String str = editText.getText().toString();
                        String txt2 = featName + ": <font color='" + NumberTxtColor + "'>" + str + "</font>";
                        button.setText(Html.fromHtml(txt2));
                        Preferences.changeFeatureString(featName, featNum, str);
                        editText.setFocusable(false);
                    }
                });
                alertName.setNegativeButton("Cancel", null);
                if (overlayRequired) {
                    AlertDialog dialog = alertName.create();
                    Objects.requireNonNull(dialog.getWindow()).setType(Build.VERSION.SDK_INT >= 26 ? 2038 : 2002);
                    dialog.show();
                } else alertName.show();
            }
        });
        linearLayout.addView(button);
        linLayout.addView(linearLayout);
    }

    private void CheckBox(LinearLayout linLayout, final int featNum, final String featName, boolean switchedOn) {
        final CheckBox checkBox = new CheckBox(getContext);
        checkBox.setText(featName);
        checkBox.setTextColor(TEXT_COLOR_2);
        checkBox.setTypeface(currentTypeface != null ? currentTypeface : Typeface.MONOSPACE);
        checkBox.setButtonTintList(ColorStateList.valueOf(CheckBoxColor));
        checkBox.setChecked(Preferences.loadPrefBool(featName, featNum, switchedOn));
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Preferences.changeFeatureBool(featName, featNum, isChecked);
            }
        });
        linLayout.addView(checkBox);
    }

    @SuppressLint("SetTextI18n")
    private void RadioButton(LinearLayout linLayout, final int featNum, final String featName, final String list) {
        final List<String> lists = new LinkedList<String>(Arrays.asList(list.split(",")));
        final TextView textView = new TextView(getContext);
        textView.setText(featName + ":");
        textView.setTextColor(TEXT_COLOR_2);
        textView.setTypeface(currentTypeface != null ? currentTypeface : Typeface.MONOSPACE);
        final RadioGroup radioGroup = new RadioGroup(getContext);
        radioGroup.setPadding(10, 5, 10, 5);
        radioGroup.setOrientation(LinearLayout.VERTICAL);
        radioGroup.addView(textView);
        final RadioButton[] radioButtons = new RadioButton[lists.size()];
        for (int i = 0; i < lists.size(); i++) {
            final int index = i;
            final String radioName = lists.get(i);
            radioButtons[i] = new RadioButton(getContext);
            radioButtons[i].setText(radioName);
            radioButtons[i].setTextColor(TEXT_COLOR_2);
            radioButtons[i].setTypeface(currentTypeface != null ? currentTypeface : Typeface.MONOSPACE);
            radioButtons[i].setButtonTintList(ColorStateList.valueOf(RadioColor));
            radioButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    String txt = featName + ": <font color='" + NumberTxtColor + "'>" + radioName + "</font>";
                    textView.setText(Html.fromHtml(txt));
                    Preferences.changeFeatureInt(featName, featNum, index);
                }
            });
            radioGroup.addView(radioButtons[i]);
        }
        int savedIndex = Preferences.loadPrefInt(featName, featNum);
        if (savedIndex >= 0 && savedIndex < radioButtons.length) {
            String txt = featName + ": <font color='" + NumberTxtColor + "'>" + lists.get(savedIndex) + "</font>";
            textView.setText(Html.fromHtml(txt));
            radioButtons[savedIndex].setChecked(true);
        }
        linLayout.addView(radioGroup);
    }

    @SuppressLint("SetTextI18n")
    private void Collapse(LinearLayout linLayout, final String text, final boolean expanded) {
        LinearLayout.LayoutParams layoutParamsLL = new LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT);
        layoutParamsLL.setMargins(0, 5, 0, 0);
        LinearLayout collapse = new LinearLayout(getContext);
        collapse.setLayoutParams(layoutParamsLL);
        collapse.setVerticalGravity(16);
        collapse.setOrientation(LinearLayout.VERTICAL);
        final LinearLayout collapseSub = new LinearLayout(getContext);
        collapseSub.setVerticalGravity(16);
        collapseSub.setPadding(0, 5, 0, 5);
        collapseSub.setOrientation(LinearLayout.VERTICAL);
        collapseSub.setBackgroundColor(BG_COLLAPSE_COLOR);
        collapseSub.setVisibility(View.GONE);
        mCollapse = collapseSub;
        final TextView textView = new TextView(getContext);
        textView.setBackgroundColor(BG_COLLAPSE_COLOR);
        textView.setText("▽ " + text + " ▽");
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(COLLAPSE_TEXT_COLOR);
        textView.setTypeface(currentTypeface != null ? currentTypeface : Typeface.MONOSPACE);
        textView.setPadding(0, 20, 0, 20);
        if (expanded) { collapseSub.setVisibility(View.VISIBLE); textView.setText("△ " + text + " △"); }
        textView.setOnClickListener(new View.OnClickListener() {
            boolean isChecked = expanded;
            @Override public void onClick(View v) {
                boolean z = !isChecked;
                isChecked = z;
                if (z) { collapseSub.setVisibility(View.VISIBLE); textView.setText("△ " + text + " △"); }
                else { collapseSub.setVisibility(View.GONE); textView.setText("▽ " + text + " ▽"); }
            }
        });
        collapse.addView(textView);
        collapse.addView(collapseSub);
        linLayout.addView(collapse);
    }

    private void Category(LinearLayout linLayout, String text) {
        TextView textView = new TextView(getContext);
        textView.setBackgroundColor(BG_CATEGORY_COLOR);
        textView.setText(">> " + text + " <<");
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(TEXT_COLOR_2);
        textView.setTypeface(currentTypeface != null ? currentTypeface : Typeface.MONOSPACE);
        textView.setPadding(0, 5, 0, 5);
        linLayout.addView(textView);
    }

    private void TextView(LinearLayout linLayout, String text) {
        TextView textView = new TextView(getContext);
        textView.setText(Html.fromHtml(text));
        textView.setTextColor(TEXT_COLOR_2);
        textView.setTypeface(currentTypeface != null ? currentTypeface : Typeface.MONOSPACE);
        textView.setPadding(10, 5, 10, 5);
        linLayout.addView(textView);
    }

    private void WebTextView(LinearLayout linLayout, String text) {
        WebView wView = new WebView(getContext);
        wView.loadData(text, "text/html", "utf-8");
        wView.setBackgroundColor(0x00000000);
        wView.setPadding(0, 5, 0, 5);
        wView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        linLayout.addView(wView);
    }

    // ============================================================
    // HELPERS
    // ============================================================
    private boolean isViewCollapsed() {
        return rootFrame == null || mCollapsed.getVisibility() == View.VISIBLE;
    }

    private int dp(int i) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, (float) i, getContext.getResources().getDisplayMetrics());
    }

    public void setVisibility(int view) {
        if (rootFrame != null) rootFrame.setVisibility(view);
    }

    // ============================================================
    // WARNA CUSTOM: sekali klik, semua warna aksen + teks berubah
    // ============================================================
    private void applyCustomColor(int color, boolean save) {
        customColorActive = true;
        color = color | 0xFF000000;
        String hex = String.format(Locale.US, "#%06X", 0xFFFFFF & color);
        THEME_COLOR = color;
        ToggleON = color;
        SeekBarColor = color;
        SeekBarProgressColor = color;
        CheckBoxColor = color;
        RadioColor = color;
        NumberTxtColor = hex;
        MENU_BORDER_COLOR = color;
        TEXT_COLOR = color;
        TEXT_COLOR_2 = color;
        COLLAPSE_TEXT_COLOR = color;
        if (logoZBtn != null) {
            GradientDrawable circle = new GradientDrawable();
            circle.setShape(GradientDrawable.OVAL);
            circle.setColor(color);
            circle.setStroke(2, color);
            logoZBtn.setBackground(circle);
        }
        if (gearBtn != null) {
            GradientDrawable gb = new GradientDrawable();
            gb.setShape(GradientDrawable.OVAL);
            gb.setColor(color);
            gb.setStroke(2, Color.WHITE);
            gearBtn.setBackground(gb);
            gearBtn.setTextColor(Color.WHITE);
        }
        if (settingsIcon != null) {
            GradientDrawable sb = new GradientDrawable();
            sb.setShape(GradientDrawable.OVAL);
            sb.setColor(color);
            sb.setStroke(2, Color.WHITE);
            settingsIcon.setBackground(sb);
            settingsIcon.setTextColor(Color.WHITE);
        }
        refreshAllUI();
        for (int i = 0; i < MEDIA_SLOTS; i++) if (mediaStatus[i] != null) mediaStatus[i].setTextColor(color);
        if (ytStatus != null) ytStatus.setTextColor(color);
        if (save) prefs.edit().putBoolean("custom_on", true).putInt("custom_color", color).apply();
    }

    private void resetCustomColor() {
        customColorActive = false;
        prefs.edit().putBoolean("custom_on", false).apply();
        int t = Preferences.loadPrefInt("ThemeColor", -200);
        int x = Preferences.loadPrefInt("TextColor", -204);
        applyThemeColor(t >= 0 && t < colorHexs.length ? t : 0);
        applyTextColor(x >= 0 && x < colorHexs.length ? x : 0);
        refreshAllUI();
        Toast.makeText(getContext, "Warna balik ke pilihan spinner", Toast.LENGTH_SHORT).show();
    }

    private void showHexDialog() {
        try {
            AlertDialog.Builder b = new AlertDialog.Builder(getContext);
            final EditText et = new EditText(getContext);
            et.setHint("#00FF41");
            et.setSingleLine(true);
            et.setFilters(new InputFilter[]{ new InputFilter.LengthFilter(7) });
            et.setText(String.format(Locale.US, "#%06X", 0xFFFFFF & THEME_COLOR));
            et.setTextColor(TEXT_COLOR_2);
            et.setTypeface(currentTypeface != null ? currentTypeface : Typeface.MONOSPACE);
            LinearLayout lay = new LinearLayout(getContext);
            lay.setOrientation(LinearLayout.VERTICAL);
            lay.setPadding(dp(16), dp(8), dp(16), 0);
            lay.addView(et);
            b.setTitle("WARNA CUSTOM (HEX)");
            b.setView(lay);
            b.setPositiveButton("TERAPKAN", new DialogInterface.OnClickListener() {
                @Override public void onClick(DialogInterface d, int w) {
                    try {
                        String s = et.getText().toString().trim();
                        if (!s.startsWith("#")) s = "#" + s;
                        int c = Color.parseColor(s);
                        if (Color.red(c) + Color.green(c) + Color.blue(c) < 90) {
                            Toast.makeText(getContext, "Terlalu gelap, teks nggak kebaca", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        applyCustomColor(c, true);
                    } catch (Exception e) {
                        Toast.makeText(getContext, "Format HEX salah (contoh #FF00AA)", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            b.setNegativeButton("Batal", null);
            if (overlayRequired) {
                AlertDialog dialog = b.create();
                Objects.requireNonNull(dialog.getWindow()).setType(Build.VERSION.SDK_INT >= 26 ? 2038 : 2002);
                dialog.show();
            } else b.show();
        } catch (Exception e) { Log.e(TAG, "Hex dialog error: " + e.getMessage()); }
    }

    // ============================================================
    // GIF / GAMBAR MELAYANG: 5 SLOT (upload, disimpen otomatis, bisa digeser)
    // ============================================================
    private Button makeMediaButton(String text, View.OnClickListener l) {
        Button b = new Button(getContext);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT);
        lp.setMargins(7, 5, 7, 5);
        b.setLayoutParams(lp);
        b.setText(text);
        b.setTextColor(TEXT_COLOR_2);
        b.setBackgroundColor(BTN_COLOR);
        b.setTypeface(currentTypeface != null ? currentTypeface : Typeface.MONOSPACE);
        b.setOnClickListener(l);
        return b;
    }

    private Button makeRowButton(String text, View.OnClickListener l) {
        Button b = new Button(getContext);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0, WRAP_CONTENT, 1f);
        lp.setMargins(7, 3, 7, 3);
        b.setLayoutParams(lp);
        b.setText(text);
        b.setTextColor(TEXT_COLOR_2);
        b.setBackgroundColor(BTN_COLOR);
        b.setTypeface(currentTypeface != null ? currentTypeface : Typeface.MONOSPACE);
        b.setOnClickListener(l);
        return b;
    }

    private File getMediaFile(int slot) {
        String ext = prefs.getString("media_ext_" + slot, "");
        if (ext.isEmpty()) return null;
        File f = new File(new File(getContext.getFilesDir(), MEDIA_DIR), "overlay" + slot + "." + ext);
        return f.exists() ? f : null;
    }

    private void updateMediaStatus() {
        for (int i = 0; i < MEDIA_SLOTS; i++) {
            if (mediaStatus[i] == null) continue;
            File f = getMediaFile(i);
            mediaStatus[i].setText("SLOT " + (i + 1) + ": " + (f == null ? "kosong, tekan UPLOAD"
                    : f.getName() + " (" + (f.length() / 1024) + " KB)"));
        }
    }

    private void pickMedia(final int target) {
        mediaPickTarget = target;
        if (!(getContext instanceof Activity)) {
            Toast.makeText(getContext, "Upload butuh Activity (mode Activity)", Toast.LENGTH_LONG).show();
            return;
        }
        final Activity act = (Activity) getContext;
        mediaPickerOwner = this;
        act.runOnUiThread(new Runnable() {
            @Override public void run() {
                try {
                    if (rootFrame != null) rootFrame.setVisibility(View.GONE);
                    act.getFragmentManager().beginTransaction()
                            .add(new MediaPickerFragment(), "skyzen_media_picker")
                            .commitAllowingStateLoss();
                } catch (Exception e) {
                    if (rootFrame != null) rootFrame.setVisibility(View.VISIBLE);
                    Log.e(TAG, "Picker error: " + e.getMessage());
                }
            }
        });
    }

    // Fragment tanpa UI: nerima hasil picker tanpa perlu ubah manifest / Activity game
    public static class MediaPickerFragment extends android.app.Fragment {
        @Override public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            if (savedInstanceState != null) return;
            try {
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.setType("image/*");
                i.putExtra(Intent.EXTRA_MIME_TYPES, new String[]{"image/gif", "image/png", "image/jpeg"});
                i.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(Intent.createChooser(i, "Pilih GIF / PNG / JPG"), 7421);
            } catch (Exception e) { finishPicker(); }
        }
        @Override public void onActivityResult(int requestCode, int resultCode, Intent data) {
            if (requestCode == 7421) {
                Menu owner = mediaPickerOwner;
                if (owner != null) {
                    if (owner.rootFrame != null) owner.rootFrame.setVisibility(View.VISIBLE);
                    if (resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {
                        owner.saveAndShowMedia(owner.mediaPickTarget, data.getData());
                    }
                }
            }
            finishPicker();
        }
        private void finishPicker() {
            try { getFragmentManager().beginTransaction().remove(this).commitAllowingStateLoss(); } catch (Exception ignored) {}
        }
    }

    private void saveAndShowMedia(int slot, Uri uri) {
        try {
            String mime = getContext.getContentResolver().getType(uri);
            String ext;
            if (mime != null && mime.contains("gif")) ext = "gif";
            else if (mime != null && mime.contains("png")) ext = "png";
            else if (mime != null && (mime.contains("jpeg") || mime.contains("jpg"))) ext = "jpg";
            else {
                String s = uri.toString().toLowerCase(Locale.ROOT);
                ext = s.contains(".gif") ? "gif" : s.contains(".png") ? "png" : "jpg";
            }
            File dir = new File(getContext.getFilesDir(), MEDIA_DIR);
            if (!dir.exists()) dir.mkdirs();
            File[] old = dir.listFiles();
            if (old != null) for (File o : old) if (o.getName().startsWith("overlay" + slot + ".")) o.delete();
            File out = new File(dir, "overlay" + slot + "." + ext);
            InputStream in = getContext.getContentResolver().openInputStream(uri);
            if (in == null) throw new IOException("stream null");
            OutputStream os = new FileOutputStream(out);
            byte[] buf = new byte[16384];
            int n;
            while ((n = in.read(buf)) > 0) os.write(buf, 0, n);
            os.flush(); os.close(); in.close();
            prefs.edit().putString("media_ext_" + slot, ext).apply();
            updateMediaStatus();
            showMedia(slot);
            Toast.makeText(getContext, "Slot " + (slot + 1) + " tersimpan otomatis", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Log.e(TAG, "Save media error: " + e.getMessage());
            Toast.makeText(getContext, "Gagal simpan file", Toast.LENGTH_SHORT).show();
        }
    }

    private Bitmap decodeSampled(File f, int maxPx) {
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(f.getAbsolutePath(), o);
        int sample = 1;
        while (Math.max(o.outWidth, o.outHeight) / sample > maxPx) sample *= 2;
        o = new BitmapFactory.Options();
        o.inSampleSize = sample;
        return BitmapFactory.decodeFile(f.getAbsolutePath(), o);
    }

    @SuppressLint({"ClickableViewAccessibility", "SetJavaScriptEnabled"})
    private void showMedia(final int slot) {
        try {
            File f = getMediaFile(slot);
            if (f == null) { Toast.makeText(getContext, "Slot " + (slot + 1) + " masih kosong, upload dulu", Toast.LENGTH_SHORT).show(); return; }
            if (mWindowManager == null || vmParams == null) return;
            hideMedia(slot, false);
            String ext = prefs.getString("media_ext_" + slot, "");
            int size = dp(prefs.getInt("media_size_" + slot, 120));

            FrameLayout box = new FrameLayout(getContext);
            View content;
            if (ext.equals("gif") && Build.VERSION.SDK_INT < 28) {
                // HP lama: GIF lewat WebView
                WebView wv = new WebView(getContext);
                wv.setBackgroundColor(Color.TRANSPARENT);
                wv.getSettings().setAllowFileAccess(true);
                wv.loadDataWithBaseURL("file:///",
                        "<html><body style='margin:0;background:transparent'><img src='file://" + f.getAbsolutePath()
                                + "' style='width:100%;height:100%;object-fit:contain'></body></html>",
                        "text/html", "UTF-8", null);
                mediaWebView[slot] = wv;
                content = wv;
            } else {
                ImageView iv = new ImageView(getContext);
                iv.setScaleType(ImageView.ScaleType.FIT_CENTER);
                if (ext.equals("gif")) {
                    Drawable d = ImageDecoder.decodeDrawable(ImageDecoder.createSource(f),
                            new ImageDecoder.OnHeaderDecodedListener() {
                                @Override public void onHeaderDecoded(ImageDecoder dec, ImageDecoder.ImageInfo info, ImageDecoder.Source s) {
                                    int w = info.getSize().getWidth(), h = info.getSize().getHeight();
                                    int m = Math.max(w, h);
                                    if (m > 512) { float r = 512f / m; dec.setTargetSize((int) (w * r), (int) (h * r)); }
                                }
                            });
                    mediaDrawable[slot] = d;
                    iv.setImageDrawable(d);
                    if (d instanceof AnimatedImageDrawable) {
                        ((AnimatedImageDrawable) d).setRepeatCount(AnimatedImageDrawable.REPEAT_INFINITE);
                        ((AnimatedImageDrawable) d).start();
                    }
                } else {
                    iv.setImageBitmap(decodeSampled(f, 1024));
                }
                content = iv;
            }
            box.addView(content, new FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT));

            // lapisan transparan di atas buat nangkep sentuhan -> geser
            View touch = new View(getContext);
            touch.setOnTouchListener(new View.OnTouchListener() {
                private float tx, ty; private int ix, iy;
                @Override public boolean onTouch(View v, MotionEvent e) {
                    WindowManager.LayoutParams lp = mediaParams[slot];
                    if (lp == null || mWindowManager == null || mediaFloat[slot] == null) return false;
                    switch (e.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            ix = lp.x; iy = lp.y; tx = e.getRawX(); ty = e.getRawY();
                            return true;
                        case MotionEvent.ACTION_MOVE:
                            lp.x = ix + (int) (e.getRawX() - tx);
                            lp.y = iy + (int) (e.getRawY() - ty);
                            try { mWindowManager.updateViewLayout(mediaFloat[slot], lp); } catch (Exception ignored) {}
                            return true;
                        case MotionEvent.ACTION_UP:
                            prefs.edit().putInt("media_x_" + slot, lp.x).putInt("media_y_" + slot, lp.y).apply();
                            return true;
                    }
                    return false;
                }
            });
            box.addView(touch, new FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT));

            WindowManager.LayoutParams lp = new WindowManager.LayoutParams(size, size, vmParams.type,
                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                            | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                            | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
                    PixelFormat.TRANSLUCENT);
            lp.gravity = Gravity.TOP | Gravity.LEFT;
            lp.x = prefs.getInt("media_x_" + slot, dp(20 + slot * 30));
            lp.y = prefs.getInt("media_y_" + slot, dp(200 + slot * 30));
            mediaParams[slot] = lp;
            mediaFloat[slot] = box;
            mWindowManager.addView(box, lp);
            prefs.edit().putBoolean("media_show_" + slot, true).apply();
        } catch (Exception e) {
            Log.e(TAG, "Show media error: " + e.getMessage());
            Toast.makeText(getContext, "Gagal nampilin slot " + (slot + 1), Toast.LENGTH_SHORT).show();
        }
    }

    private void hideMedia(int slot, boolean remember) {
        try {
            if (Build.VERSION.SDK_INT >= 28 && mediaDrawable[slot] instanceof AnimatedImageDrawable)
                ((AnimatedImageDrawable) mediaDrawable[slot]).stop();
        } catch (Exception ignored) {}
        mediaDrawable[slot] = null;
        if (mediaWebView[slot] != null) {
            try { mediaWebView[slot].loadUrl("about:blank"); mediaWebView[slot].destroy(); } catch (Exception ignored) {}
            mediaWebView[slot] = null;
        }
        if (mediaFloat[slot] != null && mWindowManager != null) {
            try { mWindowManager.removeView(mediaFloat[slot]); } catch (Exception ignored) {}
        }
        mediaFloat[slot] = null;
        mediaParams[slot] = null;
        if (remember) prefs.edit().putBoolean("media_show_" + slot, false).apply();
    }

    private void hideAllMedia() {
        for (int i = 0; i < MEDIA_SLOTS; i++) hideMedia(i, false);
    }

    // Nampilin lagi otomatis pas menu dibuka (tunggu WindowManager siap)
    private void restoreMediaWhenReady(final int tries) {
        boolean any = false;
        for (int i = 0; i < MEDIA_SLOTS; i++) if (prefs.getBoolean("media_show_" + i, false) && getMediaFile(i) != null) any = true;
        if (!any) return;
        new Handler().postDelayed(new Runnable() {
            @Override public void run() {
                if (mWindowManager != null && vmParams != null) {
                    for (int i = 0; i < MEDIA_SLOTS; i++)
                        if (prefs.getBoolean("media_show_" + i, false) && getMediaFile(i) != null && mediaFloat[i] == null) showMedia(i);
                } else if (tries < 20) restoreMediaWhenReady(tries + 1);
            }
        }, 700);
    }

    // ============================================================
    // MUSIK YOUTUBE: player melayang (window sendiri), jadi tetap jalan
    // walau panel Z MENU / menu utama ditutup
    // ============================================================
    private String[] parseYoutube(String link) {
        try {
            link = link.trim();
            if (!link.startsWith("http")) link = "https://" + link;
            Uri u = Uri.parse(link);
            String host = u.getHost();
            if (host == null) return null;
            host = host.toLowerCase(Locale.ROOT);
            String vid = null;
            String list = u.getQueryParameter("list");
            List<String> seg = u.getPathSegments();
            if (host.contains("youtu.be")) {
                if (!seg.isEmpty()) vid = seg.get(0);
            } else if (host.contains("youtube.com") || host.contains("youtube-nocookie.com")) {
                vid = u.getQueryParameter("v");
                if (vid == null && seg.size() >= 2) {
                    String k = seg.get(0);
                    if (k.equals("shorts") || k.equals("embed") || k.equals("live") || k.equals("v")) vid = seg.get(1);
                }
            } else return null;
            if (vid != null && !vid.matches("[A-Za-z0-9_-]{6,15}")) vid = null;
            if (list != null && !list.matches("[A-Za-z0-9_-]{10,64}")) list = null;
            if (vid == null && list == null) return null;
            return new String[]{ vid == null ? "" : vid, list == null ? "" : list };
        } catch (Exception e) { return null; }
    }

    private void showYoutubeDialog() {
        try {
            AlertDialog.Builder b = new AlertDialog.Builder(getContext);
            final EditText et = new EditText(getContext);
            et.setHint("https://youtu.be/xxxxxxxxxxx");
            et.setSingleLine(true);
            et.setText(prefs.getString(KEY_YT_LAST, ""));
            et.setTextColor(TEXT_COLOR_2);
            et.setTypeface(currentTypeface != null ? currentTypeface : Typeface.MONOSPACE);
            Button paste = new Button(getContext);
            paste.setText("PASTE DARI CLIPBOARD");
            paste.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    try {
                        ClipboardManager cm = (ClipboardManager) getContext.getSystemService(Context.CLIPBOARD_SERVICE);
                        if (cm != null && cm.hasPrimaryClip() && cm.getPrimaryClip().getItemCount() > 0) {
                            CharSequence t = cm.getPrimaryClip().getItemAt(0).getText();
                            if (t != null) et.setText(t.toString());
                        }
                    } catch (Exception ignored) {}
                }
            });
            LinearLayout lay = new LinearLayout(getContext);
            lay.setOrientation(LinearLayout.VERTICAL);
            lay.setPadding(dp(16), dp(8), dp(16), 0);
            lay.addView(et);
            lay.addView(paste);
            b.setTitle("LINK YOUTUBE");
            b.setView(lay);
            b.setPositiveButton("PUTAR", new DialogInterface.OnClickListener() {
                @Override public void onClick(DialogInterface d, int w) { playYoutube(et.getText().toString()); }
            });
            b.setNegativeButton("Batal", null);
            if (overlayRequired) {
                AlertDialog dialog = b.create();
                Objects.requireNonNull(dialog.getWindow()).setType(Build.VERSION.SDK_INT >= 26 ? 2038 : 2002);
                dialog.show();
            } else b.show();
        } catch (Exception e) { Log.e(TAG, "YT dialog error: " + e.getMessage()); }
    }

    // Bikin window player melayang (sekali aja), bisa digeser lewat header
    @SuppressLint({"SetJavaScriptEnabled", "ClickableViewAccessibility"})
    private void ensureYoutubeWindow() {
        if (ytFloat != null) return;
        if (mWindowManager == null || vmParams == null) throw new IllegalStateException("window belum siap");
        LinearLayout box = new LinearLayout(getContext);
        box.setOrientation(LinearLayout.VERTICAL);
        GradientDrawable border = new GradientDrawable();
        border.setColor(Color.BLACK);
        border.setStroke(2, THEME_COLOR);
        box.setBackground(border);

        LinearLayout header = new LinearLayout(getContext);
        header.setOrientation(LinearLayout.HORIZONTAL);
        header.setGravity(Gravity.CENTER_VERTICAL);
        header.setBackgroundColor(0xEE0a0a0a);

        TextView dragHandle = new TextView(getContext);
        dragHandle.setText("♪ YOUTUBE  (geser di sini)");
        dragHandle.setTextColor(TEXT_COLOR_2);
        dragHandle.setTextSize(11f);
        dragHandle.setSingleLine(true);
        dragHandle.setPadding(dp(8), dp(6), dp(8), dp(6));
        dragHandle.setTypeface(currentTypeface != null ? currentTypeface : Typeface.MONOSPACE);
        ytDragHandle = dragHandle;
        header.addView(dragHandle, new LinearLayout.LayoutParams(0, WRAP_CONTENT, 1f));

        TextView eyeBtn = new TextView(getContext);
        eyeBtn.setText("👁");
        eyeBtn.setTextColor(Color.WHITE);
        eyeBtn.setTextSize(16f);
        eyeBtn.setPadding(dp(10), dp(4), dp(10), dp(4));
        eyeBtn.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { toggleYoutubeVideo(); } });
        header.addView(eyeBtn);

        TextView pauseBtn = new TextView(getContext);
        pauseBtn.setText("⏯");
        pauseBtn.setTextColor(Color.WHITE);
        pauseBtn.setTextSize(16f);
        pauseBtn.setPadding(dp(10), dp(4), dp(10), dp(4));
        pauseBtn.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { toggleYoutube(); } });
        header.addView(pauseBtn);

        TextView closeBtnYt = new TextView(getContext);
        closeBtnYt.setText("✕");
        closeBtnYt.setTextColor(Color.WHITE);
        closeBtnYt.setTextSize(16f);
        closeBtnYt.setPadding(dp(10), dp(4), dp(10), dp(4));
        closeBtnYt.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { stopYoutube(true); } });
        header.addView(closeBtnYt);

        dragHandle.setOnTouchListener(new View.OnTouchListener() {
            private float tx, ty; private int ix, iy;
            @Override public boolean onTouch(View v, MotionEvent e) {
                if (ytParams == null || ytFloat == null || mWindowManager == null) return false;
                switch (e.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        ix = ytParams.x; iy = ytParams.y; tx = e.getRawX(); ty = e.getRawY();
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        ytParams.x = ix + (int) (e.getRawX() - tx);
                        ytParams.y = iy + (int) (e.getRawY() - ty);
                        try { mWindowManager.updateViewLayout(ytFloat, ytParams); } catch (Exception ignored) {}
                        return true;
                    case MotionEvent.ACTION_UP:
                        prefs.edit().putInt("yt_x", ytParams.x).putInt("yt_y", ytParams.y).apply();
                        return true;
                }
                return false;
            }
        });
        box.addView(header, new LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT));

        WebView web = new WebView(getContext);
        web.setBackgroundColor(Color.BLACK);
        WebSettings ws = web.getSettings();
        ws.setJavaScriptEnabled(true);
        ws.setDomStorageEnabled(true);
        ws.setMediaPlaybackRequiresUserGesture(false);
        web.setWebChromeClient(new WebChromeClient());
        web.setWebViewClient(new WebViewClient());
        box.addView(web, new LinearLayout.LayoutParams(MATCH_PARENT, dp(135)));

        ytParams = new WindowManager.LayoutParams(dp(240), WRAP_CONTENT, vmParams.type,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                        | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                        | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
                PixelFormat.TRANSLUCENT);
        ytParams.gravity = Gravity.TOP | Gravity.LEFT;
        ytParams.x = prefs.getInt("yt_x", dp(20));
        ytParams.y = prefs.getInt("yt_y", dp(80));
        ytFloat = box;
        ytWebView = web;
        mWindowManager.addView(box, ytParams);
        ytVideoHidden = prefs.getBoolean("yt_hidden", false);
        applyYoutubeVideoVisibility();
    }

    private void playYoutube(String link) {
        String[] ids = parseYoutube(link);
        if (ids == null) { Toast.makeText(getContext, "Link YouTube nggak valid", Toast.LENGTH_SHORT).show(); return; }
        try {
            prefs.edit().putString(KEY_YT_LAST, link.trim()).apply();
            // matiin musik lokal
            if (mediaPlayer != null) {
                try { if (mediaPlayer.isPlaying()) mediaPlayer.stop(); mediaPlayer.release(); } catch (Exception ignored) {}
                mediaPlayer = null; isMusicPlaying = false;
            }
            ensureYoutubeWindow();
            String vid = ids[0], list = ids[1];
            // YouTube wajib tau "siapa yang nge-embed": origin berbasis package app (bukan youtube.com)
            String origin = "https://" + getContext.getPackageName().toLowerCase(Locale.ROOT).replaceAll("[^a-z0-9.-]", "-");
            StringBuilder pv = new StringBuilder("autoplay:1,playsinline:1,controls:1,rel:0,fs:0,loop:1");
            pv.append(",origin:'").append(origin).append("'");
            if (!list.isEmpty()) pv.append(",listType:'playlist',list:'").append(list).append("'");
            else pv.append(",playlist:'").append(vid).append("'");
            String html = "<html><head><meta name='viewport' content='width=device-width,initial-scale=1'>"
                    + "<style>html,body{margin:0;height:100%;background:#000}</style></head><body><div id='p'></div>"
                    + "<div id='err' style='position:absolute;top:0;left:0;right:0;z-index:9;color:#ff5555;font:12px monospace;padding:4px'></div><script>"
                    + "var player;var t=document.createElement('script');t.src='https://www.youtube.com/iframe_api';document.head.appendChild(t);"
                    + "function onYouTubeIframeAPIReady(){player=new YT.Player('p',{width:'100%',height:'100%',"
                    + (vid.isEmpty() ? "" : "videoId:'" + vid + "',")
                    + "playerVars:{" + pv + "},events:{onReady:function(e){e.target.playVideo();},"
                    + "onError:function(e){var m={2:'link/ID salah',5:'error player HTML5',100:'video dihapus/private',101:'pemilik larang diputar di luar YouTube',150:'pemilik larang diputar di luar YouTube',153:'referer/origin ditolak'};"
                    + "document.getElementById('err').innerText='Error '+e.data+': '+(m[e.data]||'tidak diketahui');}}});}"
                    + "</script></body></html>";
            ytWebView.loadDataWithBaseURL(origin, html, "text/html", "UTF-8", null);
            if (ytStatus != null) ytStatus.setText("Memutar: " + (vid.isEmpty() ? "playlist " + list : vid) + "\n(player melayang, bisa digeser)");
        } catch (Exception e) {
            Log.e(TAG, "YT play error: " + e.getMessage());
            Toast.makeText(getContext, "Gagal muter YouTube", Toast.LENGTH_SHORT).show();
        }
    }

    // Sembunyiin layar video: player tetap jalan (suara nyala), cuma tampilannya diciutin jadi bar kecil
    private void applyYoutubeVideoVisibility() {
        if (ytWebView == null || ytFloat == null || ytParams == null) return;
        try {
            ViewGroup.LayoutParams wl = ytWebView.getLayoutParams();
            wl.height = ytVideoHidden ? 1 : dp(135);
            ytWebView.setLayoutParams(wl);
            ytParams.width = ytVideoHidden ? dp(190) : dp(240);
            if (ytDragHandle != null) ytDragHandle.setText(ytVideoHidden ? "♪ MUSIK (geser)" : "♪ YOUTUBE  (geser di sini)");
            if (mWindowManager != null) mWindowManager.updateViewLayout(ytFloat, ytParams);
        } catch (Exception e) { Log.e(TAG, "YT visibility error: " + e.getMessage()); }
    }

    private void toggleYoutubeVideo() {
        if (ytWebView == null) { Toast.makeText(getContext, "Belum ada yang diputar", Toast.LENGTH_SHORT).show(); return; }
        ytVideoHidden = !ytVideoHidden;
        prefs.edit().putBoolean("yt_hidden", ytVideoHidden).apply();
        applyYoutubeVideoVisibility();
    }

    private void toggleYoutube() {
        if (ytWebView == null) { Toast.makeText(getContext, "Belum ada yang diputar", Toast.LENGTH_SHORT).show(); return; }
        ytWebView.evaluateJavascript("if(player){player.getPlayerState()==1?player.pauseVideo():player.playVideo();}", null);
    }

    private void stopYoutube(boolean toast) {
        if (ytWebView == null && ytFloat == null) return;
        if (ytWebView != null) {
            try { ytWebView.loadUrl("about:blank"); } catch (Exception ignored) {}
        }
        if (ytFloat != null && mWindowManager != null) {
            try { mWindowManager.removeView(ytFloat); } catch (Exception ignored) {}
        }
        if (ytWebView != null) {
            try { ytWebView.destroy(); } catch (Exception ignored) {}
        }
        ytWebView = null;
        ytFloat = null;
        ytParams = null;
        ytDragHandle = null;
        if (ytStatus != null) ytStatus.setText("Tempel link YouTube lalu putar. Player melayang, tetap jalan walau menu ditutup.");
        if (toast) Toast.makeText(getContext, "YouTube dihentikan", Toast.LENGTH_SHORT).show();
    }

    // ============================================================
    // SUARA TOMBOL
    // ============================================================
    private void playClickSound() {
        if (soundMode < 0 || soundMode >= soundList.length - 1) return; // item terakhir = OFF
        try {
            if (soundMode == SOUND_SYSTEM_CLICK) {
                AudioManager am = (AudioManager) getContext.getSystemService(Context.AUDIO_SERVICE);
                if (am != null) am.playSoundEffect(AudioManager.FX_KEY_CLICK, 1.0f);
                return;
            }
            if (toneGen == null) toneGen = new ToneGenerator(AudioManager.STREAM_SYSTEM, 80);
            int tone, ms = 60;
            switch (soundMode) {
                case 1: tone = ToneGenerator.TONE_PROP_BEEP2; break;
                case 2: tone = ToneGenerator.TONE_PROP_PROMPT; break;
                case 3: tone = ToneGenerator.TONE_PROP_ACK; break;
                case 4: tone = ToneGenerator.TONE_DTMF_5; ms = 40; break;
                default: tone = ToneGenerator.TONE_PROP_BEEP; break;
            }
            toneGen.startTone(tone, ms);
        } catch (Exception e) {
            if (toneGen != null) { try { toneGen.release(); } catch (Exception ignored) {} }
            toneGen = null;
        }
    }

    // Cari view yang bisa diklik di koordinat layar (x, y); dipakai supaya suara cuma bunyi kalau memang kena tombol
    private View findClickableAt(View v, int x, int y) {
        if (v == null || v.getVisibility() != View.VISIBLE || v instanceof WebView) return null;
        int[] loc = new int[2];
        v.getLocationOnScreen(loc);
        if (x < loc[0] || x > loc[0] + v.getWidth() || y < loc[1] || y > loc[1] + v.getHeight()) return null;
        if (v instanceof ViewGroup && !(v instanceof Spinner)) {
            ViewGroup g = (ViewGroup) v;
            for (int i = g.getChildCount() - 1; i >= 0; i--) {
                View r = findClickableAt(g.getChildAt(i), x, y);
                if (r != null) return r;
            }
        }
        return (v.isClickable() && v.isEnabled()) ? v : null;
    }

    // ============================================================
    // DIALOG AMAN (otomatis pakai window overlay kalau menu jalan sebagai service)
    // ============================================================
    private void showSafeDialog(AlertDialog.Builder b) {
        try {
            if (overlayRequired) {
                AlertDialog d = b.create();
                Objects.requireNonNull(d.getWindow()).setType(Build.VERSION.SDK_INT >= 26 ? 2038 : 2002);
                d.show();
            } else b.show();
        } catch (Exception e) { Log.e(TAG, "Dialog error: " + e.getMessage()); }
    }

    // ============================================================
    // HAPUS SEMUA GIF / GAMBAR (5 slot)
    // ============================================================
    private void confirmDeleteAllMedia() {
        AlertDialog.Builder b = new AlertDialog.Builder(getContext);
        b.setTitle("HAPUS SEMUA GIF / GAMBAR");
        b.setMessage("Semua file di " + MEDIA_SLOTS + " slot akan dihapus permanen dan tidak bisa dikembalikan. Lanjut?");
        b.setPositiveButton("HAPUS SEMUA", new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface d, int w) { deleteAllMedia(); }
        });
        b.setNegativeButton("Batal", null);
        showSafeDialog(b);
    }

    private void deleteAllMedia() {
        try {
            hideAllMedia();
            int deleted = 0;
            File[] files = new File(getContext.getFilesDir(), MEDIA_DIR).listFiles();
            if (files != null) for (File f : files) if (f.delete()) deleted++;
            android.content.SharedPreferences.Editor ed = prefs.edit();
            for (int i = 0; i < MEDIA_SLOTS; i++) {
                ed.remove("media_ext_" + i).remove("media_show_" + i).remove("media_x_" + i).remove("media_y_" + i);
            }
            ed.apply();
            updateMediaStatus();
            Toast.makeText(getContext, deleted + " file dihapus, semua slot kosong", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e(TAG, "Delete all media error: " + e.getMessage());
            Toast.makeText(getContext, "Gagal menghapus sebagian file", Toast.LENGTH_SHORT).show();
        }
    }

    // ============================================================
    // SCAN AREA SEKITAR: daftar jaringan WiFi yang terdeteksi di sekitar (sinyal, band, keamanan, estimasi jarak)
    // Butuh izin: ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE, CHANGE_WIFI_STATE (+ Lokasi/GPS HP menyala)
    // ============================================================
    @SuppressWarnings("deprecation")
    private void scanNearbyArea() {
        try {
            if (Build.VERSION.SDK_INT >= 23
                    && getContext.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                if (getContext instanceof Activity) {
                    ((Activity) getContext).requestPermissions(new String[]{ Manifest.permission.ACCESS_FINE_LOCATION }, 7731);
                    Toast.makeText(getContext, "Izinkan akses lokasi, lalu tekan SCAN lagi", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getContext, "Izin lokasi belum diberikan (aktifkan di Pengaturan > Aplikasi)", Toast.LENGTH_LONG).show();
                }
                return;
            }
            final WifiManager wm = (WifiManager) getContext.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            if (wm == null || !wm.isWifiEnabled()) {
                Toast.makeText(getContext, "Nyalakan WiFi dulu untuk scan", Toast.LENGTH_SHORT).show();
                return;
            }
            Toast.makeText(getContext, "Scanning area sekitar...", Toast.LENGTH_SHORT).show();
            try { wm.startScan(); } catch (Exception ignored) {}
            new Handler().postDelayed(new Runnable() {
                @Override public void run() {
                    try { showScanResults(wm.getScanResults()); }
                    catch (SecurityException se) { Toast.makeText(getContext, "Izin lokasi ditolak", Toast.LENGTH_SHORT).show(); }
                }
            }, 2500);
        } catch (Exception e) {
            Log.e(TAG, "Scan error: " + e.getMessage());
            Toast.makeText(getContext, "Scan gagal", Toast.LENGTH_SHORT).show();
        }
    }

    private void showScanResults(List<ScanResult> raw) {
        if (raw == null || raw.isEmpty()) {
            Toast.makeText(getContext, "Tidak ada hasil. Pastikan Lokasi/GPS HP menyala, lalu coba lagi (Android membatasi scan beberapa kali per 2 menit).", Toast.LENGTH_LONG).show();
            return;
        }
        List<ScanResult> list = new ArrayList<ScanResult>(raw);
        Collections.sort(list, new Comparator<ScanResult>() {
            @Override public int compare(ScanResult a, ScanResult b) { return b.level - a.level; }
        });
        int open = 0;
        StringBuilder sb = new StringBuilder();
        for (ScanResult r : list) {
            String caps = r.capabilities == null ? "" : r.capabilities;
            String sec = caps.contains("WPA3") || caps.contains("SAE") ? "WPA3"
                    : caps.contains("WPA2") ? "WPA2" : caps.contains("WPA") ? "WPA"
                    : caps.contains("WEP") ? "WEP" : "OPEN";
            if (sec.equals("OPEN")) open++;
            String band = r.frequency >= 5925 ? "6 GHz" : r.frequency >= 4900 ? "5 GHz" : "2.4 GHz";
            int bars = r.level >= -50 ? 5 : r.level >= -60 ? 4 : r.level >= -70 ? 3 : r.level >= -80 ? 2 : 1;
            StringBuilder bar = new StringBuilder("[");
            for (int i = 0; i < 5; i++) bar.append(i < bars ? '#' : '.');
            bar.append(']');
            double dist = Math.pow(10.0, (27.55 - 20.0 * Math.log10(Math.max(r.frequency, 1)) + Math.abs(r.level)) / 20.0);
            String name = (r.SSID == null || r.SSID.isEmpty()) ? "<hidden>" : r.SSID;
            sb.append(name).append("\n  ").append(bar).append(' ').append(r.level).append(" dBm | ")
              .append(band).append(" | ").append(sec).append(" | ~")
              .append(String.format(Locale.US, "%.0f", dist)).append(" m\n\n");
        }
        TextView tv = new TextView(getContext);
        tv.setText("Terdeteksi: " + list.size() + " jaringan  (terbuka: " + open + ")\n"
                + "Jarak hanya estimasi kasar dari kekuatan sinyal.\n\n" + sb);
        tv.setTextColor(TEXT_COLOR_2);
        tv.setTextSize(12f);
        tv.setTypeface(currentTypeface != null ? currentTypeface : Typeface.MONOSPACE);
        tv.setPadding(dp(16), dp(8), dp(16), dp(8));
        ScrollView sv = new ScrollView(getContext);
        sv.setBackgroundColor(Color.parseColor("#0A0A0A"));
        sv.addView(tv);
        AlertDialog.Builder b = new AlertDialog.Builder(getContext);
        b.setTitle("SCAN AREA SEKITAR");
        b.setView(sv);
        b.setPositiveButton("SCAN ULANG", new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface d, int w) { scanNearbyArea(); }
        });
        b.setNegativeButton("Tutup", null);
        showSafeDialog(b);
    }

    // ============================================================
    // DESTROY
    // ============================================================
    public void onDestroy() {
        hideAllMedia();
        stopYoutube(false);
        if (mediaPlayer != null) { mediaPlayer.release(); mediaPlayer = null; }
        if (toneGen != null) { try { toneGen.release(); } catch (Exception ignored) {} toneGen = null; }
        if (effectHandler != null) effectHandler.removeCallbacksAndMessages(null);
        if (bgWebView != null) {
            try {
                bgWebView.loadUrl("about:blank");
                bgWebView.stopLoading();
                bgWebView.destroy();
            } catch (Exception e) { }
            bgWebView = null;
        }
        if (rootFrame != null && mWindowManager != null) {
            try { mWindowManager.removeView(rootFrame); } catch (Exception e) { }
        }
    }
}
