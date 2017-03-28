package com.ds;

public class HashDemo {
	
	private int a = 2;
	private int b = 3;
	private int c = 4;
	String d = "Vineet";
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + a;  //31*1 + 2
		result = prime * result + b;  //31* 33 + 3
		result = prime * result + c;  //31 * 1026 + 4
		result = prime * result + ((d == null) ? 0 : d.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HashDemo other = (HashDemo) obj;
		if (a != other.a)
			return false;
		if (b != other.b)
			return false;
		if (c != other.c)
			return false;
		if (d == null) {
			if (other.d != null)
				return false;
		} else if (!d.equals(other.d))
			return false;
		return true;
	}


	public static void main(String[] args) {
		new HashDemo().hashCode();
	}
	
/*    public int hashCode() {
        int h = hash;
        if (h == 0 && value.length > 0) {
            char val[] = value;

            for (int i = 0; i < value.length; i++) {
                h = 31 * h + val[i];  //result = 31 * result + charcode
            }
            hash = h;
        }
        return h;
    }
	*/
	
	

}
