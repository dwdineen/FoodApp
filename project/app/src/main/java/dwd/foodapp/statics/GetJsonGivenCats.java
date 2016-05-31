package dwd.foodapp.statics;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class GetJsonGivenCats extends AsyncTask<String, String, String> {


	@Override
	protected String doInBackground(String... params) {

		String result = "";

		URL url = null;
		try {
			url = new URL("http://192.168.1.121:80");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		//ArrayList<NameValuePair> param = new ArrayList<NameValuePair>();

		try {
			// Set up HTTP post

			// HttpClient is more then less deprecated. Need to change to URLConnection
			URLConnection urlConnection = url.openConnection();
			InputStream in = new BufferedInputStream(urlConnection.getInputStream());

			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			StringBuffer buffer = new StringBuffer();

			String line;
			while ((line = reader.readLine()) != null){
				buffer.append(line);
			}

			result = buffer.toString();


		} catch (Exception e) {
			e.printStackTrace();
		}

		return  result;
	}

	@Override
	protected void onPostExecute(String s) {

		System.out.println(s);

	}
}
// protected Void doInBackground(String... params)
