package com.markwatson.server;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Copyright Mark Watson 2010-2011. All Rights Reserved.
 * License: LGPL version 3 (http://www.gnu.org/licenses/lgpl-3.0.txt)
 */

public class DbRestServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
      PrintWriter out = resp.getWriter();
      try {
          String sql = req.getQueryString().substring(6);  // remove query=
          int index = sql.indexOf("&");
          sql = sql.substring(0, index);
          out.println(DbUtils.doQuery(sql.replaceAll("20%", " ")));
      } catch (Exception ex) {
        ex.printStackTrace(System.err);
        out.println("[]");
      }
    }
}