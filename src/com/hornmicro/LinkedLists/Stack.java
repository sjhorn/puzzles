package com.hornmicro.linkedlists;

import java.io.IOException;

public interface Stack {
	public Object pop() throws IOException;
	public void push(Object o);
}
