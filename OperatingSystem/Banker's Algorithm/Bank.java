package com.donald.bank;

import java.util.ArrayList;

/**
 * Created by DonaldY on 2017/4/3.
 */
public class Bank {

    private ArrayList<Process> processes;
    private ArrayList<String> resources;
    private ArrayList<Integer> availables;
    private ArrayList<Boolean> flags;

    public Bank(ArrayList resources, ArrayList availables
        , ArrayList processes) {
        this.processes = processes;
        this.resources = resources;
        this.availables = availables;
        this.flags = new ArrayList<Boolean>(processes.size());
        for (int i = 0 ; i < processes.size(); ++i)
            flags.add(false);
    }

    public void bankWork() {
        // To determine whether to change with the tag
        boolean tag;
        while (true) {
            tag = false;
            for (int i = 0; i < processes.size(); ++i) {
                if (isAllocation(i) && !flags.get(i)) {
                    for (int j = 0 ; j < resources.size(); ++j) {
                        int oldAvailable = availables.get(j);
                        int addition = processes.get(i).getAllocation(j);
                        availables.set(j, oldAvailable + addition);
                    }

                    flags.set(i, true);
                    tag = true;
                }
            }

            if (isAllTrue()) {
                System.out.println("safe.");
                break;
            }

            if (!tag) {
                System.out.println("unsafe.");
                break;
            }
        }

    }

    private Boolean isAllocation(int index) {
        Process process = processes.get(index);
        for (int i = 0 ; i < resources.size(); ++i)
            if (availables.get(i) < process.getNeedAlloc(i))
                return false;

        return true;
    }

    private Boolean isAllTrue() {
        System.out.println();
        for (Boolean flag : flags)
            if (flag == false)
                return false;
        return true;
    }


    public String getProcessesDetail() {
        StringBuilder sb = new StringBuilder();
        sb.append("processName \t  Allocation \t   Max  \t     Need \t  Available\n");
        for (Process process : processes) {
            sb.append(getProcessDetail(process));
            sb.append("\n");
        }
        return sb.toString();
    }

    public String getProcessDetail(Process process) {

        String detail = process.getName()
            + "          \t   " + getListName(process.getAllocations())
            + "  \t  " + getListName(process.getMaxAllocs())
            + "\t   " + getListName(process.getNeedAllocs());

        return detail;
    }

    ///////////////////////Backup  ///////////////////////////
    public String getListName(ArrayList arrayList) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < arrayList.size(); ++i) {
            sb.append(arrayList.get(i));

            if (i + 1 < arrayList.size())
                sb.append(",");
        }

        sb.append("]");
        return sb.toString();
    }
}
