package com.agrawroh.selemize.utilities;

/**
 * Tuple Class
 * 
 * @author agraw_ds7m
 * @version 2016-11-18 v1.0
 */
public class Tuple<A, B> {

    private A firstValue;
    private B secondValue;

    /* Default Constructor */
    public Tuple(A firstValue, B secondValue) {
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }

    /* Copy Constructor */
    public Tuple(Tuple<A, B> tuple) {
        this.firstValue = tuple.getFirstValue();
        this.secondValue = tuple.getSecondValue();
    }

    /**
     * @return the firstValue
     */
    public A getFirstValue() {
        return firstValue;
    }

    /**
     * @param firstValue
     *            the firstValue to set
     */
    public void setFirstValue(A firstValue) {
        this.firstValue = firstValue;
    }

    /**
     * @return the secondValue
     */
    public B getSecondValue() {
        return secondValue;
    }

    /**
     * @param secondValue
     *            the secondValue to set
     */
    public void setSecondValue(B secondValue) {
        this.secondValue = secondValue;
    }

    @Override
    public boolean equals(Object tuple) {
        /* Check Instance */
        if (null == tuple || !(tuple instanceof Tuple<?, ?>)) {
            return false;
        }

        /* Check & Return Equals */
        Tuple<?, ?> givenTuple = (Tuple<?, ?>) tuple;
        return this.getFirstValue().getClass() == givenTuple.getFirstValue()
                .getClass()
                && this.getSecondValue().getClass() == givenTuple
                        .getSecondValue().getClass()
                && this.getFirstValue().equals(givenTuple.getFirstValue())
                && this.getSecondValue().equals(givenTuple.getSecondValue());
    }

    @Override
    public int hashCode() {
        /* Generate Unique HashCode */
        int result = 17;

        result = 31 * result + firstValue.hashCode();
        result = 47 * result + secondValue.hashCode();

        /* Return HashCode */
        return result;
    }
}
