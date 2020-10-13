package javaDsAlgoCN;

public class ComplexNumbers {
    private int R;
    private int I;

    public ComplexNumbers(int R, int I){
        this.R = R;
        this.I = I;
    }
    public void plus(ComplexNumbers C2){
        this.R = this.R + C2.R;
        this.I = this.I + C2.I;
    }
    public void multiply(ComplexNumbers C2){
        int r1 = this.R;
        int i1 = this.I;
        int r2 = C2.R;
        int i2 = C2.I;
        this.R = (r1*r2 - i1*i2);
        this.I = (r1*i2 + i1*r2);
    }
    public void print(){
        int r;
        int i;
        r = this.R;
        i = this.I;
        if(i < 0){
            System.out.println(r + " - i" + i);
        } else {
            System.out.println(r + " + i" + i);
        }
    }

}
