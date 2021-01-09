package com.maeverose.game.character;

import java.util.Random;

public abstract class BaseCharacter {
    private String CharacterName;
    private final Pronoun[] pronounSet = {null,null};



    public String selectPronoun(Pronoun.PronounType type) throws Pronoun.NoPronounSetException {
        if(pronounSet[0] != null && pronounSet[1] != null)
        {
            Random r = new Random();
            r.setSeed(System.currentTimeMillis());
            return r.nextInt(100) > 60 ? pronounSet[1].getPronoun(type.ordinal()) : pronounSet[0].getPronoun(type.ordinal());
        }
        if(pronounSet[0] != null && pronounSet[1] == null)
        {
            return pronounSet[0].getPronoun(type.ordinal());
        }
        if(pronounSet[0] == null && pronounSet[1] != null)
        {
            pronounSet[0] = pronounSet[1];
            pronounSet[1] = null;
            return pronounSet[0].getPronoun(type.ordinal());
        }
        throw new Pronoun.NoPronounSetException();
    }
}
