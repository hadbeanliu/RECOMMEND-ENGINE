package com.rongji.cms.recommend.engine.support;

import com.rongji.dfish.platform.permission.service.impl.PermissionClientJsonImpl;
import com.rongji.dfish.platform.ums.service.impl.UmsClientJsonImpl;
import com.rongji.dfish.platform.ums.service.impl.UmsxClientJsonImpl;

public class config {
public static UmsClientJsonImpl getUmsCLient() {
	UmsClientJsonImpl ums=new UmsClientJsonImpl();
	ums.setPlatformUrl("http://dfish2.local.work.net/dfish-servman/");
	ums.setAccount("DFISH");
	ums.setPassword("123ABC");
//	ums.do(roleId, userId);
	return ums;
}
public static UmsxClientJsonImpl getUmsXCLient() {
	UmsxClientJsonImpl umsx=new UmsxClientJsonImpl();
	umsx.setPlatformUrl("http://dfish2.local.work.net/dfish-servman/");
	umsx.setAccount("DFISH");
	umsx.setPassword("123ABC");
	return umsx;
	
}
public static PermissionClientJsonImpl getPermissionClient() {
	PermissionClientJsonImpl permission=new PermissionClientJsonImpl();
	permission.setPlatformUrl("http://dfish2.local.work.net/dfish-servman/");
	//permission.doesUserHasPermission(arg0, arg1, arg2)
	permission.setAccount("DFISH");
	permission.setPassword("123ABC");
	return permission;
	
}
}
