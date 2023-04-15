/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shortestnonpreemptive;

import java.util.Comparator;

/**
 *
 * @author Yara Farid
 */
public class SJFNonCmp implements Comparator<Process> {

    public int compare(Process p1, Process p2) {
        return (p1.getStartTime() > p2.getStartTime() ? 1 : -1);
    }
}
