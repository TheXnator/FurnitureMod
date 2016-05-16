package thexnator.furnituremod.calc;

import java.util.Stack;

public interface ICalculatorFrame<E> {
	public Stack<E> stack();

	public ISymbol<E> getSymbol(String id);
}
