package com.linw.demoentity.util.mybatis;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

/**
 * insert return
 * 
 * @author linw
 *
 */
public class Mybatis3PluginByInsertReturn extends BaseMybatis3Plugin {

	@Override
	public boolean sqlMapInsertElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
		
		addSelectKey(element, introspectedTable);
	
		return super.sqlMapInsertElementGenerated(element, introspectedTable);
	}

	@Override
	public boolean sqlMapInsertSelectiveElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
		
		addSelectKey(element, introspectedTable);
		
		return super.sqlMapInsertSelectiveElementGenerated(element, introspectedTable);
	}
	
	private void addSelectKey(XmlElement element, IntrospectedTable introspectedTable) {
		
		String primaryKeyType = getPkJavaType(introspectedTable);
		String primaryKeyName = getPkJavaName(introspectedTable);
		
		XmlElement isNotNullElement = new XmlElement("selectKey");
		isNotNullElement.addAttribute(new Attribute("resultType", primaryKeyType));
		isNotNullElement.addAttribute(new Attribute("order", "AFTER"));
		isNotNullElement.addAttribute(new Attribute("keyProperty", primaryKeyName));
		isNotNullElement.addElement(new TextElement("SELECT LAST_INSERT_ID()"));
		element.addElement(isNotNullElement);
		
	}
}