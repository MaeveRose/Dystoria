package com.maeverose.game.character;

public class Pronoun{
    private final String[] pronounArray ={"","","","",""};//Subject, Object, Possesive Adjective, Possessive Pronoun, Reflexive
    public enum PronounType
    {
        Subject,Object,PossAdj,PossPro,Reflexive
    }
    public static class NoPronounSetException extends Exception{

    }
    public interface CommonPronouns
    {
        Pronoun FEMALE_STARK = new Pronoun("she","her","her","hers","herself");
        Pronoun MALE_STARK = new Pronoun("he","him","his","his","himself");
        Pronoun THEY_STARK = new Pronoun("they","them","their","theirs","themself");
        Pronoun XE_STARK = new Pronoun("xe","xer","xir","xirs","xirself");
        Pronoun ZE_STARK = new Pronoun("ze","zir","zir","zirs","zirself");
        Pronoun SIE_STARK = new Pronoun("sie","hir","hir","hirs","hirself");
        //public static final Pronoun TONY_STARK = new Pronoun("i","am","ironman","tony's","tonyself");
    }

    private Pronoun(String s, String t, String u, String v, String w)
    {


    }
    public String getPronoun(int i) throws NoPronounSetException {
        if (i >= 5 || i <= 0) throw new NoPronounSetException();
        return this.pronounArray[i];
    }

    public Pronoun makePronoun(String subject, String object, String possessiveAdjective, String possesive, String reflexive)
    {
        return null;
    }
    public boolean equals(Pronoun o)
    {
        for(int i = 0; i < 5; i++)
        {
            if(!pronounArray[i].equalsIgnoreCase(o.pronounArray[i])) return false;
        }
        return true;

    }
}
