// Generated from /Users/frankchen/source/open/bithon/server/collector/src/main/antlr4/PostAggregatorExpression.g4 by ANTLR 4.9.1
package com.sbss.bithon.server.metric.metric.aggregator.ast;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;

/**
 * This class provides an empty implementation of {@link PostAggregatorExpressionVisitor},
 * which can be extended to create a visitor which only needs to handle a subset
 * of the available methods.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public class PostAggregatorExpressionBaseVisitor<T> extends AbstractParseTreeVisitor<T> implements PostAggregatorExpressionVisitor<T> {
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public T visitProg(PostAggregatorExpressionParser.ProgContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public T visitExpression(PostAggregatorExpressionParser.ExpressionContext ctx) { return visitChildren(ctx); }
}