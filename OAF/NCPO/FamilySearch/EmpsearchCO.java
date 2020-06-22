/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package oracle.apps.ncpo.EmpSearch.webui;

import com.sun.java.util.collections.HashMap;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.OAViewObject;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;

/**
 * Controller for ...
 */
public class EmpsearchCO extends OAControllerImpl
{
  public static final String RCS_ID="$Header$";
  public static final boolean RCS_ID_RECORDED =
        VersionInfo.recordClassVersion(RCS_ID, "%packagename%");

  /**
   * Layout and page setup logic for a region.
   * @param pageContext the current OA page context
   * @param webBean the web bean corresponding to the region
   */
  public void processRequest(OAPageContext pageContext, OAWebBean webBean)
  {
    super.processRequest(pageContext, webBean);
      OAApplicationModule am1=pageContext.getApplicationModule(webBean);
      OAViewObject empVO=(OAViewObject)am1.findViewObject("SearchVO1");
      //empVO.setWhereClauseParams(null);
      //empVO.setWhereClauseParam(0,null);
      empVO.setWhereClauseParam(1,null);
      empVO.executeQuery();
  }

  /**
   * Procedure to handle form submissions for form elements in
   * a region.
   * @param pageContext the current OA page context
   * @param webBean the web bean corresponding to the region
   */
  public void processFormRequest(OAPageContext pageContext, OAWebBean webBean)
  {
    super.processFormRequest(pageContext, webBean);
      System.out.println("in processform");
    if(pageContext.getParameter("MSearch")!=null){
        OAApplicationModule ame=pageContext.getApplicationModule(webBean);
        String lname=pageContext.getParameter("EMName");
        String ldept=pageContext.getParameter("EMDept");
        OAViewObject evo=(OAViewObject)ame.findViewObject("SearchVO1");
        //WHERE NAME LIKE NVL(:1,NAME) and Dept LIKE NVL(:2,Dept)
       // evo.setWhereClauseParams(null);
       evo.setWhereClause("lower(NAME) LIKE lower(NVL(:1,NAME)) and Dept LIKE NVL(:2,Dept)");
        evo.setWhereClauseParam(0,lname);
        evo.setWhereClauseParam(1,ldept);
        evo.executeQuery();
        
    }
    if("updateFA".equals(pageContext.getParameter(EVENT_PARAM))){
        System.out.println("Inside the UpdateAction");
        String Uname=pageContext.getParameter("PName");
        String Udept=pageContext.getParameter("PDept");
        System.out.println("Name is "+Uname+" dept is "+Udept);
        HashMap emphash=new HashMap();
        emphash.put("phashname",Uname);
        emphash.put("phashdept",Udept);        
        pageContext.setForwardURL("OA.jsp?page=/oracle/apps/ncpo/EmpSearch/webui/UpdatePG",
                                            null,
                                            OAWebBeanConstants.KEEP_MENU_CONTEXT,                            
                                            null,                                                   
                                            emphash,
                                            true,                            
                                            OAWebBeanConstants.ADD_BREAD_CRUMB_NO,
                                            OAWebBeanConstants.IGNORE_MESSAGES);
       
    }
  }

}
