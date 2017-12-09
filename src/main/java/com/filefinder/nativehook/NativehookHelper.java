package com.filefinder.nativehook;

import org.jnativehook.GlobalScreen;
import org.jnativehook.keyboard.NativeKeyEvent;

public class NativehookHelper
{
    /**
     * Send the key combination to the os
     * @param modifiers (ex: NativeKeyEvent.ALT_L_MASK)
     * @param key (ex: NativeKeyEvent.VC_SPACE)
     */
    public static void keyPress(int modifiers, int key)
    {
        NativeKeyEvent nativeKeyEvent = new NativeKeyEvent(NativeKeyEvent.NATIVE_KEY_PRESSED,modifiers,0, key, NativeKeyEvent.CHAR_UNDEFINED);
        GlobalScreen.postNativeEvent(nativeKeyEvent);
    }
}
