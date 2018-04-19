package com.example.others.hash;

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import java.text.DecimalFormat;

/**
 * 分桶hash ，使用murmur3_128hash的方式取高低位
 * 桶为高位
 * hash值为低位
 *
 *
 */
public class BitmapHashFunction {

	long hashCodeMod;

	long hashPartitionMod;

	int hashCodeBits = 30;

	int hashPartitionBits = 6;

	DecimalFormat HASH_DF = new DecimalFormat("0000000000");// 10

	DecimalFormat PARTITION_DF = new DecimalFormat("00");

	HashFunction hashFunction = Hashing.murmur3_128();

	public BitmapHashFunction() {
		init(30, 6);
	}
	
	public BitmapHashFunction(int hashCodeBits, int hashPartitionBits) {
		init(hashCodeBits,hashPartitionBits);
	}

	private void init(int hashCodeBits, int hashPartitionBits) {
		if ((hashCodeBits >= 0 && hashCodeBits <= 30 && hashPartitionBits >= 0 && hashPartitionBits <= (64 - hashCodeBits))) {
			this.hashCodeBits = hashCodeBits;
			this.hashPartitionBits = hashPartitionBits;
		}

		long hashCodeMod = 0;
		for (int i = 0; i < hashCodeBits; i++) {
			hashCodeMod |= (1L << i);
		}
		long hashPartitionMod = 0;
		for (int i = 0; i < hashPartitionBits; i++) {
			hashPartitionMod |= (1L << (i + hashCodeBits));
		}

		this.hashCodeMod = hashCodeMod;
		this.hashPartitionMod = hashPartitionMod;
	}


	/**
	 * 得到字符串的hash值进行前缀补零格式化
	 * @param str hash字符串
	 * @return  第一个元素为桶值，第二个为hash值
	 */
	@SuppressWarnings("deprecation")
	public String[] generateHash(String str) {
		long hash = hashFunction.hashString(str).asLong();

		int bitCode = (int) (hash & hashCodeMod);// 30

		int partition = (int) ((hash & hashPartitionMod) >> hashCodeBits);// 6

		return new String[] { PARTITION_DF.format(partition),
				HASH_DF.format(bitCode) };
	}

	/**
	 * hash的调和函数
	 * 调和函数为 （1-2^30+6）*log(1-(total/2^30+6))
	 * @param total 调和前的总数
	 * @return 调和后的总数
	 */
	public long harmonic(long total) {
		double pow = Math.pow(2, hashCodeBits + hashPartitionBits);
		total = Math.round((0 - pow) * Math.log(1 - (total / pow)));
		return total;
	}

}
