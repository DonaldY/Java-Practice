package com.donald.bank;


import java.util.ArrayList;

/**
 * Created by DonaldY on 2017/4/3.
 */
public class Process {
    private String name;
    private ArrayList<Integer> allocations;
    private ArrayList<Integer> maxAllocs;
    private ArrayList<Integer> needAllocs;

    public Process(String name, ArrayList allocationList
        , ArrayList maxAllocs) {
        this.name = name;
        this.allocations = allocationList;
        this.maxAllocs = maxAllocs;
        calucateNeed();
    }

    private void calucateNeed() {
        needAllocs = new ArrayList<>(maxAllocs.size());
        for (int i = 0 ; i < maxAllocs.size(); ++i) {
            int max = maxAllocs.get(i);
            int ready = allocations.get(i);
            if (max < ready)
                throw new RuntimeException("Process : " + name + ", index : " + i
                    + ", max : " + max + ", ready : " + ready);
            needAllocs.add(max - ready);
        }
    }
    public String getName() {
        return name;
    }

    public Integer getAllocation(int index) {
        return allocations.get(index);
    }

    public ArrayList getAllocations() {
        return allocations;
    }

    public ArrayList getMaxAllocs() {
        return maxAllocs;
    }

    public ArrayList getNeedAllocs() {
        return needAllocs;
    }

    public Integer getNeedAlloc(int index) {
        return needAllocs.get(index);
    }
}
