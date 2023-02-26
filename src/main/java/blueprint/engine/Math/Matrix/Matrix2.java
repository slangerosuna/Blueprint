package blueprint.engine.Math.Matrix;

public class Matrix2 {
    public static final int SIZE = 2;
    private float[] elements = new float[SIZE * SIZE];

    public static Matrix2 identity() {
		Matrix2 result = new Matrix2();
		for(int x = 0; x < SIZE; x++) {
			for(int y = 0; y < SIZE; y++) {
				result.set(x, y, 0);
			}
		}
		
		result.set(0, 0, 1);
		result.set(1, 1, 1);
		
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
        return get(0, 0) * get(1, 1) - get(0, 1) * get(1, 0);
    }
}
