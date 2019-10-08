package org.nancy;

import java.util.function.Function;

/**
 * @author nnjeundji
 *
 */
@FunctionalInterface
public interface Comparator<T> {
	
	public int compare(T t1, T t2);
	
	// for a specific type of argument (Integr here)
/*	public static Comparator<Person> comparing (Function <Person, Integer> f){
		return (p1,p2) -> f.apply(p1) - f.apply(p2);
	} */

	// more generic to any comparable type of the argument
	/*public static Comparator<Person> comparing (Function <Person, Comparable> f){
		return (p1,p2) -> f.apply(p1).compareTo(f.apply(p2));
	}*/

	
	// more generic to any comparable type of argument of any object
	public static <U> Comparator<U> comparing (Function <U, Comparable> f){
		return (p1,p2) -> f.apply(p1).compareTo(f.apply(p2));
	}
	
	// take a comparator as a param
	public default Comparator<T> thenComparing (Comparator<T> cmp){
		return (p1,p2) -> compare(p1,p2) == 0 ? cmp.compare(p1, p2) : compare(p1, p2);
	}
	
	// take a function  as a param
	public default Comparator<T> thenComparing (Function <T, Comparable> f){
		/*Comparator<T> cmp = comparing(f);
		return cmp;*/
		return comparing(f);
	}
}
