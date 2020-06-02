// Generated from D:/Career/D0_SF/Satellites/enceladus/enceladus-core/src/main/resources/antlr\DataType.g4 by ANTLR 4.6
package net.milanqiu.enceladus.antlr.generated;

import net.milanqiu.enceladus.antlr.AltNumberStoredParserRuleContext;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link DataTypeParser}.
 */
public interface DataTypeListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link DataTypeParser#dataType}.
	 * @param ctx the parse tree
	 */
	void enterDataType(DataTypeParser.DataTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link DataTypeParser#dataType}.
	 * @param ctx the parse tree
	 */
	void exitDataType(DataTypeParser.DataTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link DataTypeParser#paramList}.
	 * @param ctx the parse tree
	 */
	void enterParamList(DataTypeParser.ParamListContext ctx);
	/**
	 * Exit a parse tree produced by {@link DataTypeParser#paramList}.
	 * @param ctx the parse tree
	 */
	void exitParamList(DataTypeParser.ParamListContext ctx);
	/**
	 * Enter a parse tree produced by {@link DataTypeParser#param}.
	 * @param ctx the parse tree
	 */
	void enterParam(DataTypeParser.ParamContext ctx);
	/**
	 * Exit a parse tree produced by {@link DataTypeParser#param}.
	 * @param ctx the parse tree
	 */
	void exitParam(DataTypeParser.ParamContext ctx);
	/**
	 * Enter a parse tree produced by {@link DataTypeParser#propertyList}.
	 * @param ctx the parse tree
	 */
	void enterPropertyList(DataTypeParser.PropertyListContext ctx);
	/**
	 * Exit a parse tree produced by {@link DataTypeParser#propertyList}.
	 * @param ctx the parse tree
	 */
	void exitPropertyList(DataTypeParser.PropertyListContext ctx);
	/**
	 * Enter a parse tree produced by {@link DataTypeParser#property}.
	 * @param ctx the parse tree
	 */
	void enterProperty(DataTypeParser.PropertyContext ctx);
	/**
	 * Exit a parse tree produced by {@link DataTypeParser#property}.
	 * @param ctx the parse tree
	 */
	void exitProperty(DataTypeParser.PropertyContext ctx);
}