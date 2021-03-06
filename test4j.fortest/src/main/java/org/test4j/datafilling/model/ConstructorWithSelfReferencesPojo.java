package org.test4j.datafilling.model;

import java.io.Serializable;

import org.test4j.datafilling.annotations.FillConstructor;

@SuppressWarnings("serial")
public class ConstructorWithSelfReferencesPojo implements Serializable {

	private int intField;

	private ConstructorWithSelfReferencesPojo parent;

	private ConstructorWithSelfReferencesPojo anotherParent;

	@FillConstructor
	public ConstructorWithSelfReferencesPojo(int intField, ConstructorWithSelfReferencesPojo parent,
			ConstructorWithSelfReferencesPojo anotherParent) {
		super();
		this.intField = intField;
		this.parent = parent;
		this.anotherParent = anotherParent;
	}

	public ConstructorWithSelfReferencesPojo() {
	}

	public int getIntField() {
		return intField;
	}

	public ConstructorWithSelfReferencesPojo getParent() {
		return parent;
	}

	public ConstructorWithSelfReferencesPojo getAnotherParent() {
		return anotherParent;
	}
}
