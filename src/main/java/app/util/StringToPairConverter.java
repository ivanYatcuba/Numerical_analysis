package app.util;

import app.util.data.Pair;

public abstract class StringToPairConverter<T, V> {

    private static final char EMPTY_CHAR = ' ';
    public static final char END_OF_LINE = '\n';

    protected abstract T stringToT(String in);
    protected abstract V stringToV(String in);

    public Pair<T, V> toPair(String input) {

        input += END_OF_LINE;

        final char[] chars = input.toCharArray();

        final StringBuilder sb = new StringBuilder();

        T tValue = null;
        V vValue = null;

        boolean isGrabbingValue = false;
        boolean isFirstValueFound = false;

        for(char c: chars) {
            if(c != EMPTY_CHAR) {
                isGrabbingValue = true;
                if(c != END_OF_LINE) {
                    sb.append(c);
                }
            }
            if(c == EMPTY_CHAR || c == END_OF_LINE) {
                if(isGrabbingValue) {
                    if(isFirstValueFound) {
                        vValue = stringToV(sb.toString()); break;
                    } else {
                        tValue = stringToT(sb.toString());
                        sb.setLength(0);
                        isFirstValueFound = true;
                    }
                }
                isGrabbingValue = false;
            }
        }

        return new Pair<>(tValue, vValue);
    }

}
