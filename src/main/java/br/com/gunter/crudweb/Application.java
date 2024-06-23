package br.com.gunter.crudweb;

import org.apache.catalina.LifecycleException;

import br.com.gunter.crudweb.config.TomcatManager;

public class Application {

	public static void main(String[] args) throws LifecycleException {
		new TomcatManager();
	}

}
