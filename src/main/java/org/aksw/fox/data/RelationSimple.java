package org.aksw.fox.data;

public class RelationSimple {
  String s = "";
  String p = "";
  String o = "";
  String tool = "";

  public RelationSimple(final String s, final String p, final String o, final String tool) {
    this.o = o;
    this.p = p;
    this.s = s;
    this.tool = tool;
  }

  @Override
  public String toString() {
    return "RelationSimple [s=" + s + ", p=" + p + ", o=" + o + ", tool=" + tool + "]";
  }
}
