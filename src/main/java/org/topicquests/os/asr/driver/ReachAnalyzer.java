/**
 * 
 */
package org.topicquests.os.asr.driver;

import org.topicquests.support.api.IResult;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;

/**
 * @author jackpark
 *
 */
public class ReachAnalyzer {
	private InDriverEnvironment environment;

	/**
	 * 
	 */
	public ReachAnalyzer(InDriverEnvironment env) {
		environment = env;
	}

	public void analyzeEidos(IResult r) {
		String json = (String)r.getResultObject();
		JSONObject raw = null;
		try {
			JSONParser p = new JSONParser(JSONParser.MODE_JSON_SIMPLE);
			raw = (JSONObject)p.parse(json);
		} catch (Exception e) {
			e.printStackTrace();
			environment.logError(e.getLocalizedMessage(), e);
			r.addErrorString(e.getMessage());
			return;
		}
		//TODO
	}
}
