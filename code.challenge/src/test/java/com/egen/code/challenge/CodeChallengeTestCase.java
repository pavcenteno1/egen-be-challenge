package com.egen.code.challenge;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;

import com.egen.code.challenge.entity.Alert;
import com.egen.code.challenge.entity.AlertType;
import com.egen.code.challenge.entity.Metric;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class CodeChallengeTestCase {

	@Test
	public void testCreateMetric() {

		try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {

			List<Metric> beforeCreating = readMetric(httpClient);
			createMetric(httpClient, "1458062848734", "153");
			List<Metric> afterCreating = readMetric(httpClient);
			assertTrue(
					afterCreating.size() > beforeCreating.size() && afterCreating.size() - beforeCreating.size() == 1);

		} catch (IOException ex) {

			ex.printStackTrace();
		}

	}

	@Test
	public void testReadMetric() {

		try {

			CloseableHttpClient httpClient = HttpClientBuilder.create().build();
			createMetric(httpClient, "1458062848735", "153");
			List<Metric> readCreating = readMetric(httpClient);
			assertTrue(!readCreating.isEmpty());
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	@Test
	public void testReadMetricWithRange() {
		try {

			CloseableHttpClient httpClient = HttpClientBuilder.create().build();
			createMetric(httpClient, "1458062848740", "153");
			createMetric(httpClient, "1458062848745", "153");
			List<Metric> readCreating = readMetricTimeRange(httpClient,"1458062848740","1458062848741");
			assertTrue(readCreating.size() == 1);
			readCreating = readMetricTimeRange(httpClient,"1458062848739","1458062848746");
			assertTrue(readCreating.size() == 2);
		} catch (IOException e) {

			e.printStackTrace();
		}

	}
	
	@Test
	public void testAlerts() {
		try {

			CloseableHttpClient httpClient = HttpClientBuilder.create().build();
			createMetric(httpClient, "1458062848760", "153");	
			createMetric(httpClient, "1458062848761", "170");
			createMetric(httpClient, "1458062848762", "130");
			List<Alert>readAlert = readAlertTimeRange(httpClient,"1458062848761","1458062848762");
			assertTrue(readAlert.size() == 2);
			
		} catch (IOException e) {

			e.printStackTrace();
		}

	}
	
	@Test
	public void testWeightRules() {
		try {

			CloseableHttpClient httpClient = HttpClientBuilder.create().build();
			createMetric(httpClient, "1458062848750", "153");	
			createMetric(httpClient, "1458062848751", "170");			
			List<Alert> readAlert = readAlert(httpClient);
			
			assertTrue(readAlert.size() == 3);
			assertTrue( readAlert.get(0).getType().equals(AlertType.Over_Weight));
			
			createMetric(httpClient, "1458062848752", "130");
			readAlert = readAlert(httpClient);
			assertTrue(readAlert.size() == 4);
			assertTrue( readAlert.get(1).getType().equals(AlertType.Under_Weight));
		} catch (IOException e) {

			e.printStackTrace();
		}

	}
	
	
	
	

	private List<Metric> readMetric(CloseableHttpClient httpClient) throws IOException, ClientProtocolException {
		HttpResponse result;
		HttpGet get = new HttpGet("http://127.0.0.1:8080/read");
		get.setHeader("Content-type", "application/json");
		result = httpClient.execute(get);

		BufferedReader br = new BufferedReader(new InputStreamReader((result.getEntity().getContent())));
		StringBuffer buffer = new StringBuffer();

		String output;
		System.out.println("Output from Server .... \n");
		while ((output = br.readLine()) != null) {
			buffer.append(output);
		}

		Gson gson = new Gson();
		Type type = new TypeToken<List<Metric>>() {
		}.getType();
		List<Metric> fromJson = gson.fromJson(buffer.toString(), type);

		return fromJson;
	}

	private void createMetric(CloseableHttpClient httpClient, String timeStamp, String value)
			throws UnsupportedEncodingException, IOException, ClientProtocolException {
		HttpPost request = new HttpPost("http://127.0.0.1:8080/create");
		StringEntity params = new StringEntity("{\"timeStamp\": \"" + timeStamp + "\",\"value\": \"" + value + "\"}");
		request.setEntity(params);
		request.setHeader("Accept", "application/json");
		request.setHeader("Content-type", "application/json");

		request.setEntity(params);
		HttpResponse result = httpClient.execute(request);
	}

	private List<Metric> readMetricTimeRange(CloseableHttpClient httpClient, String initial, String _final)
			throws IOException, ClientProtocolException {
		HttpResponse result;
		HttpGet get = new HttpGet("http://127.0.0.1:8080/readByTimeRange?initial=" + initial + "&final=" + _final);
		get.setHeader("Content-type", "application/json");
		result = httpClient.execute(get);

		BufferedReader br = new BufferedReader(new InputStreamReader((result.getEntity().getContent())));
		StringBuffer buffer = new StringBuffer();

		String output;
		System.out.println("Output from Server .... \n");
		while ((output = br.readLine()) != null) {
			buffer.append(output);
		}

		Gson gson = new Gson();
		Type type = new TypeToken<List<Metric>>() {
		}.getType();
		List<Metric> fromJson = gson.fromJson(buffer.toString(), type);

		return fromJson;
	}

	private List<Alert> readAlert(CloseableHttpClient httpClient) throws IOException, ClientProtocolException {
		HttpResponse result;
		HttpGet get = new HttpGet("http://127.0.0.1:8080/alert/read");
		get.setHeader("Content-type", "application/json");
		result = httpClient.execute(get);

		BufferedReader br = new BufferedReader(new InputStreamReader((result.getEntity().getContent())));
		StringBuffer buffer = new StringBuffer();

		String output;
		System.out.println("Output from Server .... \n");
		while ((output = br.readLine()) != null) {
			buffer.append(output);
		}

		Gson gson = new Gson();
		Type type = new TypeToken<List<Alert>>() {
		}.getType();
		List<Alert> fromJson = gson.fromJson(buffer.toString(), type);

		return fromJson;
	}
	
	private List<Alert> readAlertTimeRange(CloseableHttpClient httpClient, String initial, String _final)
			throws IOException, ClientProtocolException {
		HttpResponse result;
		HttpGet get = new HttpGet("http://127.0.0.1:8080/alert/readByTimeRange?initial=" + initial + "&final=" + _final);
		get.setHeader("Content-type", "application/json");
		result = httpClient.execute(get);

		BufferedReader br = new BufferedReader(new InputStreamReader((result.getEntity().getContent())));
		StringBuffer buffer = new StringBuffer();

		String output;
		System.out.println("Output from Server .... \n");
		while ((output = br.readLine()) != null) {
			buffer.append(output);
		}

		Gson gson = new Gson();
		Type type = new TypeToken<List<Alert>>() {
		}.getType();
		List<Alert> fromJson = gson.fromJson(buffer.toString(), type);

		return fromJson;
	}
	
	
}
