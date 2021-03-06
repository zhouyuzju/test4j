package org.test4j.datafilling.model;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import org.test4j.datafilling.annotations.FillList;

public class MultiDimensionalTestPojo {

	@FillList(size = 2)
	private List<List<List<String>>> threeDimensionalList;

	@FillList(size = 2)
	private Set<Set<Set<Double>>> threeDimensionalSet;

	@FillList(size = 2)
	private Collection<Collection<Collection<Long>>> threeDimensionalCollection;

	@FillList(size = 2)
	private Map<Boolean, Map<Float, Map<Integer, Calendar>>> threeDimensionalMap;

	@FillList(size = 2)
	private Queue<Queue<Queue<Date>>> threeDimensionalQueue;

	@FillList(size = 2)
	private String[][][] threeDimensionalArray;

	public List<List<List<String>>> getThreeDimensionalList() {
		return threeDimensionalList;
	}

	public void setThreeDimensionalList(List<List<List<String>>> threeDimensionalList) {
		this.threeDimensionalList = threeDimensionalList;
	}

	public Set<Set<Set<Double>>> getThreeDimensionalSet() {
		return threeDimensionalSet;
	}

	public void setThreeDimensionalSet(Set<Set<Set<Double>>> threeDimensionalSet) {
		this.threeDimensionalSet = threeDimensionalSet;
	}

	public Collection<Collection<Collection<Long>>> getThreeDimensionalCollection() {
		return threeDimensionalCollection;
	}

	public void setThreeDimensionalCollection(Collection<Collection<Collection<Long>>> threeDimensionalCollection) {
		this.threeDimensionalCollection = threeDimensionalCollection;
	}

	public Map<Boolean, Map<Float, Map<Integer, Calendar>>> getThreeDimensionalMap() {
		return threeDimensionalMap;
	}

	public void setThreeDimensionalMap(Map<Boolean, Map<Float, Map<Integer, Calendar>>> threeDimensionalMap) {
		this.threeDimensionalMap = threeDimensionalMap;
	}

	public Queue<Queue<Queue<Date>>> getThreeDimensionalQueue() {
		return threeDimensionalQueue;
	}

	public void setThreeDimensionalQueue(Queue<Queue<Queue<Date>>> threeDimensionalQueue) {
		this.threeDimensionalQueue = threeDimensionalQueue;
	}

	public String[][][] getThreeDimensionalArray() {
		return threeDimensionalArray;
	}

	public void setThreeDimensionalArray(String[][][] threeDimensionalArray) {
		this.threeDimensionalArray = threeDimensionalArray;
	}

	@Override
	public String toString() {
		return "MultiDimensionalTestPojo\n" + "[threeDimensionalList=" + threeDimensionalList + ",\n"
				+ "threeDimensionalSet=" + threeDimensionalSet + ",\n" + "threeDimensionalCollection="
				+ threeDimensionalCollection + ",\n" + "threeDimensionalMap=" + threeDimensionalMap + ",\n"
				+ "threeDimensionalQueue=" + threeDimensionalQueue + ",\n" + "threeDimensionalArray="
				+ printArrayRecursively(threeDimensionalArray) + "]";
	}

	private String printArrayRecursively(Object[] array) {
		StringBuilder sb = new StringBuilder("[");
		for (Object object : array) {
			if (object.getClass().isArray()) {
				sb.append(printArrayRecursively((Object[]) object));
			} else {
				sb.append(object.toString());
			}
			sb.append(", ");
		}
		sb.setLength(sb.length() - 2);
		sb.append("]");

		return sb.toString();
	}
}
