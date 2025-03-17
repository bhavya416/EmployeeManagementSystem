package com.java.practice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StringsPractice {

	public static void main(String[] args) {
		List<String> l = new ArrayList<>();
		l.add("Hello");
		l.add("World");
		l.add("Banana");
		l.add("Eclipse");
		l.add("GitHub");
		l.add("Bananaland");
		l.add("Hello");
		String s = "aabbccddefg";

//		StringWithMostUniqueChars(l);
//		MostFrequentCharInaString(l);
//		sortStringsByDistinctChars(l);
//		StringwithHighestVowelCount(l);
//		StringswithoutDuplicateChars(l);
//		StringsThatAppearsMost(l);
//		firstRepeatingChar(s);
		firstNonRepeatingChar(s);

	}

	private static void StringWithMostUniqueChars(List<String> l) {
		String o1 = l.stream().max(Comparator.comparing(String::length)).orElse(null);
		System.out.println("StringWithMaxSize   " + o1);

		String o2 = l.stream().max(Comparator.comparingLong(s -> s.chars().distinct().count())).orElse(null);
		System.out.println("StringWithMostUniqueChars   " + o2);

		String o3 = l.stream().max(Comparator.comparing(String::length).reversed()).orElse(null);
		System.out.println("StringWithleastSize   " + o3);

		// groupingBy(Function<T, K>) o/p:Map<K, List<T>> Simple grouping
		// groupingBy(Function<T, K>, Collector<T, A, D>) ,o/p Map<K, D> Grouping +
		// transformation (e.g., count, sum)
		// groupingBy(Function<T, K>, Supplier<M>, Collector<T, A, D>) ,o/p M<K, D>
		// Custom map type + grouping

		Map<Integer, List<String>> o4 = l.stream().collect(Collectors.groupingBy(String::length));
		System.out.println("GroupByLength   " + o4);

	}

	private static void MostFrequentCharInaString(List<String> l) {

	}

	private static void sortStringsByDistinctChars(List<String> l) {
		List<String> o = l.stream()
				.sorted(Comparator.comparingLong((String s) -> s.chars().distinct().count()).reversed())
				.collect(Collectors.toList());
		System.out.println("sortStringByDistinctChars   " + o);
	}

	private static void StringwithHighestVowelCount(List<String> l) {
		// vowels a,e,i,o,u
		Map<Character, Integer> freqMap = new HashMap<>();
		Map<String, Integer> countMap = new HashMap<>();
		freqMap.put('a', 0);
		freqMap.put('e', 0);
		freqMap.put('i', 0);
		freqMap.put('o', 0);
		freqMap.put('u', 0);
		for (String s : l) {
			int sum = 0;
			for (Character c : s.toCharArray()) {
				if (freqMap.containsKey(c)) {
					// freqMap.put(c,freqMap.getOrDefault(c, 0)+1);
					sum = sum + 1;
				}

			}
			countMap.put(s, sum);

		}

		System.out.println("StringwithHighestVowelCount:::"
				+ Collections.max(countMap.entrySet(), Map.Entry.comparingByValue()).getKey());
	}

	private static void StringswithoutDuplicateChars(List<String> l) {

		List<String> o = l.stream().filter(StringsPractice::methodStringswithoutDuplicateChars).collect(Collectors.toList());
		System.out.println("StringswithoutDuplicateChars::" + o);

	}

	private static boolean methodStringswithoutDuplicateChars(String s) {
		Set<Character> st = new HashSet<>();
		boolean flag = true;
		for (char c : s.toCharArray()) {
			if (st.contains(c)) {
				flag = false;
			}
			st.add(c);
		}
		return flag;

	}

	private static void StringsThatAppearsMost(List<String> l) {
		
		Map<String, Long> m= l.stream().collect(Collectors.groupingBy(s->s,Collectors.counting()));
	
		
		System.out.println("StringsThatAppearsMost:::"
				+ Collections.max(m.entrySet(),Map.Entry.comparingByValue()).getKey());
		;
	}
	
	//abbccddefg
	private static void firstRepeatingChar(String s) {
		
		Set<Character> set = new LinkedHashSet<>();
		Set<Character> duplicateset = new LinkedHashSet<>();
		for(int i=0;i<s.length();i++) {
			if(set.contains(s.charAt(i))) {
				duplicateset.add(s.charAt(i));
			}else {
			set.add(s.charAt(i));}
		}
		
		duplicateset.stream().findFirst().ifPresent(System.out::println);
		
	}
	
	//aabbccddefg
	private static void firstNonRepeatingChar(String s) {
		Map<Character,Long> map = new LinkedHashMap<>();
	
		map=	s.chars().mapToObj(c->(char)c).collect(Collectors.groupingBy(c->c,LinkedHashMap::new,Collectors.counting()));
		System.out.println(map.entrySet().stream().filter(e->e.getValue()==1).map(e->e.getKey()).findFirst());
		
		
		
	}



}
