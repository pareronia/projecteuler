package com.github.pareronia.projecteuler;

public abstract class ProblemBase<Input, Output> {

	protected abstract Output solve(Input input);

	public record NoInput() {}
}
