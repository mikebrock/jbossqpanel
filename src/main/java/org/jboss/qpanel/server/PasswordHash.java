package org.jboss.qpanel.server;

import java.security.MessageDigest;

/**
 * @author Mike Brock
 */
public class PasswordHash {
  static char[] dict = new char[]{
      'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
      'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1',
      '2', '3', '4', '5', '6', '7', '8', '9', '0', '$', '#'};


  public static String hashPassword(String password) throws Exception {
    MessageDigest digest = MessageDigest.getInstance("SHA-256");
    digest.update(password.getBytes());

    for (int i = 0; i < 1000; i++) {
      digest.update(digest.digest());
    }

    byte[] bytes = digest.digest();

    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < bytes.length; i++) {
      int idx = ((int) bytes[i]) % dict.length;
      if (idx < 0) idx = -idx;
      builder.append(dict[idx]);
    }

    return builder.toString();
  }

}
