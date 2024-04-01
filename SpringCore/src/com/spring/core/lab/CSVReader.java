package com.spring.core.lab;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

// 將 data.csv 轉 List<Map<String, String>>
public class CSVReader {

	public static void main(String[] args) throws Exception {
		String filePath = "src/com/spring/core/lab/data.csv";
		List<Map<String, String>> dataList = readCsv(filePath);
		// 印出
		System.out.println(dataList.size());
		//dataList.forEach(data -> System.out.println(data));
		
		System.out.println("及格");
		dataList.stream().filter(map -> Double.parseDouble(map.get("score")) >= 60).forEach(System.out::println);
		
		System.out.println("不及格");
		dataList.stream().filter(map -> Double.parseDouble(map.get("score")) < 60).forEach(System.out::println);
		
	}
	
	public static List<Map<String, String>> readCsv(String filePath) throws Exception {
		List<Map<String, String>> dataList = new ArrayList<>();
		
		// 讀取 data.csv
		List<String> allLines = Files.readAllLines(Path.of(filePath));
		if(allLines.isEmpty()) {
			return dataList;
		}
		
		// 將第二行(.get(1))作為標題
		String[] keys = allLines.get(1).split(",");
		
		// 從第三行開始遍歷讀取資料
		for(int i=2;i<allLines.size();i++) {
			// 分割每一行的數據
			String[] values = allLines.get(i).split(",");
			// 準備一個 Map 容器存放資料
			Map<String, String> rowMap = new LinkedHashMap<>();
			for(int j=0;j<keys.length;j++) {
				rowMap.put(keys[j].trim(), values[j].trim());
			}
			// 將 map 加入到 list 中
			dataList.add(rowMap);
		}
		
		return dataList;
	}

}
