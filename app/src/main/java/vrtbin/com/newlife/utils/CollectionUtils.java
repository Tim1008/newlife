package vrtbin.com.newlife.utils;

import java.util.Collection;
import java.util.Map;

/**
 * Desc :
 * Date : 16/6/13.
 * Author: tim.
 */
public class CollectionUtils {

  public static <T> boolean isEmpty(Collection<T> list) {
    return list == null || list.isEmpty();
  }

  public static <K, V> boolean isEmpty(Map<K, V> map) {
    return map == null || isEmpty(map.keySet());
  }
}
