package com.linw.demoentity.util.mybatis;

import org.mybatis.generator.api.ShellRunner;  
  
/**
 * 
 * @author linw
 *
 */
public class BaseMybatis3Main {
	
	private static String XML_NAME_DEMO = "demo";
	
	public static void generate(String xmlName) {  
        String config = ClassLoader.getSystemResource("config/db/mybatis/MyBatisCodeGenerator-" + xmlName + ".xml").getFile();  
        String[] arg = { "-configfile", config, "-overwrite" };  
        ShellRunner.main(arg);
    }
	
	public static void main(String[] args) {  
		generate(XML_NAME_DEMO);
	}
}  