package javaDsAlgoCN;

class DynamicArray {
    private int data[];
    //private int nextIndex;

    public DynamicArray() {
        data = new int[5];
        //nextIndex = 0;
    }
    public void set(int index, int element) {
        if (index > data.length - 1) {
            restructure(index+1);
        }
        if (index <= data.length) {
            data[index] = element;
        }
    }
    public int get(int index) {
        //if (index >= nextIndex) {
        //	return -1;
        //}
        return data[index];
    }
    private void restructure(int nwSize) {
        int temp[] = data;
        data = new int[nwSize];
        for (int i = 0; i < temp.length; i++) {
            data[i] = temp[i];
        }
    }
}


public class Polynomial {

    DynamicArray coefficients;
    private int deg;

    public Polynomial(){
        coefficients = new DynamicArray();
        deg = 0 ;
    }
    public void setCoefficient(int degree, int coeff){
        coefficients.set(degree, coeff);
        if(this.deg < degree) this.deg = degree;
    }

    public void setCoefficientMul(int degree, int coeff){
        int curr;
        if (this.deg < degree){
            coefficients.set(degree, coeff);
            if(this.deg < degree) this.deg = degree;
        } else if (this.deg >= degree){
            curr = coefficients.get(degree);
            coefficients.set(degree, coeff + curr);
        }
    }

    public void print(){
        int coeff;
        int i;
        for(i = 0 ; i <= deg ; i++){
            coeff = coefficients.get(i);
            if (coeff != 0)
                System.out.print(coeff + "x" + i + " ");
        }
        System.out.println("");
    }

    public Polynomial add(Polynomial p){
        Polynomial res = new Polynomial();
        int i = 0;
        int j = 0;
        while(i<=this.deg && j<=p.deg){
            res.setCoefficient(i, this.coefficients.get(i) + p.coefficients.get(j));
            i++;
            j++;
        }
        if(i < this.deg){
            for(;i<=this.deg;i++){
                res.setCoefficient(i, this.coefficients.get(i));
            }
        }else if(j < p.deg){
            for(;j<=p.deg;j++){
                res.setCoefficient(j, (-1)*p.coefficients.get(j));
            }
        }

        return res;
    }

    public Polynomial subtract(Polynomial p){
        Polynomial res = new Polynomial();
        int i = 0;
        int j = 0;
        while(i<=this.deg && j<=p.deg){
            res.setCoefficient(i, this.coefficients.get(i) - p.coefficients.get(j));
            i++;
            j++;
        }
        if(i==this.deg){
            for(;j<=p.deg;j++){
                res.setCoefficient(j, p.coefficients.get(j));
            }
        }else if(j==p.deg){
            for(;i<=this.deg;i++){
                res.setCoefficient(i, this.coefficients.get(i));
            }
        }

        return res;
    }

    public Polynomial multiply(Polynomial p){
        Polynomial res = new Polynomial();
        int p1;
        int p2;
        int prod;
        int currDeg;
        for (int i = 0 ; i<=p.deg ; i++){
            p1 = p.coefficients.get(i);
            if (p1 != 0 ){
                for (int j = 0 ; j<=this.deg ; j++){
                    p2 = this.coefficients.get(j);
                    if (p2 != 0){
                        prod = p1*p2;
                        currDeg = i + j;
                        res.setCoefficientMul(currDeg, prod);
                    }
                }
            }
        }
        return res;
    }
}

