package com.pipelines.multiplicationservice.controllers;

import org.json.JSONException;
import org.json.JSONObject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MultiplicationController {

	@GetMapping(path = "/process")
	@ResponseBody
	public String Process(@RequestParam int num1, @RequestParam int num2) {
		long result = num1 * num2;
		JSONObject respObj = getResponseObj(num1, num2, result);
		return respObj.toString();
	}

	private JSONObject getResponseObj(int num1, int num2, long result) {
		JSONObject inputObj = new JSONObject();
		JSONObject resultObj = new JSONObject();
		JSONObject returnObj = new JSONObject();
		try {
			inputObj.put("num1", num1);
			inputObj.put("num2", num2);
			resultObj.put("operation", "multiply");
			resultObj.put("value", result);
			returnObj.put("input", inputObj);
			returnObj.put("result", resultObj);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnObj;
	}
}
