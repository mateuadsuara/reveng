package com.github.demonh3x.alchemy;

public interface Alchemist<Source, Another> {
    Another transmuteForwards(Source source);
    Source transmuteBackwards(Another another);
}
