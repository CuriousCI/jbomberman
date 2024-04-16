package jbomberman.model;

// TODO: icon avatar
/* 
 * @author SomebodyThatIUsedToKnow
 * 
 * @param nickname Some cringe nickname
 * @param games You play too much, go outside
 *
 * <pre>
 * {@code
 * var user = new User("BomberUser23", 10, 7, 5)
 * }
 * </pre>
 * */
public record User(String nickname, int games, int victories, int level) {
};
