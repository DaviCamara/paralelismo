import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

class SumForkJoin extends RecursiveTask<Long> {
    int low;
    int high;
    long[] array;
    SumForkJoin(long[] arr, int lo, int hi) {
        array = arr;
        low = lo;
        high = hi;
    }
    protected Long compute() {
        if(high - low <= 1) {
            System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaa"+ array[high]);
            return array[high];
        }
        else {
            int mid = low + (high - low) / 2;
            SumForkJoin left = new SumForkJoin(array, low, mid);

            SumForkJoin right = new SumForkJoin(array, mid, high);
            Long aux = array[0];
            left.fork();
            right.fork();

            array[0] = left.join() + right.join() + aux;
            System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbbbbbb" + left.join() + right.join());
            return left.join() + right.join();
        }
    }

    public static void main(String[] args) {
        long[] myArr = new long[6];
        myArr[0] = 3;

        myArr[1] = 12;

        myArr[2] = 45;

        myArr[3] = 23;

        myArr[4] = 11;

        myArr[5] = 20;
        ForkJoinPool pool = new ForkJoinPool();
        long SumForkJ = pool.invoke(new SumForkJoin(myArr, 0, 5));
        System.out.println("ccccccccccccccccccccccccccc" +myArr[0]);
    }

}