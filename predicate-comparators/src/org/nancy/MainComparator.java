package org.nancy;


import java.util.function.Function;

/** Refactoring a comparison using the key extractor */
public class MainComparator {

	public static void main(String[] args) {

		// comparaison based on the age
		Comparator<Person> cmpAge = (p1,p2) -> p1.getAge() - p2.getAge();
		
		// comparaison based on the firstname
		Comparator<Person> cmpFirstName = (p1,p2) -> p1.getFirstName().compareTo(p2.getFirstName());
		
		// comparaison based on the lastName
		Comparator<Person> cmpLasstName = (p1,p2) -> p1.getFirstName().compareTo(p2.getFirstName());
		
		/** Refactoring should be like*/
		
		Function<Person, Integer> f1 = p -> p.getAge();
		Function<Person, String> f2 = p -> p.getFirstName();
		Function<Person, String> f3 = p -> p.getLastName();
		
		// check the interface to see the static comparing method
		//Comparator<Person> cmpPerson = Comparator.comparing(f1); // this  work
		// Comparator<Person> cmpPerson = Comparator.comparing( p -> p.getAge()); // by passing lambda
		Comparator<Person> cmpPersonAge = Comparator.comparing(Person::getAge); // by method reference, note the comparing method is static
		Comparator<Person> cmpPersonLastName = Comparator.comparing(Person::getLastName);
		
		
		// thenComparing is a default method which returns a compartor Person -> check the interface
		Comparator<Person> cmp = cmpPersonAge.thenComparing(cmpLasstName);
		
		
		// this thenComparing method is an overloaded method of the default thenComparing which take
		//a function  as parameter instead of the usual Compare object and return a comparator
		Comparator<Person> cmp2 = Comparator.comparing(Person :: getAge)
											.thenComparing(Person::getFirstName)
											.thenComparing(Person::getLastName);
		}

}
