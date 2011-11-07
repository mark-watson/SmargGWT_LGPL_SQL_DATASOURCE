package com.markwatson.client;

import com.google.gwt.core.client.EntryPoint;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.layout.VStack;

/**
 * Copyright Mark Watson 2010-2011. All Rights Reserved.
 * License: LGPL version 3 (http://www.gnu.org/licenses/lgpl-3.0.txt)
 */

/**
 * GWT/SmartGWT entry point
 */
public class HelloWorld implements EntryPoint {

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
    final VStack vs = new VStack();
    vs.setLeft(50);
    vs.setTop(30);
    vs.setWidth("70%");
    vs.setMembersMargin(20);

    // first sample ListGrid filled with an arbitrary SQL query:
    ListGrid listGrid = new ListGrid();
    listGrid.setDataSource(new SqlDS("select title, content, uri from news where content like '%Congress%'"));
    listGrid.setAutoFetchData(true);

    // second sample ListGrid filled with an arbitrary SQL query:
    ListGrid listGrid2 = new ListGrid();
    listGrid2.setDataSource(new SqlDS("select title, content from news"));
    listGrid2.setAutoFetchData(true);

    vs.addMember(listGrid);
    vs.addMember(listGrid2);
    vs.draw();
  }
}