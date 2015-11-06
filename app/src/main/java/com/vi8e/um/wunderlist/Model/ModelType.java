package com.vi8e.um.wunderlist.Model;

/**
 * Created by Fixer on 11/6/2015.
 */
public class ModelType {

public static final String LIST = "list";
public static final String SUB_TASK = "subTask";
public static final String TASK = "task";
public static final String COMMENT = "comment";

public String type;
public ModelType(String type) {
	this.type = type;
}

}
