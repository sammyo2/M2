package edu.gatech.oad.antlab.person;

/**
 *  A simple class for person 1
 *  returns their name and a
 *  modified string
 *
 *  @author Shelby
 *  @version 1.1
 */


public static void main (String[] args) {
  Person p1 = new Person("Shelby");

  String result = p1.calc("gtg123b");

  System.out.println(result);

}

public class Person1 {
  /** Holds the persons real name */
  private String name;
  	/**
	 * The constructor, takes in the persons
	 * name
	 * @param pname the person's real name
	 */

  public Person1(String pname) {
    name = pname;
  }
  	/**
	 * This method should take the string
	 * input and return its characters rotated
	 * 2 positions.
	 * given "gtg123b" it should return
	 * "g123bgt".
	 *
	 * @param input the string to be modified
	 * @return the modified string
	 */
	private String calc(String input) {
	  //Person 1 put your implementation here

    for(int i  = 0; i < 2; i++) {
      for(int j = 1; j < input.length(); j++) {
        char temp = input.charAt(0);
        inut.charAt(j - 1) = input.charAt(j);
      }
      input.charAt(input.charAt(input.length() -1 )) = temp;
    }
    return input;
	}

	/**
	 * Return a string rep of this object
	 * that varies with an input string
	 *
	 * @param input the varying string
	 * @return the string representing the
	 *         object
	 */
	public String toString(String input) {
	  return name + calc(input);
	}

}
