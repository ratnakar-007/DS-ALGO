package DynamicProgramming;

public class CountBoardPath {
    public static int cbp (int s, int d, int[] mem) {

        if (s > d) return 0;

        if (s==d) return 1;

        if (mem[s]!= 0) return  mem[s];

        int cntStoD = 0;

        for (int dice = 1; dice <=6 ; dice ++){
            int i = s + dice;
            int cntItoD = cbp(i, d, mem);
            cntStoD += cntItoD;
        }
        mem[s] = cntStoD;
        return mem[s];
    }

    public static int cbpTabu(int s,int d, int[] mem){
        int[] f = new int[d + 1];
        f[d] = 1;

        for (int x = d - 1; x>=0 ; x--){
            for (int dice = 1 ; dice<=6 ; dice++ ){
                if (x + dice < d){
                    f[x] += f[x+dice];
                }
            }
        }
        return f[0];
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        System.out.println(cbp(0, 30, new int[30]));
        long end = System.currentTimeMillis();
        System.out.println(end - start  );
    }
}
