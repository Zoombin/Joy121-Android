package com.joy.json.operation;

public interface IOperation<I, O, R> {

	O exec(I in, R res) throws Exception;

}
