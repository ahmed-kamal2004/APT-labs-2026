public class IdentityMatrix extends Matrix {

    public IdentityMatrix(int side_length){
            super(side_length,side_length);
    }

    @Override
    public boolean SetNumbers(int[] n) {
        int len = n.length;
        int len_square = (int) Math.sqrt(len);
        int counter = 0;
        int looper = 0;
        while(counter < len){
            if(n[counter] == 0){
                looper = (looper + 1) % len_square;
            }
            else{
                if(looper != 0){
                    return false;
                }
            }
            counter++;
        }
        return super.SetNumbers(n);c
    }

    public void Transpose(){
        // Same
    }
}
