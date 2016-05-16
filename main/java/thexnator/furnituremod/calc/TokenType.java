package thexnator.furnituremod.calc;

import java.util.EnumSet;
import java.util.Set;

enum TokenProperties {
	VALUE,
	NEXT_OP_INFIX,
	POSSIBLE_FUNCTION
}

public enum TokenType {
	DEC_NUMBER(TokenProperties.VALUE),
	HEX_NUMBER(TokenProperties.VALUE),
	OCT_NUMBER(TokenProperties.VALUE),
	BIN_NUMBER(TokenProperties.VALUE),
	QUOTED_NUMBER(TokenProperties.VALUE),

	SYMBOL(TokenProperties.POSSIBLE_FUNCTION),
	SYMBOL_WITH_ARGS(TokenProperties.POSSIBLE_FUNCTION),

	OPERATOR(TokenProperties.NEXT_OP_INFIX),
	LEFT_BRACKET(TokenProperties.NEXT_OP_INFIX),
	RIGHT_BRACKET(),
	SEPARATOR(TokenProperties.NEXT_OP_INFIX);

	public boolean isValue() {
		return properties.contains(TokenProperties.VALUE);
	}

	public final boolean isNextOpInfix() {
		return properties.contains(TokenProperties.NEXT_OP_INFIX);
	}

	public final boolean isPossibleFunction() {
		return properties.contains(TokenProperties.POSSIBLE_FUNCTION);
	}

	private final Set<TokenProperties> properties;

	private TokenType(TokenProperties property, TokenProperties... properties) {
		this.properties = EnumSet.of(property, properties);
	}

	private TokenType() {
		this.properties = EnumSet.noneOf(TokenProperties.class);
	}
}
