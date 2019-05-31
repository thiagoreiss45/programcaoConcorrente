package org.example.ine5410;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.lang.Thread;
import java.lang.Runnable;

public class MergeSortThread<T extends Comparable<T>> implements MergeSort<T> {
    @Nonnull
    @Override
    public ArrayList<T> sort(@Nonnull final List<T> list) {
        //1. Há duas sub-tarefas, execute-as em paralelo usando threads
        //  (Para pegar um retorno da thread filha faça ela escrever em um ArrayList)

        if (list.size() <= 1)
            return new ArrayList<>(list);
        int mid = list.size() / 2;
        final ArrayList<ArrayList<T>> left = new ArrayList<>();
        left.add(null);

        /* ~~~~ Execute essa linha paralelamente! ~~~~ */
        Thread myThread = new Thread() {

            public void run() {
                left.set(0,MergeSortThread.this.sort(list.subList(0, mid)));

            }

        };

        myThread.start();


        ArrayList<T> right = sort(list.subList(mid, list.size()));
        try{
            myThread.join();
        }catch(Exception e){
            System.out.println("igor");
        }
        return MergeSortHelper.merge(left.get(0), right);
    }
}
