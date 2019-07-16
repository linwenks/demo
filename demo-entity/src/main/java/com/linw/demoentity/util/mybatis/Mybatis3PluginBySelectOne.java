package com.linw.demoentity.util.mybatis;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;  
  
/**
 * select one
 * @author linw
 *
 */
public class Mybatis3PluginBySelectOne extends BaseMybatis3Plugin {
	
	private String methodName = "selectByExampleForOne";
	
	@Override
	public boolean clientGenerated(Interface interfaze,
			TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		String name = "example";
		Method method = new Method(methodName);
		method.addParameter(new Parameter(new FullyQualifiedJavaType(introspectedTable.getExampleType()), name));
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setReturnType(new FullyQualifiedJavaType(introspectedTable.getBaseRecordType()));
		interfaze.addMethod(method);
		return super.clientGenerated(interfaze, topLevelClass, introspectedTable);
	}
	
	@Override
	public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
		XmlElement rootElement = document.getRootElement();
		
		XmlElement selectOne = new XmlElement("select");
		selectOne.addAttribute(new Attribute("id", methodName));
		selectOne.addAttribute(new Attribute("resultMap", "BaseResultMap"));
		selectOne.addAttribute(new Attribute("parameterType", "java.lang.Long"));
		selectOne.addElement(new TextElement("select"));
		
		// distinct
		XmlElement ifOne = new XmlElement("if");
		ifOne.addAttribute(new Attribute("test", "distinct"));
		ifOne.addElement(new TextElement("distinct"));
		selectOne.addElement(ifOne);
		
		// include Base_Column_List
		XmlElement includeXml = new XmlElement("include");
		includeXml.addAttribute(new Attribute("refid", "Base_Column_List"));
		selectOne.addElement(includeXml);
		
		// text table
		selectOne.addElement(new TextElement("from " + introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime()));

		// _parameter
		XmlElement ifTwo = new XmlElement("if");
		ifTwo.addAttribute(new Attribute("test", "_parameter != null"));
		
		XmlElement includeRefXml = new XmlElement("include");
		includeRefXml.addAttribute(new Attribute("refid", "Example_Where_Clause"));
		ifTwo.addElement(includeRefXml);
		
		selectOne.addElement(ifTwo);
		
		// orderByClause
		XmlElement ifThree = new XmlElement("if");
		ifThree.addAttribute(new Attribute("test", "orderByClause != null"));
		ifThree.addElement(new TextElement("order by ${orderByClause}"));
		selectOne.addElement(ifThree);
		
		rootElement.addElement(selectOne);
		return super.sqlMapDocumentGenerated(document, introspectedTable);
	}
}  