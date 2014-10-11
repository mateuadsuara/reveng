package com.github.demonh3x.reveng;

public interface Alchemist<Source, Another> {
    Another transmuteForwards(Source source);
    Source transmuteBackwards(Another another);
}
