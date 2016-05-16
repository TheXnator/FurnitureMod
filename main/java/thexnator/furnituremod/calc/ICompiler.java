package thexnator.furnituremod.calc;

public interface ICompiler<E> {
	public IExecutable<E> compile(Iterable<Token> input);
}
