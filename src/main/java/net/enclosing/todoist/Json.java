package net.enclosing.todoist;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.arnx.jsonic.JSON;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Json {
	public static final String TOKEN = "3717c1f33d70b5d05e6212afe0c404d7b39f9edb";
	public static final String TOKENPARAM = "?token=" + TOKEN;
	public static String postItem(String apimethod,Map<String, String> params) throws Exception {
		params = params==null?new HashMap<String, String>():params;

//		final Gson gson = new Gson();
		String paramString = Json.toParamString(params);
		if(paramString.length()>200){
			Map<String,String> tempmap = new HashMap<String, String>();
			tempmap.put("id", params.get("id"));
			tempmap.put("content", params.get("content"));
			paramString = Json.toParamString(tempmap);
			post(apimethod, paramString);
			tempmap = new HashMap<String, String>();
			tempmap.put("id", params.get("id"));
			tempmap.put("priority", params.get("priority"));
			paramString = Json.toParamString(tempmap);
			post(apimethod, paramString);
			tempmap = new HashMap<String, String>();
			tempmap.put("id", params.get("id"));
			tempmap.put("labels", params.get("labels"));
			paramString = Json.toParamString(tempmap);
			return post(apimethod, paramString);
		}else{
			return post(apimethod, paramString);
		}
	}
	public static void updateNote(String content,String note_id)throws Exception{
		Map<String,String> map = new HashMap<String, String>();
		map.put("content", URLEncoder.encode(content, "UTF-8"));
		map.put("note_id", note_id);
		post("updateNote", toParamString(map));
	}

	public static void addNote(String content,String item_id)throws Exception{
		Map<String,String> map = new HashMap<String, String>();
		map.put("content", content);
		map.put("item_id", item_id);
		post("addNote", toParamString(map));
	}

	private static String post(String apimethod, String paramString)
			throws Exception {
		final String apiuri = apimethod + Json.TOKENPARAM + paramString ;
		final String urlString = Todoist.APIURL + apiuri;
		System.err.println(urlString);
		return Json.readUrl(urlString);
	}

	public static String toParamString(Map<String, String> params) {
		if(params ==null ) return "";
		String sep = "&";
		StringBuilder builder = new StringBuilder();
		for (Map.Entry<String, String> entry : params.entrySet()) {
			System.out.println("Key : " + entry.getKey() + " Value : "
				+ entry.getValue());
			builder.append(sep);
			builder.append(entry.getKey()+"="+entry.getValue());
		}
		return builder.toString();
	}

    public static String readUrl(String urlString) throws Exception {
        BufferedReader reader = null;

        try{
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader (url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[]chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read); 

            return buffer.toString();
        } finally {
            if (reader != null)
                reader.close();
        }

    }
    

	public static List<Label> getLabels(String apimethod,Map<String, String> params)  {
		try {

			apimethod = apimethod==null?"getLabels":apimethod;
			params = params==null?new HashMap<String, String>():params;
			
			String paramString = Json.toParamString(params);
			
			final String apiuri = apimethod + Json.TOKENPARAM + paramString ;//"&project_id="; this is for inbox
			final String urlString = Todoist.APIURL + apiuri;
			String jsonString = Json.readUrl(urlString);
			System.err.println(jsonString);
			List<Label> labels =  new ArrayList<Label>();
			
			LinkedHashMap list = (LinkedHashMap)JSON.decode(jsonString);
			for (Object key : list.keySet()) {
			    Map labelmap = (Map)list.get(key);
				Label label = new Label((String)labelmap.get("name"),((BigDecimal)labelmap.get("id")).intValue());
				labels.add(label);
			}
			return labels;
		} catch (Exception e) {
			e.printStackTrace();
		}
//		Utils.printLabels(labels);
		//@TODO 
		return null;
	}
	

	public static String getLabelsString(List<Integer> labels) {
		if(labels.size()==0) return "[]";
		 StringBuilder builder = new StringBuilder();
		 builder.append("[");
		 for (Integer integer : labels) {
			 builder.append(integer);
			 builder.append(",");
		}
		 builder.deleteCharAt(builder.length()-1);
		 
		 builder.append("]");
		 System.err.println(builder.toString());
		 return builder.toString();
	}
	
	public static List<Project> getProjects(String apimethod) {
		Type collectionType = new TypeToken<Collection<Project>>(){}.getType();
		return (List<Project>)Json.getObjects(apimethod, null, collectionType);
	}
	public static List<Item> getItems(String apimethod,Map<String, String> params) {
		Type collectionType = new TypeToken<Collection<Item>>(){}.getType();
		return  (List<Item>)Json.getObjects(apimethod, params, collectionType);
	}

	public static List getObjects(String apimethod, Map<String, String> params,Type collectionType) {
		try {
			Gson gson = new Gson();
			String paramString = Json.toParamString(params);

			final String apiuri = apimethod + Json.TOKENPARAM + paramString ;//"&project_id="; this is for inbox
			final String urlString = Todoist.APIURL + apiuri;
			String jsonString = Json.readUrl(urlString);
			System.err.println(jsonString);
			return  gson.fromJson(jsonString, collectionType);

		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}	
	public static Object getObject(String apimethod, Map<String, String> params,Type collectionType) {
		try {
			Gson gson = new Gson();
			String paramString = Json.toParamString(params);

			final String apiuri = apimethod + Json.TOKENPARAM + paramString ;//"&project_id="; this is for inbox
			final String urlString = Todoist.APIURL + apiuri;
			String jsonString = Json.readUrl(urlString);
			System.err.println(jsonString);
			return  gson.fromJson(jsonString, collectionType);

		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	public static Label getLabel(String name) {
		List<Label> labels = Label.LABELS;
		for (Label label : labels) {
			if(label.getName().equalsIgnoreCase(name))
				return label;
		}
		return null;
	}

	public static void addLabel(String name) {
		try {
			final String apiuri = "addLabel" + Json.TOKENPARAM + "&name=" + name;
			final String urlString = Todoist.APIURL + apiuri;
			URL url = new URL(urlString);
			System.err.println(urlString);
			url.getContent();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
