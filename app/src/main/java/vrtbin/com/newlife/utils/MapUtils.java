package vrtbin.com.newlife.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Desc :
 * Date : 16/6/13.
 * Author: tim.
 */
public class MapUtils {

  public static String mapToString(Map<String, String> map) {
    if (CollectionUtils.isEmpty(map)) {
      return "";
    }
    StringBuilder stringBuilder = new StringBuilder();
    for (String key : map.keySet()) {
      if (stringBuilder.length() > 0) {
        stringBuilder.append("&");
      }
      String value = map.get(key);
      try {
        stringBuilder.append((key != null ? URLEncoder.encode(key, "UTF-8") : ""));
        stringBuilder.append("=");
        stringBuilder.append(value != null ? URLEncoder.encode(value, "UTF-8") : "");
      } catch (Exception e) {
        return "";
      }
    }

    return stringBuilder.toString();
  }

  public static Map<String, String> stringToMap(String target) {
    Map<String, String> map = new HashMap<String, String>();
    if (target == null) {
      return map;
    }
    String[] nameValuePairs = target.split("&");
    for (String nameValuePair : nameValuePairs) {
      String[] nameValue = nameValuePair.split("=");
      try {
        map.put(URLDecoder.decode(nameValue[0], "UTF-8"), nameValue.length > 1
            ? URLDecoder.decode(nameValue[1], "UTF-8") : "");
      } catch (UnsupportedEncodingException e) {
        return map;
      }
    }

    return map;
  }

  /**
   * Map按key进行排序
   */
  public static Map<String, String> sortMapByKey(Map<String, String> map) {
    if (CollectionUtils.isEmpty(map)) {
      return map;
    }

    Map<String, String> sortMap = new TreeMap<>(
        new Comparator<String>() {
          @Override
          public int compare(String s1, String s2) {
            int c = 0;
            try {
              c = s1.compareTo(s2);
            } catch (Exception e) {
              return c;
            }
            return c;
          }
        });

    sortMap.putAll(map);

    return sortMap;
  }

}
