package com.netcracker.lab1.stetsenko.controller;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ���� on 25.01.2016.
 */
public class Actions {
   public enum EnumActions {
       TASK_LIST_1,
       TASK_LIST_2,
       TASK_LIST_3,
       TASK_LIST_4,
       TASK_LIST_5,
       TASK_LIST_6,
       ADD_TASK_1,
       ADD_TASK_2,
       ADD_TASK_3,
       ERROR_ADD_TASK_1,
       ERROR_ADD_TASK_2,
       ERROR_EDIT_TASK_1,
       ERROR_EDIT_TASK_2
   }

    public static Map<String, EnumActions> MAP_ENUM = new HashMap<String, EnumActions>();
    static {
        MAP_ENUM.put("TASK_LIST_1", EnumActions.TASK_LIST_1);
        MAP_ENUM.put("TASK_LIST_2", EnumActions.TASK_LIST_2);
        MAP_ENUM.put("TASK_LIST_3", EnumActions.TASK_LIST_3);
        MAP_ENUM.put("TASK_LIST_4", EnumActions.TASK_LIST_4);
        MAP_ENUM.put("TASK_LIST_5", EnumActions.TASK_LIST_5);
        MAP_ENUM.put("TASK_LIST_6", EnumActions.TASK_LIST_6);
        MAP_ENUM.put("ADD_TASK_1", EnumActions.ADD_TASK_1);
        MAP_ENUM.put("ADD_TASK_2", EnumActions.ADD_TASK_2);
        MAP_ENUM.put("ADD_TASK_3", EnumActions.ADD_TASK_3);
        MAP_ENUM.put("ERROR_ADD_TASK_1", EnumActions.ERROR_ADD_TASK_1);
        MAP_ENUM.put("ERROR_ADD_TASK_2", EnumActions.ERROR_ADD_TASK_2);
        MAP_ENUM.put("ERROR_EDIT_TASK_1", EnumActions.ERROR_EDIT_TASK_1);
        MAP_ENUM.put("ERROR_EDIT_TASK_2", EnumActions.ERROR_EDIT_TASK_2);

    }


}
