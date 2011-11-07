package com.markwatson.server;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright Mark Watson 2010-2011. All Rights Reserved.
 * License: LGPL version 3 (http://www.gnu.org/licenses/lgpl-3.0.txt)
 */

public class DbUtils {
  static String dbURL;
  static Connection dbCon;

  static {
    try {
      Class.forName("org.postgresql.Driver");
      // Define the data source for the driver
      dbURL = "jdbc:postgresql://localhost/kbsportal_development";
      dbCon = DriverManager.getConnection(dbURL, "postgres", "foo21");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }


  public static String doQuery(String sql) throws Exception {
    ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
    List<Map<String, String>> ret = new ArrayList<Map<String, String>>();
    Statement statement = dbCon.createStatement();
    ResultSet rs = statement.executeQuery(sql.replaceAll("20%", " "));
    java.sql.ResultSetMetaData meta = rs.getMetaData();
    int size = meta.getColumnCount();
    while (rs.next()) {
      Map<String, String> row = new HashMap<String, String>();
      for (int i = 1; i <= size; i++) {
        String column = meta.getColumnName(i);
        Object obj = rs.getObject(i);
        row.put(column, "" + obj);
      }
      ret.add(row);
    }
    StringWriter sw = new StringWriter();
    mapper.writeValue(sw, ret);
    return sw.toString();
  }
}
/**
 id  | content |      title      |                uri
 -----+---------+-----------------+-----------------------------------
 172 | 17662   |  Mark site      | http://markwatson.com/consulting/
 184 | 17663   |  OBama's Budget | http://news.com/12345
 */