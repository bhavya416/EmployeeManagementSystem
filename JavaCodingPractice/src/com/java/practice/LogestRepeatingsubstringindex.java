package com.java.practice;

public class LogestRepeatingsubstringindex {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s ="abbbccbb";
		longestRepeatingsubstringindexbruteforce(s);
		longestUniformSubstringwithRepeatingChars(s);
	}


	private static void longestRepeatingsubstringindexbruteforce(String s) {
		int maxlen=0;
		int startindex=-1;
		for(int i=0;i<s.length();i++) {
			for(int j=i;j<s.length();j++) {
				String sub=s.substring(i, j);
				int firstoccurance = s.indexOf(sub);
				int secondoccurance = s.indexOf(sub, firstoccurance+1);
				
				if(secondoccurance!=-1 && sub.length()>maxlen ) {
					maxlen=sub.length();
					startindex=i;
					
				}
			}
		}
		int endindex=(maxlen>0) ? startindex+maxlen-1:-1;
		System.out.println("startIndex "+startindex +" endindex "+endindex);
		
	}
	//abbbccbb
	private static void longestUniformSubstringwithRepeatingChars(String s) {
		int length =1;
		int startindex=0;
		int start=0;
		int maxlen=1;
		for(int i=0;i<s.length()-1;i++) {
			if(s.charAt(i)==s.charAt(i+1)) {
				length++;
			}else {
				startindex=i+1;
				length=1;
			}
			//System.out.println(length);
			if(length>maxlen) {
				System.out.println(startindex);
				System.out.println(length);
				start=startindex;
				maxlen=length;
			}
			
			
		}
		int endindex=maxlen>1?start+maxlen-1:-1;
		System.out.println("startIndex "+start +" endindex "+endindex);
	}




}
