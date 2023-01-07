package controller;

/**
 * An interaction with the user consists of some input to send the program and some output to
 * expect.  We represent it as an object that takes in two StringBuilders and produces the intended
 * effects on them
 */
public interface Interaction {

  /**
   * Applies the intended use onto the in and/or the out.
   * @param in the users input
   * @param out the
   */
  void apply(StringBuilder in, StringBuilder out);
}

