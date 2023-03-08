/*
 * Copyright (C) 2015 The CyanogenMod Project
 *               2017-2019 The LineageOS Project
 *               2020-2023 Paranoid Android
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.pixys.glyph.Utils;

import android.content.Context;
import android.content.Intent;
import android.os.UserHandle;
import android.util.Log;

import com.pixys.glyph.Constants.Constants;
import com.pixys.glyph.Manager.SettingsManager;
import com.pixys.glyph.Services.CallReceiverService;
import com.pixys.glyph.Services.ChargingService;
import com.pixys.glyph.Services.FlipToGlyphService;
import com.pixys.glyph.Services.NotificationService;
import com.pixys.glyph.Services.MusicVisualizerService;

public final class ServiceUtils {

    private static final String TAG = "GlyphServiceUtils";
    private static final boolean DEBUG = true;

    public static void startCallReceiverService(Context context) {
        if (DEBUG) Log.d(TAG, "Starting Glyph call receiver service");
        context.startServiceAsUser(new Intent(context, CallReceiverService.class),
                UserHandle.CURRENT);
    }

    protected static void stopCallReceiverService(Context context) {
        if (DEBUG) Log.d(TAG, "Stopping Glyph call receiver service");
        context.stopServiceAsUser(new Intent(context, CallReceiverService.class),
                UserHandle.CURRENT);
    }

    public static void startChargingService(Context context) {
        if (DEBUG) Log.d(TAG, "Starting Glyph charging service");
        context.startServiceAsUser(new Intent(context, ChargingService.class),
                UserHandle.CURRENT);
    }

    protected static void stopChargingService(Context context) {
        if (DEBUG) Log.d(TAG, "Stopping Glyph charging service");
        context.stopServiceAsUser(new Intent(context, ChargingService.class),
                UserHandle.CURRENT);
    }

    public static void startFlipToGlyphService(Context context) {
        if (DEBUG) Log.d(TAG, "Starting Flip to Glyph service");
        context.startServiceAsUser(new Intent(context, FlipToGlyphService.class),
                UserHandle.CURRENT);
    }

    protected static void stopFlipToGlyphService(Context context) {
        if (DEBUG) Log.d(TAG, "Stopping Flip to Glyph service");
        context.stopServiceAsUser(new Intent(context, FlipToGlyphService.class),
                UserHandle.CURRENT);
    }

    public static void startMusicVisualizerService(Context context) {
        if (DEBUG) Log.d(TAG, "Starting Music Visualizer service");
        context.startServiceAsUser(new Intent(context, MusicVisualizerService.class),
                UserHandle.CURRENT);
    }

    protected static void stopMusicVisualizerService(Context context) {
        if (DEBUG) Log.d(TAG, "Stopping Music Visualizer service");
        context.stopServiceAsUser(new Intent(context, MusicVisualizerService.class),
                UserHandle.CURRENT);
    }

    private static void startNotificationService(Context context) {
        if (DEBUG) Log.d(TAG, "Starting Glyph notifs service");
        context.startServiceAsUser(new Intent(context, NotificationService.class),
                UserHandle.CURRENT);
    }

    protected static void stopNotificationService(Context context) {
        if (DEBUG) Log.d(TAG, "Stopping Glyph notifs service");
        context.stopServiceAsUser(new Intent(context, NotificationService.class),
                UserHandle.CURRENT);
    }

    public static void checkGlyphService(Context context) {
        if (SettingsManager.isGlyphEnabled(context)) {
            Constants.setBrightness(SettingsManager.getGlyphBrightness(context));
            if (SettingsManager.isGlyphChargingEnabled(context)) {
                startChargingService(context);
            } else {
                stopChargingService(context);
            }
            if (SettingsManager.isGlyphCallEnabled(context)) {
                startCallReceiverService(context);
            } else {
                stopCallReceiverService(context);
            }
            if (SettingsManager.isGlyphNotifsEnabled(context)) {
                startNotificationService(context);
            } else {
                stopNotificationService(context);
            }
            if (SettingsManager.isGlyphFlipEnabled(context)) {
                startFlipToGlyphService(context);
            } else {
                stopFlipToGlyphService(context);
            }
            if (SettingsManager.isGlyphMusicVisualizerEnabled(context)) {
                startMusicVisualizerService(context);
            } else {
                stopMusicVisualizerService(context);
            }
        } else {
            stopChargingService(context);
            stopCallReceiverService(context);
            stopNotificationService(context);
            stopFlipToGlyphService(context);
            stopMusicVisualizerService(context);
        }
    }
}
