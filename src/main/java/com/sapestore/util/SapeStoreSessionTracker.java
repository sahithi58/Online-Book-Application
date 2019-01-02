package com.sapestore.util;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.opensymphony.xwork2.util.TextParseUtil;

/**
 * Service class for tracking session.
 * 
 * CHANGE LOG 
 * VERSION 	DATE 		AUTHOR 	MESSAGE 
 * 1.0 		20-06-2014 	SAPIENT Initial version
 */

public class SapeStoreSessionTracker  implements Interceptor{
    
	private static final long serialVersionUID = 1L;
	Map<String, Object>session;
    private Set<String> excludeMethods=Collections.EMPTY_SET;
    private Set<String> includeMethods=Collections.EMPTY_SET;
    
    public void setExcludeMethods(String excludeMethods) {
        this.excludeMethods =
                TextParseUtil.commaDelimitedStringToSet(excludeMethods);
    }
    public void setIncludeMethods(String includeMethods) {
        this.includeMethods = 
                TextParseUtil.commaDelimitedStringToSet(includeMethods);
    }
    public void destroy() {
        
    }
    public void init() {
        
    }
    public String intercept(ActionInvocation invocation) throws Exception {
        String result="";
        String method=invocation.getProxy().getMethod();
        session=ActionContext.getContext().getSession();
        String trackerId=(String)session.get("trackerId");
        if(excludeMethods.contains(method)){
            result=invocation.invoke();
        }else if(includeMethods.contains(method)){
            if(trackerId==null){
                result="nosession";
            }else{
                result=invocation.invoke();
            }
        }
        return result;
    }
}
