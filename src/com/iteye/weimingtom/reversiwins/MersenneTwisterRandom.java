package com.iteye.weimingtom.reversiwins;

/**
 * Mersenne Twister with improved initialization (2002)
 * @see http://www.math.sci.hiroshima-u.ac.jp/~m-mat/MT/MT2002/mt19937ar.html
 * @see http://www.math.sci.hiroshima-u.ac.jp/~m-mat/MT/MT2002/CODES/mt19937ar.c
 * 
 * changed:
 * 		1. init_genrand: mt[mti], add temp
 * 		2. >>: >>>
 * 		3. uint => int
 * 		4. long genrand_int32()
 * 		5. y & 0xffffffffL
 */
public class MersenneTwisterRandom {
	private static final int N = 624;
	private static final int M = 397;
	private static final int MATRIX_A = 0x9908b0df;
	private static final int UPPER_MASK = 0x80000000;
	private static final int LOWER_MASK = 0x7fffffff;
	private int[] mt = new int[N];
	private int mti = N + 1;
	
	public void init_genrand(int s) {
		mt[0] = s & 0xffffffff;
		for (mti = 1; mti < N; mti++) {
			int temp = (1812433253 * (mt[mti - 1] ^ (mt[mti - 1] >>> 30))) & 0xFFFFFFFF;
			mt[mti] = temp + mti;
			mt[mti] &= 0xffffffff;
		}
	}
	
	public void init_by_array(int[] init_key, int key_length) {
		int i, j, k;
		init_genrand(19650218);
		i = 1;
		j = 0;
		k = (N > key_length ? N : key_length);
		for (; k != 0; k--) {
			mt[i] = (mt[i] ^ ((mt[i - 1] ^ (mt[i - 1] >>> 30)) * 1664525)) + init_key[j] + j;
			mt[i] &= 0xffffffff;
			i++; 
			j++;
			if (i >= N) { 
				mt[0] = mt[N - 1]; 
				i = 1; 
			}
			if (j >= key_length) {
				j = 0;
			}
		}
		for (k = N - 1; k != 0; k--) {
			mt[i] = (mt[i] ^ ((mt[i - 1] ^ (mt[i - 1] >>> 30)) * 1566083941)) - i;
			mt[i] &= 0xffffffff;
			i++;
			if (i >= N) { 
				mt[0] = mt[N - 1];
				i = 1; 
			}
		}
		mt[0] = 0x80000000;
	}
	
	public long genrand_int32() {
		int y;
		int[] mag01 = {0x0, MATRIX_A};
		if (mti >= N) {
			int kk;	
			if (mti == N + 1) {
				init_genrand(5489);
			}
			for (kk = 0; kk < N - M; kk++) {
				y = (mt[kk] & UPPER_MASK) | (mt[kk + 1] & LOWER_MASK);
				mt[kk] = mt[kk + M] ^ (y >>> 1) ^ mag01[y & 0x1];
			}
			for (; kk < N - 1; kk++) {
				y = (mt[kk] & UPPER_MASK) | (mt[kk + 1] & LOWER_MASK);
				mt[kk] = mt[kk + (M - N)] ^ (y >>> 1) ^ mag01[y & 0x1];
			}
			y = (mt[N - 1] & UPPER_MASK) | (mt[0] & LOWER_MASK);
			mt[N - 1] = mt[M - 1] ^ (y >>> 1) ^ mag01[y & 0x1];
			mti = 0;
		}
		y = mt[mti++];
		y ^= (y >>> 11);
		y ^= (y << 7) & 0x9d2c5680;
		y ^= (y << 15) & 0xefc60000;
		y ^= (y >>> 18);
		return y & 0xffffffffL;
	}
	
	public int genrand_int31() {
		return (int)(genrand_int32() >> 1);
	}
	
	public double genrand_real1() {
		return genrand_int32() * (1.0 / 4294967295.0); 
	}
	
	public double genrand_real2() {
		return genrand_int32() * (1.0 / 4294967296.0);
	}
	
	public double genrand_real3() {
		return ((double)(genrand_int32()) + 0.5) * (1.0 / 4294967296.0);
	}
	
	public double genrand_res53() { 
		long a = genrand_int32() >>> 5, b = genrand_int32() >>> 6;
		return(a * 67108864.0 + b) * (1.0 / 9007199254740992.0);
	}
	
	public MersenneTwisterRandom() {
		
	}
	
    public int nextInt(int min, int max) {
        return min + (int)Math.floor(genrand_real2() * (max - min + 1));
    }
	
	public static final void main(String[] args) {
		{
			String strTrace = "";
			int i;
			int[] init = {0x123, 0x234, 0x345, 0x456};
			MersenneTwisterRandom random = new MersenneTwisterRandom();
			random.init_by_array(init, init.length);
			strTrace += "1000 outputs of genrand_int32()\n";
			for (i = 0; i < 1000; i++) {
				strTrace += random.genrand_int32() + " ";
				if (i % 5 == 4) {
					strTrace += "\n";
				}
			}
			strTrace += "\n1000 outputs of genrand_real2()\n";
			for (i = 0; i < 1000; i++) {
				strTrace += random.genrand_real2() + " ";
				if (i % 5 == 4) 
				{
					strTrace += "\n";
				}
			}
			System.out.println(strTrace);
		}
		{
			//coin test
			MersenneTwisterRandom mt = new MersenneTwisterRandom();
			mt.init_genrand((int)System.currentTimeMillis());
			int heads = 0;
			int tails = 0;
			for (int j = 1; j <= 1000000; j++) {
				int i = (int)Math.floor(mt.genrand_real2() * 2);
				if (i == 0) { 
					heads ++;
				} else { 
					tails ++;
				}
			}
			System.out.println("heads = " + heads);
			System.out.println("tails = " + tails);
		}
	}
}
