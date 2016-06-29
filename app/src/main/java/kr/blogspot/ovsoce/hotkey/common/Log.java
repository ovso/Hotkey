package kr.blogspot.ovsoce.hotkey.common;

import kr.blogspot.ovsoce.hotkey.BuildConfig;

public class Log {
	private final static String TAG = "HOTKEY";
	public static void d(String tag, String msg) {
		if(BuildConfig.DEBUG) android.util.Log.d(TAG,tag + " -> " + msg);
	}
	public static void e(String tag, String msg) {
		if(BuildConfig.DEBUG) android.util.Log.e(TAG, tag + " -> " + msg);
	}
	public static void w(String tag, String msg) {
		if(BuildConfig.DEBUG) android.util.Log.w(TAG, tag + " -> " + msg);
	}
	public static void i(String tag, String msg) {
		if(BuildConfig.DEBUG) android.util.Log.i(TAG,tag + " -> " + msg);
	}
	public static final void e(String message) {
		if (BuildConfig.DEBUG)Log.e(TAG, getBuildMessage(message));
	}
	public static final void w(String message) {
		if (BuildConfig.DEBUG) android.util.Log.w(TAG, getBuildMessage(message));
	}
	public static final void i(String message) {
		if (BuildConfig.DEBUG) android.util.Log.i(TAG, getBuildMessage(message));
	}
	public static final void d(String message) {
		if (BuildConfig.DEBUG) android.util.Log.d(TAG, getBuildMessage(message));
	}
	public static final void v(String message) {
		if (BuildConfig.DEBUG) android.util.Log.v(TAG, getBuildMessage(message));
	}
	private static String getBuildMessage(String msg) {
		StackTraceElement ste = Thread.currentThread().getStackTrace()[4];

		StringBuilder sb = new StringBuilder();
		sb.append(ste.getFileName().replace(".java", ""));
		sb.append(" -> ");
		sb.append(ste.getMethodName()+"()");
		sb.append(" ");
		sb.append(msg);

		return sb.toString();
	}
/*
	private boolean isDebuggable(Context context) {
		boolean debuggable = false;

		PackageManager pm = context.getPackageManager();
		try {
			ApplicationInfo appinfo = pm.getApplicationInfo(context.getPackageName(), 0);
			debuggable = (0 != (appinfo.flags & ApplicationInfo.FLAG_DEBUGGABLE));
		} catch (NameNotFoundException e) {

		}

		return debuggable;
	}
*/

}
