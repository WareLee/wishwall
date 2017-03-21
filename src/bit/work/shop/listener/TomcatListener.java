package bit.work.shop.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


import bit.work.shop.utils.C3P0Utils;

public class TomcatListener implements ServletContextListener{  
    
    /* tomcat关闭的时候清理连接池
     * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent) 
     */  
    @Override  
    public void contextDestroyed(ServletContextEvent arg0) {  
    	System.out.println("tomcat关闭了..........");  
    	C3P0Utils.cleanup();
    }  
  
    /* (non-Javadoc) 
     * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent) 
     */  
    @Override  
    public void contextInitialized(ServletContextEvent arg0) {  
          
//        System.out.println("tomcate启动了..............");  
    }  
  
}  