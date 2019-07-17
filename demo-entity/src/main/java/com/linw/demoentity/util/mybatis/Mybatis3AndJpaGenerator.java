package com.linw.demoentity.util.mybatis;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.internal.DefaultCommentGenerator;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * mybatis
 * jpa
 * 注释
 *
 * @author linw
 *
 */
public class Mybatis3AndJpaGenerator extends DefaultCommentGenerator {

	@Override
	public void addFieldComment(Field field, IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn) {
        field.addJavaDocLine("/*");
		field.addJavaDocLine(" * " + introspectedColumn.getRemarks());
        field.addJavaDocLine(" */");

		var pk = introspectedTable.getPrimaryKeyColumns().get(0).getActualColumnName();
		if (pk.equalsIgnoreCase(introspectedColumn.getActualColumnName())) {
			field.addJavaDocLine("@Id");
		}

		//field.addJavaDocLine("@Column(length=" + 1 + ")");
	}

	@Override
	public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		
		topLevelClass.addJavaDocLine("/**");
        topLevelClass.addJavaDocLine(" * " + introspectedTable.getRemarks());
        topLevelClass.addJavaDocLine(" * ");
        var tableName = introspectedTable.getFullyQualifiedTable().getIntrospectedTableName();
        topLevelClass.addJavaDocLine(" *" + tableName);
        topLevelClass.addJavaDocLine(" */");

		topLevelClass.addImportedType(Entity.class.getName());
		topLevelClass.addImportedType(Table.class.getName());
		//topLevelClass.addImportedType(Column.class.getName());
		topLevelClass.addImportedType(Id.class.getName());

		topLevelClass.addAnnotation("@Entity");
		topLevelClass.addAnnotation("@Table(name = \"" + tableName + "\")");

	}
}  