package org.litejunit.v1;

/**
 * Created by DonaldY on 2017/8/22.
 */
public interface Test {
    public abstract int countTestCases();
    public abstract void run(TestResult tr);
}
