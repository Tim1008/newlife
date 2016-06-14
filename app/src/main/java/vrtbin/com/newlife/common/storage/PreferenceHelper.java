package vrtbin.com.newlife.common.storage;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import vrtbin.com.newlife.config.GlobalConfig;

public class PreferenceHelper {
  private static final String MODULE_DEFAULT = "default";
  private SharedPreferences mSharedPreferences;

  private static PreferenceHelper sInstance = new PreferenceHelper();

  public static PreferenceHelper getInstance() {
    return sInstance;
  }

  private PreferenceHelper() {}

  private void ensureInit() {
    if (mSharedPreferences != null) {
      return;
    }
    mSharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(GlobalConfig.getAppContext());
  }

  public String getString(String key, String defValue) {
    return getString(MODULE_DEFAULT, key, defValue);
  }

  public String getString(String moduleName, String key, String defValue) {
    ensureInit();
    return mSharedPreferences.getString(moduleName + "_" + key, defValue);
  }

  public void saveString(String key, String value) {
    saveString(MODULE_DEFAULT, key, value);
  }

  public void saveString(String moduleName, String key, String value) {
    if (moduleName != null && key != null) {
      ensureInit();
      mSharedPreferences.edit().putString(moduleName + "_" + key, value).apply();
    }
  }

  public Set<String> getStringSet(String key, Set<String> defValues) {
    return getStringSet(MODULE_DEFAULT, key, defValues);
  }

  public Set<String> getStringSet(String moduleName, String key, Set<String> defValues) {
    ensureInit();
    return mSharedPreferences.getStringSet(moduleName + "_" + key, defValues);
  }

  public void saveStringSet(String moduleName, String key, Set<String> values) {
    if (moduleName != null && key != null) {
      ensureInit();
      mSharedPreferences.edit().putStringSet(moduleName + "_" + key, values).apply();
    }
  }

  public void saveStringSet(String key, Set<String> values) {
    saveStringSet(MODULE_DEFAULT, key, values);
  }

  public int getInt(String moduleName, String key, int defValue) {
    ensureInit();
    return mSharedPreferences.getInt(moduleName + "_" + key, defValue);
  }

  public int getInt(String key, int defValue) {
    return getInt(MODULE_DEFAULT, key, defValue);
  }

  public void saveInt(String moduleName, String key, int value) {
    if (moduleName != null && key != null) {
      ensureInit();
      mSharedPreferences.edit().putInt(moduleName + "_" + key, value).apply();
    }
  }

  public void saveInt(String key, int value) {
    saveInt(MODULE_DEFAULT, key, value);
  }

  public long getLong(String moduleName, String key, long defValue) {
    ensureInit();
    return mSharedPreferences.getLong(moduleName + "_" + key, defValue);
  }

  public long getLong(String key, long defValue) {
    ensureInit();
    return getLong(MODULE_DEFAULT, key, defValue);
  }

  public void saveLong(String moduleName, String key, long value) {
    if (moduleName != null && key != null) {
      ensureInit();
      mSharedPreferences.edit().putLong(moduleName + "_" + key, value).apply();
    }
  }

  public void saveLong(String key, long value) {
    saveLong(MODULE_DEFAULT, key, value);
  }

  public float getFloat(String moduleName, String key, float defValue) {
    ensureInit();
    return mSharedPreferences.getFloat(moduleName + "_" + key, defValue);
  }

  public float getFloat(String key, float defValue) {
    return getFloat(MODULE_DEFAULT, key, defValue);
  }

  public void saveFloat(String moduleName, String key, float value) {
    if (moduleName != null && key != null) {
      ensureInit();
      mSharedPreferences.edit().putFloat(moduleName + "_" + key, value).apply();
    }
  }

  public void saveFloat(String key, float value) {
    saveFloat(MODULE_DEFAULT, key, value);
  }

  public boolean getBoolean(String moduleName, String key, boolean defValue) {
    ensureInit();
    return mSharedPreferences.getBoolean(moduleName + "_" + key, defValue);
  }

  public boolean getBoolean(String key, boolean defValue) {
    return getBoolean(MODULE_DEFAULT, key, defValue);
  }

  public void saveBoolean(String moduleName, String key, boolean value) {
    if (moduleName != null && key != null) {
      ensureInit();
      mSharedPreferences.edit().putBoolean(moduleName + "_" + key, value).apply();
    }
  }

  public void saveBoolean(String key, boolean value) {
    saveBoolean(MODULE_DEFAULT, key, value);
  }

  private Map<String, ?> getAll(String moduleName, boolean ignoreKeyPrefix) {
    ensureInit();
    Map<String, ?> allData = mSharedPreferences.getAll();
    Map<String, Object> moduleData = new HashMap<String, Object>();

    Set<String> keySet = allData.keySet();
    for (String key : keySet) {
      String indexStr = moduleName + "_";
      if (key != null && key.startsWith(indexStr)) {
        String k = ignoreKeyPrefix ? key.substring(indexStr.length()) : key;
        moduleData.put(k, allData.get(key));
      }
    }
    return moduleData;
  }

  public Map<String, ?> getAll(String moduleName) {
    return getAll(moduleName, false);
  }

  public Map<String, ?> getAllIgnoreKeyPrefix(String moduleName) {
    return getAll(moduleName, true);
  }

  public void remove(String moduleName, String key) {
    if (moduleName != null && key != null) {
      ensureInit();
      mSharedPreferences.edit().remove(moduleName + "_" + key).apply();
    }
  }

  public void remove(String key) {
    remove(MODULE_DEFAULT, key);
  }

  public boolean contains(String moduleName, String key) {
    if (moduleName != null && key != null) {
      ensureInit();
      return mSharedPreferences.contains(moduleName + "_" + key);
    }
    return false;
  }

  public boolean contains(String key) {
    return contains(MODULE_DEFAULT, key);
  }

  public void clear(String moduleName) {
    ensureInit();
    SharedPreferences.Editor editor = mSharedPreferences.edit();

    Map<String, ?> moduleData = getAll(moduleName);
    Set<String> keySet = moduleData.keySet();
    for (String key : keySet) {
      editor.remove(key);
    }

    editor.apply();
  }

}
