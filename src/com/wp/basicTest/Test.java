package com.wp.basicTest;

/**
 * @author wenpeng
 * @date 创建时间： 2017年5月10日 下午5:44:21
 * @description 
 */
public class Test {

	public static void main(String[] args) {
		
		int[] arr = new int[]{1,1,3,3,3,1,1};
		int cnt = 0;
		int val = 0;
		for(int i=0;i<arr.length;i++){
			if(cnt==0){
				val = arr[i];
				cnt++;
			}else if(arr[i]==val){
				cnt++;
				System.out.println("cnt ="+cnt);
			}else{
				cnt--;
			}
		}
		System.out.println(val);
	}
}
