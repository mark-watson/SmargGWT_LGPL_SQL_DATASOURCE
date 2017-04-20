package com.markwatson.client;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.types.DSDataFormat;
import com.smartgwt.client.types.FieldType;

import java.util.Arrays;
import java.util.List;

/**
 * Copyright Mark Watson 2010-2011. All Rights Reserved.
 * License: LGPL version 3 (http://www.gnu.org/licenses/lgpl-3.0.txt)
 */


public class SqlDS extends DataSource {
  public SqlDS(String sql) {
    setID(id);
    setDataFormat(DSDataFormat.JSON);

    List<String> tokens = Arrays.asList(sql.toLowerCase().replaceAll(",", " ").split(" "));
    int index1 = tokens.indexOf("select");
    int index2 = tokens.indexOf("from");
    for (int i = index1 + 1; i < index2; i++) {
      if (tokens.get(i).length() > 0) {
        addField(new DataSourceField(tokens.get(i), FieldType.TEXT, tokens.get(i)));
      }
    }
    // should do a better job at UUENCODEing SQL:
    setDataURL("news?query=" + sql.replaceAll(" ", "20%"));
  }
}
