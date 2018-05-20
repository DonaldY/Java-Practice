package com.donald.bank;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;


/**
 * Created by DonaldY on 2017/4/3.
 */
public class BankTest {



    @Test
    public void testBank() {
        ArrayList<Process> processes = new ArrayList<>();
        Integer [] allocation = {0, 1, 0};
        Integer [] maxs = {7, 5, 3};
        //Integer [] needs = {7, 4, 3};
        Process process = new Process("P0", getArrayList(allocation)
            , getArrayList(maxs));
        processes.add(process);

        allocation = new Integer[]{2, 0, 0};
        maxs = new Integer[] {3, 2, 2};
        //needs = new Integer[] {1, 2, 2};
        process = new Process("P1", getArrayList(allocation)
            , getArrayList(maxs));
        processes.add(process);

        allocation = new Integer[]{3, 0, 2};
        maxs = new Integer[] {9, 0, 2};
        //needs = new Integer[] {6, 0, 0};
        process = new Process("P2", getArrayList(allocation)
            , getArrayList(maxs));
        processes.add(process);

        allocation = new Integer[]{2, 1, 1};
        maxs = new Integer[] {2, 2, 2};
        //needs = new Integer[] {0, 1, 1};
        process = new Process("P3", getArrayList(allocation)
            , getArrayList(maxs));
        processes.add(process);

        allocation = new Integer[]{0, 0, 2};
        maxs = new Integer[] {4, 3, 3};
        //needs = new Integer[] {4, 3, 1};
        process = new Process("P4", getArrayList(allocation)
            , getArrayList(maxs));
        processes.add(process);


        ArrayList<Integer> availables = getArrayList(new Integer[]{3, 3, 2});
        ArrayList<String> resources = getArrayList(new String[]{"A", "B", "C"});

        Bank bank = new Bank(resources, availables, processes);
        System.out.println(bank.getProcessesDetail());

        bank.bankWork();

    }

    private ArrayList getArrayList(Object [] array) {
        ArrayList<Object> list = new ArrayList<Object>(array.length);
        Collections.addAll(list, array);
        return list;
    }

    @Test
    public void testMatrix() {
        Integer [][] allocations = {
            {0, 1, 0}
            , {2, 0, 0}
            , {3, 0, 2}
            , {2, 1, 1}
            , {0, 0, 2}};

        Integer [][] maxs = {
            {7, 5, 3}
            , {3, 2, 2}
            , {9, 0, 2}
            , {2, 2, 2}
            , {4, 3, 3}};

        ArrayList<Integer> availables = getArrayList(new Integer[]{3, 3, 2});
        ArrayList<String> resources = getArrayList(new String[]{"A", "B", "C"});
        ArrayList<String> processName = getArrayList(new String[]{"P0", "P1", "P2", "P3", "P4"});

        ArrayList<Process> processes = ProcessUtils.getProcesses(processName, allocations, maxs);

    }
}
