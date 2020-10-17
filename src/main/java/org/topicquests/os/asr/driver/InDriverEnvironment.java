/**
 * 
 */
package org.topicquests.os.asr.driver;

import org.topicquests.os.asr.pd.api.ISentenceParser;
import org.topicquests.support.RootEnvironment;
import org.topicquests.support.api.IResult;

import net.minidev.json.JSONObject;

/**
 * @author jackpark
 *
 */
public class InDriverEnvironment extends RootEnvironment implements ISentenceParser {
	private final String
		REACH_HOST = super.getStringProperty("ReachHost"),
		REACH_PORT = super.getStringProperty("ReachPort"),
		REACH_SERVICE;
	private ReachClient http;
	private ReachAnalyzer reach;
	/**
	 */
	public InDriverEnvironment() {
		super("ind-props.xml", "logger.properties");
		http = new ReachClient(this);
		REACH_SERVICE = "http://"+REACH_HOST+":"+Integer.parseInt(REACH_PORT)+"/";
		reach = new ReachAnalyzer(this);
	}

	@Override
	public IResult processSentence(String sentence) {
		JSONObject query = new JSONObject();
		query.put("text", sentence);
		IResult r =  http.put(REACH_SERVICE, query.toJSONString());
		reach.analyzeEidos(r);
		return r;
	}

	@Override
	public void shutDown() {
		// TODO Auto-generated method stub
	}

}
