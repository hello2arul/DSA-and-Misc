package Misc;

/**
 * https://www.hackerrank.com/contests/w15/challenges/haunted-house
 * It is a well-known fact that Mr. Krabs owns a very popular restaurant, known as the Krusty Krab.
 * He makes a lot of money from his restaurant, and all day long he's only counting his money.
 * Not satisfied with his billions, he also opened an amusement park, called Krustyland,
 * which contains lots of famous attractions, like roller coasters and ferris wheels.
 * Recently, he has decided to open a haunted house. It is the scariest haunted house ever!
 * In fact, it is so scary, that most people don't even want to go inside alone.
 *
 * There are  people who want to visit the haunted house.
 * The  person will only go if at least  other people will go with him. Additionally,
 * that person doesn't want to go with more than  other people, since it would ruin the experience for them.
 *
 * What is the maximum number of people that can visit the haunted house at one time so that no constraint is violated?
 *
 * Input Format
 *
 * The first line contains a single integer, , the number of people.
 *
 *  lines follow. The  line contains two integers,  and ,
 *  which are the minimum and maximum number of people the  person wants to go with, respectively.
 *
 * Constraints
 *
 *
 * Output Format
 *
 * Output a single integer, the maximum number of people Mr. Krabs can lure into the haunted house.
 *
 * Sample Input
 *
 * 6
 * 1 2
 * 1 4
 * 0 3
 * 0 1
 * 3 4
 * 0 2
 * Sample Output
 *
 * 3
 * Explanation
 *
 * We can bring  people, the first three people in the list.
 *
 * The first person will only go with  or  other people.
 *
 * The second person will go with  to  other people.
 *
 * The third person will go alone, or with up to  other people.
 *
 * Notice that the solution is not unique; we can also, for example, bring along the first, second,
 * and last guy on the list. But it's easy to see, that there's no way to bring more than  people.
 */
public class HauntedHouse {
}
