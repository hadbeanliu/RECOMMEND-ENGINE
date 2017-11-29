package com.rongji.cms.recommend.engine.meta;


public interface ComputingSystem {

  public static String DATA_TYPE ="computing.type";

  public static String DATA_CLASS ="computing.class";

  public static String INPUT_TABLE ="input.table";

  public static String OUTPUT_TABLE ="output.table";

  public static String COMPUTING_ID ="computing.biz.code";

  public static String COMPUTING_BITCH_ID ="computing.bitch.id";

  public static String COMPUTING_CONF_ID ="computing.conf.id";

  public static String KEY_KEY_SPLIT_SIGN ="/001";

  public static String KEY_VALUE_SPLIT_SIGN ="/002";
  
  public static String VALUE_KEY_SPLIT_SIGN ="/003";

  //M Q PARAMS
  public static String MESSAGE_ACTIVITY_USER ="activeMQ.user";

  public static String MESSAGE_ACTIVITY_PASSWORD ="activeMQ.password";

  public static String MESSAGE_ACTIVITY_HOST ="activeMQ.host";

  public static String MESSAGE_ACTIVITY_PORT ="activeMQ.port";
  public static String MESSAGE_ACTIVITY_DIST ="activeMQ.distination";

}