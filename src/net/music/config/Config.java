package net.music.config;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import net.music.util.PlayStatus;

public class Config {
	JSONObject config;
	JSONObject DefaultValue;
	public Config() {
		config = new JSONObject();
		 DefaultValue=new JSONObject();
		 Init();
		
	}

	private void Init() {
		double[] ydv={0D,0D,0D,0D,0D,0D,0D,0D,0D,0D};
		DefaultValue.put("ydv", ydv);
		DefaultValue.put("playstatus", PlayStatus.DEFAULT);
		DefaultValue.put("volume_value", 0.3);
		DefaultValue.put("YDMax", 10);
		DefaultValue.put("YDMin", -10);
	}
	
	public void Save(String path) throws IOException {
		File configfile = new File(path);
		if (!configfile.exists())
			configfile.createNewFile();
		FileOutputStream fi = new FileOutputStream(configfile);
		OutputStreamWriter osw = new OutputStreamWriter(fi, "utf-8");
		BufferedWriter bw = new BufferedWriter(osw);
		bw.write(this.config.toString() + "\n");
		bw.flush();
		bw.close();
	}

	@SuppressWarnings("static-access")
	public static Config Load(String path) throws IOException {
		File configfile = new File(path);
		if (!configfile.exists())
			throw new FileNotFoundException("路径错误:" + configfile.getPath() + "不存在");
		FileInputStream fi = new FileInputStream(configfile);
		InputStreamReader isr = new InputStreamReader(fi, "utf-8");
		BufferedReader br = new BufferedReader(isr);
		String o = "";
		while (true) {
			String tmpre = br.readLine();
			if (tmpre==null) {
				break;
			} else {
				o = o + tmpre;
			}

		}
		br.close();
		Config cfg = new Config();
		cfg.setConfig(new JSONObject().parseObject(o));
		return cfg;
	}

	public JSONObject getConfig() {
		return config;
	}

	private void setConfig(JSONObject config) {
		this.config = config;
	}
	
	public void put(String key,Object value) {
		config.put(key, value);
	}

	public void clear() {
		config.clear();
	}

	public Object clone() {
		return config.clone();
	}

	public void forEach(BiConsumer<? super String, ? super Object> action) {
		config.forEach(action);
	}

	public Object get(Object key) {
		return config.get(key);
	}

	public BigDecimal getBigDecimal(String key) {
		return config.getBigDecimal(key);
	}

	public BigInteger getBigInteger(String key) {
		return config.getBigInteger(key);
	}

	public Boolean getBoolean(String key) {
		return config.getBoolean(key);
	}

	public boolean getBooleanValue(String key) {
		return config.getBooleanValue(key);
	}

	public Byte getByte(String key) {
		return config.getByte(key);
	}

	public byte getByteValue(String key) {
		return config.getByteValue(key);
	}

	public byte[] getBytes(String key) {
		return config.getBytes(key);
	}

	public Date getDate(String key) {
		return config.getDate(key);
	}

	public Double getDouble(String key) {
		return config.getDouble(key);
	}

	public double getDoubleValue(String key) {
		return config.getDoubleValue(key);
	}

	public Float getFloat(String key) {
		return config.getFloat(key);
	}

	public float getFloatValue(String key) {
		return config.getFloatValue(key);
	}

	public Map<String, Object> getInnerMap() {
		return config.getInnerMap();
	}

	public int getIntValue(String key) {
		return config.getIntValue(key);
	}

	public Integer getInteger(String key) {
		return config.getInteger(key);
	}

	public JSONArray getJSONArray(String key) {
		return config.getJSONArray(key);
	}

	public JSONObject getJSONObject(String key) {
		return config.getJSONObject(key);
	}

	public Long getLong(String key) {
		return config.getLong(key);
	}

	public long getLongValue(String key) {
		return config.getLongValue(key);
	}

	public <T> T getObject(String key, Class<T> clazz) {
		return config.getObject(key, clazz);
	}

	public <T> T getObject(String key, Type type) {
		return config.getObject(key, type);
	}

	public <T> T getObject(String key, @SuppressWarnings("rawtypes") TypeReference typeReference) {
		return config.getObject(key, typeReference);
	}

	public Object getOrDefault(Object key, Object defaultValue) {
		return config.getOrDefault(key, defaultValue);
	}

	public Short getShort(String key) {
		return config.getShort(key);
	}

	public short getShortValue(String key) {
		return config.getShortValue(key);
	}

	public java.sql.Date getSqlDate(String key) {
		return config.getSqlDate(key);
	}

	public String getString(String key) {
		return config.getString(key);
	}

	public Timestamp getTimestamp(String key) {
		return config.getTimestamp(key);
	}

	public boolean isEmpty() {
		return config.isEmpty();
	}

	public Set<String> keySet() {
		return config.keySet();
	}

	public void putAll(Map<? extends String, ? extends Object> m) {
		config.putAll(m);
	}

	public boolean remove(Object key, Object value) {
		return config.remove(key, value);
	}

	public Object remove(Object key) {
		return config.remove(key);
	}

	public boolean replace(String key, Object oldValue, Object newValue) {
		return config.replace(key, oldValue, newValue);
	}

	public Object replace(String key, Object value) {
		return config.replace(key, value);
	}

	public void replaceAll(BiFunction<? super String, ? super Object, ? extends Object> function) {
		config.replaceAll(function);
	}

	public String toJSONString() {
		return config.toJSONString();
	}

	
}
