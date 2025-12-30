package com.practice.string.functions;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EvaluateJson {

	public static void main(String[] args) throws JsonProcessingException, IOException {
		String ruleData = "{\"attributeKey\" : \"ext,sii,sgn\", \"dataType\":\"array,array,number\"}";
		String eventData = "{\"ext\" : [{\"sii\": {\"1\": {\"sgn\" : 20},\"2\": {\"sgn\" : 25},\"3\": {\"sgn\" : 30}}},{\"sii\": {\"1\": {\"sgn\" : 10},\"2\": {\"sgn\" : 15},\"3\": {\"sgn\" : 5}}}]}";
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode rule = mapper.readTree(ruleData);
		JsonNode event = mapper.readTree(eventData);
		String[] attributeKey = rule.get("attributeKey").asText().split(",");
		String[] dataType = rule.get("dataType").asText().split(",");
		if (attributeKey.length != dataType.length) {
			System.out.println("Attributekey and dataType length should match");
			return;
		}
		Set<Map<String, String>> results = new HashSet<>();
		int level = 0;
		traverseAndValidate(results, event, attributeKey, dataType, level);
		for (Map<String, String> map : results) {
			System.out.println(mapper.writeValueAsString(map));
		}

	}

	private static void traverseAndValidate(Set<Map<String, String>> results, JsonNode event, String[] attributeKey,
			String[] dataType, int level) {
		if (level >= attributeKey.length)
			return;
		String currentKey = attributeKey[level];
		String currentType = dataType[level];
		JsonNode childNode = event.get(currentKey);
		if ("array".equalsIgnoreCase(currentType)) {
			if (!childNode.isArray()) {
				System.out.println(childNode + " not a array.");
				return;
			}
			for (JsonNode jsonNode : childNode) {
				traverseAndValidate(results, jsonNode, attributeKey, dataType, level + 1);
			}

		} else if ("object".equalsIgnoreCase(currentType)) {
			if (!childNode.isObject()) {
				System.out.println(childNode + " not a object.");
				return;
			}
			childNode = event.get(currentKey);
			for (JsonNode jsonNode : childNode) {
				traverseAndValidate(results, jsonNode, attributeKey, dataType, level + 1);
			}

		} else if ("number".equalsIgnoreCase(currentType)) {
			if (!childNode.isNumber()) {
				System.out.println(childNode + " not a number.");
				return;
			}
			Map<String, String> map = new HashMap<>();
			map.put("attributeKey", childNode.asText());
			results.add(map);
		} else if ("string".equalsIgnoreCase(currentType)) {
			if (!childNode.isTextual()) {
				System.out.println(childNode + " not a string.");
				return;
			}
			Map<String, String> map = new HashMap<>();
			map.put("attributeKey", childNode.asText());
			results.add(map);
		}

	}
}
