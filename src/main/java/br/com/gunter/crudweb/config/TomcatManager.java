package br.com.gunter.crudweb.config;

import java.io.File;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

public class TomcatManager {

	public TomcatManager() throws LifecycleException {
		var tomcat = new Tomcat();
		int port = Integer.parseInt(ConfigUtil.getProperty("tomcat.port"));
		tomcat.setPort(port);
		tomcat.getConnector();

		Context ctx = tomcat.addWebapp("/", new File(ConfigUtil.getProperty("tomcat.webappdir")).getAbsolutePath());

		var novoDirClasses = new File("target/classes").getAbsolutePath();
		WebResourceRoot resources = new StandardRoot(ctx);
		var dirBaseClasses = new DirResourceSet(resources, "/WEB-INF/classes", novoDirClasses, "/");
		resources.addPreResources(dirBaseClasses);
		ctx.setResources(resources);

		tomcat.start();
		tomcat.getServer().await();
	}

}
