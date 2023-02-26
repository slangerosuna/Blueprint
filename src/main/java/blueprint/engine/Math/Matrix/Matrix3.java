package blueprint.engine.Math.Matrix;

public class Matrix3 {
    public static final int SIZE = 3;
    private float[] elements = new float[SIZE * SIZE];

    public static Matrix3 identity() {
		Matrix3 result = new Matrix3();
		for(int x = 0; x < SIZE; x++) {
			for(int y = 0; y < SIZE; y++) {
				result.set(x, y, 0);
			}
		}
		
		result.set(0, 0, 1);
		result.set(1, 1, 1);
		result.set(2, 2, 1);
		
		return result;
	}
    public float get(int x, int y) {
		return elements[x + (y * SIZE)];
	}
	
	public void set(int x, int y, float value) {
		elements[x + (y * SIZE)] = value;
	}
	public float[] getAll(){
		return elements;
	}
    public float determinant(){
        float result = 0;

        for(int i = 0; i < SIZE; i++){
            result += get(i, 0) * subMatrix(i).determinant();
        }
        
        return result; 
    }
    public Matrix2 subMatrix(int i){
        Matrix2 result = Matrix2.identity();
        for(int x = 0; x < SIZE; x++) { if(x != i) { for(int y = 1; y < SIZE; y++) { result.set(x > 1 ? x - 1 : x, y - 1, get(x, y)); } } }
        return result;
    }
}
