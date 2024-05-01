public class Matrix implements Addable{
    private int[][] Numbers;
    public int M; // num of rows
    public int N; // num of columns

    //constructor
    public Matrix(){};
    public Matrix(int m,int n){
        this.M = m;
        this.N = n;

        this.Numbers = new int[this.M][this.N];
    }

    public boolean SetNumbers(int [] n){
        if(n.length != this.N * this.M)
            return false;
        int counter = 0;
        for(int i = 0;i<this.M;i++){
            for(int j =0;j<this.N;j++){
                this.Numbers[i][j] = n[counter++];
            }
        }
        return true;
    }

    public void Print(){
        for(int i = 0;i<this.M;i++){
            for(int j =0;j<this.N;j++){
                System.out.print("  "+this.Numbers[i][j]);
            }
            System.out.println("");
        }

        System.out.println("");            System.out.println("");
    }

    public void Transpose(){
        int swapper = this.M;
        this.M = this.N;
        this.N = this.M;

        int[][]newer_arr = new int[this.M][this.N];

        for(int i =0;i<this.M;i++){
            for(int j =0;j<this.N;j++){
                newer_arr[i][j] = this.Numbers[j][i];
            }
        }
        this.Numbers = newer_arr;
    }

    public Addable Add(Addable x){
        if(x instanceof Matrix){
            if(((Matrix)x).N == this.N  && ((Matrix)x).M == this.M){
                Matrix new_mat = new Matrix(this.M,this.N);
                int counter = 0;
                int[] arr = new int[this.N*this.M];
                for(int i =0;i<this.M;i++){
                    for(int j =0;j<this.N;j++){
                        arr[counter++] = this.Numbers[i][j] + ((Matrix) x).Numbers[i][j];
                    }
                }
                new_mat.SetNumbers(arr);
                return new_mat;
            }
        }
        return this;
    }

}
