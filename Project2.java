//Fernando Vargas
//Project 2: Minimize Number Late Algorithm Testing
//11/9/2022
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.io.File;
import java.util.Comparator;




public class Project2
{
    public static int LATE(Job[] jobs)
    {

        int currENDTIME = 0;

        for(Job job : jobs)
        {
            job.start = currENDTIME;
            job.finish = currENDTIME + job.processT;
            currENDTIME += job.processT;
        }

        int count = 0;
        for(Job job : jobs)
        {
            if(job.finish > job.deadLine)
            {
                count++;
            }
        }
        return count;
    }



    public static void main(String[] args) {
        Job[] jobs;
        try {
            Scanner scanner = new Scanner(new File("input.txt"));
            int size = Integer.parseInt(scanner.nextLine());
            jobs = new Job[size];

            for (int i = 0; i < size; i++) {
                String[] line = scanner.nextLine().split(" ");
                int processT = Integer.parseInt(line[0]);
                int deadLine = Integer.parseInt(line[1]);
                jobs[i] = new Job(processT, deadLine);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            return;
        }

        //from java library
        Arrays.sort(jobs, new EDFcompare());
        System.out.println("EDF: " + LATE(jobs));


        Arrays.sort(jobs, new SJFcompare());
        System.out.println("SJF: " + LATE(jobs));


        Arrays.sort(jobs, new LSFcompare());
        System.out.println("LSF: " + LATE(jobs));

    }
}

class Job{

    int deadLine;
    int processT;
    int start;
    int finish;

    public Job(int processT, int deadLine)
    {
        this.processT = processT;
        this.deadLine = deadLine;
    }


}


class EDFcompare implements Comparator<Job>
{

    public int compare(Job job1, Job job2)
    {
        return job1.deadLine - job2.deadLine;
    }
}
class SJFcompare implements Comparator<Job>
{

    public int compare(Job job1, Job job2)
    {
        return job1.processT - job2.processT;
    }
}

class LSFcompare implements Comparator<Job>
{

    public int compare(Job job1, Job job2)
    {
        return (job1.deadLine - job1.processT) - (job2.deadLine - job2.processT);
    }
}

